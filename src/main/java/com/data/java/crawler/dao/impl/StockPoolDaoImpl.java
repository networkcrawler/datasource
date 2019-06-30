package com.data.java.crawler.dao.impl;

import com.data.java.crawler.dao.StockPoolDao;
import com.data.java.crawler.dto.StockPoolDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class StockPoolDaoImpl implements StockPoolDao {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public void insertMany(List<StockPoolDTO> list) {
        try{
            mongoTemplate.insertAll(list);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void insertOne(StockPoolDTO stockPoolDTO) {
        try{
            mongoTemplate.insert(stockPoolDTO);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public StockPoolDTO selectOne(String code) {
        try{
            Query query = new Query(Criteria.where("code").is(code));
            return mongoTemplate.findOne(query,StockPoolDTO.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
