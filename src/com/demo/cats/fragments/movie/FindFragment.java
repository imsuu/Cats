package com.demo.cats.fragments.movie;
import java.util.ArrayList;
import java.util.List;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.cats.db.All;
import com.cats.db.Movie;
import com.cats.db.MyDatabaseHelper;
import com.cats.weights.baseWeights.BaseFragment;
import com.cats.weights.updateView.UpdateListView;
import com.cats.weights.updateView.UpdateListView.UpdateDataListener;
import com.cats.weights.updateView.UpdateListView.UpdateViewState;
import com.demo.cats.R;
import com.demo.cats.R.id;
import com.demo.cats.adapter.MyAdapter;



public class FindFragment extends BaseFragment{

	private ListView listview;
	SQLiteDatabase db;
	List<Movie> movieList; 
	MyAdapter myAdapter;  
	private MyDatabaseHelper dbHelper;
	
	@Override
	public void setContentView(ViewGroup container) {
		// TODO Auto-generated method stub
		rootView = mInflater.inflate(R.layout.fragment_movie_find_layout, container,false);
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void bindData() {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
		 View cview=inflater.inflate(R.layout.fragment_movie_find_layout, null); 
		 movieList = new ArrayList<Movie>();  

		 dbHelper=new MyDatabaseHelper(getActivity(),"CatsEye.db", null, 1);
		 db = dbHelper.getWritableDatabase();
		//查询数据
	        listview = (ListView) cview.findViewById(R.id.find_listview);
	        Query();

	        // 创建MyAdapter实例  
	        myAdapter = new MyAdapter(getActivity(),R.layout.fragment_movie_wait_item_layout,movieList);  
	        // 向listview中添加Adapter  
	        listview.setAdapter(myAdapter); 
	        
	        
	        listview.setOnItemClickListener(new OnItemClickListener(){  
	        	  
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					Movie movie=movieList.get(position);
					
					Intent intent = new Intent(getActivity(),com.demo.cats.MovieDetailsActivity.class);
					final All mymovie = (All)getActivity().getApplication();
					mymovie.setMymovie(movie.getNamemovie());
				//	Toast.makeText(getActivity(),movie.getNamemovie(), Toast.LENGTH_SHORT).show();

			    	startActivity(intent);  
				}  
	        });  

		return cview;
		
	}
	
	
	private void Query() {
		// TODO Auto-generated method stub
		Cursor cursor = db.query("movie", null, null, null, null, null, null);  
        while (cursor.moveToNext()) {  
            String moviename = cursor.getString(0);  
            String moviejianjie = cursor.getString(10);
            Movie movie = new Movie(moviename,R.drawable.poster,moviejianjie);  
	//		 Toast.makeText(getActivity(), moviejianjie, Toast.LENGTH_SHORT).show();
            	movieList.add(movie);  
        }
        cursor.close();
	}

}
