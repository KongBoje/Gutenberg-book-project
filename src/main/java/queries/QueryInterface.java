/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queries;
;

import entities.AuthorBook;
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
		}

		throw new UnsupportedOperationException();
	}

	public static boolean mentionedCities(String booktitle) {
		throw new UnsupportedOperationException();
	}

	public static boolean getBooksAndCities(String authorname) {
		throw new UnsupportedOperationException();
	}

	public static boolean getBooksMentioningRange(float latitude, float longtitude, float leeway) {
		throw new UnsupportedOperationException();
	}
}
