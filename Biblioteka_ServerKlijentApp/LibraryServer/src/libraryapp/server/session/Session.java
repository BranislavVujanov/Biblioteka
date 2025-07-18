/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libraryapp.server.session;

import java.util.ArrayList;
import java.util.List;
import libraryapp.server.thread.ProcessClientRequests;

/**
 *
 * @author Branislav Vujanov
 */
public class Session {
    
    private static final Session instance = new Session();
    private final List<Client> clients = new ArrayList<>();
    private final List<ProcessClientRequests> request= new ArrayList<>();
    
    private Session(){
        
    }
    
    public static Session getInstance(){
        return instance;
    }

    public List<Client> getClients() {
        return clients;
    }

    public List<ProcessClientRequests> getRequest() {
        return request;
    }
    
    
  
}
