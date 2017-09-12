package com.demo.cats;

import com.cats.db.All;
import com.cats.db.MyApplication;
import com.cats.db.MyDatabaseHelper;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class RegisterActivity extends Activity{
	

	private Button goLogin;
	private Button sregister;
	private	EditText pnum;
	private	EditText psw;
	private	EditText agapsw;
	private MyDatabaseHelper dbHelper;
	

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cats_register);
		goLogin=(Button) findViewById(R.id.goLogin);
		MyApplication.getInstance().addActivity(this);
		dbHelper=new MyDatabaseHelper(this, "CatsEye.db", null, 1);
		goLogin.setOnClickListener(new OnClickListener()
		 {
			
			    @Override   
			public void onClick(View v)
			    {//dosth…}
	                //创建组件，通过组件来响应
			    	/*Toast toast=Toast.makeText(getApplicationContext(), "默认的Toast", Toast.LENGTH_SHORT);
					toast.show(); */
			    	Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
			    	startActivity(intent);   
			    	finish();
			    }
			    
		 });
		sregister=(Button) findViewById(R.id.btnRe);
		pnum=(EditText)findViewById(R.id.zcphonenum);
		psw=(EditText)findViewById(R.id.zcpassword);
		agapsw=(EditText)findViewById(R.id.againpassword);
		
		sregister.setOnClickListener(new OnClickListener() {
//			Toast toast=Toast.makeText(getApplicationContext(), "默认的Toast", Toast.LENGTH_SHORT);
//			toast.show();
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String getpnum=pnum.getText().toString();
		    	String getpsw=psw.getText().toString();
		    	String getagapsw=agapsw.getText().toString();
		    	SQLiteDatabase db=dbHelper.getWritableDatabase();
		    	if(getpnum.length()==0)
		    	{
		    		Toast.makeText(RegisterActivity.this, "请输入手机号码！", Toast.LENGTH_SHORT).show();
		    		return;
		    	}
		    	
		    	if(getpsw.length()==0)
		    	{
		    		Toast.makeText(RegisterActivity.this, "请输入密码！", Toast.LENGTH_SHORT).show();
		    		return;
		    	}
		    	
		    	if(!getagapsw.equals(getpsw))
		    	{
		    		
		    		Toast.makeText(RegisterActivity.this, "两次密码不相同！", Toast.LENGTH_SHORT).show();
//		    		Toast.makeText(RegisterActivity.this, getpsw, Toast.LENGTH_SHORT).show();
//		    		Toast.makeText(RegisterActivity.this, getagapsw, Toast.LENGTH_SHORT).show();
		    		agapsw.setText("");
		    		psw.setText("");
		    		return;
		    	}
		    	
//		    	 String sql1 = "select * from user";
//		         Cursor c = db.rawQuery(sql1, null);
//		         String Str = null;
//		         while (c.moveToNext()) {
//		             //第一列为id
//		        	 Str =  c.getString(1); //获取第2列的值,第一列的索引从0
//		            
//		         }
//		         
		         if(reg(getpnum))
		         {
		                Toast.makeText(RegisterActivity.this, "已存在此用户", Toast.LENGTH_SHORT).show(); 
		                return;
		         }
		    	
//		    	select * form user where user_name=
//		    	ContentValues values=new ContentValues();
//		    	values.put("phonenum",getpnum);
//		    	values.put("password",getpsw);
//		    	db.insert("user", null, values);
//		    	values.clear();
		    	
		    	db.execSQL("insert into user (phonenum, password) values(?, ?)",
		    			new String[] { getpnum,	getpsw });
		    	final All app = (All)getApplication();
		    	app.setLoginusername(getpnum);
		    	app.setIslogin(1);
		    	app.setPage(4);
		    	db.close(); 
		    	if(app.getWherecome()==1){
		    		finish();
		    	}
		    	else{
		    	Intent intent = new Intent(RegisterActivity.this,CatsActivity.class);
		    	startActivity(intent); 
		    	finish();
		    	}
		    	

			}
		});
		
	/*	sregister.setOnClickListener(new OnClickListener()
		 {
			
			
//			Toast toast=Toast.makeText(getApplicationContext(), "默认的Toast", Toast.LENGTH_SHORT);
//			toast.show();
			    @Override 
			public void onClick(View v)
			    {
	                //创建组件，通过组件来响应
			    	Toast toast=Toast.makeText(getApplicationContext(), "默认的Toast", Toast.LENGTH_SHORT);
					toast.show(); 
			    	String getpnum=pnum.getText().toString();
			    	String getpsw=psw.getText().toString();
			    	String getagapsw=agapsw.getText().toString();
			    	if(getpnum==null)
			    	{
//			    		Toast toast=Toast.makeText(getApplicationContext(), "默认的Toast", Toast.LENGTH_SHORT);
//						toast.show();
			    	}
			    	SQLiteDatabase db=dbHelper.getReadableDatabase();
			    	db.execSQL("insert into user (phoennum, password) values(?, ?)",
			    			new String[] { getpnum,	getpsw });
			    	
			    }
			    
		 });*/
	}
	
    public boolean reg(String username){  
    	dbHelper=new MyDatabaseHelper(this, "CatsEye.db", null, 1);
    	SQLiteDatabase db=dbHelper.getWritableDatabase();
        String sql="select * from user where phonenum=?";  
        Cursor cursor=db.rawQuery(sql, new String[]{username});         
        if(cursor.moveToFirst()==true){  
            cursor.close();  
            return true;  
        }  
        return false;  
    } 
}
