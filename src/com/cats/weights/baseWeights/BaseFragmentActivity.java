package com.cats.weights.baseWeights;

import com.cats.db.MyApplication;
import com.cats.db.MyDatabaseHelper;
import com.cats.utils.ActivityUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public abstract class BaseFragmentActivity extends FragmentActivity{
	
	private MyDatabaseHelper dbHelper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MyApplication.getInstance().addActivity(this);
		ActivityUtil.getInstance().addActivity(this);
		bindData();
		setContentView();
		initView();
		setOnClickListener();
		dbHelper=new MyDatabaseHelper(this,"CatsEye.db", null, 1);
		 dbHelper.getWritableDatabase();
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
	public void bindData(){};
	/**
	 *设置监听事件 
	 */
	public void setOnClickListener(){};
}
