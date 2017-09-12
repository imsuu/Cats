package com.cats.db;

public class Cinema{
	private String namecinema;
	private String addresscinema;
	public Cinema(String name,String address){
		this.addresscinema=address;
		this.namecinema=name;
	}

	public String getNamecinema() {
		return namecinema;
	}

	public void setNamecinema(String namecinema) {
		this.namecinema = namecinema;
	}

	public String getAddresscinema() {
		return addresscinema;
	}

	public void setAddresscinema(String addresscinema) {
		this.addresscinema = addresscinema;
	}
}