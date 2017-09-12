package com.cats.weights.baseWeights;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment{
	
	public Activity mActivity;
	public LayoutInflater mInflater;
	public View rootView;
	public boolean isDestroyView = false;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.mActivity = getActivity();
		this.mInflater = LayoutInflater.from(mActivity);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		setContentView(container);
		initView();
		bindData();
		setOnClickListener();
		return rootView;
	}
	
	@Override
	public void onDestroyView() {
		isDestroyView = true;
		super.onDestroyView();
	}
	public abstract void setContentView(ViewGroup container);
	public abstract void initView();
	public abstract void bindData();
	public void setOnClickListener(){};
}
