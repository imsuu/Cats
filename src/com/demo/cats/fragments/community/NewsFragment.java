package com.demo.cats.fragments.community;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;
import com.cats.weights.baseWeights.BaseFragment;
import com.demo.cats.R;

public class NewsFragment extends BaseFragment{
	
	WebView wv;
	@Override
	public void setContentView(ViewGroup container) {
		rootView = mInflater.inflate(R.layout.fragment_community_recommend_layout, container, false);
	}

	@Override
	public void initView() {

	}
	
	
	@Override
	public void bindData() {
		
	}
}
