package com.demo.cats;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.cats.db.All;
import com.cats.db.Cinema;
import com.cats.db.MyApplication;
import com.cats.db.MyDatabaseHelper;
import com.cats.db.Time;
import com.demo.cats.MovieCinemaActivity.MyAdapter;


public class GoOrder extends Activity{
	
	ImageView imgBack;
	TextView myCinema;
	TextView myCinema1;
	TextView myMovie;
	TextView myAdress;
	String strcinema;
	String strmovie;
	TextView myTime;
	private ListView listview;
	SQLiteDatabase db;
	List<Time> timeList; 
	MyAdapter myAdapter;  
	private MyDatabaseHelper dbHelper;
	
	
	public void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		MyApplication.getInstance().addActivity(this);
		setContentView(R.layout.order_layout);
		
		imgBack = (ImageView)findViewById(R.id.movieback);
		imgBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		final All page = (All)getApplication();
		
		myCinema=(TextView)findViewById(R.id.mycinema);
		myCinema1=(TextView)findViewById(R.id.mycinema1);
		myMovie=(TextView)findViewById(R.id.mymovie);
		myAdress=(TextView)findViewById(R.id.myaddress);
		myTime=(TextView)findViewById(R.id.mytime );
		
		myCinema.setText(page.getMycinemaname());
		myCinema1.setText(page.getMycinemaname());
		myMovie.setText(page.getMymovie());
		myAdress.setText(page.getMycinemaaddress());

		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
		String date=sdf.format(new java.util.Date());  
		myTime.setText(date);
		page.setMytime(date);
		
		 timeList = new ArrayList<Time>();  
			//	 Toast.makeText(getActivity(), "asdasda", Toast.LENGTH_SHORT).show();
				 dbHelper=new MyDatabaseHelper(this,"CatsEye.db", null, 1);
				 db = dbHelper.getWritableDatabase();
					//查询数据
				        listview = (ListView)findViewById(R.id.list_time);
				        Query();
				        // 创建MyAdapter实例  
				        myAdapter = new MyAdapter(this);  
				        // 向listview中添加Adapter  
				        listview.setAdapter(myAdapter);
				        listview.setOnItemClickListener(new OnItemClickListener(){  
				        	  
							@Override
							public void onItemClick(AdapterView<?> parent, View view,
									int position, long id) {
								// TODO Auto-generated method stub
								
								Time mytime=timeList.get(position);
								page.setMyhall(mytime.getHall());
								page.setMytimetime(mytime.getStarttime());
								page.setMydanjia(Integer.parseInt(mytime.getPrice()));
								Intent intent = new Intent(GoOrder.this,DoSeat.class);
//								Toast.makeText(getActivity(), cinema.getAddresscinema(), Toast.LENGTH_SHORT).show();
						    	startActivity(intent);  
							}  
				        });  		        
				        
	}


	private void Query() {
		// TODO Auto-generated method stub
		final All page = (All)getApplication();
		strcinema=page.getMycinemaname();
		strmovie=page.getMymovie();
		SQLiteDatabase db=dbHelper.getWritableDatabase();
        String sql="select * from cinemamovie where cinemaname=? and moviename=?";  
        Cursor cursor=db.rawQuery(sql, new String[]{strcinema,strmovie});  
        while (cursor.moveToNext()) {  		        // 创建MyAdapter实例  
	        myAdapter = new MyAdapter(this);  
	        // 向listview中添加Adapter  
	        listview.setAdapter(myAdapter);
        
        String stime = cursor.getString(2);
        String etime = cursor.getString(3);
        String pri=cursor.getString(4);
        String hal=cursor.getString(5);
        String vie=cursor.getString(6);
 //   	Toast.makeText(getApplicationContext(), stime, Toast.LENGTH_SHORT).show();
        Time mytime = new Time(stime,etime,pri,hal,vie);  
        timeList.add(mytime);  
        }
	}

	
	
	// 创建MyAdapter继承BaseAdapter  
    class MyAdapter extends BaseAdapter {  
        private Context context;  
        private LayoutInflater inflater;  
  
        public MyAdapter(Context context) {  
            this.context = context;  
            inflater = LayoutInflater.from(context);  
        }  
  
        @Override  
        public int getCount() {  
            // TODO Auto-generated method stub  
            return timeList.size();  
        }  
  
        @Override  
        public Object getItem(int position) {  
            // TODO Auto-generated method stub  
            return null;  
        }  
  
        @Override  
        public long getItemId(int position) {  
            // TODO Auto-generated method stub  
            return 0;  
        }  
  
        @Override  
        public View getView(int position, View convertView, ViewGroup parent) {  
            // 从timeList取出time  
            Time p = timeList.get(position);  
            ViewHolder viewHolder = null;  
            if (convertView == null) {  
                viewHolder = new ViewHolder();  
                convertView = inflater.inflate(R.layout.list_time, null);  
                viewHolder.txt_stime = (TextView) convertView  
                        .findViewById(R.id.listsatrttime);  
                viewHolder.txt_etime = (TextView) convertView  
                        .findViewById(R.id.listendtime);  
                viewHolder.txt_type = (TextView) convertView  
                        .findViewById(R.id.listtype);  
                viewHolder.txt_price = (TextView) convertView  
                        .findViewById(R.id.listprice);  
                viewHolder.txt_hall = (TextView) convertView  
                        .findViewById(R.id.listhall);  
                convertView.setTag(viewHolder);  
            } else {  
                viewHolder = (ViewHolder) convertView.getTag();  
            }  
            //向TextView中插入数据  
            viewHolder.txt_stime.setText(p.getStarttime());  
            viewHolder.txt_etime.setText(p.getEndtime());    
            viewHolder.txt_type.setText(p.getType());  
            viewHolder.txt_price.setText(p.getPrice());  
            viewHolder.txt_hall.setText(p.getHall());  
            return convertView;  
        }  
    }  
  
    class ViewHolder {  
        private TextView txt_stime;  
        private TextView txt_etime;
        private TextView txt_type;  
        private TextView txt_price;
        private TextView txt_hall;  
    }  
	
	
	
	
	
}
