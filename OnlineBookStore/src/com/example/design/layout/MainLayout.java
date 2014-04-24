package com.example.design.layout;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import com.example.api.ABView;
import com.example.api.abLyt;
import com.example.design.view.SStart;
import com.example.design.view.SliderBtn;
import com.example.onlinebook.MainActivity;

public class MainLayout extends abLyt{
	SStart start;
	SliderBtn slider;
	public static SlidreMlayout Mlayout;
	public MainLayout(Context context, int width, int height, int x, int y) {
		super(context, width, height, x, y);
		setBackgroundColor(Color.parseColor("#D3EDF8"));
		
		start = new SStart(context, width, MainActivity.cf.dpix[40], 0, height - MainActivity.cf.dpix[40]);
		addView(start);
		
		slider = new SliderBtn(context, width, MainActivity.cf.dpix[40], 0, 0);
		addView(slider);
		
		Mlayout = new SlidreMlayout(context, width, height - MainActivity.cf.dpix[40], 0, MainActivity.cf.dpix[40] - height);
		addView(Mlayout);
		
		
	
	}

}
