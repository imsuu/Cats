package com.demo.cats.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cats.db.Movie;
import com.demo.cats.R;

//创建MyAdapter继承BaseAdapter  
public class MyAdapter extends ArrayAdapter<Movie> {  
    private Context context;  
    private LayoutInflater inflater;  
    private int resourceId;

    public MyAdapter(Context context,int textViewResourceId,List<Movie> objects) {  
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
    }  

 

    @Override  
    public long getItemId(int position) {  
        // TODO Auto-generated method stub  
        return 0;  
    }  

    @Override  
    public View getView(int position, View convertView, ViewGroup parent) {  
        Movie p = getItem(position);  
        ViewHolder viewHolder;  
        View view;
        if (convertView == null) {  
            viewHolder = new ViewHolder();  
//            convertView = inflater.inflate(R.layout.fragment_movie_hot_item_layout, null);  
            view=LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder.txt_name=(TextView)view.findViewById(R.id.itemmoviename);
            viewHolder.txt_jianjie=(TextView)view.findViewById(R.id.itemmoviejj);
            viewHolder.img=(ImageView)view.findViewById(R.id.cinemaimg);
            view.setTag(viewHolder);
        } else {  
        	view=convertView;
            viewHolder = (ViewHolder) view.getTag();  
        }  
        viewHolder.img.setImageResource(p.getPostermovie());
        viewHolder.txt_jianjie.setText(p.getJianjie());
        viewHolder.txt_name.setText(p.getNamemovie());
        return view;  
    }  
}  

class ViewHolder {  
    TextView txt_name;  
    ImageView img;
    TextView txt_jianjie;  
}  
