/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libraryapp.server.communication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import libraryapp.server.session.Session;
import libraryapp.server.thread.ProcessClientRequests;

/**
 *
 * @author Branislav Vujanov
 */
public class Server extends Thread {

    
    private ServerSocket serverSocket;
    
    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(9000);
            while (!isInterrupted()) {
                System.out.println("Waiting for connection...");
                Socket socket = serverSocket.accept();
                System.out.println("Client connected!");
                handleClient(socket);
            }
            serverSocket.close();
        } catch (IOException ex) {
            System.out.println("Server has been stopped via IOException: " + ex);
        }
    }
    
    public void stopServer(){
        try {
            //prvo zatvaramo sve klijente (ako ima povezanih), pa potom i server
            for (ProcessClientRequests client: Session.getInstance().getRequest()){
                try {
                    client.interrupt();
                    client.closeClient();
                } catch (IOException e) {
                    System.out.println("Client closed via IOException: " + e);
                }
            }
            interrupt();
            serverSocket.close();
        } catch (IOException ex) {
            System.out.println("Server has been stopped via IOException");        }
    }

    private void handleClient(Socket socket) {
        ProcessClientRequests processClientRequests = new ProcessClientRequests(socket);
        processClientRequests.start();
    }

}
