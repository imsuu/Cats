package com.cats.db;

import android.app.Application;

public class All extends Application{
	private int page;//第几页
	private int islogin;//是否登录
	private String loginusername;//登录的用户名
	private String mycinemaname;//选择的影院名字
	private String mycinemaaddress;//影院地址
	private String Mymovie;//选择的电影
	private int ismovieon;//选择的电影是否上映，确定是否显示立即购票按钮
	private String mytime;//选择的日期
	private String mytimetime;//选择的时间
	private String mydatetime;//选择的日期时间
	private String myhall;//选择的大厅
	private int wherecome;//那种方式到达login页面
	private int count;//选择的座位数/购票数
	private String myseat;//选择的座位号
	private int myprice;//jiage
	private int mydanjia;
	
	public String getMycinemaname() {
		return mycinemaname;
	}

	public void setMycinemaname(String mycinemaname) {
		this.mycinemaname = mycinemaname;
	}

	public String getMycinemaaddress() {
		return mycinemaaddress;
	}

	public void setMycinemaaddress(String mycinemaaddress) {
		this.mycinemaaddress = mycinemaaddress;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	@Override
	public void onCreate(){
		page=1;
		islogin=0;
		loginusername="text";
		count=0;
		setWherecome(0);
		super.onCreate();
	}
	
	public int getIslogin() {
		return islogin;
	}

	public void setIslogin(int islogin) {
		this.islogin = islogin;
	}

	public String getLoginusername() {
		return loginusername;
	}

	public void setLoginusername(String loginusername) {
		this.loginusername = loginusername;
	}

	public String getMymovie() {
		return Mymovie;
	}

	public void setMymovie(String mymovie) {
		Mymovie = mymovie;
	}

	public int getIsmovieon() {
		return ismovieon;
	}

	public void setIsmovieon(int ismovieon) {
		this.ismovieon = ismovieon;
	}

	public String getMytime() {
		return mytime;
	}

	public void setMytime(String mytime) {
		this.mytime = mytime;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getMyhall() {
		return myhall;
	}

	public void setMyhall(String myhall) {
		this.myhall = myhall;
	}

	public String getMytimetime() {
		return mytimetime;
	}

	public void setMytimetime(String mytimetime) {
		this.mytimetime = mytimetime;
	}

	public String getMydatetime() {
		return mydatetime;
	}

	public void setMydatetime(String mydatetime) {
		this.mydatetime = mydatetime;
	}

	public int getWherecome() {
		return wherecome;
	}

	public void setWherecome(int wherecome) {
		this.wherecome = wherecome;
	}

	public String getMyseat() {
		return myseat;
	}

	public void setMyseat(String myseat) {
		this.myseat = myseat;
	}

	public int getMyprice() {
		return myprice;
	}

	public void setMyprice(int myprice) {
		this.myprice = myprice;
	}

	public int getMydanjia() {
		return mydanjia;
	}

	public void setMydanjia(int mydanjia) {
		this.mydanjia = mydanjia;
	}


}
	