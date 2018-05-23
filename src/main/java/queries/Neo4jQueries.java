/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queries;

import entities.AuthorBook;
import entities.Book;
import entities.BookCities;
import java.util.ArrayList;

/**
 *
 * @author Micha
 */
public class Neo4jQueries {

     public static ArrayList<AuthorBook> getMentioningBooksWithAuthors(String cityname) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static ArrayList<BookCities> getBooksAndCities(String authorname) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static ArrayList<Book> mentionedCities(String booktitle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static ArrayList<Book> getBooksMentioningRange(double latitude, double longtitude, int leeway) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
