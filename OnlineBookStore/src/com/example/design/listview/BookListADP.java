package com.example.design.listview;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsoluteLayout;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.api.ABView;
import com.example.api.abLyt;
import com.example.onlinebook.MainActivity;
import com.example.service.AppService;

@SuppressLint("NewApi")
public class BookListADP extends BaseAdapter {
	Context context;
	JSONArray booklist;
	boolean isLongClick;
	
	public BookListADP(Context context) {
		this.context = context;
		booklist = AppService.db.getJArrayBooklist();
		isLongClick = false;
	}

	public void dataChange(){
		booklist = AppService.db.getJArrayBooklist();
	}
	@Override
	public int getCount() {
		return booklist.length();
	}

	@Override
	public JSONObject getItem(int position) {
		try {
			return (JSONObject) booklist.get(position);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		JSONObject json = null;
		String bookname = null, price = null, item = null,stock = null,bookid = null;
		try {
			json = (JSONObject) booklist.get(position);
			bookname = (String) json.get("bname");
			price = (String) json.get("price");
			item = (String) json.get("item");
			stock = (String) json.get("stock");
			bookid = (String) json.get("bid");
		} catch (JSONException e) {

		}

		Row row;
		if (view == null) {
			row = new Row(context, MainActivity.cf.w, MainActivity.cf.dpix[110]);
			view = row;
		} else {
			row = (Row) view;
		}
		final JSONObject temp = json;
		row.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				try {
					onItemClick(temp);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		row.setOnLongClickListener(new View.OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View arg0) {
				try {
					isLongClick = true;
					Toast toast = Toast.makeText(context, "long click....", Toast.LENGTH_SHORT);
			        toast.show();
					onItemLongClick(temp);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return false;
			}
		});
		row.bookname.setText(bookname);
		row.item.setText(item + " iteam available....");
		row.price.setText("Price : Rs. " + price);
		row.stock.setText(stock);
		if(stock.equals("In Stock")){
			row.stock.setTextColor(Color.GREEN);
		}else {
			row.stock.setTextColor(Color.RED);
		}
		
		URL newurl = null;
		try {
			newurl = new URL("http://127.0.0.1:8080/img/"+bookid+".jpg");
			row.img.setImageBitmap(BitmapFactory.decodeStream(newurl.openConnection() .getInputStream()));
		} catch (MalformedURLException e) {
			
		} catch (IOException e) {
			
		} 
		
		
		
		return view;
	}
	
	protected void onItemClick(JSONObject json) throws JSONException {
		
	}
	protected void onItemLongClick(JSONObject json) throws JSONException {
		
	}

	class Row extends abLyt {
		int width, height;
		TextView bookname, item, price,stock;
		ImageView img;
		View view;
		public Row(Context context, int width, int height) {
			super(context);
			this.width = width;
			this.height = height;
			setLParams();
			setBackgroundColor(Color.BLACK);
			
			view = new View(context);
			view.setLayoutParams(new AbsoluteLayout.LayoutParams(MainActivity.cf.w, MainActivity.cf.dpix[110], 0, 0));
			addView(view);
			
			
			img = new ImageView(context);
			img.setLayoutParams(new LayoutParams(MainActivity.cf.dpix[100], MainActivity.cf.dpix[71], MainActivity.cf.dpix[15], MainActivity.cf.dpix[15]));
			addView(img);
			
			
			bookname = new TextView(context,null,R.attr.textAppearanceMedium);
			//bookname.setTextSize(MainActivity.cf.dpix[10]);
			setLView(bookname, MainActivity.cf.dpix[125],
					MainActivity.cf.dpix[5]);
			bookname.setTextColor(Color.WHITE);
			addView(bookname);

			item = new TextView(context,null,R.attr.textAppearanceSmall);
			//item.setTextSize(MainActivity.cf.dpix[8]);
			setLView(item, MainActivity.cf.dpix[125], MainActivity.cf.dpix[40]);
			addView(item);

			price = new TextView(context,null,R.attr.textAppearanceSmall);
			//price.setTextSize(MainActivity.cf.dpix[8]);
			setLView(price, MainActivity.cf.dpix[125], MainActivity.cf.dpix[62]);
			addView(price);
			
			stock = new TextView(context,null,R.attr.textAppearanceSmall);
			//stock.setTextSize(MainActivity.cf.dpix[8]);
			setLView(stock, MainActivity.cf.dpix[125], MainActivity.cf.dpix[84]);
			addView(stock);

			view.bringToFront();
			
		}

		public void setLView(View view, int x, int y) {

			AbsoluteLayout.LayoutParams l = new AbsoluteLayout.LayoutParams(
					AbsoluteLayout.LayoutParams.WRAP_CONTENT,
					AbsoluteLayout.LayoutParams.WRAP_CONTENT, x, y);

			view.setLayoutParams(l);
		}

		public void setLParams() {
			AbsListView.LayoutParams l = new AbsListView.LayoutParams(width,
					height);
			setLayoutParams(l);
		}

	}
	
	

}
