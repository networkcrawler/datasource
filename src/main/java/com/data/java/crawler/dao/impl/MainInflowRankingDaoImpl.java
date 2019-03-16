package com.data.java.crawler.dao.impl;

import com.data.java.crawler.dao.MainInflowRankingDao;
import com.data.java.crawler.dto.MainInflowRankingDTO;
import com.data.java.crawler.utils.DateFormatUtils;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class MainInflowRankingDaoImpl implements MainInflowRankingDao {
	@Autowired
	private MongoTemplate mongoTemplate;

    public void insertMany(List<MainInflowRankingDTO> lists) {
        try{
        	mongoTemplate.insertAll(lists);
		}catch (Exception e){
        	e.printStackTrace();
		}
    }

    /**
     * 根据股票编码和日期关联查询记录
     */
	public List<MainInflowRankingDTO> findBySymbolAndCreate(String symbol) {
		try{
			Criteria criteria = new Criteria();
			criteria.and("symbol").is(symbol);
			criteria.and("created").is(DateFormatUtils.getDay(null));
			Query query = new Query(criteria);
			return mongoTemplate.find(query,MainInflowRankingDTO.class);
		}catch (Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void updateBySymbolAndCreate(List<MainInflowRankingDTO> lists) {
        //创建文档
        for(MainInflowRankingDTO tmp:lists) {
			try{
				Criteria criteria = new Criteria();
				criteria.and("symbol").is(tmp.getSymbol());
				criteria.and("created").is(DateFormatUtils.getDay(new Date()));
				Query query = new Query(criteria);
				Update update = Update.update("tmp",tmp);
				mongoTemplate.updateMulti(query,update,"main_inflow_ranking");
			}catch (Exception e){
				e.printStackTrace();
			}
        }
	}
}
