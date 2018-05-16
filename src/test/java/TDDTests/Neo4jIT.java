/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDDTests;

import com.mycompany.gutenbergproject.connections.Neo4jConnection;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.neo4j.driver.v1.Driver;

/**
 *
 * @author Micha
 */
public class Neo4jIT {
    
    @Test
    @DisplayName("Returns all titles and authors from a book in a given city")
    public void getBooksFromCityIT(){
        Driver driver = Neo4jConnection.getConnection();
    }
    
    @Test
    @DisplayName("Returns all cities mentioned in a books title and plots them onto a map")
    public void getCityFromBookTitleIT(){
        
    }
    
    @Test
    @DisplayName("Returns all books written by that author and plots mentioned cities in those books onto a map")
    public void getBooksByAuthorIT(){
        
    }
    
    @Test
    @DisplayName("Returns all books mentioning a city in vicinity of the given geolocation")
    public void getBooksByGeolocationIT(){
        
    }
}
