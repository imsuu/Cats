package com.cats.weights;

import com.cats.interfaces.ViewClickListener;
import com.demo.cats.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.view.View.OnTouchListener;

@SuppressLint("ClickableViewAccessibility")
public class SelectedButton extends Button implements OnTouchListener{

	private int normalTextColor,pressedTextColor;
	private int normalBack,pressedBack;
	private ViewClickListener mListener;
	
	public SelectedButton(Context context) {
		this(context, null);
	}
	public SelectedButton(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}
	public SelectedButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(attrs);
	}
	
	private void init(AttributeSet attrs){
		Resources res = getContext().getResources();
		normalTextColor = res.getColor(R.color.textcolor_red_d);
		pressedTextColor = res.getColor(R.color.textcolor_white_a);
		TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.SelectButton);
		normalBack = ta.getResourceId(R.styleable.SelectButton_background_normal, -1);
		pressedBack = ta.getResourceId(R.styleable.SelectButton_background_select, -1);
		ta.recycle();
		this.setGravity(Gravity.CENTER);
		this.setOnTouchListener(this);
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			this.setTextColor(pressedTextColor);
			if(pressedBack != -1){
				this.setBackgroundResource(pressedBack);
			}
			break;
		case MotionEvent.ACTION_UP:
			if(mListener != null){
				mListener.onClick(this);
			}
			break;
		}
		return true;
	}
	
	public void setNormalState(){
		this.setTextColor(normalTextColor);
		if(normalBack != -1){
			this.setBackgroundResource(normalBack);
		}
	}
	
	public void setViewClickListener(ViewClickListener listener){
		this.mListener = listener;
	}
}
