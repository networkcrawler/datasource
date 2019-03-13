package com.data.java.crawler.task;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.data.java.crawler.dao.IndividualReportDao;
import com.data.java.crawler.dao.impl.IndividualReportDaoImpl;
import com.data.java.crawler.dto.EastMoneyIndividualReportDTO;

/**
 * 执行抓取 个股研报 的信息
 * @author admin
 *
 */
public class IndividualReportTask{
	private String url = "http://data.eastmoney.com/report/";
	private IndividualReportDao individualReportDao = new IndividualReportDaoImpl();
	
	public void done() {
		//获取匹配的字符串
		String line = null;
		BufferedReader bufferedReader = null;
		try {
			Document document = Jsoup.connect(url)
				.ignoreContentType(true)
				.ignoreHttpErrors(true)
				.get();
			bufferedReader = new BufferedReader(new InputStreamReader(
					new ByteArrayInputStream(document.toString().getBytes("utf8")),
					"utf8"
					));
			
			Pattern pattern = Pattern.compile("var data =*");
			while((line=bufferedReader.readLine())!=null) {
				line = line.trim();
				Matcher matcher = pattern.matcher(line);
				if(matcher.find()) {
					break;
				}
			}
						
		}catch(Exception e) {
			e.printStackTrace();
		}
		//对字符串进行截串操作
		line = line.substring(line.indexOf("var data =")+10,line.lastIndexOf(";"));
		//解析json串
		Map<String,Object> lineMap = JSONObject.parseObject(line, Map.class);
		JSONArray lineArray = (JSONArray) lineMap.get("data");
		List<EastMoneyIndividualReportDTO> list = new LinkedList();
		for(int i=0;i<lineArray.size();i++) {
			EastMoneyIndividualReportDTO eastMoneyIndividualReportDTO = JSONObject.parseObject(lineArray.get(i).toString(), EastMoneyIndividualReportDTO.class);
			
			//查询该标题的文章是否存在
			EastMoneyIndividualReportDTO tmp = individualReportDao.findByTitle(eastMoneyIndividualReportDTO.getTitle());
			
			if(tmp==null)
				list.add(eastMoneyIndividualReportDTO);
		}
		//将数据写入数据库
		individualReportDao.insertMany(list);
	}
	
	public static void main(String[] args) {
		IndividualReportTask i = new IndividualReportTask();
		i.done();
	}
}
