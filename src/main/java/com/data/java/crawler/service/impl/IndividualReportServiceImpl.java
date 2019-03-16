package com.data.java.crawler.service.impl;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.data.java.crawler.service.IndividualReportService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.data.java.crawler.dao.IndividualReportDao;
import com.data.java.crawler.dao.impl.IndividualReportDaoImpl;
import com.data.java.crawler.dto.EastMoneyIndividualReportDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ִ��ץȡ �����б� ����Ϣ
 * @author admin
 *
 */
@Service
public class IndividualReportServiceImpl implements IndividualReportService {
	private static Logger LOGGER = LoggerFactory.getLogger(IndividualReportServiceImpl.class);
	private String url = "http://data.eastmoney.com/report/";
	@Autowired
	private IndividualReportDao individualReportDao;
	
	public void done() {
		LOGGER.info("��ʼ��ȡ�����б���Ϣ\n\t");
		//��ȡƥ����ַ���
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
		//���ַ������нش�����
		line = line.substring(line.indexOf("var data =")+10,line.lastIndexOf(";"));
		//����json��
		Map<String,Object> lineMap = JSONObject.parseObject(line, Map.class);
		JSONArray lineArray = (JSONArray) lineMap.get("data");
		List<EastMoneyIndividualReportDTO> list = new LinkedList();
		for(int i=0;i<lineArray.size();i++) {
			EastMoneyIndividualReportDTO eastMoneyIndividualReportDTO = JSONObject.parseObject(lineArray.get(i).toString(), EastMoneyIndividualReportDTO.class);
			
			//��ѯ�ñ���������Ƿ����
			EastMoneyIndividualReportDTO tmp = individualReportDao.findByTitle(eastMoneyIndividualReportDTO.getTitle());
			
			if(tmp==null) {
				eastMoneyIndividualReportDTO.setCreated(new Date());
				eastMoneyIndividualReportDTO.setUpdated(new Date());
				list.add(eastMoneyIndividualReportDTO);
			}
		}
		//������д�����ݿ�
		individualReportDao.insertMany(list);
		LOGGER.info("��ȡ�����б���Ϣ������"+list.size()+"������\n\t");

	}

}
