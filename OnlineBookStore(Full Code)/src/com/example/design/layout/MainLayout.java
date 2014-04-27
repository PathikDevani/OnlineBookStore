package com.example.design.layout;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.widget.AbsoluteLayout;
import android.widget.TextView;

import com.example.api.ABView;
import com.example.api.abLyt;
import com.example.design.view.SStart;
import com.example.design.view.SliderBtn;
import com.example.msgencode.keyList;
import com.example.onlinebook.MainActivity;

public class MainLayout extends abLyt {
	SStart start;
	SliderBtn slider;
	IntentFilter intentFilter;
	public static SlidreMlayout Mlayout;
	TextView ipADD,devloper;

	@SuppressWarnings("deprecation")
	public MainLayout(Context context, int width, int height, int x, int y) {
		super(context, width, height, x, y);
		setBackgroundColor(Color.parseColor("#D3EDF8"));
		intentFilter = new IntentFilter();
		intentFilter.addAction(keyList.brodCast.CONNECT_TO_NETWORK);
		intentFilter.addAction(keyList.brodCast.DISCONNECT_TO_NETWORK);
		intentFilter.addAction(keyList.brodCast.DISTORY_APP);
		context.registerReceiver(receiver, intentFilter);
		
		start = new SStart(context, width, MainActivity.cf.dpix[40], 0, height
				- MainActivity.cf.dpix[40]);
		addView(start);

		ipADD = new TextView(context);
		ipADD.setTextColor(Color.BLACK);
		

		ipADD.setLayoutParams(new AbsoluteLayout.LayoutParams(
				AbsoluteLayout.LayoutParams.WRAP_CONTENT,
				AbsoluteLayout.LayoutParams.WRAP_CONTENT,
				MainActivity.cf.dpix[15], MainActivity.cf.dpix[50]));
		setIP();
		addView(ipADD);
		ipADD.measure(0,0);
		
		devloper = new TextView(context);
		devloper.setText("Develop by:\n----------------------------------\n1) Pathik devani\n2) Chirag Patel");
		devloper.setTextColor(Color.BLACK);
		devloper.setLayoutParams(new AbsoluteLayout.LayoutParams(AbsoluteLayout.LayoutParams.WRAP_CONTENT, AbsoluteLayout.LayoutParams.WRAP_CONTENT, MainActivity.cf.dpix[15], MainActivity.cf.dpix[15 + 50] + ipADD.getMeasuredHeight()));
		addView(devloper);
		
		slider = new SliderBtn(context, width, MainActivity.cf.dpix[40], 0, 0);
		addView(slider);

		Mlayout = new SlidreMlayout(context, width, height
				- MainActivity.cf.dpix[40], 0, MainActivity.cf.dpix[40]
				- height);
		Mlayout.bringToFront();
		addView(Mlayout);
	}
	public void setIP(){

		if (MainActivity.getLocalIpAddress().length > 0) {
			ipADD.setText("IP : " + MainActivity.getLocalIpAddress()[0]
					+ ":8080");
		} else {
			ipADD.setText("First connect to NETWORK....");
		}
	
	}
	BroadcastReceiver receiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (intent.getAction().equals(keyList.brodCast.CONNECT_TO_NETWORK)) {
				setIP();
			}else if (intent.getAction().equals(keyList.brodCast.DISCONNECT_TO_NETWORK)) {
				setIP();
			} 
			else if (intent.getAction().equals(keyList.brodCast.DISTORY_APP)) {
				context.unregisterReceiver(receiver);
			}
		}
	};
}
