/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.List;

/**
 *
 * @author OpieOP
 */
public class BookCities {
    private String bookName;

    public BookCities(String bookName, List<String> cities) {
        this.bookName = bookName;
        this.cities = cities;
    }
    private List<String> cities;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public List<String> getCities() {
        return cities;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }
    
    
}
