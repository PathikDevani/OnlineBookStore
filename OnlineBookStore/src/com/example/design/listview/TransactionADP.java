package com.example.design.listview;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.api.abLyt;
import com.example.database.Book;
import com.example.onlinebook.MainActivity;

import android.R;
import android.R.bool;
import android.content.Context;
import android.graphics.Color;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsoluteLayout;
import android.widget.BaseAdapter;
import android.widget.TextView;
@SuppressWarnings("deprecation")
public class TransactionADP extends BaseAdapter {
	JSONArray tList;
	Context context;

	public TransactionADP(Context context) {
		tList = MainActivity.db.getTransacionList();
		this.context = context;
	}

	@Override
	public int getCount() {

		return tList.length();
	}

	@Override
	public JSONObject getItem(int position) {

		try {
			return (JSONObject) tList.get(position);
		} catch (JSONException e) {
			return null;
		}
		
	}

	@Override
	public long getItemId(int position) {

		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		JSONObject json = getItem(position);
		String tid = null,bid = null,uname = null,money = null ,item = null,time = null;
		
		try {
			tid = json.getString("tid");
			bid = json.getString("bid");
			uname = json.getString("user");
			money = json.getString("money");;
			item = json.getString("item");;
			time = json.getString("time");;
		} catch (JSONException e) {
			
		}
		Book book = MainActivity.db.getBook(bid);
		Row row ;
		if(convertView == null){
			row = new Row(context);
			convertView = row;
		}
		else {
			row = (Row) convertView;
		}
		row.text.setText("On "+time.toUpperCase() + " person buy book " + book.getName().toUpperCase() + " " +item.toUpperCase() +" times "+ "and person name is "+uname.toUpperCase()+" totle pay money in RS is " + money);
		return convertView;
	}

	public void dataChange() {
		tList = MainActivity.db.getTransacionList();
	}

	class Row extends abLyt {
		TextView text;
		public Row(Context context) {
			super(context);
			setBackgroundColor(Color.WHITE);
			setLP();
			
			text = new TextView(context,null,R.attr.textAppearanceSmall);
			//text.setEllipsize(TextUtils.TruncateAt.END);
			text.setMaxLines(10);
			text.setTextColor(Color.BLACK);
			//text.setLines(3);
			text.setLayoutParams(new AbsoluteLayout.LayoutParams(
					MainActivity.cf.w - MainActivity.cf.dpix[20],
					AbsoluteLayout.LayoutParams.WRAP_CONTENT,
					MainActivity.cf.dpix[5], MainActivity.cf.dpix[0]));
			addView(text);
		}
		public void setLP(){
			AbsListView.LayoutParams l = new AbsListView.LayoutParams(MainActivity.cf.w, AbsListView.LayoutParams.WRAP_CONTENT);
			setLayoutParams(l);
		}
	}
}
