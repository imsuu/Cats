package com.demo.cats.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import com.cats.adapter.common.CommonAdapter;
import com.cats.adapter.common.ViewHolder;
import com.cats.db.Movie;
import com.demo.cats.R;

public abstract class HotAdapter extends CommonAdapter<String>{

	public HotAdapter(Context context, List<String> mDatas, int itemLayoutId) {
		super(context, mDatas, itemLayoutId);
	}

	@Override
	public void convert(ViewHolder viewHolder, int position, String item,int itemViewType) {
		viewHolder.setText(R.id.itemmoviename, item);
	}
	
	
}
