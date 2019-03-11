package com.data.java.crawler.dto;

public class EastMoneyFundRealPerDTO {
	private Integer id;
	private String symbol;//代码
	private String name;//名称
	private Double lastTrade;//当天价格
	private Double chg;//涨跌幅，无%
	private Double inflowMain;//今日主力净流入额，单位为万
	private Double inflowMainPer;//今日主力净流入占比，%
	private Double superInflowMain;//今日超大单净流入额，单位为万
	private Double superInflowMainPer;//今日超大单净流入占比，%
	private Double largeInflowMain;//今日大订单净流入额，单位为万
	private Double largeInflowMainPer;//今日大订单净流入占比，%
	private Double midInflowMain;//今日中单净流入额，单位为万
	private Double midInflowMainPer;//今日中单净流入占比，%
	private Double smallInflowMain;//今日小单净流入额，单位为万
	private Double smallInflowMainPer;//今日小单净流入占比，%
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getLastTrade() {
		return lastTrade;
	}
	public void setLastTrade(Double lastTrade) {
		this.lastTrade = lastTrade;
	}
	public Double getChg() {
		return chg;
	}
	public void setChg(Double chg) {
		this.chg = chg;
	}
	public Double getInflowMain() {
		return inflowMain;
	}
	public void setInflowMain(Double inflowMain) {
		this.inflowMain = inflowMain;
	}
	public Double getInflowMainPer() {
		return inflowMainPer;
	}
	public void setInflowMainPer(Double inflowMainPer) {
		this.inflowMainPer = inflowMainPer;
	}
	public Double getSuperInflowMain() {
		return superInflowMain;
	}
	public void setSuperInflowMain(Double superInflowMain) {
		this.superInflowMain = superInflowMain;
	}
	public Double getSuperInflowMainPer() {
		return superInflowMainPer;
	}
	public void setSuperInflowMainPer(Double superInflowMainPer) {
		this.superInflowMainPer = superInflowMainPer;
	}
	public Double getLargeInflowMain() {
		return largeInflowMain;
	}
	public void setLargeInflowMain(Double largeInflowMain) {
		this.largeInflowMain = largeInflowMain;
	}
	public Double getLargeInflowMainPer() {
		return largeInflowMainPer;
	}
	public void setLargeInflowMainPer(Double largeInflowMainPer) {
		this.largeInflowMainPer = largeInflowMainPer;
	}
	public Double getMidInflowMain() {
		return midInflowMain;
	}
	public void setMidInflowMain(Double midInflowMain) {
		this.midInflowMain = midInflowMain;
	}
	public Double getMidInflowMainPer() {
		return midInflowMainPer;
	}
	public void setMidInflowMainPer(Double midInflowMainPer) {
		this.midInflowMainPer = midInflowMainPer;
	}
	public Double getSmallInflowMain() {
		return smallInflowMain;
	}
	public void setSmallInflowMain(Double smallInflowMain) {
		this.smallInflowMain = smallInflowMain;
	}
	public Double getSmallInflowMainPer() {
		return smallInflowMainPer;
	}
	public void setSmallInflowMainPer(Double smallInflowMainPer) {
		this.smallInflowMainPer = smallInflowMainPer;
	}
}
