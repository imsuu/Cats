package com.demo.cats.fragments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.cats.db.*;
import com.cats.weights.baseWeights.BaseFragment;
import com.demo.cats.CinemaMovieActivity;
import com.demo.cats.R;


public class CinemaFragment extends BaseFragment{


	private int pressedTextColor;
	private ListView listview;
	SQLiteDatabase db;
	List<Cinema> cinemaList; 
	MyAdapter myAdapter;  
	private MyDatabaseHelper dbHelper;
	@Override
	public void setContentView(ViewGroup container) {
		Resources res = getActivity().getResources();
		pressedTextColor = res.getColor(R.color.textcolor_white_a);
		rootView = mInflater.inflate(R.layout.fragment_cinema_layout, container, false);
	}

	@Override
	public void initView() {

	}

	@Override
	public void bindData() {
		
	}

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
		 View cview=inflater.inflate(R.layout.fragment_cinema_layout, null); 
		 cinemaList = new ArrayList<Cinema>();  
	//	 Toast.makeText(getActivity(), "asdasda", Toast.LENGTH_SHORT).show();
		 dbHelper=new MyDatabaseHelper(getActivity(),"CatsEye.db", null, 1);
		 db = dbHelper.getWritableDatabase();
		//查询数据
	        listview = (ListView) cview.findViewById(R.id.list_cinema);
	        Query();
	        
	        // 创建MyAdapter实例  
	        myAdapter = new MyAdapter(getActivity());  
	        // 向listview中添加Adapter  
	        listview.setAdapter(myAdapter); 
	        
	        
	        listview.setOnItemClickListener(new OnItemClickListener(){  
	        	  
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					Cinema cinema=cinemaList.get(position);
//					Toast.makeText(getActivity(), cinema.getNamecinema(), Toast.LENGTH_SHORT).show();
					
					Intent intent = new Intent(getActivity(),com.demo.cats.CinemaMovieActivity.class);
					final All mycinema = (All)getActivity().getApplication();
//					Toast.makeText(getActivity(), cinema.getAddresscinema(), Toast.LENGTH_SHORT).show();
	                mycinema.setMycinemaaddress(cinema.getAddresscinema());
	                mycinema.setMycinemaname(cinema.getNamecinema());
			    	startActivity(intent);  
				}  
	        });  
//	        CinemaAdapter adapter=new CinemaAdapter(getActivity(), R.layout.list_cinema, cinemalist);
//	        listview.setAdapter(adapter);

//	        listview.setOnItemClickListener(new OnItemClickListener() {
//
//				@Override
//				public void onItemClick(AdapterView<?> parent, View view,
//						int position, long id) {
//					// TODO Auto-generated method stub
////					int p=position;
////					Toast.makeText(getActivity(), p, Toast.LENGTH_SHORT).show();
//
//					Cinema mycinema=cinemalist.get(position);
//					Toast.makeText(getActivity(), mycinema.getNamecinema(), Toast.LENGTH_SHORT).show();
//				}
//	        	
//			});
	        
		return cview;
		
	}
	
	private void Query()
	{
        
	    // 查询数据  
	        Cursor cursor = db.query("cinema", null, null, null, null, null, null);  
	        while (cursor.moveToNext()) {  
	            String cinemaname = cursor.getString(0);  
	            String cinemaaddress = cursor.getString(1);  
	            Cinema cinema = new Cinema(cinemaname,cinemaaddress);  
	            cinemaList.add(cinema);  
	        }  

		
//        try {
//			dbHelper=new MyDatabaseHelper(getActivity(), "CatsEye.db", null, 1);
//    		SQLiteDatabase db = dbHelper.getWritableDatabase();
//    		//游标查询每条数据
//    		Cursor cursor = db.query("cinema", null, null, null, null, null, null);
//    		//定义list存储数据
//    		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//    		//适配器SimpleAdapter数据绑定
//    		//错误:构造函数SimpleAdapter未定义 需把this修改为MainActivity.this
//    		
//    		SimpleAdapter adapter = new SimpleAdapter(getActivity(), list, R.layout.list_cinema,
//    				new String[]{"cinemaname", "cinemaaddress"}, 
//    				new int[]{R.id.listcinemaname, R.id.listcinemaaddress});
//    		//读取数据 游标移动到下一行
//    		
//    		while(cursor.moveToNext()) {
//    			Map<String, Object> map = new HashMap<String, Object>();
//    			map.put("cinemaname","\t\t\t\t"+ cursor.getString(cursor.getColumnIndex("cinemaname")) );
//    			map.put("cinemaaddress","\t\t\t\t"+ cursor.getString(cursor.getColumnIndex("cinemaaddress")) );
//    			list.add(map);
//    		}
//    		listview.setAdapter(adapter);
//		}
//		catch (Exception e){
//			Log.i("exception", e.toString());
//		}
//        Toast.makeText(getActivity(), "aaaaaaaa", Toast.LENGTH_SHORT).show();
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
