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
import java.util.List;
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
        ArrayList<BookCities> bookCities = getBooksAndCities("Abbott, Evelyn");
        int count = 0;
        for (BookCities bookCity : bookCities) {
            if (count == 5) {
                break;
            }
            System.out.println(bookCity.getBookName() + ": ");
            System.out.println(bookCity.getCities());
            count++;
        }

        //ArrayList<Book> books = getBooksMentioningRange( 40.6995f,-99.0815f, 10000);
        //ArrayList<City> cities = mentionedCities("—And Devious the Line of Duty");
        //ArrayList<AuthorBook> myStuff = getMentioningBooksWithAuthors("'Sar-e Pul'");
    }

    public static ArrayList<AuthorBook> getMentioningBooksWithAuthors(String cityname) {
        ArrayList<Integer> mentions = new ArrayList();
        ArrayList<AuthorBook> authorBook = new ArrayList<>();
        MongoDatabase db = mongo.getDatabase("tester5");
        MongoCollection<Document> coll = db.getCollection("bookCityRelations");

        BasicDBObject query = new BasicDBObject("city_id", getIntegerId(cityname, db.getCollection("cities"), "name"));
        FindIterable<Document> docs = coll.find(query);
        for (Document doc : docs) {
            mentions.add(doc.getInteger("book_id"));
        }
        BasicDBObject query2 = new BasicDBObject();
        query2.put("id", new BasicDBObject("$in", mentions));
        MongoCollection<Document> coll2 = db.getCollection("bookFinal");
        FindIterable<Document> docs2 = coll2.find(query2);
        for (Document doc : docs2) {
            authorBook.add(new AuthorBook(doc.getString("author"), doc.getString("title")));
        }
        return authorBook;
    }

    public static ArrayList<BookCities> getBooksAndCities(String authorname) {
        List<Document> booksFromAuthor = new ArrayList();
        List<Integer> authorsRelations = new ArrayList();
        MongoDatabase db = mongo.getDatabase("tester5");
        MongoCollection<Document> coll = db.getCollection("authorBookRelations");
        BasicDBObject query = new BasicDBObject("author_id", getIntegerId(authorname, db.getCollection("authors2"), "name"));
        FindIterable<Document> docs = coll.find(query);
        for (Document doc : docs) {
            authorsRelations.add(doc.getInteger("book_id"));
        }
        BasicDBObject query2 = new BasicDBObject();
        query2.put("id", new BasicDBObject("$in", authorsRelations));
        MongoCollection<Document> coll2 = db.getCollection("books");
        FindIterable<Document> docs2 = coll2.find(query2);
        for (Document doc : docs2) {
            booksFromAuthor.add(doc);
        }
        MongoCollection<Document> coll3 = db.getCollection("bookCityRelations");
        MongoCollection<Document> coll4 = db.getCollection("cities");
        ArrayList<BookCities> citiesBooks = new ArrayList();
        for (Document doc : booksFromAuthor) {
            BasicDBObject query3 = new BasicDBObject("book_id", doc.getInteger("id"));
            FindIterable<Document> docs3 = coll3.find(query3);
            List<Integer> cityIds = new ArrayList();
            for (Document document : docs3) {
                cityIds.add(document.getInteger("city_id"));
            }
            BasicDBObject query4 = new BasicDBObject();
            query4.put("id", new BasicDBObject("$in", cityIds));
            FindIterable<Document> docs4 = coll4.find(query4);
            List<String> bookCities = new ArrayList();
            for (Document document : docs4) {
                bookCities.add(document.getString("name"));
            }
            citiesBooks.add(new BookCities(doc.getString("title"), bookCities));
        }

        return citiesBooks;
    }

    public static ArrayList<City> mentionedCities(String booktitle) {
        ArrayList<Integer> mentions = new ArrayList();
        ArrayList<City> cities = new ArrayList<>();
        MongoDatabase db = mongo.getDatabase("tester5");
        MongoCollection<Document> coll = db.getCollection("bookCityRelations");
        BasicDBObject query = new BasicDBObject("book_id", getIntegerId(booktitle, db.getCollection("books"), "title"));
        FindIterable<Document> docs = coll.find(query);
        for (Document doc : docs) {
            mentions.add(doc.getInteger("city_id"));
        }
        System.out.println("metions: " + mentions);
        BasicDBObject query2 = new BasicDBObject();
        query2.put("id", new BasicDBObject("$in", mentions));
        MongoCollection<Document> coll2 = db.getCollection("cities");
        FindIterable<Document> docs2 = coll2.find(query2);
        System.out.println(docs2.first());
        for (Document doc : docs2) {
            String[] numbers = doc.toString().replaceAll("[^0-9.,]", "").split(",");
            cities.add(new City(doc.getInteger("id"), doc.getString("name"), Float.parseFloat(numbers[5]), Float.parseFloat(numbers[4])));
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
            nearbyBooks.add(new Book(doc.getInteger("id"), doc.getString("title")));
        }
        return nearbyBooks;
    }

    private static Integer getIntegerId(String name, MongoCollection<Document> mongoColl, String type) {
        BasicDBObject query = new BasicDBObject(type, name);
        FindIterable<Document> docs = mongoColl.find(query);
        return docs.first().getInteger("id");
    }

    private static ArrayList<Document> getRelations() {
        ArrayList<Document> relations = new ArrayList();
        
        return relations;
    }   

}
