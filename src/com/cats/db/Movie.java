package com.cats.db;

public class Movie {

	private String namemovie;
	private int postermovie;
	private String jianjie;
	
	public Movie(String moviename,int poster, String jianjie2) {
		// TODO Auto-generated constructor stub
		this.namemovie=moviename;
		this.postermovie=poster;
		this.jianjie=jianjie2;
	}
	public String getNamemovie() {
		return namemovie;
	}
	public void setNamemovie(String namemovie) {
		this.namemovie = namemovie;
	}
	public int getPostermovie() {
		return postermovie;
	}
	public void setPostermovie(int postermovie) {
		this.postermovie = postermovie;
	}
	public String getJianjie() {
		return jianjie;
	}
	public void setJianjie(String jianjie) {
		this.jianjie = jianjie;
	}

}
