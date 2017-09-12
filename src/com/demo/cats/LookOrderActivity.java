package com.demo.cats;

import java.util.ArrayList;
import java.util.List;

import com.cats.db.All;
import com.cats.db.Cinema;
import com.cats.db.Movie;
import com.cats.db.MyApplication;
import com.cats.db.MyDatabaseHelper;
import com.cats.db.Orders;
import com.demo.cats.MovieCinemaActivity.ViewHolder;
import com.demo.cats.adapter.MyAdapter;

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
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class LookOrderActivity extends Activity{
	ImageView back;
	private ListView listview;
	SQLiteDatabase db;
	List<Orders> orderList; 
	MyAdapter myAdapter;  
	private MyDatabaseHelper dbHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_list_layout);
		MyApplication.getInstance().addActivity(this);
		
		orderList = new ArrayList<Orders>();  

		 dbHelper=new MyDatabaseHelper(this,"CatsEye.db", null, 1);
		 db = dbHelper.getWritableDatabase();
		//查询数据
	        listview = (ListView)findViewById(R.id.list_order);
	        Query();
	        // 创建MyAdapter实例  
	        myAdapter = new MyAdapter(this);  
	        // 向listview中添加Adapter  
	        listview.setAdapter(myAdapter);
	        
	        
		
		back=(ImageView)findViewById(R.id.moveback);
		back.setOnClickListener(new OnClickListener() {
			final All page = (All)getApplication();
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				page.setPage(4);
				Intent intent = new Intent(LookOrderActivity.this,CatsActivity.class);
		    	startActivity(intent); 
				finish();
				
			}
		});
		
		
	}
	private void Query() {
		// TODO Auto-generated method stub
		 Cursor cursor = db.query("orders", null, null, null, null, null, null);  
		 final All page = (All)getApplication();
	        while (cursor.moveToNext()) {  
	        	if(cursor.getString(0).equals(page.getLoginusername()))
	        	{
	        		String moviename = cursor.getString(1)+" "+cursor.getString(7)+"张";  
	        		String cinema = cursor.getString(2);
	        		String time =cursor.getString(3);
	        		String seat =cursor.getString(5);
	        		String price=cursor.getString(6)+"元";
	        		
	        		Orders or = new Orders(cinema,moviename,time,seat,price);  
	        		orderList.add(or);  
	        	}
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
            return orderList.size();  
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
            // 从orderList取出order  
        	Orders p = orderList.get(position);  
            ViewHolder viewHolder = null;  
            if (convertView == null) {  
                viewHolder = new ViewHolder();  
                convertView = inflater.inflate(R.layout.list_order, null);  
                viewHolder.txt_cinema = (TextView) convertView  
                        .findViewById(R.id.ordercinema);  
                viewHolder.txt_movie = (TextView) convertView  
                        .findViewById(R.id.ordermovie);  
                viewHolder.txt_price = (TextView) convertView  
                        .findViewById(R.id.orderprice);  
                viewHolder.txt_time = (TextView) convertView  
                        .findViewById(R.id.ordertime);  
                viewHolder.txt_seat = (TextView) convertView  
                        .findViewById(R.id.orderseat);       
                convertView.setTag(viewHolder);  
            } else {  
                viewHolder = (ViewHolder) convertView.getTag();  
            }  
            //向TextView中插入数据  
        
            viewHolder.txt_cinema.setText(p.getOrdercinema());  
            viewHolder.txt_movie.setText(p.getOrdermovie());    
            viewHolder.txt_price.setText(p.getOrderprice());  
            viewHolder.txt_time.setText(p.getOrdertime());    
            viewHolder.txt_seat.setText(p.getOrderseat());                
            return convertView;  
        }  
    }  
  
    class ViewHolder {  
        private TextView txt_cinema;
        private TextView txt_movie;
        private TextView txt_price;
        private TextView txt_time;
        private TextView txt_seat;
    }  
}
