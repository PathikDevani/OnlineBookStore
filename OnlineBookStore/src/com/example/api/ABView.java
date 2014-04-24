package com.example.api;

import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.AbsoluteLayout.LayoutParams;

@SuppressWarnings("deprecation")
public class ABView extends View {

	public ABView(Context context) {
		super(context);
	}

	public ABView(Context context, int width, int height, int x, int y) {
		super(context);
		setPos(width, height, x, y);
	}

	public LayoutParams Ls = new AbsoluteLayout.LayoutParams(0, 0, 0, 0);

	public void setPos(int width, int height, int x, int y) {
		Ls.height = height;
		Ls.width = width;
		Ls.x = x;
		Ls.y = y;
		setLayoutParams(Ls);
	}

	public void onDown(MotionEvent event) {

	}

	public void onUp(MotionEvent event) {

	}


	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Rect rectf = new Rect();
		getHitRect(rectf);

		
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			
			onDown(event);
			break;
		case MotionEvent.ACTION_MOVE:
			onMove(event);
			break;
		case MotionEvent.ACTION_OUTSIDE:
			onOut(event);

			break;
		case MotionEvent.ACTION_UP:

			if (event.getX() > 0 && event.getX() < rectf.width() && event.getY() > 0 && event.getY() < rectf.height())
				onClick(event);
			onUp(event);
			break;
		default:
			onleave(event);
		}
		// invalidate();

		return true;
	}

	public void onClick(MotionEvent event) {
		// TODO Auto-generated method stub
		
	}

	public void onleave(MotionEvent event) {
		// TODO Auto-generated method stub

	}

	public void onOut(MotionEvent event) {
		// TODO Auto-generated method stub

	}

	public void onMove(MotionEvent event) {
		// TODO Auto-generated method stub

	}

	public void setAnim(int y) {
		// TODO Auto-generated method stub

	}

	public void setx(int x) {
		Ls.x = x;
		setLayoutParams(Ls);
	}

	public void sety(int y) {
		Ls.y = y;
		setLayoutParams(Ls);
	}

	public void setwidth(int i) {
		Ls.width = i;
		setLayoutParams(Ls);
	}

}
