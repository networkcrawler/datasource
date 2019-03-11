package com.data.java.crawler.dto;

import java.util.List;

public class EastMoneyFundRealDTO {
	private Integer pages;
	private String date;
	private String[] data;
	public Integer getPages() {
		return pages;
	}
	public void setPages(Integer pages) {
		this.pages = pages;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String[] getData() {
		return data;
	}
	public void setData(String[] data) {
		this.data = data;
	}
}
