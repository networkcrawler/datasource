package com.data.java.crawler.dto;
/**
 * ִ��ץȡ �����б� ����Ϣ
 * @author admin
 *
 */
public class EastMoneyIndividualReportDTO {
	private String companyCode;//��˾����
	private String sratingName;//����
	private String insCode;//��������
	private String insName;//��������
	private String author;//����
	private String change;//�����䶯
	private String infoCode;//���ֵ�ַ   ��ȫ��ַ��http://data.eastmoney.com/report/20190312/APPJ5XBLPlE1ASearchReport.html
	private String title;//�б�����
	private Integer type;//
	private String secuName;//��˾����
	private String rate;//����
	private String secuFullCode;//��˾����
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