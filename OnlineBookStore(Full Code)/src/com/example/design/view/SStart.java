package com.example.design.view;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.AbsoluteLayout;
import android.widget.TextView;

import com.example.api.abLyt;
import com.example.msgencode.keyList;
import com.example.onlinebook.MainActivity;
import com.example.service.AppService;

@SuppressWarnings("deprecation")
public class SStart extends abLyt {

	TextView text;
	int width, height;
	IntentFilter intentFilter;
	Context context;

	public SStart(Context context, int width, int height, int x, int y) {
		super(context, width, height, x, y);
		setBackgroundColor(Color.parseColor("#1C789F"));

		this.context = context;
		this.height = height;
		this.width = width;
		intentFilter = new IntentFilter();
		intentFilter.addAction(keyList.brodCast.SEVER_STARTED);
		intentFilter.addAction(keyList.brodCast.SEVER_STOPED);
		intentFilter.addAction(keyList.brodCast.DISTORY_APP);
		context.registerReceiver(receiver, intentFilter);

		text = new TextView(context);
		text.setTextColor(Color.WHITE);
		addView(text);
		if (keyList.brodCast.isServerStart) {
			text.setText("Stop");
		} else {
			text.setText("Start");
		}
		setText();
	}

	@Override
	public void onClick(MotionEvent event) {
		if (keyList.brodCast.isServerStart) {
			context.sendBroadcast(new Intent(keyList.brodCast.SEVER_STOP));
		} else {
			context.sendBroadcast(new Intent(keyList.brodCast.SEVER_START));
		}
	}

	public void setText() {
		text.measure(0, 0);
		text.setLayoutParams(new AbsoluteLayout.LayoutParams(
				AbsoluteLayout.LayoutParams.WRAP_CONTENT,
				AbsoluteLayout.LayoutParams.WRAP_CONTENT, (width - text
						.getMeasuredWidth()) / 2, (height - text
						.getMeasuredHeight()) / 2));
	}

	BroadcastReceiver receiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (intent.getAction().equals(keyList.brodCast.SEVER_STARTED)) {
				text.setText("Stop");
				setText();
			} else if (intent.getAction().equals(keyList.brodCast.SEVER_STOPED)) {
				text.setText("Start");
				setText();
			} else if (intent.getAction().equals(keyList.brodCast.DISTORY_APP)) {
				context.unregisterReceiver(receiver);
			}
		}
	};

}
