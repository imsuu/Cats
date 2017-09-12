package com.cats.adapter;

import java.util.List;

import com.cats.adapter.common.ViewHolder;
import com.cats.db.All;
import com.cats.db.Cinema;
import com.demo.cats.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CinemaAdapter extends ArrayAdapter<Cinema>{

	private int resourceId;
	
	public CinemaAdapter(Context context, int textViewResourceId,
			List<Cinema> object){
		super(context,textViewResourceId,object);
		resourceId=textViewResourceId;
	}
	@Override 
	public View getView(int position,View convertView,ViewGroup parent){
		Cinema cinema=getItem(position);
		View view;
		ViewHolder viewHolder;
		if(convertView==null){
			view=LayoutInflater.from(getContext()).inflate(resourceId, null);
			viewHolder=new ViewHolder();
			viewHolder.nameCinema=(TextView)view.findViewById(R.id.listcinemaname);
			viewHolder.addressCinema=(TextView)view.findViewById(R.id.listcinemaaddress);
			view.setTag(viewHolder);
		}else{
			view=convertView;
			viewHolder=(ViewHolder) view.getTag();
		}
			
		return view;
	} 
	
	class ViewHolder{
		TextView nameCinema;
		TextView addressCinema;
	}
}
