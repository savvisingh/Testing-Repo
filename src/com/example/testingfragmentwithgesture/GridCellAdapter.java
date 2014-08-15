package com.example.testingfragmentwithgesture;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

 public class GridCellAdapter extends BaseAdapter implements OnClickListener
            {
                private final Context _context;

                private final List<String> list;
                private static final int DAY_OFFSET = 1;
                private final String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
                private final int[] daysOfMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
                private int daysInMonth;
                private int currentDayOfMonth;
                private int currentWeekDay;
                private Button gridcell;
                private TextView num_events_per_day;
                private ImageView img1,img2,img3;
               

                // Days in Current Month
                public GridCellAdapter(Context context, int month, int year){
                        super();
                        this._context = context;
                        this.list = new ArrayList<String>();
                        Calendar calendar = Calendar.getInstance();
                        setCurrentDayOfMonth(calendar.get(Calendar.DAY_OF_MONTH));
                        setCurrentWeekDay(calendar.get(Calendar.DAY_OF_WEEK));

                        // Print Month
                        printMonth(month, year);

                        // Find Number of Events
                        
                    }
                private int getMonthIndex (String month){
                	for (int i=0;i<12;i++)
                	{
                		if (month==months[i])
                			return i+1;
                	}
                	 return 0;
                }
                private String getMonthAsString(int i){
                        return months[i];
                     
                    }

                private int getNumberOfDaysOfMonth(int i){
                        return daysOfMonth[i];
                    }

                public String getItem(int position){
                        return list.get(position);
                    }

                @Override
                public int getCount(){
                        return list.size();
                    }

                private void printMonth(int mm, int yy){
                        int trailingSpaces = 0;
                        int daysInPrevMonth = 0;
                        int prevMonth = 0;
                        int prevYear = 0;
                        int nextMonth = 0;
                        int nextYear = 0;
                        if (yy%4==0){
                            daysOfMonth[1]=29;
                        }
                        int currentMonth = mm - 1;
                        daysInMonth = getNumberOfDaysOfMonth(currentMonth);


                        // Gregorian Calendar : MINUS 1, set to FIRST OF MONTH
                        GregorianCalendar cal = new GregorianCalendar(yy, currentMonth, 1);
                        

                        if (currentMonth == 11){
                                prevMonth = currentMonth - 1;
                                daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth);
                                nextMonth = 0;
                                prevYear = yy;
                                nextYear = yy + 1;
                            }
                        else if (currentMonth == 0){
                                prevMonth = 11;
                                prevYear = yy - 1;
                                nextYear = yy;
                                daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth);
                                nextMonth = 1;
                            }
                        else{
                                prevMonth = currentMonth - 1;
                                nextMonth = currentMonth + 1;
                                nextYear = yy;
                                prevYear = yy;
                                daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth);
                            }

                        int currentWeekDay = cal.get(Calendar.DAY_OF_WEEK) - 1;
                        trailingSpaces = currentWeekDay;
                       
                       
                        // Trailing Month days
                        for (int i = 0; i < trailingSpaces; i++){
                                list.add(String.valueOf((daysInPrevMonth - trailingSpaces + DAY_OFFSET) + i)
                                		+ "-GREY" + "-" +String.valueOf(prevMonth) + "-" + prevYear);
                            }

                        // Current Month Days
                        for (int i = 1; i <= daysInMonth; i++){
                                if (i == getCurrentDayOfMonth())
                                        list.add(String.valueOf(i) + "-BLUE" + "-" + String.valueOf(currentMonth) + "-" + yy);
                                else
                                        list.add(String.valueOf(i) + "-WHITE" + "-" + String.valueOf(currentMonth) + "-" + yy);
                            }

                        // Leading Month days
                        for (int i = 0; i < list.size() % 7; i++){
                                list.add(String.valueOf(i + 1) + "-GREY" + "-" + String.valueOf(nextMonth) + "-" + nextYear);
                            }
                    }

               

                @Override
                public long getItemId(int position){
                        return position;
                    }

                @Override
                public View getView(int position, View convertView, ViewGroup parent){
                        View row = convertView;
                        if (row == null){
                           LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                row = inflater.inflate(R.layout.callender_grid_cell, parent, false);
                            }

                        // Get a reference to the Day gridcell
                        gridcell = (Button) row.findViewById(R.id.calendar_day_gridcell);
                        gridcell.setOnClickListener(this);
                        img1=(ImageView)row.findViewById(R.id.imageView1);
                        img2=(ImageView)row.findViewById(R.id.imageView2);
                        img3=(ImageView)row.findViewById(R.id.imageView3);

                        // ACCOUNT FOR SPACING

                        String[] day_color = list.get(position).split("-");
                        String theday = day_color[0];
                        String themonth = day_color[2];
                        String theyear = day_color[3];
                       
                           
                        
                        // Set the Day GridCell
                        gridcell.setText(theday);
                        gridcell.setTag(theday + "-" + String.valueOf(Integer.parseInt(themonth)+1) + "-" + theyear);

                        if (day_color[1].equals("GREY"))
                                {gridcell.setTextColor(Color.LTGRAY);
                                gridcell.setBackgroundResource(R.drawable.grid_selector);}
                        
                        if (day_color[1].equals("WHITE"))
                        { gridcell.setTextColor(Color.BLACK);
                        gridcell.setBackgroundResource(R.drawable.grid_selector);}
                        
                        if (day_color[1].equals("BLUE"))
                         {gridcell.setTextColor(Color.BLACK);
                          gridcell.setBackgroundResource(R.drawable.current_date);
                         }
                                         
                        DataBase_Handler db1 = new DataBase_Handler(_context);
                        List<Integer> list =new ArrayList <Integer>();
                        list=db1.Check_event(Integer.parseInt(theday), Integer.parseInt(themonth)+1);
                       if(list.size()>0){
                    	   int no =list.size();
                         int i=0;
                    	   int _event_type=list.get(i);
                          
                       switch ( _event_type ) {					
                    case 1:
					       img1.setImageResource(R.drawable.khanda_blue);	break;
					case 2:
					       img1.setImageResource(R.drawable.khanda_black);	break;
					case 3:
					       img1.setImageResource(R.drawable.khanda_green);	break;
					case 4:
					       img1.setImageResource(R.drawable.khanda_red);	break;
					case 5:
					       img1.setImageResource(R.drawable.khanda_violet); break;
					case 6:
					       img1.setImageResource(R.drawable.khanda_light_blue); break;

					  
					}
                       i++;
                       if(i<no){
                    	    _event_type=list.get(i);
                    	   switch ( _event_type ) {					
                           case 1:
       					       img2.setImageResource(R.drawable.khanda_blue);	break;
       					case 2:
       					       img2.setImageResource(R.drawable.khanda_black);	break;
       					case 3:
       					       img2.setImageResource(R.drawable.khanda_green);	break;
       					case 4:
       					       img2.setImageResource(R.drawable.khanda_red);	break;
       					case 5:
       					       img2.setImageResource(R.drawable.khanda_violet); break;
       					case 6:
       					       img2.setImageResource(R.drawable.khanda_light_blue); break;

       					  
       					}
                       }
                       i++;
                       if(i<no){
                    	    _event_type=list.get(i);
                    	   switch ( _event_type ) {					
                           case 1:
       					       img3.setImageResource(R.drawable.khanda_blue);	break;
       					case 2:
       					       img3.setImageResource(R.drawable.khanda_black);	break;
       					case 3:
       					       img3.setImageResource(R.drawable.khanda_green);	break;
       					case 4:
       					       img3.setImageResource(R.drawable.khanda_red);	break;
       					case 5:
       					       img3.setImageResource(R.drawable.khanda_violet); break;
       					case 6:
       					       img3.setImageResource(R.drawable.khanda_light_blue); break;

       					  
       					}
                       }
                       
                       }
                      
                        return row;
                    }
                @Override
                public void onClick(View view){
                	
                        String date_month_year = (String) view.getTag();
                        String flag ="Date selected ...";
                      
                        Intent events= new Intent(_context,no_of_events.class);
                        events.putExtra("selected_cell",date_month_year );
                        events.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                		_context.startActivity(events);
                    }

                public int getCurrentDayOfMonth(){
                        return currentDayOfMonth;
                    }

                private void setCurrentDayOfMonth(int currentDayOfMonth){
                        this.currentDayOfMonth = currentDayOfMonth;
                    }
                public void setCurrentWeekDay(int currentWeekDay){
                        this.currentWeekDay = currentWeekDay;
                    }
                public int getCurrentWeekDay(){
                        return currentWeekDay;
                    }
            }
