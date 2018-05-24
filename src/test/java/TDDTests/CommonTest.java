/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDDTests;

import entities.AuthorBook;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;
import queries.QueryInterface;
import queries.QueryInterface.DBChoice;
import static queries.QueryInterface.DBChoice.DB_MYSQL;

/**
 *
 * @author Lasse
 */
public class CommonTest {
    
    private static final DBChoice TEST_DB = DB_MYSQL;
    
    @Test
    public void testQuery1() {
        ArrayList<AuthorBook> ab;
        
        ab = QueryInterface.getMentioningBooksWithAuthors("Sar-e Pul", TEST_DB);
        Assert.assertEquals(3, ab.size());
        
        ab = QueryInterface.getMentioningBooksWithAuthors("Paris", TEST_DB);
        Assert.assertEquals(23414, ab.size());
        
        ab = QueryInterface.getMentioningBooksWithAuthors("London", TEST_DB);
        Assert.assertEquals(29856, ab.size());
        
        ab = QueryInterface.getMentioningBooksWithAuthors("Rio de Janeiro", TEST_DB);
        Assert.assertEquals(648, ab.size());
        
        ab = QueryInterface.getMentioningBooksWithAuthors("Santa Cruz de la Sierra", TEST_DB);
        Assert.assertEquals(20, ab.size());
    }
}
