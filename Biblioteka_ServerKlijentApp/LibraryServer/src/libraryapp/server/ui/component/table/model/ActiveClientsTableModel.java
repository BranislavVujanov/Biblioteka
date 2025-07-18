/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libraryapp.server.ui.component.table.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import libraryapp.server.session.Client;

/**
 *
 * @author Branislav Vujanov
 */
public class ActiveClientsTableModel extends AbstractTableModel {

    private final List<Client> clients;
    private final String[] columns = new String[]{"Name", "Login time"};
    private final DateFormat df = new SimpleDateFormat("dd.MM.yyyy. HH.mm.ss");

    
    public  ActiveClientsTableModel(List<Client> clients) {
        this.clients = clients;
    }

    @Override
    public int getRowCount() {
        if (clients != null) {
            return clients.size();
        }
        return 0;
    }

    @Override
    public int getColumnCount() {
//        return columns.length;
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Client client = clients.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return client.getClientName();
            case 1:
                return df.format(client.getLoginTime());
            default:
                return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
    
    
    

}
