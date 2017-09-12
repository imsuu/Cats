package com.cats.utils;

import android.util.Log;

public class LogUtil {
	public enum State{DEBUG,RELEASE};
	private static LogUtil logUtil;
	private static State state = State.DEBUG;
	
	public static LogUtil getInstance(){
		if(logUtil == null){
			synchronized (LogUtil.class) {
				if(logUtil == null){
					logUtil = new LogUtil();
				}
			}
		}
		return logUtil;
	}
	
	/**
	 *设置信息打印状态 
	 */
	public void setLogState(State logState){
		LogUtil.state = logState;
	}
	
	/**
	 *信息打印 
	 */
	public void printLogInfo(String info){
		printLogInfo(LogUtil.class,info);
	}
	
	public void printLogInfo(@SuppressWarnings("rawtypes") Class clazz,String info){
		printLogInfo(clazz.getSimpleName(),info);
	}
	
	public void printLogInfo(String tag,String info){
		if(LogUtil.state == State.DEBUG){
			Log.i(tag, info);
		}
	}
	
	/**
	 *错误信息打印 
	 */
	public void printLogError(String info){
		printLogInfo(LogUtil.class,info);
	}
	
	public void printLogError(@SuppressWarnings("rawtypes") Class clazz,String info){
		printLogInfo(clazz.getSimpleName(),info);
	}
	
	public void printLogError(String tag,String info){
		if(LogUtil.state == State.DEBUG){
			Log.e(tag, info);
		}
	}	
}
