package com.data.java.crawler.dao.impl;

import com.data.java.crawler.dao.MainInflowRankingDao;
import com.data.java.crawler.dto.EastMoneyFundRealPerDTO;
import com.data.java.crawler.dto.MainInflowRankingDTO;
import com.data.java.crawler.utils.DateFormatUtils;
import com.data.java.crawler.utils.MongoConnectUtils;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.bson.Document;

public class MainInflowRankingDaoImpl implements MainInflowRankingDao {
	
    public void insertMany(List<MainInflowRankingDTO> lists) {
        //连接数据库
        MongoDatabase database = MongoConnectUtils.connect("eastmoneycenter");
        //获取集合
        MongoCollection collection = database.getCollection("main_inflow_ranking");

        //创建文档
        List<Document> documents = new LinkedList<Document>();
        for(MainInflowRankingDTO tmp:lists) {
        	Document document = new Document();
        	document.put("symbol", tmp.getSymbol());
        	document.put("name", tmp.getName());
        	document.put("lasttrade", tmp.getLasttrade());
        	document.put("todayNetPrimary", tmp.getTodayNetPrimary());
        	document.put("todayRanking", tmp.getTodayRanking());
        	document.put("todayChg", tmp.getTodayChg());
        	document.put("fireNetPrimary", tmp.getFireNetPrimary());
        	document.put("fireRanking", tmp.getFireRanking());
        	document.put("fireChg", tmp.getFireChg());
        	document.put("tenNetPrimary", tmp.getTenNetPrimary());
        	document.put("tenRanking", tmp.getTenRanking());
        	document.put("tenChg", tmp.getTenChg());
        	document.put("plateName", tmp.getPlateName());
        	document.put("plateSymbol", tmp.getPlateSymbol());
        	document.put("created", DateFormatUtils.getDay(new Date()));
        	document.put("updated", DateFormatUtils.getDay(new Date()));
        	
        	documents.add(document);
        }
        collection.insertMany(documents);
    }

    /**
     * 根据股票编码和日期关联查询记录
     */
	public List<MainInflowRankingDTO> findBySymbolAndCreate(String symbol) {
		//连接数据库
        MongoDatabase database = MongoConnectUtils.connect("eastmoneycenter");
        //获取集合
        MongoCollection collection = database.getCollection("main_inflow_ranking");
		//查询数据
        FindIterable<MainInflowRankingDTO> results = collection.find(
        		Filters.and(Filters.eq("symbol", symbol),Filters.eq("created", DateFormatUtils.getDay(null)))
        		);
        
        MongoCursor<MainInflowRankingDTO> ite = results.iterator();
        List<MainInflowRankingDTO> list = new LinkedList<MainInflowRankingDTO>();
        while(ite.hasNext()) {
        	list.add(ite.next());
        }
        
        return list ;
	}

	public void updateBySymbolAndCreate(List<MainInflowRankingDTO> lists) {
		
		//连接数据库
        MongoDatabase database = MongoConnectUtils.connect("eastmoneycenter");
        //获取集合
        MongoCollection collection = database.getCollection("main_inflow_ranking");

        //创建文档
        for(MainInflowRankingDTO tmp:lists) {
        	Document document = new Document();
        	document.put("symbol", tmp.getSymbol());
        	document.put("name", tmp.getName());
        	document.put("lasttrade", tmp.getLasttrade());
        	document.put("todayNetPrimary", tmp.getTodayNetPrimary());
        	document.put("todayRanking", tmp.getTodayRanking());
        	document.put("todayChg", tmp.getTodayChg());
        	document.put("fireNetPrimary", tmp.getFireNetPrimary());
        	document.put("fireRanking", tmp.getFireRanking());
        	document.put("fireChg", tmp.getFireChg());
        	document.put("tenNetPrimary", tmp.getTenNetPrimary());
        	document.put("tenRanking", tmp.getTenRanking());
        	document.put("tenChg", tmp.getTenChg());
        	document.put("plateName", tmp.getPlateName());
        	document.put("plateSymbol", tmp.getPlateSymbol());
        	document.put("updated", DateFormatUtils.getDay(new Date()));
        	
        	collection.updateOne(
        			Filters.and(
        					Filters.eq("symbol",tmp.getSymbol()),
        					Filters.eq("created", DateFormatUtils.getDay(new Date()))
        					), document);
        }
	}
}
