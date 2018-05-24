/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package performanceTest;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import queries.QueryInterface;

/**
 *
 * @author OpieOP
 */
public class Mongo_performanceTest {
    
    
    private static final String[] citySet = {"'Sar-e Pul'","'Szczecin'","'New York City'","'London'", "'Copenhagen'"};
    private static final String[] titleSet = {"Charlotte Brontë and Her Circle", "I Married a Ranger, A Short Method Of Prayer", "In the Track of the Bookworm", "Much Ado About Something","Bimbi, Stories for Children"};
    private static final String[] authorSet = {"Abbott, Edwin Abbott", "Karasowski, Maurycy" , "Taylor, Robert Bruce", "Sherman, Frederic, Mrs.", "Terry, Dorothy"};
    private static final float[][] coordinateSet = {{51f,0f},{36.2154f, 65.9325f},{53.4289f,14.553f},{40.7143f,-74.006f},{55.6759f,12.5655f}};
    
    private static long start;
    private static long end;

    
    public Mongo_performanceTest() {
        
    }
    
    public static void main(String[] args) {
        
        ArrayList<Long> myResults = MongoPerformance();
        System.out.println("Dette er mine resultater: " + myResults);
    }
    
    public static ArrayList<Long> MongoPerformance(){
        ArrayList<Long> results = new ArrayList();
        start = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            getMentioningBooksWithAuthorTest(citySet[i]);
        }
        end = System.currentTimeMillis();
        results.add((end-start)/1000L);
        
        start = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            mentionedCitiesTest(titleSet[i]);
        }
        end = System.currentTimeMillis();
        results.add((end-start)/1000L);
        
        start = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            getBooksAndCitiesTest(authorSet[i]);
        }
        end = System.currentTimeMillis();
        results.add((end-start)/1000L);
        
        start = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            getBooksMentioningRange(coordinateSet[i][0],coordinateSet[i][1], 10000);
        }
        end = System.currentTimeMillis();
        results.add((end-start)/1000L);
        
        return results;
    }
    
    public void setUp() {
        
    }
    

    public static void getMentioningBooksWithAuthorTest(String cityname) {
        System.out.println("-----------------------------------: Starting Test! ");
        QueryInterface.getMentioningBooksWithAuthors(cityname, QueryInterface.DBChoice.DB_MONGODB);
    }

    public static void mentionedCitiesTest(String booktitle) {
        QueryInterface.mentionedCities(booktitle, QueryInterface.DBChoice.DB_MONGODB);
    }

    public static void getBooksAndCitiesTest(String authorname) {
        QueryInterface.getBooksAndCities(authorname, QueryInterface.DBChoice.DB_MONGODB);
    }

    public static void getBooksMentioningRange(float latitude, float longtitude, int i) {
        QueryInterface.getBooksMentioningRange(latitude, longtitude, i, QueryInterface.DBChoice.DB_MONGODB);
    }
}
