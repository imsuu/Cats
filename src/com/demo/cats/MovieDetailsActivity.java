package com.demo.cats;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;
import com.cats.db.All;
import com.cats.db.Movie;
import com.cats.db.MyApplication;
import com.cats.db.MyDatabaseHelper;

public class MovieDetailsActivity extends Activity{

	public TextView movieName;
	public TextView movieEnglish;
	public TextView movieType;
	public TextView movieduration;
	public TextView movieTime;
	public TextView moviejj;
	public ImageView imgBack;
	public Button buttonBuy;
	public MyDatabaseHelper dbHelper;
	SQLiteDatabase db;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		MyApplication.getInstance().addActivity(this);
		setContentView(R.layout.movie_details_layout);
		imgBack=(ImageView)findViewById(R.id.movieback);
		imgBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		final All page = (All)getApplication();
		
		
		movieName=(TextView)findViewById(R.id.itemmoviename);
		movieEnglish=(TextView)findViewById(R.id.itemmovieenglishname);
		movieType=(TextView)findViewById(R.id.itemmovietype);
		movieduration=(TextView)findViewById(R.id.itemmovieduration);
		movieTime=(TextView)findViewById(R.id.itemmovietime);
		moviejj=(TextView)findViewById(R.id.itemmoviexq);
		buttonBuy=(Button)findViewById(R.id.btnbuy);
		moviejj.setMovementMethod(ScrollingMovementMethod.getInstance());

		movieName.setText(page.getMymovie());
		dbHelper=new MyDatabaseHelper(this,"CatsEye.db", null, 1);
		 db = dbHelper.getWritableDatabase();
		 
		 String sql="select * from movie where moviename=?";  
	     Cursor cursor=db.rawQuery(sql, new String[]{page.getMymovie()}); 
        while (cursor.moveToNext()) {  
            movieType.setText(cursor.getString(2));
            movieEnglish.setText(cursor.getString(3));
            movieduration.setText(cursor.getString(5));
            movieTime.setText(cursor.getString(6));
            moviejj.setText(cursor.getString(9));
            page.setIsmovieon(cursor.getInt(11));
            cursor.close();
        }
        
		if(page.getIsmovieon()!=1)
			buttonBuy.setVisibility(View.GONE);

		
		
		buttonBuy.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
		    	Intent intent = new Intent(MovieDetailsActivity.this,MovieCinemaActivity.class);
		    	startActivity(intent);    
		    	finish();
			}
		});
		
	}

}

