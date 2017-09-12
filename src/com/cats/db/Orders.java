package com.cats.db;

public class Orders {
		private String ordercinema;
		private String ordermovie;
		private String ordertime;
		private String orderseat;
		private String orderprice;
		
		
		public Orders(String cinema, String moviename, String time,
				String seat, String price) {
			// TODO Auto-generated constructor stub
			this.ordercinema=cinema;
			this.ordermovie=moviename;
			this.orderprice=price;
			this.orderseat=seat;
			this.ordertime=time;
		}
		public String getOrdercinema() {
			return ordercinema;
		}
		public void setOrdercinema(String ordercinema) {
			this.ordercinema = ordercinema;
		}
		public String getOrdermovie() {
			return ordermovie;
		}
		public void setOrdermovie(String ordermovie) {
			this.ordermovie = ordermovie;
		}
		public String getOrdertime() {
			return ordertime;
		}
		public void setOrdertime(String ordertime) {
			this.ordertime = ordertime;
		}
		public String getOrderseat() {
			return orderseat;
		}
		public void setOrderseat(String orderseat) {
			this.orderseat = orderseat;
		}
		public String getOrderprice() {
			return orderprice;
		}
		public void setOrderprice(String orderprice) {
			this.orderprice = orderprice;
		}

}
