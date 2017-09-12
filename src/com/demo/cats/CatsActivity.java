package com.demo.cats;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import com.cats.db.All;
import com.cats.db.MyApplication;
import com.cats.weights.TabItemView;
import com.cats.weights.TabItemView.TabClickListner;
import com.cats.weights.baseWeights.BaseFragmentActivity;
import com.demo.cats.fragments.CinemaFragment;
import com.demo.cats.fragments.CommunityFragment;
import com.demo.cats.fragments.MineFragment;
import com.demo.cats.fragments.MovieFragment;

public class CatsActivity extends BaseFragmentActivity implements TabClickListner{

	private TabItemView movie,cinema,community,mine,lastSelectedTab;
	private MovieFragment movieFragment;
	private CinemaFragment cinemaFragment;
	private MineFragment mineFragment;
	private CommunityFragment communityFragment;
	private FragmentManager fragmentManager;
	
	@Override
	public void setContentView() {
		setContentView(R.layout.activity_cats_layout);
		fragmentManager = getSupportFragmentManager();
	}
	
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		// TODO Auto-generated method stub
//		super.onCreate(savedInstanceState);
//		final All app = (All)getApplication();
//		Toast.makeText(this, app.getPage(), Toast.LENGTH_SHORT).show();
//	}
	@Override
	public void initView() {
		View tabLayout = findViewById(R.id.tabLayout);
		movie = (TabItemView) tabLayout.findViewById(R.id.movie);
		cinema = (TabItemView) tabLayout.findViewById(R.id.cinema);
		community = (TabItemView) tabLayout.findViewById(R.id.community);
		mine = (TabItemView) tabLayout.findViewById(R.id.mine);
		
		
		
		final All page = (All)getApplication();
	//	Toast.makeText(this, page.getPage(), Toast.LENGTH_SHORT).show();
	
		if(page.getPage()==1)
		{	
			lastSelectedTab = movie;
			movie.setTabSelected(true);
			setTabSelection(0);
		}
//		lastSelectedTab = movie;
		if(page.getPage()==4)
		{	
			lastSelectedTab = mine;
			mine.setTabSelected(true);
			setTabSelection(3);
		}
	}
	
	@Override
	public void setOnClickListener() {
		movie.setTabClickListener(this);
		cinema.setTabClickListener(this);
		community.setTabClickListener(this);
		mine.setTabClickListener(this);
	}

	@Override
	public void onTabClick(View view) {
		switch (view.getId()) {
		case R.id.movie://index = 0;
			judgeTabSame(movie);
			setTabSelection(0);
			break;
		case R.id.cinema://index = 1;
			judgeTabSame(cinema);
			setTabSelection(1);
			break;
		case R.id.community://index = 2;
			judgeTabSame(community);
			setTabSelection(2);
			break;
		case R.id.mine://index = 3;
			judgeTabSame(mine);
			setTabSelection(3);
			break;
		}
	}
	
	/**
	 *判断是否是同一个tab标签,并设置状态 
	 */
	private void judgeTabSame(TabItemView clickTab){
		if(lastSelectedTab != clickTab){
			lastSelectedTab.setTabSelected(false);
			lastSelectedTab = clickTab;
			lastSelectedTab.setTabSelected(true);
		}
	}
	/**
	 *根据传入的index参数来设置选中的tab页 
	 */
	@SuppressLint("CommitTransaction")
	private void setTabSelection(int index){
		//开启一个Fragment事务
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		hideAllFragment(transaction);
		switch (index) {
		case 0:
			if(movieFragment == null){
				movieFragment = new MovieFragment();
				transaction.add(R.id.content,movieFragment);
			}else{
				transaction.show(movieFragment);
			}
			break;
		case 1:
			if(cinemaFragment == null){
				cinemaFragment = new CinemaFragment();
				transaction.add(R.id.content,cinemaFragment);
			}else{
				transaction.show(cinemaFragment);
			}
			break;
		case 2:
			if(communityFragment == null){
				communityFragment = new CommunityFragment();
				transaction.add(R.id.content,communityFragment);
			}else{
				transaction.show(communityFragment);
			}
			break;
		case 3:
			if(mineFragment == null){
				mineFragment = new MineFragment();
				transaction.add(R.id.content,mineFragment);
			}else{
				transaction.show(mineFragment);
			}
			break;
		}
		transaction.commit();
	}
	
	/**
	 *隐藏掉所有的fragment，防止多个fragment显示 
	 */
	private void hideAllFragment(FragmentTransaction transaction){
		if(movieFragment != null){
			transaction.hide(movieFragment);
		}
		if(cinemaFragment != null){
			transaction.hide(cinemaFragment);
		}
		if(communityFragment != null){
			transaction.hide(communityFragment);
		}
		if(mineFragment != null){
			transaction.hide(mineFragment);
		}
	}
	
	
	private long exitTime = 0;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){   
	        if((System.currentTimeMillis()-exitTime) > 2000){  
	            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();                                
	            exitTime = System.currentTimeMillis();   
	        } else {
	        	
//	            finish();
//	            System.exit(0);
	            MyApplication.getInstance().exit();
	        }
	        return true;   
	    }
	    return super.onKeyDown(keyCode, event);
	}
	
}
