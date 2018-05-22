/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gutenbergproject.connections;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import static com.mongodb.client.model.Filters.near;
import com.mongodb.client.model.geojson.Position;
import java.awt.print.Book;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javafx.scene.effect.Light.Point;
import org.bson.Document;

/**
 *
 * @author OpieOP
 */
public class MongoDBDataAcess {

    private MongoDBConnection connection = null;
    private static MongoClient mongo = null;

    public MongoDBDataAcess() {
        this.connection = new MongoDBConnection();
        this.mongo = connection.getMongoConnection();
    }

    public static void main(String[] args) {
        MongoDBDataAcess dbAcess= new MongoDBDataAcess();
        
        ArrayList<Document> myBooks = getBooksMentioningNearbyCities(-74.0059731f, 40.7143528f, 1000);
        for (Document myBook : myBooks) {
            System.out.println("my book: " + myBook.getString("title"));
        }
        MongoDatabase db = mongo.getDatabase("tester5");
        MongoIterable collections = db.listCollectionNames();
        for (Object collection : collections) {
            System.out.println(collection);
        }

    }

    public static ArrayList<Document> getCityMentionsFromBook(int bookId) {
        ArrayList<Integer> mentions = new ArrayList();
        ArrayList<Document> cities = new ArrayList<>();
        MongoDatabase db = mongo.getDatabase("tester5");
        MongoCollection<Document> coll = db.getCollection("bookCityRelations");
        BasicDBObject query = new BasicDBObject("book_id", bookId);
        FindIterable<Document> docs = coll.find(query);
        for (Document doc : docs) {
            mentions.add(doc.getInteger("city_id"));
        }
        System.out.println("metions: " + mentions);
        BasicDBObject query2 = new BasicDBObject();
        query2.put("id", new BasicDBObject("$in", mentions));
        MongoCollection<Document> coll2 = db.getCollection("cities");
        FindIterable<Document> docs2 = coll2.find(query2);
        for (Document doc : docs2) {
            cities.add(doc);
        }
        return cities;
    }

    public static ArrayList<Document> getBooksByAuthor(int authorId) {
        ArrayList<Document> booksFromAuthor = new ArrayList();
        ArrayList<Integer> authorsRelations = new ArrayList();
        MongoDatabase db = mongo.getDatabase("tester5");
        MongoCollection<Document> coll = db.getCollection("authorBookRelations");
        BasicDBObject query = new BasicDBObject("author_id", authorId);
        FindIterable<Document> docs = coll.find(query);
        for (Document doc : docs) {
            authorsRelations.add(doc.getInteger("book_id"));
        }
        BasicDBObject query2 = new BasicDBObject();
        query2.put("id", new BasicDBObject("$in", authorsRelations));
        MongoCollection<Document> coll2 = db.getCollection("books");
        FindIterable<Document> docs2 = coll2.find(query2);
        for (Document doc : docs2) {
            System.out.println(doc.toJson());
            booksFromAuthor.add(doc);
        }
        return booksFromAuthor;
    }

    public static ArrayList<Document> getAuthorMentionedCities(int authorId) {
        ArrayList<Document> CitiesMentioned = new ArrayList();
        Set<Integer> authorCities = new HashSet<>();
        MongoDatabase db = mongo.getDatabase("tester5");
        MongoCollection<Document> coll = db.getCollection("allRelations");
        BasicDBObject query = new BasicDBObject("author_id", authorId);
        FindIterable<Document> docs = coll.find(query);
        for (Document doc : docs) {
            authorCities.add(doc.getInteger("city_id"));
        }
        BasicDBObject query2 = new BasicDBObject();
        query2.put("id", new BasicDBObject("$in", authorCities));
        MongoCollection<Document> coll2 = db.getCollection("cities");
        FindIterable<Document> docs2 = coll2.find(query2);
        for (Document doc : docs2) {
            CitiesMentioned.add(doc);
        }
        //DBObject query = QueryBuilder.start("id").in(new String[] {"foo", "bar"}).get();        
        return CitiesMentioned;
    }

    public static ArrayList<Document> getBooksMentioningNearbyCities(float longtitude, float lattitude, int leeway) {
        ArrayList<Document> nearbyBooks = new ArrayList<>();
        Set<Integer> nearbyCities = new HashSet<>();
        Set<Integer> nearbyBookIds = new HashSet<>();
        MongoDatabase db = mongo.getDatabase("tester5");
        MongoCollection<Document> coll = db.getCollection("cities");
        float[] cords = {longtitude, lattitude};
        
        BasicDBObject queryOnlyFind = new BasicDBObject("location", 
                new BasicDBObject("$nearSphere", new BasicDBObject("type", "Point").append("coordinates", cords)).append("$maxDistance", leeway));
        
        FindIterable<Document> docs = coll.find(queryOnlyFind);
        for (Document doc : docs) {
            nearbyCities.add(doc.getInteger("id"));
        }
        MongoCollection<Document> coll2 = db.getCollection("bookCityRelations");
        BasicDBObject query2 = new BasicDBObject();
        query2.put("id", new BasicDBObject("$in", nearbyCities));
        FindIterable<Document> docs2 = coll2.find(query2);
        for (Document doc : docs2) {
            nearbyBookIds.add(doc.getInteger("book_id"));
        }
        MongoCollection<Document> coll3 = db.getCollection("books");
        BasicDBObject query3 = new BasicDBObject();
        query2.put("id", new BasicDBObject("$in", nearbyBookIds));
        FindIterable<Document> docs3 = coll3.find(query2);
        for (Document doc : docs3) {
            nearbyBooks.add(doc);
        }
        return nearbyBooks;
    }

    public static ArrayList<String> getAllBookNames(String collectionName) {
        ArrayList<String> books = new ArrayList<>();
        MongoDatabase db = mongo.getDatabase("tester5");
        MongoCollection<Document> collection = db.getCollection(collectionName);
        FindIterable<Document> docs = collection.find();
        System.out.println("This is a test btw: -----------" + docs.first().toJson());
        for (Document doc : docs) {
            books.add(doc.getString("title"));
        }
        return books;
    }

    public static ArrayList<String> getAllCityNames(String collectionName) {
        ArrayList<String> cities = new ArrayList<>();
        MongoDatabase db = mongo.getDatabase("tester5");
        MongoCollection<Document> collection = db.getCollection(collectionName);
        FindIterable<Document> docs = collection.find();

        for (Document doc : docs) {
            cities.add(doc.getString("long").toString());
        }
        return cities;
    }

    public String getBookInfo(String bookName) {
        // DB db = getMongoDB(mongo);
        // DBCollection table = db.getCollection("books");
        //    return table.find("");
        return null;
    }

}
//    private static void populateWithTestData() {
//        try {
//            MongoDatabase database = mongo.getDatabase("test2");
//            MongoCollection collectionOfBooks = database.getCollection("books");
//            MongoCollection collectionOfCities = database.getCollection("cities");
//            MongoCollection collectionOfMentions = database.getCollection("mentions");
//
//            //cities
//            List<Document> cities = new ArrayList();
//            Document city1 = new Document();
//            city1.put("name", "Madrid");
//            city1.put("lat", 40.41678);
//            city1.put("lon", -3.70379);
//            cities.add(city1);
//            Document city2 = new Document();
//            city2.put("name", "Toledo");
//            city2.put("lat", 39.86283);
//            city2.put("lon", -4.02732);
//            cities.add(city2);
//            Document city3 = new Document();
//            city3.put("name", "Wiltshire");
//            city3.put("lat", 51.34920);
//            city3.put("lon", -1.99271);
//            cities.add(city3);
//
//            collectionOfCities.insertMany(cities);
//
//            //books
//            List<Document> books = new ArrayList();
//            Document book1 = new Document();
//            book1.put("title", "The Three Musketeers");
//            book1.put("author", "Alexandre Dumas");
//            List<Document> b1cities = new ArrayList();
//            b1cities.add(city1);
//            b1cities.add(city2);
//            book1.put("cities", b1cities);
//            books.add(book1);
//
//            Document book2 = new Document();
//            book2.put("title", "The Black Tulip");
//            book2.put("author", "Alexandre Dumas");
//            List<Document> b2cities = new ArrayList();
//            b2cities.add(city1);
//            book2.put("cities", b2cities);
//            books.add(book2);
//
//            Document book3 = new Document();
//            book3.put("title", "Pride and Prejudice");
//            book3.put("author", "Jane Austen");
//            List<Document> b3cities = new ArrayList();
//            b3cities.add(city3);
//            book3.put("cities", b3cities);
//            books.add(book3);
//
//            //mentions
//            List<Document> mentions = new ArrayList();
//            // Structuring of Mentions 
//            Document m1 = new Document();
//            m1.put("book", book1);
//            m1.put("city", city1);
//            m1.put("mentions", 56);
//            mentions.add(m1);
//            Document m2 = new Document();
//            m2.put("book", book1);
//            m2.put("city", city2);
//            m2.put("mentions", 10);
//            mentions.add(m2);
//            Document m3 = new Document();
//            m3.put("book", book2);
//            m3.put("city", city1);
//            m3.put("mentions", 2);
//            mentions.add(m3);
//            Document m4 = new Document();
//            m4.put("book", book3);
//            m4.put("city", city3);
//            m4.put("mentions", 11);
//            mentions.add(m4);
//
//            collectionOfBooks.insertMany(books);
//            collectionOfMentions.insertMany(mentions);
//
//            System.out.println("DONE POPULATING THE DATABASE");
//
//        } catch (Exception e) {
//            System.out.println("SOMETHING WENT WRONG");
//            e.printStackTrace();
//        }
//
//    }
