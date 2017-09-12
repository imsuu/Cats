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

public class MovieCinemaActivity extends Activity{

	public ImageView imgBack;
	public TextView moviename;
	public TextView mytime;
	private ListView listview;
	SQLiteDatabase db;
	List<Cinema> cinemaList; 
	MyAdapter myAdapter;  
	private MyDatabaseHelper dbHelper;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		MyApplication.getInstance().addActivity(this);
		setContentView(R.layout.movie_cinema_layout);
		imgBack=(ImageView)findViewById(R.id.movieback);
		imgBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		mytime=(TextView)findViewById(R.id.ctime);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
		String date=sdf.format(new java.util.Date());  
		mytime.setText(date);
		final All page = (All)getApplication();
		moviename=(TextView)findViewById(R.id.cmoviename);
		moviename.setText(page.getMymovie());
		
		 cinemaList = new ArrayList<Cinema>();  
	//	 Toast.makeText(getActivity(), "asdasda", Toast.LENGTH_SHORT).show();
		 dbHelper=new MyDatabaseHelper(this,"CatsEye.db", null, 1);
		 db = dbHelper.getWritableDatabase();
			//查询数据
		        listview = (ListView)findViewById(R.id.list_cinema2);
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
						Cinema cinema=cinemaList.get(position);
						Intent intent = new Intent(MovieCinemaActivity.this,com.demo.cats.GoOrder.class);
						final All mycinema = (All)getApplication();
//						Toast.makeText(getActivity(), cinema.getAddresscinema(), Toast.LENGTH_SHORT).show();
		                mycinema.setMycinemaaddress(cinema.getAddresscinema());
		                mycinema.setMycinemaname(cinema.getNamecinema());
		                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		                String date=sdf.format(new java.util.Date());  
		                mycinema.setMytime(date);
				    	startActivity(intent);  
					}  
		        });  
		        
	}

	
	
	
	
	
	private void Query() {
		// TODO Auto-generated method stub
		 Cursor cursor = db.query("cinema", null, null, null, null, null, null);  
	        while (cursor.moveToNext()) {  
	            String cinemaname = cursor.getString(0);  
	            String cinemaaddress = cursor.getString(1);  
	            Cinema cinema = new Cinema(cinemaname,cinemaaddress);  
	            cinemaList.add(cinema);  
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
            return cinemaList.size();  
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
            // 从cinemaList取出Cinema  
            Cinema p = cinemaList.get(position);  
            ViewHolder viewHolder = null;  
            if (convertView == null) {  
                viewHolder = new ViewHolder();  
                convertView = inflater.inflate(R.layout.list_cinema, null);  
                viewHolder.txt_name = (TextView) convertView  
                        .findViewById(R.id.listcinemaname);  
                viewHolder.txt_address = (TextView) convertView  
                        .findViewById(R.id.listcinemaaddress);  
     
                convertView.setTag(viewHolder);  
            } else {  
                viewHolder = (ViewHolder) convertView.getTag();  
            }  
            //向TextView中插入数据  
            viewHolder.txt_name.setText(p.getNamecinema());  
            viewHolder.txt_address.setText(p.getAddresscinema());    
  
            return convertView;  
        }  
    }  
  
    class ViewHolder {  
        private TextView txt_name;  
        private TextView txt_address;  
    }  
	
	
	
	
}
