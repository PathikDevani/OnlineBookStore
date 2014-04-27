package com.example.design.layout;

import org.json.JSONArray;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.TextView;

import com.example.api.ABView;
import com.example.api.abLyt;
import com.example.onlinebook.MainActivity;

@SuppressWarnings("deprecation")
public class SlidreMlayout extends abLyt {
	abLyt btnList;
	BookListlayout BlistL;
	TransactionListLayout TlistL;
	AddBookLayout AbookL;

	public SlidreMlayout(final Context context, final int width,
			final int height, int x, int y) {
		super(context, width, height, x, y);
		setBackgroundColor(Color.GREEN);

		BlistL = new BookListlayout(context, width, height
				- MainActivity.cf.dpix[40], 0, 0);
		TlistL = new TransactionListLayout(context, width, height
				- MainActivity.cf.dpix[40], 0, 0);
		AbookL = new AddBookLayout(context, width, height
				- MainActivity.cf.dpix[40], 0, 0);
		addView(BlistL);
		addView(TlistL);
		addView(AbookL);

		btnList = new abLyt(context, width, MainActivity.cf.dpix[40], 0, height
				- MainActivity.cf.dpix[40]) {
			abLyt Blist, Tview, AddB;
			{
				Blist = new abLyt(context, width / 3, MainActivity.cf.dpix[40],
						0, 0) {
					TextView title;
					{
						setBackgroundColor(Color.BLUE);
						title = new TextView(context);
						setTXT(title, "BookList", width / 3,
								MainActivity.cf.dpix[40]);
						addView(title);
					}

					@Override
					public void onClick(MotionEvent event) {
						if (BlistL.getVisibility() == View.GONE) {
							BlistL.setVisibility(View.VISIBLE);
							if (AbookL.getVisibility() == View.VISIBLE) {
								AbookL.setVisibility(View.GONE);
							}
							if (TlistL.getVisibility() == View.VISIBLE) {
								TlistL.setVisibility(View.GONE);
							}
						}
					}
				};
				Tview = new abLyt(context, width / 3, MainActivity.cf.dpix[40],
						width / 3, 0) {
					TextView title;
					{
						setBackgroundColor(Color.CYAN);
						title = new TextView(context);
						setTXT(title, "Transaction", width / 3,
								MainActivity.cf.dpix[40]);
						title.setTextColor(Color.BLACK);
						addView(title);
					}

					@Override
					public void onClick(MotionEvent event) {
						if (TlistL.getVisibility() == View.GONE) {
							TlistL.setVisibility(View.VISIBLE);
							if (AbookL.getVisibility() == View.VISIBLE) {
								AbookL.setVisibility(View.GONE);
							}
							if (BlistL.getVisibility() == View.VISIBLE) {
								BlistL.setVisibility(View.GONE);
							}
						}
					}
				};
				AddB = new abLyt(context, width / 3, MainActivity.cf.dpix[40],
						2 * (width / 3), 0) {
					TextView title;
					{
						setBackgroundColor(Color.LTGRAY);
						title = new TextView(context);
						setTXT(title, "AddBook", width / 3,
								MainActivity.cf.dpix[40]);
						title.setTextColor(Color.BLACK);
						addView(title);
					}

					@Override
					public void onClick(MotionEvent event) {
						if (AbookL.getVisibility() == View.GONE) {
							AbookL.setVisibility(View.VISIBLE);
							if (TlistL.getVisibility() == View.VISIBLE) {
								TlistL.setVisibility(View.GONE);
							}
							if (BlistL.getVisibility() == View.VISIBLE) {
								BlistL.setVisibility(View.GONE);
							}
						}
					}
				};

				addView(Blist);
				addView(Tview);
				addView(AddB);
			}

		};
		addView(btnList);

		TlistL.setVisibility(View.GONE);
		AbookL.setVisibility(View.GONE);
	}

	public void setTXT(TextView title, String str, int w, int h) {
		title.setText(str);
		title.measure(0, 0);
		title.setTextColor(Color.WHITE);
		title.setLayoutParams(new AbsoluteLayout.LayoutParams(
				AbsoluteLayout.LayoutParams.WRAP_CONTENT,
				AbsoluteLayout.LayoutParams.WRAP_CONTENT, (w - title
						.getMeasuredWidth()) / 2, (h - title
						.getMeasuredHeight()) / 2));

	}
}
