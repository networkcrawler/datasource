package com.data.java.crawler.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * 执行抓取 个股研报 的信息
 * @author admin
 *
 */
@Document(collection = "individual_report")
public class EastMoneyIndividualReportDTO {
	@Id
	private String id;
	private String companyCode;//公司代码
	private String sratingName;//评级
	private String insCode;//机构编码
	private String insName;//机构名称
	private String author;//作者
	private String change;//评级变动
	private String infoCode;//部分地址   完全地址是http://data.eastmoney.com/report/20190312/APPJ5XBLPlE1ASearchReport.html
	private String title;//研报标题
	private Integer type;//
	private String secuName;//公司名称
	private String rate;//评级
	private String secuFullCode;//公司代码
	private Date created;//创建日期
	private Date updated;//更新日期

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getSratingName() {
		return sratingName;
	}
	public void setSratingName(String sratingName) {
		this.sratingName = sratingName;
	}
	public String getInsCode() {
		return insCode;
	}
	public void setInsCode(String insCode) {
		this.insCode = insCode;
	}
	public String getInsName() {
		return insName;
	}
	public void setInsName(String insName) {
		this.insName = insName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getChange() {
		return change;
	}
	public void setChange(String change) {
		this.change = change;
	}
	public String getInfoCode() {
		return infoCode;
	}
	public void setInfoCode(String infoCode) {
		this.infoCode = infoCode;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getSecuName() {
		return secuName;
	}
	public void setSecuName(String secuName) {
		this.secuName = secuName;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getSecuFullCode() {
		return secuFullCode;
	}
	public void setSecuFullCode(String secuFullCode) {
		this.secuFullCode = secuFullCode;
	}
}
