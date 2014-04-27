package com.example.design.layout;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import android.R;
import android.R.integer;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.LocalBroadcastManager;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsoluteLayout;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.api.ABView;
import com.example.api.abLyt;
import com.example.database.Book;
import com.example.design.listview.BookListADP;
import com.example.msgencode.keyList;
import com.example.onlinebook.MainActivity;
import com.example.service.AppService;

@SuppressWarnings("deprecation")
public class BookListlayout extends abLyt {
	ListView list;
	BookListADP adp;
	Context context;
	IntentFilter intentFilter;

	public BookListlayout(final Context context, int width, int height, int x,
			int y) {
		super(context, width, height, x, y);
		setBackgroundColor(Color.BLUE);

		this.context = context;
		intentFilter = new IntentFilter();
		intentFilter.addAction(keyList.brodCast.UPDATE_BOOK_LIST);
		intentFilter.addAction(keyList.brodCast.DISTORY_APP);
		context.registerReceiver(receiver, intentFilter);

		list = new ListView(context);
		AbsoluteLayout.LayoutParams l = new LayoutParams(width, height, 0, 0);
		list.setLayoutParams(l);
		list.setBackgroundColor(Color.BLACK);
		adp = new BookListADP(context) {
			@Override
			protected void onItemClick(JSONObject json) throws JSONException {

				abLyt main = new abLyt(context, MainActivity.cf.w,
						MainActivity.cf.h, 0, 0) {
					abLyt a;

					@Override
					protected void onAttachedToWindow() {
						super.onAttachedToWindow();
						Animation fadeIn = new AlphaAnimation(0, 1);
						fadeIn.setInterpolator(new DecelerateInterpolator());
						fadeIn.setDuration(100);
						this.startAnimation(fadeIn);
						a = this;
						setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View arg0) {
								removieAnim(a);
							}
						});
					}

				};
				main.setBackgroundColor(Color.parseColor("#BF000000"));
				abLyt layout = new abLyt(context, MainActivity.cf.dpix[300],
						MainActivity.cf.dpix[295],
						(MainActivity.cf.w - MainActivity.cf.dpix[300]) / 2,
						(MainActivity.cf.h - MainActivity.cf.dpix[295]) / 2);
				layout.setBackgroundColor(Color.WHITE);
				main.addView(layout);
				add(main, layout, json);
				MainActivity.main.addView(main);
			}
		};
		list.setAdapter(adp);
		addView(list);

	}

	protected void add(final abLyt main, abLyt layout, JSONObject json)
			throws JSONException {
		String bookname;
		final String bookid;
		String price, item;
		bookname = (String) json.get("bname");
		bookid = (String) json.get("bid");
		price = (String) json.get("price");
		item = (String) json.get("item");

		ImageView img = new ImageView(context);
		img.setLayoutParams(new AbsoluteLayout.LayoutParams(
				MainActivity.cf.dpix[106], MainActivity.cf.dpix[150],
				MainActivity.cf.dpix[5], MainActivity.cf.dpix[5]));
		layout.addView(img);

		try {
			InputStream ims = context.getAssets()
					.open("img/" + bookid + ".jpg");
			Drawable d = Drawable.createFromStream(ims, null);
			img.setImageDrawable(d);
		} catch (IOException ex) {
			InputStream ims;
			try {
				ims = context.getAssets().open("img/unknow.jpg");
				Drawable d = Drawable.createFromStream(ims, null);
				img.setImageDrawable(d);
			} catch (IOException e) {

			}

		}
		TextView bname = new TextView(context, null,
				R.attr.textAppearanceMedium);
		bname.setText(bookname);
		bname.setEllipsize(TextUtils.TruncateAt.END);
		bname.setMaxLines(2);
		// bname.setTextSize(MainActivity.cf.dpix[10]);
		bname.setTextColor(Color.BLACK);
		bname.setLayoutParams(new AbsoluteLayout.LayoutParams(
				MainActivity.cf.dpix[300 - 120 - 5],
				AbsoluteLayout.LayoutParams.WRAP_CONTENT,
				MainActivity.cf.dpix[120], MainActivity.cf.dpix[5]));
		layout.addView(bname);
		bname.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);

		TextView bprice = new TextView(context, null,
				R.attr.textAppearanceSmall);
		bprice.setText("Price: Rs. " + price);
		bprice.setEllipsize(TextUtils.TruncateAt.END);
		bprice.setMaxLines(1);
		// bprice.setTextSize(MainActivity.cf.dpix[8]);
		bprice.setTextColor(Color.BLACK);
		bprice.setLayoutParams(new AbsoluteLayout.LayoutParams(
				MainActivity.cf.dpix[300 - 120 - 5],
				AbsoluteLayout.LayoutParams.WRAP_CONTENT,
				MainActivity.cf.dpix[120], MainActivity.cf.dpix[35]
						+ bname.getMeasuredHeight()));
		layout.addView(bprice);
		bprice.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);

		TextView bitem = new TextView(context, null, R.attr.textAppearanceSmall);
		bitem.setText(item + " book avalble....");
		bitem.setEllipsize(TextUtils.TruncateAt.END);
		bitem.setMaxLines(1);
		// bitem.setTextSize(MainActivity.cf.dpix[8]);
		bitem.setTextColor(Color.BLACK);
		bitem.setLayoutParams(new AbsoluteLayout.LayoutParams(
				MainActivity.cf.dpix[300 - 120 - 5],
				AbsoluteLayout.LayoutParams.WRAP_CONTENT,
				MainActivity.cf.dpix[120], MainActivity.cf.dpix[35]
						+ bname.getMeasuredHeight()
						+ bprice.getMeasuredHeight()));
		layout.addView(bitem);
		bitem.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);

		TextView Additeam = new TextView(context, null,
				R.attr.textAppearanceSmall);
		Additeam.setText("Add Item : ");
		Additeam.setEllipsize(TextUtils.TruncateAt.END);
		Additeam.setMaxLines(1);
		// Additeam.setTextSize(MainActivity.cf.dpix[8]);
		Additeam.setTextColor(Color.BLACK);
		Additeam.setLayoutParams(new AbsoluteLayout.LayoutParams(
				AbsoluteLayout.LayoutParams.WRAP_CONTENT,
				AbsoluteLayout.LayoutParams.WRAP_CONTENT,
				MainActivity.cf.dpix[5], MainActivity.cf.dpix[160]));
		layout.addView(Additeam);
		Additeam.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);

		final EditText addI = new EditText(context);
		// addI.setBackgroundColor(Color.parseColor("#00000000"));
		addI.setHint("Enter No");
		// addI.setTextSize(MainActivity.cf.dpix[8]);
		addI.setTextColor(Color.BLACK);
		addI.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
		addI.setLayoutParams(new AbsoluteLayout.LayoutParams(
				MainActivity.cf.dpix[290],
				AbsoluteLayout.LayoutParams.WRAP_CONTENT,
				MainActivity.cf.dpix[5], MainActivity.cf.dpix[153 + 10]
						+ Additeam.getMeasuredHeight()));
		layout.addView(addI);

		abLyt delete = new abLyt(context, MainActivity.cf.dpix[140],
				MainActivity.cf.dpix[40], MainActivity.cf.dpix[5],
				MainActivity.cf.dpix[250]) {
			{
				setBackgroundColor(Color.RED);
				TextView title = new TextView(context);
				setTXT(title, "Delete", MainActivity.cf.dpix[140],
						MainActivity.cf.dpix[40]);
				title.setTextColor(Color.WHITE);
				addView(title);
			}

			@Override
			public void onClick(MotionEvent event) {
				AppService.db.deletBook(bookid);
				Intent i = new Intent(keyList.brodCast.DELETE_BOOK);
				i.putExtra("bid", bookid);
				context.sendBroadcast(i);
				context.sendBroadcast(new Intent(
						keyList.brodCast.UPDATE_BOOK_LIST));
				removieAnim(main);
			}
		};
		layout.addView(delete);

		abLyt ok = new abLyt(context, MainActivity.cf.dpix[140],
				MainActivity.cf.dpix[40], MainActivity.cf.dpix[300 - 145],
				MainActivity.cf.dpix[250]) {
			{
				setBackgroundColor(Color.GREEN);
				TextView title = new TextView(context);
				setTXT(title, "Ok", MainActivity.cf.dpix[140],
						MainActivity.cf.dpix[40]);
				addView(title);
			}

			@Override
			public void onClick(MotionEvent event) {
				if (isNumeric(addI.getText().toString())) {
					Book book = AppService.db.getBook(bookid);
					book.setItem(Integer.parseInt(book.getItem())
							+ Integer.parseInt(addI.getText().toString()) + "");
					if(Integer.parseInt(book.getItem())>0){
						book.setStock("In Stock");
					}else {
						book.setStock("Out of Stock");						
					}
					AppService.db.setBook(book);
					if(keyList.brodCast.isServerStart){
						Intent i = new Intent(keyList.brodCast.REFRESH_BOOK);
						i.putExtra("bid", bookid);
						context.sendBroadcast(i);
					}
					adp.dataChange();
					adp.notifyDataSetChanged();
					removieAnim(main);
				} else {
					removieAnim(main);
				}
			}

			public boolean isNumeric(String str) {
				try {
					double d = Double.parseDouble(str);
				} catch (NumberFormatException nfe) {
					return false;
				}
				return true;
			}
		};
		layout.addView(ok);
	}

	public void setLView(View view, int x, int y) {

		AbsoluteLayout.LayoutParams l = new AbsoluteLayout.LayoutParams(
				AbsoluteLayout.LayoutParams.WRAP_CONTENT,
				AbsoluteLayout.LayoutParams.WRAP_CONTENT, x, y);

		view.setLayoutParams(l);
	}

	public void removieAnim(abLyt main) {
		Animation fadeOut = new AlphaAnimation(1, 0);
		fadeOut.setInterpolator(new AccelerateInterpolator());
		fadeOut.setDuration(100);
		main.startAnimation(fadeOut);
		MainActivity.main.removeView(main);
	}

	BroadcastReceiver receiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (intent.getAction().equals(keyList.brodCast.UPDATE_BOOK_LIST)) {
				adp.dataChange();
				adp.notifyDataSetChanged();
			} else if (intent.getAction().equals(keyList.brodCast.DISTORY_APP)) {
				context.unregisterReceiver(receiver);
			}
		}
	};

	public void setTXT(TextView title, String str, int w, int h) {
		title.setText(str);
		title.measure(0, 0);
		title.setTextColor(Color.BLACK);
		title.setLayoutParams(new AbsoluteLayout.LayoutParams(
				AbsoluteLayout.LayoutParams.WRAP_CONTENT,
				AbsoluteLayout.LayoutParams.WRAP_CONTENT, (w - title
						.getMeasuredWidth()) / 2, (h - title
						.getMeasuredHeight()) / 2));

	}
}
