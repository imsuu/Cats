package com.cats.interfaces;

import com.cats.weights.TitleTabBar;
import android.support.v4.view.ViewPager;

public class CatsPagerChangeListener extends BaseOnPageChangeListener{
	
	public CatsPagerChangeListener(ViewPager pager,TitleTabBar tabBar) {
		super(pager, tabBar);
	}

	@Override
	public void moveNextFalse() {
		titleTabBar.scrollBar(beginPosition, endPosition, 100);
	}

	@Override
	public void moveing() {
		titleTabBar.scrollBar(beginPosition, endPosition, 0);
	}

	@Override
	public void moveNextTrue() {
		focusedFragment(currentFragmentIndex,lastFragmentIndex);
		titleTabBar.scrollBar(beginPosition, endPosition, 200);
	}
	
	public void focusedFragment(int selectPosition,int lastPosition){};
	
}
