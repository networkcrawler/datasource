package com.data.java.crawler.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtils {
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * ���� yyyy-MM-dd��ʽ��ʱ��
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
