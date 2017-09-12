package com.cats.db;

public class Time {
	private String starttime;
	private String endtime;
	private String type;
	private String price;
	private String hall;
	
	
	
	public Time(String stime, String etime, String pri, String hal, String vie) {
		// TODO Auto-generated constructor stub
		this.endtime=etime;
		this.hall=hal;
		this.price=pri;
		this.starttime=stime;
		this.type=vie;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getHall() {
		return hall;
	}
	public void setHall(String hall) {
		this.hall = hall;
	}
	
}
