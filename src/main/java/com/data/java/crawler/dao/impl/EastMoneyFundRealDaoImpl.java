package com.data.java.crawler.dao.impl;

import java.util.List;
import com.data.java.crawler.dao.EastMoneyFundRealDao;
import com.data.java.crawler.dto.EastMoneyFundRealPerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component
public class EastMoneyFundRealDaoImpl implements EastMoneyFundRealDao {
	@Autowired
	private MongoTemplate mongoTemplate;

	public void insert(List<EastMoneyFundRealPerDTO> lists) {
		try{
			mongoTemplate.insertAll(lists);
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}

	public List<EastMoneyFundRealPerDTO> findOneBySymbol(String symbol) {
		try{
			Query query = new Query(Criteria.where("symbol").is(symbol));
			return mongoTemplate.find(query,EastMoneyFundRealPerDTO.class);
		}catch (Exception e){
			e.printStackTrace();throw new RuntimeException(e);
		}
	}

	public void update(List<EastMoneyFundRealPerDTO> lists) {
		try{
			Query query = new Query(Criteria.where("symbol").in());
			Update update = Update.update("lists", lists);
			mongoTemplate.updateMulti(query,update,"fund_real");
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
