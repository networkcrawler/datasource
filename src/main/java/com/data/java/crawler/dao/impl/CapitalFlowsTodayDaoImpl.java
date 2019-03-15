package com.data.java.crawler.dao.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.bson.Document;

import com.data.java.crawler.dao.CapitalFlowsTodayDao;
import com.data.java.crawler.dto.CapitalFlowsTodayDTO;
import com.data.java.crawler.utils.MongoConnectUtils;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class CapitalFlowsTodayDaoImpl implements CapitalFlowsTodayDao {

	public void insertMany(List<CapitalFlowsTodayDTO> list) {
		//连接数据库
		MongoCollection collection = connect();
		//创建文档
		List<Document> documents = new LinkedList<Document>();
		for(CapitalFlowsTodayDTO tmp:list) {
			Document document = new Document();
			document.put("plateSymbol", tmp.getPlateSymbol());
			document.put("plateName", tmp.getPlateName());
			document.put("chg", tmp.getChg());
			document.put("inflowMain", tmp.getInflowMain());
			document.put("inflowMainPer", tmp.getInflowMainPer());
			document.put("superInflowMain", tmp.getSuperInflowMain());
			document.put("superInflowMainPer", tmp.getSuperInflowMainPer());
			document.put("largeInflowMain", tmp.getLargeInflowMain());
			document.put("largeInflowMainPer", tmp.getLargeInflowMainPer());
			document.put("midInflowMain", tmp.getMidInflowMain());
			document.put("midInflowMainPer", tmp.getMidInflowMainPer());
			document.put("smallInflowMain", tmp.getSmallInflowMain());
			document.put("smallInflowMainPer", tmp.getSmallInflowMainPer());
			document.put("name", tmp.getName());
			document.put("symbol", tmp.getSymbol());
			document.put("created", new Date());
			document.put("updated", new Date());
			
			documents.add(document);
		}
		collection.insertMany(documents);

	}
	
	private MongoCollection connect() {
		MongoDatabase datasource = MongoConnectUtils.connect("eastmoneycenter");
		return datasource.getCollection("capital_flows_today");
	}

}
