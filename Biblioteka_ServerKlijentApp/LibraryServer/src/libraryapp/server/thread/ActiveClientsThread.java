/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libraryapp.server.thread;

import java.util.List;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import libraryapp.server.session.Client;
import libraryapp.server.session.Session;
import libraryapp.server.ui.component.table.model.ActiveClientsTableModel;

/**
 *
 * @author Branislav Vujanov
 */
public class ActiveClientsThread extends Thread {

    private JTable tblClients;
    boolean signal = true;

    public ActiveClientsThread(JTable tblClients) {
        this.tblClients = tblClients;
    }

    @Override
    public void run() {
        try {
            while (signal && !isInterrupted()) {
                List<Client> clients = Session.getInstance().getClients();
                TableModel tableModel = new ActiveClientsTableModel(clients);
                tblClients.setModel(tableModel);

                sleep(1000);
            }
        } catch (InterruptedException ex) {
            System.out.println("Metoda run() klase ActiveClientsThread je prekinuta: " + ex);
        }
    }

    
    public void stopActiveClientsThread(){
        signal = false;
        interrupt();
    }
}
