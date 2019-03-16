package com.data.java.crawler.dao.impl;

import java.util.*;

import org.bson.Document;

import com.data.java.crawler.dao.CompanyRatingDao;
import com.data.java.crawler.dto.CompanyRatingDTO;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class CompanyRatingDaoImpl implements CompanyRatingDao {
	@Autowired
	private MongoTemplate mongoTemplate;

	public CompanyRatingDTO findByHref(String href) {
		try {
			Query query = new Query(Criteria.where("href").is(href));
			return mongoTemplate.findOne(query, CompanyRatingDTO.class);
		}catch (Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void insertMany(List<CompanyRatingDTO> list) {
		try{
			mongoTemplate.insertAll(list);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
