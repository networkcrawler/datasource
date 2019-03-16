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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class IndividualReportDaoImpl implements IndividualReportDao {
	@Autowired
	private MongoTemplate mongoTemplate;

	public void insertMany(List<EastMoneyIndividualReportDTO> list) {
		try {
			mongoTemplate.insertAll(list);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateMany(List<EastMoneyIndividualReportDTO> list) {
		// TODO Auto-generated method stub
		
	}

	public EastMoneyIndividualReportDTO findByTitle(String title) {
		try{
			Query query = new Query(Criteria.where("title").is(title));
			return mongoTemplate.findOne(query,EastMoneyIndividualReportDTO.class);
		}catch (Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
