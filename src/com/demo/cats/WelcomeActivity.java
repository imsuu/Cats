package com.demo.cats;

import com.cats.db.MyApplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


public class WelcomeActivity extends Activity{
	
	private final long SPLASH_LENGTH = 2000;  
        Handler handler = new Handler();
        public void onCreate(Bundle savedInstanceState) {
        	super.onCreate(savedInstanceState);
        	setContentView(R.layout.welcome_layout);
        	MyApplication.getInstance().addActivity(this);
        	handler.postDelayed(new Runnable() {  //使用handler的postDelayed实现延时跳转
        	
        		public void run() {  
        			Intent intent = new Intent(WelcomeActivity.this, CatsActivity.class);  
        			startActivity(intent);  
        			finish();     
        		}  
        	}, SPLASH_LENGTH);//2秒后跳转至应用主界面MainActivity
        
        }
}