package com.demo.cats.fragments;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cats.db.All;
import com.cats.weights.ImageDoubleTextView;
import com.cats.weights.baseWeights.BaseFragment;
import com.demo.cats.R;

public class MineFragment extends BaseFragment {

	public TextView userlogin;
	ImageDoubleTextView myticket;
	
	@Override
	public void setContentView(ViewGroup container) 
	{
		rootView = mInflater.inflate(R.layout.fragment_mine_layout, container, false);
	}
	
	@Override
	public void initView()
	{

	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		 View view=inflater.inflate(R.layout.fragment_mine_layout, null);  
		 userlogin = (TextView) view.findViewById(R.id.userName);
		 myticket=(ImageDoubleTextView)view.findViewById(R.id.mineTickets);
		final All page = (All)getActivity().getApplication();
		if(page.getIslogin()==0)
			{
				//Toast.makeText(getActivity(), "未登录", Toast.LENGTH_SHORT).show();
				 userlogin.setOnClickListener(new OnClickListener()
				 {
					    @Override   
					public void onClick(View v)
					    {//dosth…}
			                //创建组件，通过组件来响应
					    	page.setWherecome(0);
					    	Intent intent = new Intent(getActivity(),com.demo.cats.LoginActivity.class);
					    	startActivity(intent);
					    }
				 });
			}
		else
			{
//				Toast.makeText(getActivity(), page.getLoginusername(), Toast.LENGTH_SHORT).show();
				
			    userlogin.setText(page.getLoginusername());
			    
//				 userlogin.setOnClickListener(new OnClickListener()
//				 {
//					    @Override   
//					public void onClick(View v)
//					    {//dosth…}
//			                //创建组件，通过组件来响应
//					    	Intent intent = new Intent(getActivity(),com.demo.cats.LoginActivity.class);
//					    	startActivity(intent);    
//					    }
//				 });
			}
		


		myticket.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
	//			Toast.makeText(getActivity(), "dasdasdasdasdas", Toast.LENGTH_SHORT).show();
		    	Intent intent = new Intent(getActivity(),com.demo.cats.LookOrderActivity.class);
		    	startActivity(intent);  
				
			}
		});
		
		return view;
	}

	@Override
	public void bindData() {
		
	}

}
