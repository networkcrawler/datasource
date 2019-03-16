package com.data.java.crawler.service.impl;

import java.util.LinkedList;
import java.util.List;

import com.data.java.crawler.service.CompanyRatingService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.data.java.crawler.dao.CompanyRatingDao;
import com.data.java.crawler.dao.impl.CompanyRatingDaoImpl;
import com.data.java.crawler.dto.CompanyRatingDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 公司评级 信息
 * @author admin
 *
 */
@Service
public class CompanyRatingServiceImpl implements CompanyRatingService {
	private static Logger LOGGER = LoggerFactory.getLogger(CompanyRatingServiceImpl.class);

	private String url = "http://stock.eastmoney.com/news/cgspj.html";
	@Autowired
	private CompanyRatingDao companyRatingDao;
	
	public void done() {
		LOGGER.info("开始获取公司评级信息\n\t");
		//获取文档
		Document document = null;
		try {
			document = Jsoup.connect(url)
					.ignoreHttpErrors(true)
					.ignoreContentType(true)
					.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36")
					.get();
		}catch(Exception e) {
			e.printStackTrace();
		}
		//解析文档得到公司评级信息
		List<CompanyRatingDTO> companyRatingDTOs = new LinkedList<CompanyRatingDTO>();
		for(int i=0;i<20;i++) {
			Element element = document.getElementById("newsTr"+i);
			
			if(element==null) break;
			
			Element element1 = element.child(0).getElementsByTag("p").get(0).getElementsByTag("a").get(0);
			String title = element1.html();
			String href = element1.attr("href");
			String info = element.child(0).getElementsByTag("p").get(1).attr("title");
			String time = element.child(0).getElementsByTag("p").get(2).html();
			
			CompanyRatingDTO companyRatingDTO = new CompanyRatingDTO();
			companyRatingDTO.setHref(href);
			CompanyRatingDTO tmp = companyRatingDao.findByHref(href);
			if(tmp!=null) {
				break;
			}
			companyRatingDTO.setTitle(title);
			companyRatingDTO.setInfo(info);
			companyRatingDTO.setTime(time);
			companyRatingDTOs.add(companyRatingDTO);
		}
		
		//将信息写入mongodb
		companyRatingDao.insertMany(companyRatingDTOs);
		LOGGER.info("获取公司评级信息结束,共"+companyRatingDTOs.size()+"条数据\n\t");

	}
}
