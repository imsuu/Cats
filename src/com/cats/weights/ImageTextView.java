package com.cats.weights;

import com.cats.interfaces.ViewClickListener;
import com.demo.cats.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.View.OnTouchListener;
/**
 *左边是图片flag，右边显示文字 
 */
@SuppressLint("ClickableViewAccessibility")
public class ImageTextView extends LinearLayout implements OnTouchListener{

	private int normalTextColor,pressedTextColor;
	private int normalBack,pressedBack;
	private int normalImg,pressImg;
	private ImageView imageView;
	private TextView textView;
	private Resources res;
	private ViewClickListener mListener;
	private int imageWidth,imageHeight;
	
	public ImageTextView(Context context) {
		this(context, null);
	}
	public ImageTextView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}
	public ImageTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(attrs);
		addImg();
		addTextView();
	}

	private void init(AttributeSet attrs){
		res = getContext().getResources();
		normalTextColor = res.getColor(R.color.textcolor_red_d);
		pressedTextColor = res.getColor(R.color.textcolor_white_a);
		this.setGravity(Gravity.CENTER);
		this.setOrientation(LinearLayout.HORIZONTAL);
		this.setFocusable(true);
		this.setFocusableInTouchMode(true);
		TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.ImageTextView);
		normalBack = ta.getResourceId(R.styleable.ImageTextView_background_normals, -1);
		pressedBack = ta.getResourceId(R.styleable.ImageTextView_background_selects, -1);
		normalImg = ta.getResourceId(R.styleable.ImageTextView_image_normal, -1);
		pressImg = ta.getResourceId(R.styleable.ImageTextView_image_select, -1);
		imageWidth = ta.getDimensionPixelSize(R.styleable.ImageTextView_image_width, LayoutParams.WRAP_CONTENT);
		imageHeight = ta.getDimensionPixelSize(R.styleable.ImageTextView_image_height, LayoutParams.WRAP_CONTENT);
		ta.recycle();
		this.setOnTouchListener(this);
		if(normalBack != -1){
			setBackgroundResource(normalBack);
		}
	}
	
	private void addImg(){
		imageView = new ImageView(getContext());
		imageView.setFocusable(false);
		imageView.setLayoutParams(new LayoutParams(imageWidth,imageHeight));
		if(normalImg != -1){
			imageView.setBackgroundResource(normalImg);
		}
		this.addView(imageView);
	}
	
	private void addTextView(){
		textView = new TextView(getContext());
		LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		params.leftMargin = res.getDimensionPixelSize(R.dimen.mine_margin);
		textView.setTextColor(normalTextColor);
		textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, res.getDimensionPixelSize(R.dimen.title_text_size_02));
		textView.setText(res.getString(R.string.select));
		textView.setSingleLine(true);
		textView.setEllipsize(TruncateAt.END);
		textView.setFocusable(false);
		this.addView(textView);
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if(pressedBack != -1){
				setBackgroundResource(pressedBack);
			}
			if(pressImg != -1){
				imageView.setBackgroundResource(pressImg);
			}
			textView.setTextColor(pressedTextColor);
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
		if(normalBack != -1){
			setBackgroundResource(normalBack);
		}
		if(normalImg != -1){
			imageView.setBackgroundResource(normalImg);
		}
		textView.setTextColor(normalTextColor);
	}
	
	public void setViewClickListener(ViewClickListener listener){
		this.mListener = listener;
	}
}
