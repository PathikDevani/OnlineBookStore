package com.example.api;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.content.Context;
import android.text.format.DateFormat;
import android.util.Log;

public class config {
	public int w, h;
	public static Context context;
	public float dpi;
	public int[] dpix = new int[1001];
	


	public config(Context baseContext, int width, int height) {
		w = width;
		h = height;
		context = baseContext;
		dpi = baseContext.getResources().getDisplayMetrics().density;
		for (int i = 1; i < 1001; i++) {
			dpix[i] = (int) (dpi * i);
		}
	}

	
	

}
