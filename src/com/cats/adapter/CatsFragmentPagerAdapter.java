package com.cats.adapter;

import java.util.List;
import com.cats.weights.baseWeights.BaseFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class CatsFragmentPagerAdapter extends FragmentPagerAdapter{

	private List<BaseFragment> fragments;
	
	public CatsFragmentPagerAdapter(FragmentManager manager,List<BaseFragment> fragments) {
		super(manager);
		this.fragments = fragments;
	}

	@Override
	public Fragment getItem(int position) {
		if(fragments != null)
			return fragments.get(position);
		return null;
	}

	@Override
	public int getCount() {
		if(fragments != null)
			return fragments.size();
		return 0;
	}

	@Override
	public int getItemPosition(Object object) {
		return POSITION_NONE;
	}
}
