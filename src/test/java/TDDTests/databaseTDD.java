package TDDTests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mycompany.gutenbergproject.connections.MongoDBConnection;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Opinator
 */
public class databaseTDD {

    private MongoClient mongo;

    public databaseTDD() {
    }

    @Before
    public void setUp() {
        mongo = getMongoConnection();
        //MySQLConnection sql = null;
        //Neo4jConnection; 
    }

    @Test
    public void getAllBooksMongoTest() {
        int bookCount;
        MongoClient myMongo = getMongoConnection();
        ArrayList<String> books = new ArrayList<>(); 
        DB db = getMongoDB(myMongo);
        DBCollection table = db.getCollection("books");
        DBCursor cursor = table.find();
        for (DBObject DBobject : cursor) {
            books.add(DBobject.get("Title").toString());
        }
        assertEquals(books.size(), 10);
    }
    
    
    public MongoClient getMongoConnection() {
        if (mongo != null) {
            return mongo;
        } else {
            mongo = new MongoClient("localhost", 27017);
            return mongo;
        }
    }
    
    public DB getMongoDB(MongoClient client) {
        DB db = client.getDB("bookstore");
        return db;
    }

    @Test
    public void getAllBooksSQL() {

    }

    @Test
    public void getAllBooksGraph() {

    }

//    //Match string with books and find equal or similar titles. 
//    @Test
//    public void getBooksByNameTest() {
//        
//    }
//    //Provide a list of all cities "supported"  
//    @Test
//    public void getAllCitiesTest() {
//        
//    }
//    //Get cities (or something else) within a specified range
//    @Test 
//    public void getNearbyCitiesTest(){
//    
//    }
//    //Get all mentioned cities from a book. 
//    @Test
//    public void getMentionedCitiesTest(){
//    
//    }
//    //Plot a list of city names on a map. 
//    @Test
//    public void plotCitiesTest(){
//    
//    }
}
