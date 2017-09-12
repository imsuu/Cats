package com.demo.cats.fragments;

import java.util.ArrayList;
import java.util.List;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;
import com.cats.adapter.CatsFragmentPagerAdapter;
import com.cats.interfaces.CatsPagerChangeListener;
import com.cats.weights.TitleTabBar;
import com.cats.weights.TitleTabBar.TitleTabClickListener;
import com.cats.weights.baseWeights.BaseFragment;
import com.demo.cats.R;
import com.demo.cats.fragments.community.TrailerFragment;
import com.demo.cats.fragments.community.NewsFragment;

public class CommunityFragment extends BaseFragment implements TitleTabClickListener{

	private FragmentManager manager;
	private TitleTabBar titleTabBar;
	private ViewPager contentPager;
	private CatsFragmentPagerAdapter mAdapter;
	private List<BaseFragment> fragments = new ArrayList<BaseFragment>();
	
	@Override
	public void setContentView(ViewGroup container) {
		rootView = mInflater.inflate(R.layout.fragment_community_layout, container, false);
		manager = this.getChildFragmentManager();
	}

	@Override
	public void initView() {
		titleTabBar = (TitleTabBar) rootView.findViewById(R.id.community_topBar);
		titleTabBar.setTitleTabClickListener(this);
		contentPager = (ViewPager) rootView.findViewById(R.id.contentPager);
		contentPager.setOnPageChangeListener(new CatsPagerChangeListener(contentPager,titleTabBar){
			@Override
			public void focusedFragment(int selectPosition, int lastPosition) {
				super.focusedFragment(selectPosition, lastPosition);
				titleTabBar.setTitleState(selectPosition);
			}
		});
	}

	@Override
	public void bindData() {
		fragments.add(new NewsFragment());
		fragments.add(new TrailerFragment());
		
		mAdapter = new CatsFragmentPagerAdapter(manager, fragments);
		contentPager.setAdapter(mAdapter);
		contentPager.setCurrentItem(0);
		contentPager.setOffscreenPageLimit(0);
	}
	
	@Override
	public void callback(int index) {
		contentPager.setCurrentItem(index);
	}
}
