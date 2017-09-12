package com.cats.adapter.common;

import java.util.Arrays;
import java.util.List;

import com.cats.db.Movie;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class CommonAdapter<T> extends BaseAdapter{
	
	public Context mContext;
	private List<String> mDatas;
	private int [] itemLayoutIds;
	private int [] viewTypes;
	private static final int defaultViewType = 0x00;
	
	public CommonAdapter(Context context, List<String> mDatas2, int itemLayoutId) {
		this.mContext = context;
		this.mDatas = mDatas2;
		this.itemLayoutIds = new int[1];
		this.viewTypes = new int[1];
		this.itemLayoutIds[0] = itemLayoutId;
		this.viewTypes[0] = defaultViewType;
	}
	public CommonAdapter(Context context, List<String> mDatas,int [] itemLayoutIds,int [] viewTypes) {
		this.mContext = context;
		this.mDatas = mDatas;
		this.itemLayoutIds = itemLayoutIds;
		this.viewTypes = viewTypes;
	}
	@Override
	public int getCount() {
		if(mDatas != null)
			return mDatas.size();
		return 0;
	}

	@Override
	public String getItem(int position) {
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	@Override
	public int getViewTypeCount() {
		return viewTypes.length;
	}
	
	/**
	 *多种布局格式需要重写此方法 
	 *
	 *因为 The item view type you are returning from getItemViewType() is >= getViewTypeCount()，所以type的最大值最好从0开始,然后++
	 */
	public int getItemViewType(int position,int [] viewTypes){
		return defaultViewType;
	};
	@Override
	public int getItemViewType(int position) {
		return getItemViewType(position,viewTypes);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		int viewType = getItemViewType(position);
		int index = Arrays.binarySearch(viewTypes, viewType);
		
		ViewHolder viewHolder = getViewHolder(position, convertView,parent,itemLayoutIds[index],viewType);
		convert(viewHolder, position,getItem(position),viewType);
		return viewHolder.getConvertView();
	}

	public abstract void convert(ViewHolder viewHolder,int position, String string,int itemViewType);

	private ViewHolder getViewHolder(int position, View convertView,ViewGroup parent,int layoutId,int itemViewType) {
		return ViewHolder.get(mContext, convertView, parent,layoutId,position,itemViewType);
	}
}
