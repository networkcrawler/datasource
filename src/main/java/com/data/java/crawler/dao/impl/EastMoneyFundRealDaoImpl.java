package com.data.java.crawler.dao.impl;

import java.util.LinkedList;
import java.util.List;

import org.bson.Document;

import com.data.java.crawler.dao.EastMoneyFundRealDao;
import com.data.java.crawler.dto.EastMoneyFundRealPerDTO;
import com.data.java.crawler.utils.DateFormatUtils;
import com.data.java.crawler.utils.MongoConnectUtils;
import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.DB;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateManyModel;

public class EastMoneyFundRealDaoImpl implements EastMoneyFundRealDao {

	public void insert(List<EastMoneyFundRealPerDTO> lists) {
		//获取数据库
		MongoDatabase mongoDatabase = MongoConnectUtils.connect("eastmoneycenter");
		//获取集合
		MongoCollection collection = mongoDatabase.getCollection("rund_real_per");
		//创建文档
		List<Document> documents = new LinkedList<Document>();
		
		for(EastMoneyFundRealPerDTO tmp:lists) {
			Document document = new Document();
			document.put("symbol", tmp.getSymbol());
			document.put("name", tmp.getName());
			document.put("lastTrade", tmp.getLastTrade());
			document.put("chg", tmp.getChg());
			document.put("inflowMain", tmp.getInflowMain());
			document.put("inflowMainPer", tmp.getInflowMainPer());
			document.put("superInflowMain", tmp.getSuperInflowMain());
			document.put("superInflowMainPer", tmp.getSuperInflowMainPer());
			document.put("largeInflowMain",tmp.getLargeInflowMain());
			document.put("largeInflowMainPer", tmp.getLargeInflowMainPer());
			document.put("midInflowMain", tmp.getMidInflowMain());
			document.put("midInflowMainPer", tmp.getMidInflowMainPer());
			document.put("smallInflowMain", tmp.getSmallInflowMain());
			document.put("smallInflowMainPer", tmp.getSmallInflowMainPer());
			document.put("created", DateFormatUtils.getDay(null));
			document.put("updated", DateFormatUtils.getDay(null));

			documents.add(document);
		}
		
		//插入数据库
		collection.insertMany(documents);
		
	}

	public List<EastMoneyFundRealPerDTO> findOneBySymbol(String symbol) {
		// 获取数据库
		MongoDatabase database = MongoConnectUtils.connect("eastmoneycenter");
		
		//获取集合
		MongoCollection collection = database.getCollection("rund_real_per");
		
		//查询数据
		FindIterable<EastMoneyFundRealPerDTO> fil = collection.find(Filters.and(Filters.eq("symbol", symbol),Filters.eq("created", DateFormatUtils.getDay(null))),EastMoneyFundRealPerDTO.class);
		List<EastMoneyFundRealPerDTO> tmp = new LinkedList<EastMoneyFundRealPerDTO>();
		MongoCursor<EastMoneyFundRealPerDTO> it = fil.iterator();
		while(it.hasNext()) {
			tmp.add(it.next());
		}
		
		return tmp;
	}

	public void update(List<EastMoneyFundRealPerDTO> lists) {
		// 获取连接
		MongoDatabase database = MongoConnectUtils.connect("eastmoneycenter");
		//获取集合
		MongoCollection collection = database.getCollection("rund_real_per");
		//组装数据
		for(EastMoneyFundRealPerDTO tmp:lists) {
			Document document = new Document();
			document.put("symbol", tmp.getSymbol());
			document.put("name", tmp.getName());
			document.put("lastTrade", tmp.getLastTrade());
			document.put("chg", tmp.getChg());
			document.put("inflowMain", tmp.getInflowMain());
			document.put("inflowMainPer", tmp.getInflowMainPer());
			document.put("superInflowMain", tmp.getSuperInflowMain());
			document.put("superInflowMainPer", tmp.getSuperInflowMainPer());
			document.put("largeInflowMain",tmp.getLargeInflowMain());
			document.put("largeInflowMainPer", tmp.getLargeInflowMainPer());
			document.put("midInflowMain", tmp.getMidInflowMain());
			document.put("midInflowMainPer", tmp.getMidInflowMainPer());
			document.put("smallInflowMain", tmp.getSmallInflowMain());
			document.put("smallInflowMainPer", tmp.getSmallInflowMainPer());
			
			collection.updateOne(Filters.eq("symbol", tmp.getSymbol()), new Document("$set",document));
		}
	}
}
