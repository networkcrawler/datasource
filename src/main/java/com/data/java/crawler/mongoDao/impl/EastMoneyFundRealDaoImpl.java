package com.data.java.crawler.mongoDao.impl;

import java.util.LinkedList;
import java.util.List;

import org.bson.Document;

import com.data.java.crawler.dto.EastMoneyFundRealPerDTO;
import com.data.java.crawler.mongoDao.EastMoneyFundRealDao;
import com.data.java.crawler.utils.MongoConnectUtils;
import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.DB;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateManyModel;

public class EastMoneyFundRealDaoImpl implements EastMoneyFundRealDao {

	public void insert(List<EastMoneyFundRealPerDTO> lists) {
		//��ȡ���ݿ�
		MongoDatabase mongoDatabase = MongoConnectUtils.connectDB("eastmoneycenter");
		//��ȡ����
		MongoCollection collection = mongoDatabase.getCollection("rund_real_per");
		//�����ĵ�
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
			
			documents.add(document);
		}
		
		//�������ݿ�
		collection.insertMany(documents);
		
	}

	public EastMoneyFundRealPerDTO findOneBySymbol(String symbol) {
		// ��ȡ���ݿ�
		MongoDatabase database = MongoConnectUtils.connectDB("eastmoneycenter");
		
		//��ȡ����
		MongoCollection collection = database.getCollection("rund_real_per");
		
		//��ѯ����
		FindIterable<EastMoneyFundRealPerDTO> fil = collection.find(Filters.eq("symbol", symbol),EastMoneyFundRealPerDTO.class);
		System.out.println(fil);
		return fil.first();
	}

	public void update(List<EastMoneyFundRealPerDTO> lists) {
		// ��ȡ����
		MongoDatabase database = MongoConnectUtils.connectDB("eastmoneycenter");
		//��ȡ����
		MongoCollection collection = database.getCollection("rund_real_per");
		//��װ����
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