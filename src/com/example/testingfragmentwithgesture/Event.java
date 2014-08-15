package com.example.testingfragmentwithgesture;



public class Event {

	 
	public int EVENTDATE;
	public int EVENTMONTH ;
	 public String EVENTTITLE ;
	 public String EVENTCONTENT ;
	 public	String EVENTTEXT ;
	 public String EVENTSTATUS ;
	 public int ID ;
	 public int EVENTTYPE;
  
	 public Event(){}
	 public Event(String title,String content,String tickertext,int month,int date,int eventtype){
		 EVENTDATE=date; 
		 EVENTMONTH=month;
		 EVENTTITLE=title;
		 EVENTCONTENT=content;
		 EVENTTEXT=tickertext; 
		 EVENTSTATUS=String.valueOf(0);
		 EVENTTYPE=eventtype;
		
	 }
	 public Event(int id,String title,String content,String tickertext,int month,int date){
		 EVENTDATE=date; 
		 EVENTMONTH=month;
		 EVENTTITLE=title;
		 EVENTCONTENT=content;
		 EVENTTEXT=tickertext; 
		 EVENTSTATUS=String.valueOf(0);
		 ID=id;
		
	 }
	 
	 
     public int getID( ){
    	 return this.ID;
     }
     public int getdate( ){
    	 return this.EVENTDATE;
     }
 
     public int getmonth( ){
    	 return this.EVENTMONTH;
     }	 
 
}
