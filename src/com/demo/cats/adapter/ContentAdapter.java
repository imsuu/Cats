package com.demo.cats.adapter;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.cats.db.Movie;
import com.demo.cats.R;
import com.demo.cats.adapter.inter.InterClick;


public class ContentAdapter extends BaseAdapter implements OnClickListener{

	private static final String TAG = "ContentAdapter";
	private List<Movie> mContentList;
	private LayoutInflater mInflater;
	private InterClick mCallback;
	public ContentAdapter(Context context, List<Movie> contentList,
			InterClick callback) {
		mContentList = contentList;
		mInflater = LayoutInflater.from(context);
		mCallback = callback;
	}

	@Override
	public int getCount() {
		Log.i(TAG, "getCount");
		return mContentList.size();
	}

	@Override
	public Object getItem(int position) {
		Log.i(TAG, "getItem");
		return mContentList.get(position);
	}

	@Override
	public long getItemId(int position) {
		Log.i(TAG, "getItemId");
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Log.i(TAG, "getView");
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.fragment_movie_hot_item_layout, null);
			holder = new ViewHolder();
			holder.textView = (TextView) convertView
					.findViewById(R.id.itemmoviename);
			holder.button1 = (Button) convertView.findViewById(R.id.itembuy);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
//		holder.textView.setText(mContentList.get(position));
		holder.button1.setOnClickListener(this);

		// 设置位置，获取点击的条目按钮
		holder.button1.setTag(position);
		return convertView;
	}

	public class ViewHolder {
		public TextView textView;
		public Button button1;

	}

	// 响应按钮点击事件,调用子定义接口，并传入View
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.itembuy:
			mCallback.goBuyClick(v);
			break;
		default:
			break;
		}
	}
}
