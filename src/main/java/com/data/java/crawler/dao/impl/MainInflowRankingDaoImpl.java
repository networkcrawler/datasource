package com.data.java.crawler.dao.impl;

import com.data.java.crawler.dao.MainInflowRankingDao;
import com.data.java.crawler.dto.MainInflowRankingDTO;
import com.data.java.crawler.utils.MongoConnectUtils;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.List;

public class MainInflowRankingDaoImpl implements MainInflowRankingDao {
    public void insertMany(List<MainInflowRankingDTO> lists) {
        //连接数据库
        MongoDatabase database = MongoConnectUtils.connect("eastmoneycenter");
        //获取集合
        MongoCollection collection = database.getCollection("main_inflow_ranking");

        //创建文档

    }
}
