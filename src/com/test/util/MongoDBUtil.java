package com.test.util;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoDBUtil {

    public static MongoDatabase getConnect() {
        MongoClient mongoclient = new MongoClient("localHost", 27017);
        MongoDatabase mongoDatabase = mongoclient.getDatabase("test");
        return mongoDatabase;
    }

//    public static MongoDatabase getConnect() {
//        List<ServerAddress> adds = new ArrayList<>();
//        ServerAddress serverAddress = new ServerAddress("192.168.0.122", 8635);
//        adds.add(serverAddress);
//        List<MongoCredential> credentials = new ArrayList<>();
//        MongoCredential mongoCredential = MongoCredential.createScramSha1Credential("rwuser", "admin", "Huawei@123##".toCharArray());
//        credentials.add(mongoCredential);
//        MongoClient mongoClient=new MongoClient(adds,credentials);
//        MongoDatabase mongoDatabase=mongoClient.getDatabase("test");
//        return mongoDatabase;
//    }
}
