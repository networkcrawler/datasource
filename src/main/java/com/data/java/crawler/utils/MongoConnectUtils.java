package com.data.java.crawler.utils;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
/**
 * �����������ݿ�Ĺ�����
 * @author admin
 *
 */


/**
 * ���ݿ����ӹ�����
 */
public class MongoConnectUtils {

    public static MongoDatabase connect(String database){
        MongoDatabase mongoDatabase = null;
        try {
            // ���ӵ� mongodb ����
            MongoClient mongoClient = new MongoClient( "localhost" , 27017 );

            // ���ӵ����ݿ�
            mongoDatabase = mongoClient.getDatabase(database);
        }catch (Exception e){
            e.printStackTrace();
        }
        return mongoDatabase;
    }
}