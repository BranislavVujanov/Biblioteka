/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libraryapp.client.controller;

import java.io.IOException;
import java.util.List;
import libraryapp.client.communication.Communication;
import libraryapp.common.domain.Book;
import libraryapp.common.domain.Loan;
import libraryapp.common.domain.UserProfile;
import libraryapp.common.exception.UserMessageException;
import libraryapp.common.transfer.Operation;
import libraryapp.common.transfer.Request;
import libraryapp.common.transfer.Response;

/**
 *
 * @author Branislav Vujanov
 */
public class Controller {

    private final static Controller instance = new Controller();

    private Controller() {
        System.out.println("Kreiran je objekat klase Controller na klijentsko strani");
    }

    public static Controller getInstance() {
        return instance;
    }

    public UserProfile login(String email, String password) throws Exception {
        UserProfile argument = new UserProfile();
        argument.setEmail(email);
        argument.setPassword(password);
        Request request = new Request(Operation.LOGIN, argument);
        Communication.getInstance().getSender().writeObject(request);
        Response response = (Response) Communication.getInstance().getReceiver().readObject();
        if (response.getException() == null) {
            return (UserProfile) response.getResult();
        } else if (response.getException() instanceof UserMessageException) {
            throw new UserMessageException(response.getException().getMessage());
        } else {
            throw new Exception(response.getException().getMessage());
        }

    }

    public List<Loan> displayCurrentLoans() throws Exception {
        Request request = new Request(Operation.DISPLAY_CURRENT_LOANS);
        Communication.getInstance().getSender().writeObject(request);
        Response response = (Response) Communication.getInstance().getReceiver().readObject();
        if (response.getException() == null) {
            return (List<Loan>) response.getResult();
        } else if (response.getException() instanceof UserMessageException) {
            throw new UserMessageException(response.getException().getMessage());
        } else {
            throw new Exception(response.getException().getMessage());
        }
    }
    
    public List<Loan> getAllLoans() throws Exception {
        Request request = new Request(Operation.DISPLAY_ALL_LOANS);
        Communication.getInstance().getSender().writeObject(request);
        Response response = (Response) Communication.getInstance().getReceiver().readObject();
        if (response.getException() == null) {
            return (List<Loan>) response.getResult();
        } else if (response.getException() instanceof UserMessageException) {
            throw new UserMessageException(response.getException().getMessage());
        } else {
            throw new Exception(response.getException().getMessage());
        }
    }
    
    public List<UserProfile> getAllUsers() throws Exception{
        Request request = new Request(Operation.USER_PRIOFILE_GET_ALL_USERS);
        Communication.getInstance().getSender().writeObject(request);
        Response response = (Response) Communication.getInstance().getReceiver().readObject();
        if (response.getException() == null) {
            return (List<UserProfile>) response.getResult();
        } else if (response.getException() instanceof UserMessageException) {
            throw new UserMessageException(response.getException().getMessage());
        } else {
            throw new Exception(response.getException().getMessage());
        }
    }
    
    public List<Loan> displayUserLoans(int id) throws Exception {
        Request request = new Request(Operation.DISPLAY_USER_LOANS, id);
        Communication.getInstance().getSender().writeObject(request);
        Response response = (Response) Communication.getInstance().getReceiver().readObject();
        if (response.getException() == null) {
            return (List<Loan>) response.getResult();
        } else if (response.getException() instanceof UserMessageException) {
            throw new UserMessageException(response.getException().getMessage());
        } else {
            throw new Exception(response.getException().getMessage());
        }
    }
    
    public List<Book> getAllBooks() throws Exception{
        Request request = new Request(Operation.BOOK_GET_ALL);
        Communication.getInstance().getSender().writeObject(request);
        Response response = (Response) Communication.getInstance().getReceiver().readObject();
        if (response.getException() == null) {
            return (List<Book>) response.getResult();
        } else if (response.getException() instanceof UserMessageException) {
            throw new UserMessageException(response.getException().getMessage());
        } else {
            throw new Exception(response.getException().getMessage());
        }
    }
    
    public void cancelLoan(Loan loan) throws Exception {
        Request request = new Request(Operation.CANCEL_LOAN, loan);
        Communication.getInstance().getSender().writeObject(request);
        Response response = (Response) Communication.getInstance().getReceiver().readObject();
        if (response.getException() == null) {
            System.out.println("Zaduzenje je prestalo da vazi");
        } else if (response.getException() instanceof UserMessageException) {
            throw new UserMessageException(response.getException().getMessage());
        } else {
            throw new Exception(response.getException().getMessage());
        }
    }
    
    public void addLoan(Loan loan) throws Exception{
        Request request = new Request(Operation.ADD_LOAN, loan);
        Communication.getInstance().getSender().writeObject(request);
        Response response = (Response) Communication.getInstance().getReceiver().readObject();
        if (response.getException() == null) {
            System.out.println("Napravljeno je novo zaduzenje");
        } else if (response.getException() instanceof UserMessageException) {
            throw new UserMessageException(response.getException().getMessage());
        } else {
            throw new Exception(response.getException().getMessage());
        }
    }
    
    public void logout() throws Exception{
        Request request = new Request(Operation.END);
        Communication.getInstance().getSender().writeObject(request);
        Response response = (Response) Communication.getInstance().getReceiver().readObject();
        if (response.getException() != null) {
            throw new Exception(response.getException().getMessage());
        }
        
    }
}
