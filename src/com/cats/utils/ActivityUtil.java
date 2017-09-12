package com.cats.utils;

import java.util.Stack;
import android.app.Activity;
import android.content.Context;

public class ActivityUtil {

	private Stack<Activity> activityStack;
	private static ActivityUtil activityUtil;
	
	private ActivityUtil() {
		if (activityStack == null) {
			activityStack = new Stack<Activity>();
		}
	}
	
	/**
	 * 单一实例
	 */
	public static ActivityUtil getInstance() {
		if (activityUtil == null) {
			synchronized (ActivityUtil.class) {
				if(activityUtil == null){
					activityUtil = new ActivityUtil();
				}
			}
		}
		return activityUtil;
	}

	/**
	 * 添加Activity到堆栈
	 */
	public void addActivity(Activity activity) {
		activityStack.add(activity);
	}

	/**
	 * 获取当前Activity（堆栈中最后一个压入的）
	 */
	public Activity getCurrentActivity() {
		Activity activity = activityStack.lastElement();
		return activity;
	}

	/**
	 * 结束当前Activity（堆栈中最后一个压入的）
	 */
	public void finishCurrentActivity() {
		finishThisActivity(getCurrentActivity());
	}

	/**
	 * 结束指定的Activity
	 */
	public void finishThisActivity(Activity activity) {
		if (activity != null) {
			activityStack.remove(activity);
			activity.finish();
			activity = null;
		}
	}

	/**
	 * 结束指定类名的Activity
	 */
	public void finishActivity(Class<?> cls) {
		for (Activity activity : activityStack) {
			if (activity.getClass().equals(cls)) {
				finishThisActivity(activity);
			}
		}
	}

	/**
	 * 结束所有Activity
	 */
	public void finishAllActivity() {
		for (int i = 0, size = activityStack.size(); i < size; i++) {
			if (null != activityStack.get(i)) {
				activityStack.get(i).finish();
			}
		}
		activityStack.clear();
		activityStack = null;
	}

	/**
	 * 退出应用程序
	 */
	public void AppExit(Context context) {
		try {
			finishAllActivity();
			System.exit(0);
		} catch (Exception e) {
		}
	}
}
