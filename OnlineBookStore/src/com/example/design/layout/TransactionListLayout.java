package com.example.design.layout;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.AbsoluteLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AbsoluteLayout.LayoutParams;

import com.example.api.ABView;
import com.example.api.abLyt;
import com.example.design.listview.TransactionADP;
import com.example.onlinebook.MainActivity;
import com.exmaple.msgencode.keyList;

public class TransactionListLayout extends abLyt {
	ListView list;
	TransactionADP adp;
	IntentFilter intentFilter;
	abLyt balance;
	public TextView balTextView;
	
	@SuppressWarnings("deprecation")
	public TransactionListLayout(final Context context, int width, int height, int x,
			int y) {
		super(context, width, height, x, y);
		setBackgroundColor(Color.LTGRAY);

		intentFilter = new IntentFilter();
		intentFilter.addAction(keyList.brodCast.UPDATE_TRANSCTION_LIST);
		intentFilter.addAction(keyList.brodCast.DISTORY_APP);
		context.registerReceiver(receiver, intentFilter);
		
		list = new ListView(context);
		AbsoluteLayout.LayoutParams l = new AbsoluteLayout.LayoutParams(width,
				height- MainActivity.cf.dpix[40], 0, MainActivity.cf.dpix[40]);
		list.setLayoutParams(l);
		adp = new TransactionADP(context);
		list.setAdapter(adp);
		addView(list);
		
		balance = new abLyt(context, width, MainActivity.cf.dpix[40], 0, 0){
			{
				setBackgroundColor(Color.parseColor("#1C789F"));	
			}
		};
		balTextView = new TextView(context);
		balTextView.setTextColor(Color.WHITE);
		balTextView.setLayoutParams(new AbsoluteLayout.LayoutParams(AbsoluteLayout.LayoutParams.WRAP_CONTENT, AbsoluteLayout.LayoutParams.WRAP_CONTENT, MainActivity.cf.dpix[10], MainActivity.cf.dpix[5]));
		balTextView.setText("ACC Blance : Rs . "+MainActivity.db.getACCBlance());
		balTextView.setTextSize(22);
		balTextView.bringToFront();
		balance.addView(balTextView);
		addView(balance);
	}

	BroadcastReceiver receiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (intent.getAction().equals(keyList.brodCast.UPDATE_TRANSCTION_LIST)) {
				adp.dataChange();
				adp.notifyDataSetChanged();
				balTextView.setText("ACC Blance : Rs . "+MainActivity.db.getACCBlance());
			} else if (intent.getAction().equals(keyList.brodCast.DISTORY_APP)) {
				context.unregisterReceiver(receiver);
			}
		}
	};

}
