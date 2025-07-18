/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libraryapp.server.thread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import libraryapp.server.controller.Controller;
import java.net.Socket;
import java.util.Date;
import java.util.List;
import libraryapp.common.domain.Book;
import libraryapp.common.domain.Loan;
import libraryapp.common.domain.UserProfile;
import static libraryapp.common.transfer.Operation.LOGIN;
import libraryapp.common.transfer.Request;
import libraryapp.common.transfer.Response;
import static libraryapp.common.transfer.Operation.DISPLAY_CURRENT_LOANS;
import libraryapp.server.session.Client;
import libraryapp.server.session.Session;

/**
 *
 * @author Branislav Vujanov
 */
public class ProcessClientRequests extends Thread {
    
    private Socket socket;
    private ObjectOutputStream sender;
    private ObjectInputStream receiver;
    private boolean signal;
    private Client client = new Client();
   

    public ProcessClientRequests(Socket socket) {
        try {
            this.socket = socket;
            this.sender = new ObjectOutputStream(this.socket.getOutputStream());
            this.receiver = new ObjectInputStream(this.socket.getInputStream());
            this.signal = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    @Override
    public void run() {
        while (signal && !isInterrupted()) {
            try {
                Request request = (Request) receiver.readObject();
                Response response = new Response();

                try {
                    System.out.println("Operation: " + request.getOperation());
                
                    switch (request.getOperation()) {
                        
                        case LOGIN:
                            UserProfile argument = (UserProfile) request.getArgument();
                            UserProfile userProfile = Controller.getInstance().login(argument.getEmail(), argument.getPassword());
                            response.setResult(userProfile);
                            
                            //deo koda za prikaz podataka o logovanju 
                            client.setClientName(userProfile.getFirstName() + " " + userProfile.getLastName());
                            client.setLoginTime(new Date());
                            Session.getInstance().getClients().add(client);
                            Session.getInstance().getRequest().add(this);
                            break;
                            
                        case DISPLAY_CURRENT_LOANS:
                            List<Loan> currentLoans = Controller.getInstance().displayCurrentLoans();
                            response.setResult(currentLoans);
                            break;
                            
                        case DISPLAY_ALL_LOANS:
                            List<Loan> allLoans = Controller.getInstance().displayAllLoans();
                            response.setResult(allLoans);
                            break;
                            
                        case USER_PRIOFILE_GET_ALL_USERS:
                            List<UserProfile> users = Controller.getInstance().getAllUsers();
                            response.setResult(users);
                            break;
                            
                        case DISPLAY_USER_LOANS:
                            int id = (int) request.getArgument();
                            List<Loan> userLoans = Controller.getInstance().displayUserLoans(id);
                            response.setResult(userLoans);
                            break;
                            
                        case BOOK_GET_ALL:
                            List<Book> allBooks = Controller.getInstance().getAllBooks();
                            response.setResult(allBooks);
                            break;
                            
                        case CANCEL_LOAN:
                            Loan canceledLoan = (Loan) request.getArgument();
                            Controller.getInstance().cancelLoan(canceledLoan);
                            break;
                            
                        case ADD_LOAN:
                            Loan loan = (Loan) request.getArgument();
                            Controller.getInstance().addLoan(loan);
                            break;
                            
                        case END: 
                            Session.getInstance().getClients().remove(client);
                            interrupt();
                            break;
                    }
                } catch (Exception e) {
//                    e.printStackTrace();
                    
                    response.setException(e);
                }

                sender.writeObject(response);

            } catch (Exception ex) {
                System.out.println("Metoda run() klase ProcessClientRequests je prekinuta via Exception: " + ex);
            }
        }
        System.out.println("ProcessClient " + client.getClientName()+" Requests Thread End");
    }

   
    public void closeClient() throws IOException{
        Session.getInstance().getClients().remove(client);
        socket.close();
    }

   
    
}
