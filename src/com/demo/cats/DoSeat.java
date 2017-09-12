package com.demo.cats;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cats.db.All;
import com.demo.cats.CatsActivity;
import com.demo.cats.GoOrder;
import com.demo.cats.R;
import com.demo.cats.RegisterActivity;
import com.ldm.seatchoosetest.OnSeatClickListener;
import com.ldm.seatchoosetest.view.SSThumView;
import com.ldm.seatchoosetest.view.SSView;

public class DoSeat extends Activity {
	private static final int ROW = 5;
	private static final int EACH_ROW_COUNT =7;
	private SSView mSSView;
	private SSThumView mSSThumView;
	ImageView back;
	int [][]myseat=new int[6][8];
	int cou;
	TextView myMoviename;
	TextView myCinemaname;
	TextView myDatetime;
	Button btnDo;
	
	
	private ArrayList<ArrayList<Integer>> list_seat_conditions = new ArrayList<ArrayList<Integer>>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		for(int i=0;i<6;i++)
			  for(int j=0;j<8;j++)
				  myseat[i][j]=0;
		cou=0;
		final All page = (All)getApplication();
		page.setCount(0);
		page.setMyseat(null);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ArrayList<Integer> object=new ArrayList<Integer>();
		/**
		 * 0过道
		 * 1未选中
		 * 2已售出
		 * 3已选中
		 */
		object.add(0);
		object.add(1);
		object.add(2);
		object.add(1);
		object.add(2);
		object.add(1);
		object.add(0);
		list_seat_conditions.add(object);
		ArrayList<Integer> object1=new ArrayList<Integer>();
		object1.add(0);
		object1.add(1);
		object1.add(1);
		object1.add(1);
		object1.add(2);
		object1.add(1);
		object1.add(0);
		
		list_seat_conditions.add(object1);
		ArrayList<Integer> object2=new ArrayList<Integer>();
		object2.add(1);
		object2.add(1);
		object2.add(2);
		object2.add(1);
		object2.add(1);
		object2.add(1);
		object2.add(2);
		
		list_seat_conditions.add(object2);
		
		ArrayList<Integer> object3=new ArrayList<Integer>();
		object3.add(1);
		object3.add(1);
		object3.add(1);
		object3.add(1);
		object3.add(1);
		object3.add(1);
		object3.add(1);
		
		list_seat_conditions.add(object3);
		
		
		ArrayList<Integer> object4=new ArrayList<Integer>();
		object4.add(1);
		object4.add(1);
		object4.add(2);
		object4.add(1);
		object4.add(1);
		object4.add(1);
		object4.add(2);
		
		list_seat_conditions.add(object4);
		
		
		back=(ImageView)findViewById(R.id.moveback);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		myMoviename=(TextView)findViewById(R.id.mymoviename);
		myCinemaname=(TextView)findViewById(R.id.mycinemaname);
		myDatetime=(TextView)findViewById(R.id.mydatetime);
		myMoviename.setText(page.getMymovie());
		myCinemaname.setText(page.getMycinemaname());
		myDatetime.setText(page.getMytime()+" "+page.getMytimetime());
		page.setMydatetime(page.getMytime()+" "+page.getMytimetime());
		
//		init();
		mSSView = (SSView)this.findViewById(R.id.mSSView);
		mSSThumView = (SSThumView)this.findViewById(R.id.ss_ssthumview);
//		mSSView.setXOffset(20);
	
		mSSView.init(EACH_ROW_COUNT, ROW, list_seat_conditions, mSSThumView, 5);
		mSSView.setOnSeatClickListener(new OnSeatClickListener() {
			
			@Override
			public boolean b(int column_num, int row_num, boolean paramBoolean) {
				String desc =  "您选择了第"+(row_num+1)+"排" + " 第" + (column_num+1) +"列";
				Toast.makeText(DoSeat.this,desc.toString(), Toast.LENGTH_SHORT).show();
				myseat[row_num+1][column_num+1]=1;
				cou=cou+1;
				return false;
			}
			
			@Override
			public boolean a(int column_num, int row_num, boolean paramBoolean) {
				String desc =  "您取消了第"+(row_num+1)+"排" + " 第" + (column_num+1) +"列";
				Toast.makeText(DoSeat.this,desc.toString(), Toast.LENGTH_SHORT).show();
				myseat[row_num+1][column_num+1]=0;
				cou=cou-1;
				return false;
			}
			
			@Override
			public void a() {
				// TODO Auto-generated method stub
				
			}
		});
		
		btnDo=(Button)findViewById(R.id.doseat);
		btnDo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(page.getIslogin()!=1){
					nomaldialog();
				}
				else if(cou==0)
				{
					Toast.makeText(DoSeat.this, "请选择座位", Toast.LENGTH_SHORT).show();
				}
				else{
					int c=0;
					String s;
					page.setCount(0);
					page.setMyseat("");
					for(int i=0;i<6;i++){
						  for(int j=0;j<8;j++){
							  if(myseat[i][j]==1)
							  {
								  c=c+1;
								  page.setCount(c);
								  s=Integer.toString(i)+"排"+Integer.toString(j)+"座    ";
								  page.setMyseat(page.getMyseat()+s);
							  }
						  }
					}
		//			Toast.makeText(getApplication(), "dwa daw", Toast.LENGTH_SHORT).show();
					page.setMyprice(c*page.getMydanjia()); 
					Intent intent = new Intent(DoSeat.this,com.demo.cats.OnOrderActivity.class);
			    	startActivity(intent); 
				}
					
			}


			private void nomaldialog() {
				// TODO Auto-generated method stub
			    /*普通的对话框*/  
			        AlertDialog.Builder normalDia=new AlertDialog.Builder(DoSeat.this);  
			        normalDia.setIcon(R.drawable.ic_maoyan);  
			        normalDia.setTitle("请登录");  
			        normalDia.setMessage("购买电影票需要登录");  
			        normalDia.setNegativeButton("取消", new DialogInterface.OnClickListener() {  
			            @Override  
			            public void onClick(DialogInterface dialog, int which) {  
			                // TODO Auto-generated method stub  
			                showClickMessage("取消");  
			            }
			        });  
			        normalDia.setPositiveButton("确定", new DialogInterface.OnClickListener() {  
			            @Override  
			            public void onClick(DialogInterface dialog, int which) {  
			                // TODO Auto-generated method stub  
			                showClickMessage("确定");
			                page.setWherecome(1);
			                Intent intent = new Intent(DoSeat.this,com.demo.cats.LoginActivity.class);
					    	startActivity(intent); 
			            }  
			        });  

			        normalDia.create().show();  
			}
			

			
            private void showClickMessage(String message)  
            {  
                Toast.makeText(DoSeat.this, "你选择的是: "+message, Toast.LENGTH_SHORT).show();  
            } 
		});
		
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cats, menu);
		return true;
	}

	
}
