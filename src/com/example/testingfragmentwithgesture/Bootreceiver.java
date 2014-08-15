package com.example.testingfragmentwithgesture;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class Bootreceiver extends BroadcastReceiver {

  	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		
  		if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
  			Intent service_intent= new Intent(context ,Start_Service.class);
  			context.startService(service_intent);
  			
  		}
	}
}
