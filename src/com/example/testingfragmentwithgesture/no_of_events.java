package com.example.testingfragmentwithgesture;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class no_of_events  extends Activity{
    
	
	ArrayList <String> list;
	
	ListView events_listview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.events_listview);
		
		events_listview=(ListView)findViewById(R.id.events_listview);
		list=new ArrayList<String>();
		
		Bundle b=getIntent().getExtras();
		String D_M_Y =b.getString("selected_cell");
		String [] event =D_M_Y.split("-");
		int eventdate= Integer.parseInt(event[0]);
		int eventmonth= Integer.parseInt(event[1]);
		 
		DataBase_Handler db1 = new DataBase_Handler(getApplicationContext());
		  list=db1.get_events_for_list(eventdate, eventmonth);
		listview_adapter adapter =new listview_adapter(getApplicationContext(),list);
		 events_listview.setAdapter(adapter);
		
	    events_listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				String selectedValue = list.get(arg2);
				Log.i("display", selectedValue);
				Intent selected_event=new Intent(getApplicationContext(),Display_event.class);
				selected_event.putExtra("selectedevent", selectedValue);
				startActivity(selected_event);
				
			}
		});
	

}
	}
