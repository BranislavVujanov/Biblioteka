/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libraryapp.server.controller;

import java.util.List;
import libraryapp.common.domain.Book;
import libraryapp.common.domain.Loan;
import libraryapp.common.domain.UserProfile;
import libraryapp.server.service.BookService;
import libraryapp.server.service.LoanService;
import libraryapp.server.service.UserProfileService;
import libraryapp.server.service.impl.BookServiceImpl;
import libraryapp.server.service.impl.LoanServiceImpl;
import libraryapp.server.service.impl.UserProfileServiceImpl;

/**
 *
 * @author Branislav Vujanov
 */
public class Controller {
    
    private final static Controller instance = new Controller();
    private final UserProfileService userProfileService;
    private final LoanService loanService;
    private final BookService bookService;
    

    private Controller() {
        this.userProfileService = new UserProfileServiceImpl();
        this.loanService = new LoanServiceImpl();
        this.bookService = new BookServiceImpl();
    }
    
    public static Controller getInstance(){
        return instance;
    }
            
    public UserProfile login(String email, String password) throws Exception{
       return userProfileService.login(email, password);
    }
    
    public List<Loan> displayCurrentLoans() throws Exception {
        return loanService.displayCurrentLoans();
    }
    
    public List<Loan> displayAllLoans() throws Exception {
        return loanService.getAll();
    }
    
    public List<UserProfile> getAllUsers() throws Exception {
        return userProfileService.getAllUsers();
    }
    
    public List<Loan> displayUserLoans(int id) throws Exception{
        return loanService.displayUserLoans(id);
    }
    
     public List<Book> getAllBooks() throws Exception{
         return bookService.getAll();  
     }
     
     public void cancelLoan(Loan loan) throws Exception {
         loanService.cancelLoan(loan);
     }
     
     public void addLoan(Loan loan) throws Exception{
         loanService.add(loan);
     }
}
