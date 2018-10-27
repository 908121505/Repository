package com.calf.cn.entity;

import java.util.List;

public class DataTables<T> {
	
	private String sEcho;
	private List<T> aaData;
	private int iTotalRecords;
	private int iTotalDisplayRecords;
	
	public String getsEcho() {
		return sEcho;
	}
	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}
	public List<T> getAaData() {
		return aaData;
	}
	public void setAaData(List<T> aaData) {
		this.aaData = aaData;
	}
	public int getiTotalRecords() {
		return iTotalRecords;
	}
	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}
	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}
	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}
	public DataTables() {
		super();
	}
	public DataTables(String sEcho, List<T> aaData, int iTotalRecords,
			int iTotalDisplayRecords) {
		super();
		this.sEcho = sEcho;
		this.aaData = aaData;
		this.iTotalRecords = iTotalRecords;
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}
	
}
