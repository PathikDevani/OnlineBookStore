package com.example.design.layout;

import org.json.JSONArray;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.api.ABView;
import com.example.api.abLyt;
import com.example.onlinebook.MainActivity;

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
			ABView Blist, Tview, AddB;
			{
				Blist = new ABView(context, width / 3, height, 0, 0) {
					{
						setBackgroundColor(Color.BLUE);
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
				Tview = new ABView(context, width / 3, height, width / 3, 0) {
					{
						setBackgroundColor(Color.CYAN);

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
				AddB = new ABView(context, width / 3, height, 2 * (width / 3),
						0) {
					{
						setBackgroundColor(Color.LTGRAY);
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

}
