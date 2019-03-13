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
 * ִ��ץȡ �����б� ����Ϣ
 * @author admin
 *
 */
public class IndividualReportTask{
	private String url = "http://data.eastmoney.com/report/";
	private IndividualReportDao individualReportDao = new IndividualReportDaoImpl();
	
	public void done() {
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
			
			if(tmp==null)
				list.add(eastMoneyIndividualReportDTO);
		}
		//������д�����ݿ�
		individualReportDao.insertMany(list);
	}
	
	public static void main(String[] args) {
		IndividualReportTask i = new IndividualReportTask();
		i.done();
	}
}
