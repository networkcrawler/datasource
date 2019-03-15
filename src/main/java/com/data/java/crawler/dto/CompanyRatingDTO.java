package com.data.java.crawler.dto;

import java.util.Date;

/**
 * 公司评级 实体类
 * @author admin
 *
 */
public class CompanyRatingDTO {
	private String title;//标题
	private String href;//链接
	private String info;//提示
	private String time;//发布时间
	private Date created;
	private Date updated;
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	@Override
	public String toString() {
		return "CompanyRatingDTO [title=" + title + ", href=" + href + ", info=" + info + ", time=" + time + "]";
	}
	
	
	
}
