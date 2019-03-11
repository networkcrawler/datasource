package com.data.java.crawler.utils;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * 数据库连接工具类
 */
public class MongoConnectUtils {

    public static MongoDatabase connect(String database){
        MongoDatabase mongoDatabase = null;
        try {
            // 连接到 mongodb 服务
            MongoClient mongoClient = new MongoClient( "localhost" , 27017 );

            // 连接到数据库
            mongoDatabase = mongoClient.getDatabase(database);
        }catch (Exception e){
            e.printStackTrace();
        }
        return mongoDatabase;
    }
}
