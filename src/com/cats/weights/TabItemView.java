package com.cats.weights;

import com.cats.utils.PhoneUtils;
import com.demo.cats.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.InflateException;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class TabItemView extends LinearLayout implements OnClickListener{

	private Context mContext;
	
	private ImageView contentLogo;
	private TextView contentText;
	
	private int logoBackResourceId;
	private String textString;
	private int textColor;
	private float textSize;
	private int contentLogoSize;
	private static final float defaultTextSize = 16;
	private int defaultColor,selectedColor;
	private TabClickListner mClickListner;
	
	
	public TabItemView(Context context) {
		this(context, null);
	}
	
	public TabItemView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}
	
	public TabItemView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.mContext = context;
		init(attrs);
		addView();
	}
	
	private void init(AttributeSet attrs){
		this.setOnClickListener(this);
		TypedArray ta = mContext.obtainStyledAttributes(attrs, R.styleable.TabItemView);
		logoBackResourceId = ta.getResourceId(R.styleable.TabItemView_contentLogoBack, -1);
		textColor = ta.getColor(R.styleable.TabItemView_contentTextColor, getResources().getColor(android.R.color.black));
		textSize = ta.getDimensionPixelSize(R.styleable.TabItemView_contentTextSize, PhoneUtils.dp2px(mContext, defaultTextSize));
		textString = ta.getString(R.styleable.TabItemView_contentTextString);
		contentLogoSize = ta.getDimensionPixelSize(R.styleable.TabItemView_contentLogoSize, LayoutParams.WRAP_CONTENT);
		ta.recycle();
		defaultColor = mContext.getResources().getColor(R.color.textcolor_black_b3);
		selectedColor = mContext.getResources().getColor(R.color.textcolor_red_d);
	}
	
	private void addView(){
		contentLogo = new ImageView(mContext);
		contentLogo.setFocusable(false);
		contentLogo.setClickable(false);
		LayoutParams logoParams = new LayoutParams(contentLogoSize,contentLogoSize);
		contentLogo.setLayoutParams(logoParams);
		if(logoBackResourceId != -1){
			contentLogo.setBackgroundResource(logoBackResourceId);
		}else{
			throw new InflateException("未设置填充图片资源");
		}
		
		this.addView(contentLogo);
		
		if(!TextUtils.isEmpty(textString)){
			contentText = new TextView(mContext);
			contentText.setFocusable(false);
			contentText.setClickable(false);
			LayoutParams textParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
			textParams.topMargin = PhoneUtils.dp2px(mContext,3);
			contentText.setLayoutParams(textParams);
			contentText.setTextColor(textColor);
			contentText.setTextSize(TypedValue.COMPLEX_UNIT_PX,textSize);
			contentText.setText(textString);
			this.addView(contentText);
		}
	}

	@Override
	public void onClick(View v) {
		setTabSelected(true);
		if(mClickListner != null){
			mClickListner.onTabClick(this);
		}
	}
	
	/**
	 *设置点击监听事件 
	 */
	public void setTabClickListener(TabClickListner listner){
		this.mClickListner = listner;
	}
	
	/**
	 *设置填充图片资源 
	 */
	public void setContentLogoBack(int resourceId){
		contentLogo.setBackgroundResource(resourceId);
	}
	
	/**
	 *设置填充文字
	 */
	public void setContentTextString(String text){
		if(contentText != null){
			contentText.setText(text);
		}
	}
	
	/**
	 *设置选中状态 
	 */
	public void setTabSelected(boolean enable){
		if(contentLogo != null){
			contentLogo.setSelected(enable);
		}
		if(contentText != null){
			if(enable){
				contentText.setTextColor(selectedColor);
			}else{
				contentText.setTextColor(defaultColor);
			}
		}
	}
	
	public interface TabClickListner{
		void onTabClick(View view);
	}
	
}
