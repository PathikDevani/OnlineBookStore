package com.example.api;



import com.example.api.shapeImg;

import android.graphics.LinearGradient;
import android.graphics.Shader.TileMode;
import android.graphics.Path;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Canvas;
import android.graphics.Rect;

public class txShape extends shapeImg {

	class Path0 extends Path {
		public Path0() {
			moveTo(400f, 100f);
			lineTo(0f, 100f);
			lineTo(0f, 0f);
			lineTo(400f, 0f);
			lineTo(400f, 100f);
		}
	}

	public Paint P0 = new Paint();
	Path0 S0 = new Path0();

	float Ht = 100;
	float Wh = 400;

	public int width, height, Y,X;
	String text;

	public void setText(String text, int size) {
		this.text = text;
		P0.setTextSize(size);
		Rect r = new Rect();
		P0.getTextBounds(text, 0, text.length(), r);
		width = r.width();
		height = r.height();
		Y = -r.top;
		X = -r.left;
	}
	public void finish(int x ,int y)
	{
		Y = Y + y;
		X = X + x;
		init((float) width / (float) Wh, (float) height / (float) Ht, x, y);
		S0.transform(matrix);
	}
	String color1,color2;
	public txShape(String c1,String c2) {
		color1 = c1;
		color2 = c2;
		LinearGradient Lg = null;
		Lg = new LinearGradient(0, 50, 400, 50, new int[] {
				Color.parseColor(color1), Color.parseColor(color2) },
				new float[] { 0.0f, 1.0f }, TileMode.MIRROR);
		Lg.setLocalMatrix(matrix);
		P0.setShader(Lg);
		P0.setAntiAlias(true);
	}
	public void setColor(String c1,String c2)
	{
		color1 = c1;
		color2 = c2;
	}
	public void draw(Canvas canvas) {
		// canvas.drawPath(S0, P0);
		canvas.drawText(text, X, Y, P0);
	}
}