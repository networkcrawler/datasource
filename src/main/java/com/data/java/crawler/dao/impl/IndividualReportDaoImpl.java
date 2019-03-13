package com.data.java.crawler.dao.impl;

import java.util.LinkedList;
import java.util.List;

import org.bson.Document;

import com.data.java.crawler.dao.IndividualReportDao;
import com.data.java.crawler.dto.EastMoneyIndividualReportDTO;
import com.data.java.crawler.utils.MongoConnectUtils;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Filters;

public class IndividualReportDaoImpl implements IndividualReportDao {

	public void insertMany(List<EastMoneyIndividualReportDTO> list) {
		//获取集合
		MongoCollection collection = getMongoCollection();
		//创建文档
		List<Document> documents = new LinkedList<Document>();
		for(EastMoneyIndividualReportDTO tmp:list) {
			Document document = new Document();
			document.put("companyCode", tmp.getCompanyCode());
			document.put("sratingName", tmp.getSratingName());
			document.put("insCode", tmp.getInsCode());
			document.put("insName", tmp.getInsName());
			document.put("author", tmp.getAuthor());
			document.put("change", tmp.getChange());
			document.put("infoCode", tmp.getInfoCode());
			document.put("title", tmp.getTitle());
			document.put("type", tmp.getType());
			document.put("secuName", tmp.getSecuName());
			document.put("rate", tmp.getRate());
			document.put("secuFullCode", tmp.getSecuFullCode());
			
			documents.add(document);
		}
		//cc插入数据库
		collection.insertMany(documents);
		
	}

	public void updateMany(List<EastMoneyIndividualReportDTO> list) {
		// TODO Auto-generated method stub
		
	}

	public EastMoneyIndividualReportDTO findByTitle(String title) {
		//获取集合
		MongoCollection collection = getMongoCollection();
		FindIterable<EastMoneyIndividualReportDTO> find = collection.find(Filters.eq("title", title));		
		return find.first();
	}
	
	private MongoCollection getMongoCollection(){
		MongoDatabase database = MongoConnectUtils.connect("eastmoneycenter");
		return database.getCollection("individual_report");
	}

}
