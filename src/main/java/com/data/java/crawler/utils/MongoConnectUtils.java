package com.data.java.crawler.utils;
import com.mongodb.MongoClient;
/**
 * 定义链接数据库的工具类
 * @author admin
 *
 */
import com.mongodb.client.MongoDatabase;

public class MongoConnectUtils {
	/**
	 * 连接到数据库
	 * @return
	 */
	public static MongoDatabase connectDB(String db) {
		try{   
		    // 连接到 mongodb 服务
		    MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
		       
		    // 连接到数据库
		    MongoDatabase mongoDatabase = mongoClient.getDatabase(db);
		    return mongoDatabase;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
