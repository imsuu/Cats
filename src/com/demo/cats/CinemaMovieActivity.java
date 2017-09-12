package com.demo.cats;

import java.util.ArrayList;
import java.util.List;

import com.cats.db.All;
import com.cats.db.Cinema;
import com.cats.db.Movie;
import com.cats.db.MyApplication;
import com.cats.db.MyDatabaseHelper;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class CinemaMovieActivity extends Activity{

	public TextView cinemaname;
	public TextView cinemaaddress;
	private ListView listview;
	SQLiteDatabase db;
	List<Movie> movieList; 
	MyAdapter myAdapter;  
	private MyDatabaseHelper dbHelper;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		MyApplication.getInstance().addActivity(this);
		setContentView(R.layout.cinema_movie_layout);
		final All page = (All)getApplication();
		cinemaname=(TextView)findViewById(R.id.listcinemaname);
		cinemaaddress=(TextView)findViewById(R.id.listcinemaaddress);
		MyApplication.getInstance().addActivity(this);
		cinemaname.setText(page.getMycinemaname());
		cinemaaddress.setText(page.getMycinemaaddress());
		
		
		 movieList = new ArrayList<Movie>();  

		 dbHelper=new MyDatabaseHelper(CinemaMovieActivity.this,"CatsEye.db", null, 1);
		 db = dbHelper.getWritableDatabase();
		//查询数据
	        listview = (ListView)findViewById(R.id.list_movie);
	        Query();

	        // 创建MyAdapter实例  
	        myAdapter = new MyAdapter(CinemaMovieActivity.this);  
	        // 向listview中添加Adapter  
	        listview.setAdapter(myAdapter); 
	        
	        

	        listview.setOnItemClickListener(new OnItemClickListener(){  
	        	  
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					Movie movie=movieList.get(position);
					
					Intent intent = new Intent(CinemaMovieActivity.this,GoOrder.class);
					final All mymovie = (All)getApplication();
					mymovie.setMymovie(movie.getNamemovie());
//					Toast.makeText(CinemaMovieActivity.this,movie.getNamemovie(), Toast.LENGTH_SHORT).show();

			    	startActivity(intent);  
				}  
	        });  
	        
	        
	}
	
	
	
	private void Query() {
		// TODO Auto-generated method stub
		Cursor cursor = db.query("movie", null, null, null, null, null, null);  
        while (cursor.moveToNext()) {  
            String moviename = cursor.getString(0);
            String m="";
            Movie movie = new Movie(moviename,R.drawable.poster,m);  
            if(cursor.getInt(11)==1)
            	movieList.add(movie);  
        }
        cursor.close();
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
            return movieList.size();  
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
            Movie p = movieList.get(position);  
            ViewHolder viewHolder = null;  
            if (convertView == null) {  
                viewHolder = new ViewHolder();  
                convertView = inflater.inflate(R.layout.list_movie, null);  
                viewHolder.txt_name = (TextView) convertView  
                        .findViewById(R.id.cimoviename);  
                viewHolder.txt_img = (ImageView) convertView  
                        .findViewById(R.id.cimoviepo);  
     
                convertView.setTag(viewHolder);  
            } else {  
                viewHolder = (ViewHolder) convertView.getTag();  
            }  
            //向TextView中插入数据  
            viewHolder.txt_name.setText(p.getNamemovie());  
  
            return convertView;  
        }  
    }  
  
    class ViewHolder {  
        private TextView txt_name;  
        private ImageView txt_img;  
    }  

}
