/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libraryapp.client.ui.component.table.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import libraryapp.common.domain.UserProfile;

/**
 *
 * @author Branislav Vujanov
 */
public class UserProfileTableModel extends AbstractTableModel{
    
    private final String[] columns = new String[]{"Uloga", "ID",  "Ime", "Prezime", "e-mail"};
    private List<UserProfile> users;

    
    public UserProfileTableModel(List<UserProfile> users) {
        this.users = users;
    }

    @Override
    public int getRowCount() {
        if (users == null) return 0;
        else return users.size();
    }

    @Override
    public int getColumnCount() {
//        return columns.length;
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        UserProfile user = users.get(rowIndex);
        switch (columnIndex){
            case 0:
                return user.getUserRole();
            case 1:
                return user.getId();
            case 2:
                return user.getFirstName();
            case 3:
                return user.getLastName();
            case 4:
                return user.getEmail();
            default :
                return "n/a";
        }
    }
    
    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
    
    public UserProfile getUserProfile(int index){
        if (users == null) return null;
        return users.get(index);
    }
}
