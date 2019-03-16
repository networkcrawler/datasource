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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class CapitalFlowsTodayDaoImpl implements CapitalFlowsTodayDao {
	@Autowired
	private MongoTemplate mongoTemplate;

	public void insertMany(List<CapitalFlowsTodayDTO> list) {
		try {
			mongoTemplate.insertAll(list);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public List<CapitalFlowsTodayDTO> find() {
		try {
			System.err.println("mongo"+mongoTemplate);
			return mongoTemplate.find(new Query(),CapitalFlowsTodayDTO.class);
		}catch (Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
