package com.demo.cats.fragments.movie;
import java.util.ArrayList;
import java.util.List;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.cats.db.All;
import com.cats.db.Movie;
import com.cats.db.MyDatabaseHelper;
import com.cats.weights.baseWeights.BaseFragment;
import com.demo.cats.R;
import com.demo.cats.R.id;




public class HotFragment extends BaseFragment{

	private ListView listview;
	SQLiteDatabase db;
	List<Movie> movieList; 
	MyBtnAdapter myAdapter;  
	Button btnbuy;
	private MyDatabaseHelper dbHelper;
	
	@Override
	public void setContentView(ViewGroup container) {
		// TODO Auto-generated method stub
		rootView = mInflater.inflate(R.layout.fragment_movie_hot_layout, container,false);
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
		 View cview=inflater.inflate(R.layout.fragment_movie_hot_layout, null); 
		 movieList = new ArrayList<Movie>();  

		 dbHelper=new MyDatabaseHelper(getActivity(),"CatsEye.db", null, 1);
		 db = dbHelper.getWritableDatabase();
		//查询数据
	        listview = (ListView) cview.findViewById(R.id.update_listview);
	        Query();

	        // 创建MyAdapter实例  
	        myAdapter = new MyBtnAdapter(getActivity(),R.layout.fragment_movie_hot_item_layout,movieList);  
	        // 向listview中添加Adapter  
	        listview.setAdapter(myAdapter); 
	        
	        
	        listview.setOnItemClickListener(new OnItemClickListener(){  
	        	  
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					Movie movie=movieList.get(position);
//					Toast.makeText(getActivity(), movie.getJianjie(), Toast.LENGTH_SHORT).show();
					
					Intent intent = new Intent(getActivity(),com.demo.cats.MovieDetailsActivity.class);
					final All mymovie = (All)getActivity().getApplication();
					mymovie.setMymovie(movie.getNamemovie());
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
            if(cursor.getInt(11)==1)
            	movieList.add(movie);  
        }
        cursor.close();
	}

	//创建MyAdapter继承BaseAdapter  
	public class MyBtnAdapter extends ArrayAdapter<Movie>{  
	  private Context context;  
	  private LayoutInflater inflater;  
	  private int resourceId;

	  public MyBtnAdapter(Context context,int textViewResourceId,List<Movie> objects) {  
	      super(context,textViewResourceId,objects);
	      resourceId = textViewResourceId;
	  }  



	  @Override  
	  public long getItemId(int position) {  
	      // TODO Auto-generated method stub  
	      return 0;  
	  }  

	  @Override  
	  public View getView(final int position, View convertView, ViewGroup parent) {  
	      Movie p = getItem(position);  
	      ViewHolder2 viewHolder;  
//	      MyListener myListener=null;
	      View view;
	      if (convertView == null) {  
	          viewHolder = new ViewHolder2();  
	          view=LayoutInflater.from(getContext()).inflate(resourceId, null);
	          viewHolder.txt_name=(TextView)view.findViewById(R.id.itemmoviename);
	          viewHolder.txt_jianjie=(TextView)view.findViewById(R.id.itemmoviejj);
	          viewHolder.img=(ImageView)view.findViewById(R.id.cinemaimg);
	          viewHolder.btn_buy=(Button)view.findViewById(R.id.itembuy);
	          view.setTag(viewHolder);
	      } else {  
	      	view=convertView;
	          viewHolder = (ViewHolder2) view.getTag();  
	      }  
	      viewHolder.img.setImageResource(p.getPostermovie());
	      viewHolder.txt_jianjie.setText(p.getJianjie());
	      viewHolder.txt_name.setText(p.getNamemovie());
	      
	      viewHolder.btn_buy.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Movie movie=movieList.get(position);
				Toast.makeText(getActivity(), movie.getJianjie(), Toast.LENGTH_SHORT).show();
				
				Intent intent = new Intent(getActivity(),com.demo.cats.MovieCinemaActivity.class);
				final All mymovie = (All)getActivity().getApplication();
				mymovie.setMymovie(movie.getNamemovie());
		    	startActivity(intent);  
			}
		});
	      
	        
	      return view;  
	  }    

	class ViewHolder2 {  
	  TextView txt_name;  
	  ImageView img;
	  TextView txt_jianjie;  
	  Button btn_buy;
	}


	}
}








//public class HotFragment extends BaseFragment implements UpdateDataListener{
//
//	private HotAdapter mAdapter;
//	private UpdateListView mListView;
//	private ListView listview;
//	private List<String> datas = new ArrayList<String>();
//	private List<Movie> movieList = new ArrayList<Movie>();
//	SQLiteDatabase db;
//	private MyDatabaseHelper dbHelper;
//	private static final int REFRESH_OK = 0;
//	private static final int LOAD_OK = 1;
//	
//	@Override
//	public void setContentView(ViewGroup container) {
//		rootView = mInflater.inflate(R.layout.fragment_movie_hot_layout, container,false);
//	}
//
//	@Override
//	public void initView() {
//		mListView = (UpdateListView) rootView.findViewById(R.id.update_listview);
//		mAdapter = new HotAdapter(mActivity, datas, R.layout.fragment_movie_hot_item_layout) {};
//		mListView.setAdapter(mAdapter);
//		mListView.setOnUpdateListener(this);
//	}
//
//	@Override
//	public void bindData() {
//		
//		if(!isDestroyView){
////			for(int i = 0;i<5;i++){
////				datas.add("测试数据:"+i);
////			}
//			
//			 dbHelper=new MyDatabaseHelper(getActivity(),"CatsEye.db", null, 1);
//			 db = dbHelper.getWritableDatabase();
//			 Query();
//			
//		}
//		mAdapter.notifyDataSetChanged();
//	}
//	
//
//	private void Query() {
//		// TODO Auto-generated method stub
//		// 查询数据  
//        Cursor cursor = db.query("movie", null, null, null, null, null, null);  
//        while (cursor.moveToNext()) {  
//            String moviename = cursor.getString(0);
//            String movieposter =cursor.getString(1);
//            String jianjie=cursor.getString(10);
//            Movie movie=new Movie(moviename,movieposter,jianjie);
//            movieList.add(movie);
//            String str=movieList.get(0).getJianjie();
//          Toast.makeText(getActivity(),str, Toast.LENGTH_SHORT).show();
//            datas.add(moviename);  
//        }  
//	}
//
//	@Override
//	public void refreshing() {
//		new Thread() {
//
//			@Override
//			public void run() {
//				try {
//					sleep(3000);
//					mHandler.sendEmptyMessage(REFRESH_OK);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//
//		}.start();
//	}
//
//	@Override
//	public void loading() {
//		
//	}
//	
//	@SuppressLint("HandlerLeak")
//	private Handler mHandler = new Handler(){
//		public void handleMessage(android.os.Message msg) {
//			switch (msg.what) {
//			case REFRESH_OK:
//				datas.clear();
//				for(int i = 0;i<5;i++){
//					datas.add("I am new refresh data:"+i);
//				}
//				mAdapter.notifyDataSetChanged();
//				mListView.setCurrentHeaderState(UpdateViewState.REFRESH_DONE);
//				mListView.refreshViewByRefreshingState();
//				break;
//			case LOAD_OK:
//				break;
//			}
//		};
//	};
//}
