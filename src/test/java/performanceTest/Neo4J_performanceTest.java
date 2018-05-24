/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package performanceTest;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author OpieOP
 */
public class Neo4J_performanceTest  implements testInterface {
    
    private final String[] citySet = {"'Sar-e Pul'","'Szczecin'","'New York City'","'London'", "'Copenhagen'"};
    private final String[] titleSet = {"Charlotte Brontë and Her Circle", "I Married a Ranger, A Short Method Of Prayer", "In the Track of the Bookworm", "Much Ado About Something","Bimbi, Stories for Children"};
    private final String[] authorSet = {"Abbott, Edwin Abbott", "Karasowski, Maurycy" , "Taylor, Robert Bruce", "Sherman, Frederic, Mrs.", "Terry, Dorothy"};
    private final float[][] coordinateSet = {{51f,0f},{36.2154f, 65.9325f},{53.4289f,14.553f},{40.7143f,-74.006f},{55.6759f,12.5655f}};
    
    public Neo4J_performanceTest() {
    }
    
    @Before
    public void setUp() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

    @Override
    public void getMentioningBooksWithAuthorTest(String cityname) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mentionedCitiesTest(String booktitle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void getBooksAndCitiesTest(String authorname) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void getBooksMentioningRange(float latitude, float longtitude, int leeway) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
