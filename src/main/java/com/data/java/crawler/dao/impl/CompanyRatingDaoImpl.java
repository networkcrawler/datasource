package com.data.java.crawler.dao.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.bson.Document;

import com.data.java.crawler.dao.CompanyRatingDao;
import com.data.java.crawler.dto.CompanyRatingDTO;
import com.data.java.crawler.utils.MongoConnectUtils;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class CompanyRatingDaoImpl implements CompanyRatingDao {

	public CompanyRatingDTO findByHref(String href) {
		MongoCollection collection = connect();
		
		//²éÑ¯Êý¾Ý
		FindIterable<CompanyRatingDTO> find = collection.find(Filters.eq("href", href));
		Iterator<CompanyRatingDTO> it =find.iterator();
		while(it.hasNext()) {
			return it.next();
		}
		
		return null;
	}

	public void insertMany(List<CompanyRatingDTO> list) {
		MongoCollection collection = connect();

		List<Document> documents = new LinkedList<Document>();
		for(CompanyRatingDTO tmp:list) {
			Document document = new Document();
			document.put("title", tmp.getTitle());
			document.put("href", tmp.getHref());
			document.put("info", tmp.getInfo());
			document.put("time", tmp.getTime());
			document.put("created", new Date());
			document.put("updated", new Date());
			documents.add(document);
		}
		collection.insertMany(documents);
		
	}
	
	private MongoCollection connect(){
		MongoDatabase datasource = MongoConnectUtils.connect("eastmoneycenter");
		return datasource.getCollection("company_rating");
	}

}
