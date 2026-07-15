/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libraryapp.client.communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Branislav Vujanov
 */
public class Communication {
    
    private static Communication instance;
    private Socket socket;
    private ObjectOutputStream sender;
    private ObjectInputStream receiver;

    private Communication() throws IOException {
        this.socket = new Socket("localhost", 9000);
        this.sender = new ObjectOutputStream(socket.getOutputStream());
        this.receiver = new ObjectInputStream(socket.getInputStream());
    }
    
    public static Communication getInstance() throws IOException{
        if (instance == null) instance = new Communication();
        return instance;
    }

    public ObjectOutputStream getSender() {
        return sender;
    }

    public ObjectInputStream getReceiver() {
        return receiver;
    }
    
    
    
    
}
