/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queries;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mycompany.gutenbergproject.connections.MongoDBConnection;
import entities.AuthorBook;
import entities.Book;
import entities.BookCities;
import entities.City;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.bson.Document;

/**
 *
 * @author OpieOP
 */
public class MongoQueries {

    private MongoDBConnection connection = null;
    private static MongoClient mongo = null;
    
    public MongoQueries() {
        this.connection = new MongoDBConnection();
        this.mongo = connection.getMongoConnection();
    }
    
    public static void main(String[] args) {
        MongoQueries connection = new MongoQueries();
        ArrayList<Book> books = getBooksMentioningRange( 40.6995f,-99.0815f, 10000);
        for (Book book : books) {
            System.out.println("Book Title: " + book.getTitle());
        }
    }
    
    public static ArrayList<AuthorBook> getMentioningBooksWithAuthors(String cityname) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static ArrayList<BookCities> getBooksAndCities(String authorname) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static ArrayList<City> mentionedCities(String booktitle) {
        ArrayList<Integer> mentions = new ArrayList();
        ArrayList<City> cities = new ArrayList<>();
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
            cities.add(new City(doc.getInteger("id"), doc.getString("name") ));
        }
        return cities;
    }

    public static ArrayList<Book> getBooksMentioningRange(float latitude, float longtitude, int leeway) {
         ArrayList<Book> nearbyBooks = new ArrayList<>();
        Set<Integer> nearbyCities = new HashSet<>();
        Set<Integer> nearbyBookIds = new HashSet<>();
        MongoDatabase db = mongo.getDatabase("tester5");
        MongoCollection<Document> coll = db.getCollection("cities");
        float[] cords = {longtitude, latitude};

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
        query3.put("id", new BasicDBObject("$in", nearbyBookIds));
        FindIterable<Document> docs3 = coll3.find(query3);
        for (Document doc : docs3) {
            nearbyBooks.add(new Book(doc.getInteger("id"),doc.getString("title")));
        }
        return nearbyBooks;
    }
    
}
