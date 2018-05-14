package TDDTests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.mycompany.gutenbergproject.connections.MongoDBConnection;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Opinator
 */
public class databaseTDD {

    private MongoDBConnection mongo;

    public databaseTDD() {
    }

    @Before
    public void setUp() {
        mongo = new MongoDBConnection();
        //MySQLConnection sql = null;
        //Neo4jConnection; 
    }

    
    @Test
    public void getAllBooks(){
    
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
