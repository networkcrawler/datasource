package com.data.java.crawler.service.impl;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.data.java.crawler.service.CapitalFlowsTodayService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.data.java.crawler.dao.CapitalFlowsTodayDao;
import com.data.java.crawler.dao.impl.CapitalFlowsTodayDaoImpl;
import com.data.java.crawler.dto.CapitalFlowsTodayDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 获取 今日行业资金流向
 * @author admin
 *
 */
@Service
public class CapitalFlowsTodayServiceImpl implements CapitalFlowsTodayService {
	private String url = "http://data.eastmoney.com/bkzj/hy.html";
	@Autowired
	private CapitalFlowsTodayDao capitalFlowsTodayDao;
	
	public void done() {
		//获取文档
		Document document = null;
		try {
			document = Jsoup.connect(url)
					.ignoreContentType(true)
					.ignoreHttpErrors(true)
					.referrer("http://data.eastmoney.com/bkzj/")
					.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36")
					.get();
		}catch(Exception e) {
			e.printStackTrace();
		}
		//获取数据行
		String line = null;
		if(document!=null) {
			BufferedReader bufferedReader = null;
			try {
				bufferedReader = new BufferedReader(
							new InputStreamReader(
										new ByteArrayInputStream(document.toString().getBytes("utf8")),
										"utf8"
									)
						);
				Pattern p = Pattern.compile("defjson:*");
				while((line=bufferedReader.readLine())!=null) {
					Matcher m = p.matcher(line);
					if(m.find()) {
						break;
					}
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(bufferedReader!=null) {
					try {
						
					}catch(Exception e) {
						bufferedReader=null;
					}
				}
			}
		}
		//解析数据
		line = line.trim();
		line = line.substring(line.indexOf("beginIndex")+9,line.lastIndexOf(","));
		Map<String,Object> map = JSONObject.parseObject(line);
		JSONArray array = (JSONArray) map.get("data");
		List<CapitalFlowsTodayDTO> captitalFlowsTodayDTOs = new LinkedList<CapitalFlowsTodayDTO>();
		for(int i=0;i<50;i++) {
			CapitalFlowsTodayDTO capitalFlowsTodayDTO = new CapitalFlowsTodayDTO();
			String tmp = (String) array.get(i);
			String[] t = tmp.split(",");
			capitalFlowsTodayDTO.setPlateName(t[2]);
			capitalFlowsTodayDTO.setPlateSymbol(t[1]);
			capitalFlowsTodayDTO.setInflowMain(t[3]);
			capitalFlowsTodayDTO.setInflowMain(t[4]);
			capitalFlowsTodayDTO.setInflowMainPer(t[5]);
			capitalFlowsTodayDTO.setSuperInflowMain(t[6]);
			capitalFlowsTodayDTO.setSuperInflowMainPer(t[7]);
			capitalFlowsTodayDTO.setLargeInflowMain(t[8]);
			capitalFlowsTodayDTO.setLargeInflowMainPer(t[9]);
			capitalFlowsTodayDTO.setMidInflowMain(t[10]);
			capitalFlowsTodayDTO.setMidInflowMainPer(t[11]);
			capitalFlowsTodayDTO.setSmallInflowMain(t[12]);
			capitalFlowsTodayDTO.setSmallInflowMainPer(t[13]);
			capitalFlowsTodayDTO.setName(t[14]);
			capitalFlowsTodayDTO.setSymbol(t[15]);
			captitalFlowsTodayDTOs.add(capitalFlowsTodayDTO);
		}
		//将数据写入数据库
		capitalFlowsTodayDao.insertMany(captitalFlowsTodayDTOs);
	}
}
