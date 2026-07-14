/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libraryapp.common.domain;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Branislav Vujanov
 */
public class Book implements Serializable{
    
    private int id;
    private String title;
    private int publishingYear;
    private int quantity;
    private List<Author> authors;

    public Book() {
    }

    public Book(String title, int publishingYear, int quantity, List<Author> authors) {
        this.title = title;
        this.publishingYear = publishingYear;
        this.quantity = quantity;
        this.authors = authors;
    }
    
    

    public Book(int id, String title, int publishingYear, int quantity, List<Author> authors) {
        this.id = id;
        this.title = title;
        this.publishingYear = publishingYear;
        this.quantity = quantity;
        this.authors = authors;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublishingYear() {
        return publishingYear;
    }

    public void setPublishingYear(int publishingYear) {
        this.publishingYear = publishingYear;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", title=" + title + ", publishingYear=" + publishingYear + ", quantity=" + quantity + ", authors=" + authors + '}';
    }

    
    
    
    
}
