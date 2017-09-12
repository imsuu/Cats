package com.demo.cats;

import com.cats.db.All;
import com.cats.db.MyApplication;
import com.cats.db.MyDatabaseHelper;
import com.demo.cats.R;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity{
	
	private Button goRegister;
	private Button goDo;
	private	EditText pnum;
	private	EditText psw;
	private MyDatabaseHelper dbHelper;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cats_login);
		MyApplication.getInstance().addActivity(this);
		pnum=(EditText)findViewById(R.id.phoneNum);
		psw=(EditText)findViewById(R.id.password);
		
		goRegister=(Button) findViewById(R.id.goRegister);
		goRegister.setOnClickListener(new OnClickListener()
		 {
			    @Override   
			public void onClick(View v)
			    {//dosth…}
			    	
	                //创建组件，通过组件来响应
			    	
			    	Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
			    	startActivity(intent);    
			    	finish();
			    }
		 });
		
		goDo=(Button) findViewById(R.id.btnDo);
		goDo.setOnClickListener(new OnClickListener()
		 {
			    @Override   
			public void onClick(View v)
			    {//dosth…}
			    	
	                //创建组件，通过组件来响应
			    	String getpnum=pnum.getText().toString();
			    	String getpsw=psw.getText().toString();
			    	if(getpnum.length()==0)
			    	{
			    		Toast.makeText(LoginActivity.this, "请输入手机号码！", Toast.LENGTH_SHORT).show();
			    		return;
			    	}
			    	
			    	if(getpsw.length()==0)
			    	{
			    		Toast.makeText(LoginActivity.this, "请输入密码！", Toast.LENGTH_SHORT).show();
			    		return;
			    	}
			    	
			    	if(login(getpnum,getpsw))
			    	{
			    		Toast.makeText(LoginActivity.this, "登陆成功！", Toast.LENGTH_SHORT).show();
			    		final All page = (All)getApplication();
			    		
			    		
			    		page.setLoginusername(getpnum);
			    		page.setIslogin(1);
			    		page.setPage(4);
				    	if(page.getWherecome()==1){
				    		finish();
				    	}
				    	else{
				    	Intent intent = new Intent(LoginActivity.this,CatsActivity.class);
				    	startActivity(intent); 
				    	finish();
				    	}
			    		
			    	}
			    	else
			    	{
			    		Toast.makeText(LoginActivity.this, "用户名或密码错误！", Toast.LENGTH_SHORT).show();
			    		psw.setText("");
			    	}
			    }
		 });

	}
	
	
    public boolean login(String username,String password){  
    	dbHelper=new MyDatabaseHelper(this, "CatsEye.db", null, 1);
    	SQLiteDatabase db=dbHelper.getWritableDatabase();
        String sql="select * from user where phonenum=? and password=?";  
        Cursor cursor=db.rawQuery(sql, new String[]{username,password});         
        if(cursor.moveToFirst()==true){  
            cursor.close();  
            return true;  
        }  
        return false;  
    } 
}

