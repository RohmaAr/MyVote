package Createables;

import java.time.LocalDateTime;

public class Duration {
	 private LocalDateTime startTime;
	 private LocalDateTime endTime;
	 public Duration()
	 {}
	 public LocalDateTime getStart()
	 {
		 return startTime;
	 }
	 public LocalDateTime getEnd()
	 {
		 return endTime;
	 }
	 public void setStartTime(LocalDateTime startTime)
	 {
		 
		 if(validTime(startTime))
		 this.startTime=startTime;
		//else mai invalidTime ka notif jana hai
	 }
	 public void setEndTime(LocalDateTime endTime)
	 {
		 if(validTime(endTime))
		 this.endTime=endTime;
		 //else mai invalidTime ka notif jana hai
	 }
	 public boolean hasStarted()
	 {
		 int n=LocalDateTime.now().compareTo(startTime);
		 if(n<0)
		 {
			 return false;
		 }
		 else	 
		   return true;	 
	 }
	 public boolean hasEndeed()
	 {
		 int n=LocalDateTime.now().compareTo(endTime);
		 if(n<0)
		 {
			 return false;
		 }
		 else	 
		   return true;	 
	 }
	 private boolean validTime(LocalDateTime l)
	 {
		 int n=l.compareTo(LocalDateTime.now());
		if (n<0)
		return false;
		else 
			return true;
	 }
}
