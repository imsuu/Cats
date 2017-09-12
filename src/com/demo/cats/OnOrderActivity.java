package com.demo.cats;

import com.cats.db.All;
import com.cats.db.MyApplication;
import com.cats.db.MyDatabaseHelper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class OnOrderActivity extends Activity{
	
	TextView orderMovie;
	TextView orderCinema;
	TextView orderTime;
	TextView orderSeat;
	TextView orderPrice;
	ImageView back;
	Button doBtn;
	int a;
	public boolean isoncl=true;

	private MyDatabaseHelper dbHelper;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		MyApplication.getInstance().addActivity(this);
		setContentView(R.layout.on_order_layout);
		final All page = (All)getApplication();
		dbHelper=new MyDatabaseHelper(this, "CatsEye.db", null, 1);
		isoncl=true;
		orderMovie=(TextView)findViewById(R.id.ordermoviename);
		orderCinema=(TextView)findViewById(R.id.ordercinema);
		orderSeat=(TextView)findViewById(R.id.orderseat);
		orderTime=(TextView)findViewById(R.id.orderdatetime);
		orderPrice=(TextView)findViewById(R.id.orderprice);
		back=(ImageView)findViewById(R.id.moveback);
		doBtn=(Button)findViewById(R.id.doorder);
		
		orderMovie.setText(page.getMymovie());
		orderCinema.setText(page.getMycinemaname()+"  "+page.getMyhall());
		orderSeat.setText(page.getMyseat());
		orderTime.setText(page.getMydatetime());
		orderPrice.setText(Integer.toString(page.getMyprice())+"元");
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		doBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(isoncl){
				String pnum=page.getLoginusername();
				String moviena=page.getMymovie();
				String cineman=page.getMycinemaname();
				String dtime=page.getMydatetime();
				String hal=page.getMyhall();
				String seat=page.getMyseat();
				int pri=page.getMyprice();
				int cou=page.getCount();
				
				SQLiteDatabase db=dbHelper.getWritableDatabase();
				db.execSQL("insert into orders(phonenum,moviename,cinemaname,datatime,hall,seat,price,zhangshu)values(?,?,?,?,?,?,?,?)",
		    			new Object[]{pnum,moviena,cineman,dtime,hal,seat,pri,cou});
				isoncl=false;
				int a=dodialog();
				db.close();
				if(a==2)
					finish();
				}
				else{
					Toast.makeText(OnOrderActivity.this, "已经提交过订单", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		
	}
	private int dodialog() {
		// TODO Auto-generated method stub
	    /*普通的对话框*/  
	        AlertDialog.Builder normalDia=new AlertDialog.Builder(OnOrderActivity.this);  
	        normalDia.setIcon(R.drawable.ic_maoyan);  
	        normalDia.setTitle("购买成功");  
	        normalDia.setMessage("购买电影票成功，是否查看我的订单？");  
	        normalDia.setNegativeButton("取消", new DialogInterface.OnClickListener() {  
	            @Override  
	            public void onClick(DialogInterface dialog, int which) {  
	                // TODO Auto-generated method stub  
	        //        showClickMessage("取消");  
	            	a=1;
	            }
	        });  
	        normalDia.setPositiveButton("确定", new DialogInterface.OnClickListener() {  
	            @Override  
	            public void onClick(DialogInterface dialog, int which) {  
	                // TODO Auto-generated method stub  
	          //      showClickMessage("确定");
	                Intent intent = new Intent(OnOrderActivity.this,LookOrderActivity.class);
			    	startActivity(intent); 
			    	a=2;
			    	finish();
	            }  
	        });  

	        normalDia.create().show();  
	        return a;
	}
}
