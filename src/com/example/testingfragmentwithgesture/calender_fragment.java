package com.example.testingfragmentwithgesture;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;



@SuppressLint("NewApi")
public class calender_fragment extends Fragment implements OnGesturePerformedListener
{
Intent service_intent;
final int Sagrant=1, Masya=2 , PuranMashi=3 ,Gurpurab=4,Historical_days=5,Other_Events=6; 
private Button selectedDayMonthYearButton;
private Button currentMonth;
private ImageView prevMonth;
private ImageView nextMonth;
private GridView calendarView;
private GridCellAdapter adapter;
private Calendar _calendar;
private int month, year;
private static final String dateTemplate = "MMMM yyyy";
String flag ="abc";
String date_month_year;
private static final String tag = "SimpleCalendarViewActivity";
private GestureLibrary mLibrary;
private Context appcontext;  

 public calender_fragment( Context context){
		appcontext=context;
 }
    @Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
    	
    	  View  view = inflater.inflate(R.layout.activity_main, container, false);
    
    	  GestureOverlayView overlay = (GestureOverlayView)view.findViewById(R.id.overlay);     
          overlay.addOnGesturePerformedListener(this);
 
          mLibrary = GestureLibraries.fromRawResource(appcontext, R.raw.gestures);
           
       
        _calendar = Calendar.getInstance(Locale.getDefault());
        month = _calendar.get(Calendar.MONTH) + 1;
        year = _calendar.get(Calendar.YEAR);
       
        currentMonth = (Button) view.findViewById(R.id.currentMonth);
        currentMonth.setText(DateFormat.format(dateTemplate, _calendar.getTime()));

        calendarView = (GridView) view.findViewById(R.id.calendar);
        DataBase_Handler db1 = new DataBase_Handler(appcontext);
 	   if( db1.check_database())
 	   {
 		  db1.add_event(new Event("Jyoti Jot Diwas", "Guru AngadDev Ji shaheedi Purab", "martyr Gursikhi Event", 7, 12,Masya));
 		  db1.add_event(new Event("Prakash Utsav", "Guru Ramdas Ji Birthday ", " Gursikhi Event", 7, 12,Gurpurab));
          db1.add_event(new Event("Prakash Utsav", "Guru Ramdas Sahib Ji Birthday", " Gursikhi Event", 7, 12,PuranMashi));
 	      db1.add_event(new Event("Prakash Utsav", "Guru HarKrishn Sahib Ji Birthday", " Gursikhi Event", 7, 23,Gurpurab));
 	     db1.add_event(new Event("Jyoti Jot Diwas", "Guru AngadDev Ji shaheedi Purab", "martyr Gursikhi Event", 8, 18,Gurpurab));
		  db1.add_event(new Event("Prakash Utsav", "Guru Ramdas Ji Birthday ", " Gursikhi Event", 8, 20,Sagrant));
         db1.add_event(new Event("Prakash Utsav", "Guru Ramdas Sahib Ji Birthday", " Gursikhi Event", 8, 24,Gurpurab));
	      db1.add_event(new Event("Prakash Utsav", "Guru HarKrishn Sahib Ji Birthday", " Gursikhi Event", 8, 6,Gurpurab));
 	   }

 	  service_intent=new Intent(appcontext,Start_Service.class);		
		 
		appcontext.startService(service_intent);
       
        adapter = new GridCellAdapter(appcontext,  month, year);
        adapter.notifyDataSetChanged();
       
        calendarView.setAdapter(adapter);
        
       
       
       
	// TODO Auto-generated method stub
	return view;
}

   
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            

           
           
    }

    
    private void setGridCellAdapterToDate(int month, int year){
            adapter = new GridCellAdapter(appcontext, month, year);
            _calendar.set(year, month - 1, _calendar.get(Calendar.DAY_OF_MONTH));
            currentMonth.setText(DateFormat.format(dateTemplate, _calendar.getTime()));
            adapter.notifyDataSetChanged();
            calendarView.setAdapter(adapter);
        }

    
  
    	
	@Override
	 public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
		Log.i("Entered","OnGesturePerformed");
        ArrayList<Prediction> predictions = mLibrary.recognize(gesture);
        for (Prediction prediction : predictions) {
        	Log.i("entered","ongestureperformed");
            if (prediction.name.equals("next")) {

                if (month > 11) {
                    month = 1;
                    year++;
                } else {
                    month++;
                }
                Log.d(tag, "Setting Next Month in GridCellAdapter: "
                        + "Month: " + month + " Year: " + year);
                setGridCellAdapterToDate(month, year);
                return;
            } else {
                if (prediction.name.equals("previous")) {

                    if (month <= 1) {
                        month = 12;
                        year--;
                    } else {
                        month--;
                    }
                    Log.d(tag, "Setting Prev Month in GridCellAdapter: "
                            + "Month: " + month + " Year: " + year);
                    setGridCellAdapterToDate(month, year);
                    return;
                }
            }

        }

    }
    



}
