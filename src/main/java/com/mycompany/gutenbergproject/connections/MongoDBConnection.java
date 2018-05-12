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

    private static MongoClient mongo;

    public static void main(String[] args) {
        MongoClient myMongo = getMongoConnection();
//      File[] files = getAllFiles();
//      generateTestData(files);

        ArrayList<String> books = getAllBookNames("books");
        System.out.println("List of all current books: \n");
        for (String book : books) {
            System.out.print(book);
        }
    }

    public static MongoClient getMongoConnection() {
        if (mongo != null) {
            return mongo;
        } else {
            mongo = new MongoClient("localhost", 27017);
            return mongo;
        }
    }

    public static DB getMongoDB(MongoClient client) {
        DB db = client.getDB("bookstore");
        return db;
    }

    public static void generateTestData(File[] files) {

        ArrayList<BasicDBObject> documents = new ArrayList<>();
        DB database = getMongoDB(mongo);
        DBCollection table = database.getCollection("books");

        for (File file : files) {
            BasicDBObject doc = new BasicDBObject();
            String contents = getBookContent("BooksSmall//" + file.getName());

            String[] title = splitter("Title:", contents);
            String[] author = splitter("Author:", contents);
            String[] releaseDate = splitter("Release Date:", contents);
            String[] language = splitter("Language:", contents);

            doc.append(title[0], title[1]);
            doc.append(author[0], author[1]);
            doc.append(releaseDate[0], releaseDate[1]);
            doc.append(language[0], language[1]);

            documents.add(doc);
        }
        for (BasicDBObject doc : documents) {
            table.insert(doc);
        }
    }

    public static String[] splitter(String indexer, String contents) {
        int startIndex = contents.indexOf(indexer);
        int endIndex = contents.indexOf("\n", startIndex + 1);
        return contents.substring(startIndex, endIndex).split(":");
    }

    public static ArrayList<String> getAllBookNames(String collection) {
        ArrayList<String> books = new ArrayList<>();
        DBCollection table = getMongoDB(mongo).getCollection(collection);
        DBCursor cursor = table.find();
        for (DBObject DBobject : cursor) {
            books.add(DBobject.get("Title").toString());
        }
        return books;
    }

    public static String getBookContent(String fileName) {
        String text = "";
        try (Scanner scanner = new Scanner(new File(fileName)) // Put this call in a finally block
        ) {
            text = scanner.useDelimiter("\\A").next();
        } catch (FileNotFoundException ex) {
            System.out.println("File Not Found Exception :)");
        }
        return text;
    }

    public static File[] getAllFiles() {
        File folder = new File("Bookssmall");
        File[] listOfFiles = folder.listFiles();
        return listOfFiles;
    }

    public String getBookInfo(String bookName) {
        DB db = getMongoDB(mongo);
        DBCollection table = db.getCollection("books");
        //    return table.find("");
        return null;
    }

    public static void databaseTest(MongoClient client) {
        DB db = client.getDB("test");
        DBCollection table = db.getCollection("testersxD");
        BasicDBObject document = new BasicDBObject();
        document.put("name", "Anders");
        document.put("age", 12);
        document.put("createdDate", new Date());
        table.insert(document);

    }

}
