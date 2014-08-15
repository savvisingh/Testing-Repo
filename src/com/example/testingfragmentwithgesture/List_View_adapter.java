package com.example.testingfragmentwithgesture;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;



public class List_View_adapter extends BaseAdapter {

	 Context context;
	 List<List_item> listItem;

	 List_View_adapter(Context context, List<List_item> listItem) {
	  this.context = context;
	  this.listItem = listItem;
	 }


	 @Override
	 public View getView(int position, View convertView, ViewGroup parent) {

	if (convertView == null) {
	            LayoutInflater mInflater = (LayoutInflater) context
	                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
	            convertView = mInflater.inflate(R.layout.navigation_listview, null);
	        }

	        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.icon);
	        TextView txtTitle = (TextView) convertView.findViewById(R.id.title);

	        List_item row_pos = listItem.get(position);
	        // setting the image resource and title
	        imgIcon.setImageResource(row_pos.getIcon());
	        txtTitle.setText(row_pos.getTitle());

	        return convertView;
	 }

	 @Override
	 public int getCount() {
	  return listItem.size();
	 }

	 @Override
	 public Object getItem(int position) {
	  return listItem.get(position);
	 }

	 @Override
	 public long getItemId(int position) {
	  return listItem.indexOf(getItem(position));
	 }

	}
