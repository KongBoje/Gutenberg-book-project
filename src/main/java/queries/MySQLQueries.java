/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queries;

import entities.AuthorBook;
import entities.Book;
import entities.BookCities;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLQueries {

    private static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://192.168.67.3/gutenberg?user=lasse&password=root");
    }

    public static ArrayList<AuthorBook> getMentioningBooksWithAuthors(String cityname) {
        ArrayList<AuthorBook> ret = new ArrayList<>();

        System.out.println("find with " + cityname);
        
        Connection c = null;
        Statement s = null;
        ResultSet r = null;

        try {
            c = getConnection();
            s = c.createStatement();
            r = s.executeQuery("SELECT book_t.title, author_t.name FROM abc_relation_t "
                    + "INNER JOIN book_t ON book_t.id = abc_relation_t.book_id "
                    + "INNER JOIN author_t ON author_t.id = abc_relation_t.author_id "
                    + "WHERE city_id = "
                    + "(SELECT city_t.id FROM city_t "
                    + "WHERE city_t.name = \"" + cityname + "\") LIMIT 10");

            System.out.println("Query ok");
            while (r.next()) {
                ret.add(new AuthorBook(r.getString("name"), r.getString("title")));
            }
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLQueries.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (r != null) {
                try {
                    r.close();
                } catch (SQLException ex) {
                    Logger.getLogger(MySQLQueries.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (s != null) {
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(MySQLQueries.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException ex) {
                    Logger.getLogger(MySQLQueries.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        System.out.println("Retrn somethin with len " + ret.size());
        return ret;
    }
    
    public static void main(String[] args) {
        getMentioningBooksWithAuthors("Paris");
    }

    public static ArrayList<Book> mentionedCities(String booktitle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static ArrayList<BookCities> getBooksAndCities(String authorname) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static ArrayList<Book> getBooksMentioningRange(double latitude,double longtitude, int leeway) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
