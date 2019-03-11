package com.data.java.crawler.utils;
import com.mongodb.MongoClient;
/**
 * �����������ݿ�Ĺ�����
 * @author admin
 *
 */
import com.mongodb.client.MongoDatabase;

public class MongoConnectUtils {
	/**
	 * ���ӵ����ݿ�
	 * @return
	 */
	public static MongoDatabase connectDB(String db) {
		try{   
		    // ���ӵ� mongodb ����
		    MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
		       
		    // ���ӵ����ݿ�
		    MongoDatabase mongoDatabase = mongoClient.getDatabase(db);
		    return mongoDatabase;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
