package com.data.java.crawler.dto;

public class CapitalFlowsTodayDTO {
	private String id;
	private String plateSymbol;//行业板块编码
	private String plateName;//行业板块名称
	private String chg;//今日涨跌幅，单位%
	private String inflowMain;//今日主力净流入净额，单位 万元
	private String inflowMainPer;//今日主力净流入占比，%
	private String superInflowMain;//今日超大单净流入额，单位为万
	private String superInflowMainPer;//今日超大单净流入占比，%
	private String largeInflowMain;//今日大订单净流入额，单位为万
	private String largeInflowMainPer;//今日大订单净流入占比，%
	private String midInflowMain;//今日中单净流入额，单位为万
	private String midInflowMainPer;//今日中单净流入占比，%
	private String smallInflowMain;//今日小单净流入额，单位为万
	private String smallInflowMainPer;//今日小单净流入占比，%
	private String name;//主力流入最大股，股票名称
	private String symbol;//主力流入最大股，股票代码
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPlateSymbol() {
		return plateSymbol;
	}
	public void setPlateSymbol(String plateSymbol) {
		this.plateSymbol = plateSymbol;
	}
	public String getPlateName() {
		return plateName;
	}
	public void setPlateName(String plateName) {
		this.plateName = plateName;
	}
	public String getChg() {
		return chg;
	}
	public void setChg(String chg) {
		this.chg = chg;
	}
	public String getInflowMain() {
		return inflowMain;
	}
	public void setInflowMain(String inflowMain) {
		this.inflowMain = inflowMain;
	}
	public String getInflowMainPer() {
		return inflowMainPer;
	}
	public void setInflowMainPer(String inflowMainPer) {
		this.inflowMainPer = inflowMainPer;
	}
	public String getSuperInflowMain() {
		return superInflowMain;
	}
	public void setSuperInflowMain(String superInflowMain) {
		this.superInflowMain = superInflowMain;
	}
	public String getSuperInflowMainPer() {
		return superInflowMainPer;
	}
	public void setSuperInflowMainPer(String superInflowMainPer) {
		this.superInflowMainPer = superInflowMainPer;
	}
	public String getLargeInflowMain() {
		return largeInflowMain;
	}
	public void setLargeInflowMain(String largeInflowMain) {
		this.largeInflowMain = largeInflowMain;
	}
	public String getLargeInflowMainPer() {
		return largeInflowMainPer;
	}
	public void setLargeInflowMainPer(String largeInflowMainPer) {
		this.largeInflowMainPer = largeInflowMainPer;
	}
	public String getMidInflowMain() {
		return midInflowMain;
	}
	public void setMidInflowMain(String midInflowMain) {
		this.midInflowMain = midInflowMain;
	}
	public String getMidInflowMainPer() {
		return midInflowMainPer;
	}
	public void setMidInflowMainPer(String midInflowMainPer) {
		this.midInflowMainPer = midInflowMainPer;
	}
	public String getSmallInflowMain() {
		return smallInflowMain;
	}
	public void setSmallInflowMain(String smallInflowMain) {
		this.smallInflowMain = smallInflowMain;
	}
	public String getSmallInflowMainPer() {
		return smallInflowMainPer;
	}
	public void setSmallInflowMainPer(String smallInflowMainPer) {
		this.smallInflowMainPer = smallInflowMainPer;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
}
