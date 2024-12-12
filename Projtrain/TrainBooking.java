package Projtrain;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TrainBooking {
   static int AvailTickets = 3;
   static int UpperAvailTickets = 2;
   static int LowerAvailTickets = 1;
   static int WlLimitTickets = 3;
   static int pId = 1;
   static List<Passenger> BkdDetails = new ArrayList<>();
   static List<Passenger> WlDetails = new ArrayList<>();

   //////////////////////////////////////////
   static void AllocatedBerth(Passenger p) {
       switch (p.getPrefBerth()) {
           case UPPER:
               if (UpperAvailTickets > 0) {
                   p.setAllocatedBerth(Berthtype.UPPER);
                   UpperAvailTickets--;
               } else {
                   p.setAllocatedBerth(Berthtype.LOWER);
                   LowerAvailTickets--;
               }
               break;
           case LOWER:
               if (LowerAvailTickets > 0) {
                   p.setAllocatedBerth(Berthtype.LOWER);
                   LowerAvailTickets--;
               } else {
                   p.setAllocatedBerth(Berthtype.UPPER);
                   UpperAvailTickets--;
               }
               break;
       }
   }

   //////////////////////////////////////////
   
   static void BookTickets(Passenger p) {
       if (AvailTickets > 0) {
           AllocatedBerth(p);
           AvailTickets--;
           BkdDetails.add(p);
           p.setStatus(Status.BOOKED);
           System.out.println("You're Booked Successfully with pId: " + p.getId());
       } else if (WlLimitTickets > 0) {
           WlDetails.add(p);
           p.setStatus(Status.WL);
           p.setBkdWl(WlDetails.size());
           WlLimitTickets--;
           System.out.println("You're Currently in Waiting List (WL)"+p.getId());
       } else {
           System.out.println("Regret, no tickets available");
       }
   }

   /////////////////////////////////////
   
   static void CancelTickets(int Id) {
       List<Passenger> CancellingPerson = BkdDetails.stream()
               .filter(p -> p.getId() == Id)
               .collect(Collectors.toList());

       if (!CancellingPerson.isEmpty()) {
           if (WlDetails.size() > 0) {
               Passenger WlPerson = WlDetails.get(0);
               WlPerson.setAllocatedBerth(CancellingPerson.get(0).getAllocatedBerth());
               BkdDetails.add(WlPerson);
               WlDetails.remove(0);
               WlLimitTickets++;
           } else {
               AvailTickets++;
           }
           BkdDetails.removeIf(e -> e.getId() == Id);
           System.out.println("Ticket cancelled successfully.");
       } else {
           System.out.println("Id is not in booking details.");
       }
   }

   /////////////////////////////////////
   static void ViewTicket(int id) {
       System.out.println("Booked Tickets:");
       BkdDetails.stream()
               .filter(p -> p.getId() == id)
               .forEach(p -> {
                   System.out.println("Id: " + p.getId());
                   System.out.println("Name: " + p.getName());
                   System.out.println("Preferred Berth: " + p.getPrefBerth());
                   System.out.println("Allocated Berth: " + p.getAllocatedBerth());
                   System.out.println("Status: " + p.getStatus());
               });

       if (BkdDetails.stream().noneMatch(p -> p.getId() == id)) {
           System.out.println("No ticket found for the given id.");
       }
   }

   static void ViewId(int WlId) {
       List<Passenger> isAvail = WlDetails.stream()
               .filter(e -> e.getId() == WlId)
               .collect(Collectors.toList());

       if (isAvail.isEmpty()) {
           System.out.println("You're not on the Waiting List (WL).");
       } else {
           System.out.println("Booked WL Status:");
           System.out.println("Current WL: " + WlDetails.size());
       }
   }

   /////////////////////////////////////
   public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       String choice = "Y";
       System.out.println("Total Available Tickets: " + AvailTickets);

       while (choice.equalsIgnoreCase("Y")) {
           System.out.println("Enter 1.Book a Ticket\t 2.View Ticket\t 3.Cancel Ticket\t 4.View WL Status");
           int prefchoice = sc.nextInt();

           switch (prefchoice) {
               case 1:
                   System.out.println("Please Enter a Name:");
                   String name = sc.next();
                   System.out.println("Enter Your Preferred Berth (UPPER/LOWER):");
                   String prefBerth = sc.next().toUpperCase(); // Convert to uppercase to match enum
             
                   try {
                       Berthtype berthtype = Berthtype.valueOf(prefBerth); // Convert String to Enum
                       BookTickets(new Passenger(pId, name, berthtype));
                       pId++;
                   } catch (IllegalArgumentException e) {
                       System.out.println("Invalid Berth Preference");
                   }
                   break;
               case 2:
                   System.out.println("Please Enter an Id:");
                   int viewId = sc.nextInt();
                   ViewTicket(viewId);
                   break;
               case 3:
                   System.out.println("Please Enter the Cancel Id:");
                   int cancelId = sc.nextInt();
                   CancelTickets(cancelId);
                   break;
               case 4:
                   System.out.println("Please Enter the Id:");
                   int wlId = sc.nextInt();
                   ViewId(wlId);
                   break;
               default:
                   System.out.println("Invalid Choice.");
           }

           System.out.println("Do you want to continue? (Y/N)");
           choice = sc.next();
       }

       sc.close();
   }
}