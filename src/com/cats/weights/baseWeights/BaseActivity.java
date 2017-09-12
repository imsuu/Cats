package com.cats.weights.baseWeights;

import com.cats.utils.ActivityUtil;
import android.app.Activity;
import android.os.Bundle;

public abstract class BaseActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActivityUtil.getInstance().addActivity(this);
		bindData();
		setContentView();
		initView();
		setOnClickListener();
	}
	
	@Override
	public void onBackPressed() {
		ActivityUtil.getInstance().finishThisActivity(this);
		super.onBackPressed();
	}
	
	/**
	 *设置 ContentView布局
	 */
	public abstract void setContentView();
	/**
	 *初始化控件 
	 */
	public abstract void initView();
	/**
	 *初始化数据源 
	 */
	public abstract void bindData();
	/**
	 *设置监听事件 
	 */
	public void setOnClickListener(){};
}
