package com.example.design.view;

import android.content.Context;
import android.graphics.Color;
import android.preference.PreferenceActivity.Header;
import android.support.v4.view.VelocityTrackerCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.widget.AbsoluteLayout.LayoutParams;

import com.example.api.ABView;
import com.example.design.layout.MainLayout;
import com.example.onlinebook.MainActivity;

public class SliderBtn extends ABView {
	int height;

	public SliderBtn(Context context, int width, int height, int x, int y) {
		super(context, width, height, x, y);
		setBackgroundColor(Color.parseColor("#1C789F"));
		
		this.height = height;
	}

	int dy = 0, dx;
	private VelocityTracker vTracker = null;

	@SuppressWarnings("deprecation")
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		int index = event.getActionIndex();
		int action = event.getActionMasked();
		int pointerId = event.getPointerId(index);

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if (vTracker == null) {
				vTracker = VelocityTracker.obtain();
			} else {
				vTracker.clear();
			}
			vTracker.addMovement(event);
			break;
		case MotionEvent.ACTION_MOVE:
			vTracker.addMovement(event);
			int y = (int) event.getRawY();
			LayoutParams Btn = (LayoutParams) getLayoutParams();
			Btn.x = 0;
			Btn.y = y - height;
			LayoutParams layout = (LayoutParams) MainLayout.Mlayout
					.getLayoutParams();
			layout.x = 0;
			layout.y = -MainActivity.cf.h + MainActivity.cf.dpix[40]
					+ (y - height);
			if (y <= height) {
				Btn.y = 0;
				layout.y = -MainActivity.cf.h + MainActivity.cf.dpix[40];
			}
			if (y >= MainActivity.cf.h) {
				Btn.y = MainActivity.cf.h - height;
				layout.y = 0;
			}
			setLayoutParams(Btn);
			MainLayout.Mlayout.setLayoutParams(layout);
			break;
		case MotionEvent.ACTION_OUTSIDE:
			break;
		case MotionEvent.ACTION_UP:
			break;
		default:
		}
		return true;
	}

}
