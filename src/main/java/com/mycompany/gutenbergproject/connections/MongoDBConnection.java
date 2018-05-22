/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gutenbergproject.connections;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author OpieOP
 */
public class MongoDBConnection {

    private static MongoClient mongo = null;

    public MongoDBConnection() {
    }

    public MongoClient getMongoConnection() {
        try {
            if (mongo != null) {
                return mongo;
            } else {
                mongo = new MongoClient("localhost", 27017);

            }
        } catch (Exception e) {
            System.out.println("ERROR: Cannot connect to MongoDatabase, stacktrace: " + e.getMessage());
        } finally {
            return mongo;
        }
    }
//    public static DB getMongoDB(MongoClient client) {
//        DB db = client.getDB("bookstore");
//        return db;
//    }

}
