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
    private int printingYear;
    private int quantity;
    private List<Author> authors;

    public Book() {
    }

    public Book(String title, int printingYear, int quantity, List<Author> authors) {
        this.title = title;
        this.printingYear = printingYear;
        this.quantity = quantity;
        this.authors = authors;
    }
    
    

    public Book(int id, String title, int printingYear, int quantity, List<Author> authors) {
        this.id = id;
        this.title = title;
        this.printingYear = printingYear;
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

    public int getPrintingYear() {
        return printingYear;
    }

    public void setPrintingYear(int printingYear) {
        this.printingYear = printingYear;
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
        return "Book{" + "id=" + id + ", title=" + title + ", printingYear=" + printingYear + ", quantity=" + quantity + ", authors=" + authors + '}';
    }

    
    
    
    
}
