package com.cats.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDatabaseHelper extends SQLiteOpenHelper{

	public static final String CREATE_USER ="create table user("
			+"phonenum text ,"
			+"password text)";
	
	private Context mContext;
	
	public MyDatabaseHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
		mContext = context;
	}


	@Override
	public void onCreate(SQLiteDatabase db)
	{
		db.execSQL(CREATE_USER);
	//	Toast.makeText(mContext, "aaaaaaaaa", Toast.LENGTH_SHORT).show();
	}
	
	@Override 
	public void onUpgrade(SQLiteDatabase db,int oldVersion, int newVersion){
		
	}
	

}
