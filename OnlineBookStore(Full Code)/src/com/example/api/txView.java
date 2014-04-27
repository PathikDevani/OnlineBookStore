package com.example.api;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.widget.AbsoluteLayout;
import android.widget.AbsoluteLayout.LayoutParams;
public class txView extends ABView{
	txShape tx;
	int x,y;
	public txView(Context context, int width, int height, int x, int y,String color) {
		super(context, width, height, x, y);
		tx = new txShape(color, color);
		tx.P0.setTypeface(Typeface.DEFAULT);
		this.x = x;
		this.y = y;
	}
	public txView(Context context, int width, int height, int x, int y) {
		super(context, width, height, x, y);
		tx = new txShape("#FFFFFFFF", "#FFFFFFFF");
		tx.P0.setTypeface(Typeface.DEFAULT);
		this.x = x;
		this.y = y;
	}
	public void setText(String text,int size)
	{
		tx.setText(text, size);
		tx.finish(x, y);
		AbsoluteLayout.LayoutParams l = (LayoutParams) getLayoutParams();
		l.height = tx.height;
		l.width = tx.width;
		setLayoutParams(l);
		invalidate();
	}
	@Override
	protected void onDraw(Canvas canvas) {
		tx.draw(canvas);
	}
}
