package Projtrain;
 enum Status{
	 BOOKED,
	 WL
 }
 
 enum Berthtype{
	 UPPER,LOWER
 }
 
 public class Passenger {
 private int Id;
 private String Name;
 private  Berthtype PrefBerth;
 private Berthtype AllocatedBerth;
 private  Status status;
 private int BkdWl;
 private int CurrentWl; 
 
  
public Passenger(int Id,String Name, Berthtype PrefBerth) {
	   this.Id = Id;
	   this.Name = Name;
	   this.PrefBerth = PrefBerth;
 }
     public int getId() {
	     return Id;
       }
      public void setId(int Id) {
	       Id = Id;
        }
     public String getName() {
	      return Name;
        }
     public void setName(String name) {
	       Name = name;
         }
     public Berthtype getPrefBerth() {
	     return PrefBerth;
         }
       public void setPrefBerth(Berthtype prefBerth) {
	        PrefBerth = prefBerth;
          } 
       public Berthtype getAllocatedBerth() {
	     return AllocatedBerth;
          }
         public void setAllocatedBerth(Berthtype AllocatedBerth) {
	   this.AllocatedBerth = AllocatedBerth;
            }
         public Status getStatus() {
	         return status;
            }
            public void setStatus(Status status) {
	         this.status = status;
             }
            public int getBkdWl() {
            	return BkdWl;
            }
            public void setBkdWl(int bkdWl) {
            	BkdWl = bkdWl;
            }
            public int getCurrentWl() {
            	return CurrentWl;
            }
            public void setCurrentWl(int currentWl) {
            	CurrentWl = currentWl;
            }
 
}
