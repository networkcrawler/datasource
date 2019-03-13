package com.data.java.crawler.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtils {
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * 返回 yyyy-MM-dd格式的时间
	 * @param date
	 * @return
	 */
	public static String getDay(Date date) {
		if(date==null) {
			return simpleDateFormat.format(new Date());
		}else {
			return simpleDateFormat.format(date);
		}
	}

}
