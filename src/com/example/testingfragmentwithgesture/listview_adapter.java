package com.example.testingfragmentwithgesture;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class listview_adapter extends BaseAdapter implements OnClickListener{
     ArrayList <String> list;
     Context context;
     TextView tvcontent,tvtitle;
     
	 listview_adapter ( Context context,ArrayList<String> list){
		this.list=list;
		 this.context=context;
		 Log.i("entered","constructor");
	 }
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public String getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position) ;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Log.i("entered","get view");
		// TODO Auto-generated method stub
		View listview=convertView;
		if (listview == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                listview= inflater.inflate(R.layout.listview_format, parent, false);
             }
		tvcontent =(TextView) listview.findViewById(R.id.content);
		tvtitle =(TextView) listview.findViewById(R.id.title);
		 String[] content = list.get(position).split("-");
         String title = content[0];
         String text = content[1];
		
         tvcontent.setText(text);
         tvtitle.setText(title);
		
		return listview;
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		
	}

}
