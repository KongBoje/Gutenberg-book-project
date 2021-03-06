/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDDTests;

import entities.AuthorBook;
import entities.Book;
import entities.BookCity;
import entities.City;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;
import queries.QueryInterface;
import queries.QueryInterface.DBChoice;
import static queries.QueryInterface.DBChoice.DB_MYSQL;
import static queries.QueryInterface.DBChoice.DB_NEO4J;

/**
 *
 * @author Lasse
 */
public class CommonTest {

    private static final DBChoice TEST_DB = DB_NEO4J;

    @Test
    public void testQuery1() {
        ArrayList<AuthorBook> ab;

        ab = QueryInterface.getMentioningBooksWithAuthors("'Sar-e Pul'", TEST_DB);
        Assert.assertEquals(3, ab.size());

        ab = QueryInterface.getMentioningBooksWithAuthors("'Paris'", TEST_DB);
        Assert.assertEquals(23414, ab.size());

        ab = QueryInterface.getMentioningBooksWithAuthors("'London'", TEST_DB);
        Assert.assertEquals(29856, ab.size());

        ab = QueryInterface.getMentioningBooksWithAuthors("'Rio de Janeiro'", TEST_DB);
        Assert.assertEquals(648, ab.size());

        ab = QueryInterface.getMentioningBooksWithAuthors("'Santa Cruz de la Sierra'", TEST_DB);
        Assert.assertEquals(20, ab.size());

    }

    @Test
    public void testQuery2() {
        ArrayList<City> cities;

        cities = QueryInterface.mentionedCities("I Married A Ranger", TEST_DB);
        Assert.assertEquals(57, cities.size());

        cities = QueryInterface.mentionedCities("Zophiel\nA Poem", TEST_DB);
        Assert.assertEquals(24, cities.size());

        cities = QueryInterface.mentionedCities("I Say No", TEST_DB);
        Assert.assertEquals(0, cities.size());

        cities = QueryInterface.mentionedCities("A Roman Singer", TEST_DB);
        Assert.assertEquals(38, cities.size());

        cities = QueryInterface.mentionedCities("Among the Canadian Alps", TEST_DB);
        Assert.assertEquals(84, cities.size());
    }

    @Test
    public void testQuery3() {
        ArrayList<BookCity> bc;

        bc = QueryInterface.getBooksAndCities("Orr, Lyndon", TEST_DB);
        Assert.assertEquals(569, bc.size());

        bc = QueryInterface.getBooksAndCities("Pulci, Luigi", TEST_DB);
        Assert.assertEquals(104, bc.size());

        bc = QueryInterface.getBooksAndCities("Inglis, James", TEST_DB);
        Assert.assertEquals(29, bc.size());

        bc = QueryInterface.getBooksAndCities("Elliott, George Roy", TEST_DB);
        Assert.assertEquals(114, bc.size());

        bc = QueryInterface.getBooksAndCities("Favenc, Ernest", TEST_DB);
        Assert.assertEquals(163, bc.size());

    }

    @Test
    public void testQuery4() {
        ArrayList<Book> boox;

        boox = QueryInterface.getBooksMentioningRange(31, 65, 1, TEST_DB);
        Assert.assertEquals(186, boox.size());

        boox = QueryInterface.getBooksMentioningRange(45, 80, 1, TEST_DB);
        Assert.assertEquals(0, boox.size());

        boox = QueryInterface.getBooksMentioningRange(45, 23, 1, TEST_DB);
        Assert.assertEquals(1407, boox.size());

        boox = QueryInterface.getBooksMentioningRange(10, 19, 1, TEST_DB);
        Assert.assertEquals(5, boox.size());

        boox = QueryInterface.getBooksMentioningRange(14, 4, 1, TEST_DB);
        Assert.assertEquals(69, boox.size());
    }
}
