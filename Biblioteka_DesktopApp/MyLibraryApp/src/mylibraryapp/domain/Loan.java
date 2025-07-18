/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mylibraryapp.domain;

import java.util.Date;

/**
 *
 * @author Branislav Vujanov
 */
public class Loan {
    
    private int id;
    private Date issuingDate;
    private Date returnDate;
    private UserProfile userProfile;
    private Book book;
    boolean valid;

    public Loan() {
    }

    public Loan( Date issuingDate, Date returnDate, UserProfile userProfile, Book book, boolean valid) {
        this.issuingDate = issuingDate;
        this.returnDate = returnDate;
        this.userProfile = userProfile;
        this.book = book;
        this.valid = valid;
    }

    public Loan(int id, Date issuingDate, Date returnDate, UserProfile userProfile, Book book, boolean valid) {
        this.id = id;
        this.issuingDate = issuingDate;
        this.returnDate = returnDate;
        this.userProfile = userProfile;
        this.book = book;
        this.valid = valid;
    }
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getIssuingDate() {
        return issuingDate;
    }

    public void setIssuingDate(Date issuingDate) {
        this.issuingDate = issuingDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public boolean isValid() {
        return valid;
    }

//    public void setValid(boolean valid) {
//        this.valid = valid;
//    }

   
    
    
}
