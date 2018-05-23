/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queries;
;

import entities.AuthorBook;
import entities.Book;
import entities.BookCities;
import java.util.ArrayList;
import static queries.QueryInterface.DBChoice.DB_MYSQL;

/**
 *
 * @author Lasse
 */
public class QueryInterface {

	public static enum DBChoice {
		DB_MYSQL,
		DB_MONGODB,
		DB_NEO4J
	};

	public static ArrayList<AuthorBook> getMentioningBooksWithAuthors(String cityname, DBChoice db) {
		if (db == DB_MYSQL) {
			return MySQLQueries.getMentioningBooksWithAuthors(cityname);
		}if (db == DBChoice.DB_MONGODB) {
			return MongoQueries.getMentioningBooksWithAuthors(cityname);
		}if (db == DBChoice.DB_NEO4J) {
			return Neo4jQueries.getMentioningBooksWithAuthors(cityname);
		}
                return null;
	}

	public static ArrayList<Book> mentionedCities(String booktitle, DBChoice db) {
		if (db == DB_MYSQL) {
			return MySQLQueries.mentionedCities(booktitle);
		}if (db == DBChoice.DB_MONGODB) {
			return MongoQueries.mentionedCities(booktitle);
		}if (db == DBChoice.DB_NEO4J) {
			return Neo4jQueries.mentionedCities(booktitle);
		}
                return null;
	}

	public static ArrayList<BookCities> getBooksAndCities(String authorname, DBChoice db) {
		if (db == DB_MYSQL) {
			return MySQLQueries.getBooksAndCities(authorname);
		}if (db == DBChoice.DB_MONGODB) {
			return MongoQueries.getBooksAndCities(authorname);
		}if (db == DBChoice.DB_NEO4J) {
			return Neo4jQueries.getBooksAndCities(authorname);
		}
                return null;
	}

	public static ArrayList<Book> getBooksMentioningRange(double latitude, double longtitude, int leeway, DBChoice db) {
		if (db == DB_MYSQL) {
			return MySQLQueries.getBooksMentioningRange(latitude,longtitude,leeway);
		}if (db == DBChoice.DB_MONGODB) {
			return MongoQueries.getBooksMentioningRange(latitude,longtitude,leeway);
		}if (db == DBChoice.DB_NEO4J) {
			return Neo4jQueries.getBooksMentioningRange(latitude,longtitude,leeway);
		}
                return null;
	}
}
