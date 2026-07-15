/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libraryapp.client.ui.form.user.profile.ki;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;
import libraryapp.client.controller.Controller;
import libraryapp.client.ui.component.table.model.UserProfileTableModel;
import libraryapp.common.domain.UserProfile;

/**
 *
 * @author Branislav Vujanov
 */
public class UserProfileSelectFormController {
    
    public static void filter(JTable tblUserProfile, String query){
        UserProfileTableModel model =  (UserProfileTableModel) tblUserProfile.getModel();
        TableRowSorter<UserProfileTableModel> tr = new TableRowSorter<>(model);
        tblUserProfile.setRowSorter(tr);
        
        tr.setRowFilter(RowFilter.regexFilter("(?i)" + query));
    }
    
    public static UserProfile selectUser(JTable tblUserProfile, UserProfile selectedUserProfile, JDialog UserProfileSelectForm){
        try {
            int index = tblUserProfile.convertRowIndexToModel(tblUserProfile.getSelectedRow());
        
            UserProfileTableModel model = (UserProfileTableModel) tblUserProfile.getModel();
            selectedUserProfile = model.getUserProfile(index);
            
            UserProfileSelectForm.dispose();  
        
        } catch (IndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(UserProfileSelectForm, "Niste odabrali korisnika");
        }
        return selectedUserProfile;
    }
    
    public static void prepareView(JTable tblUserProfile){
        try {
            
            tblUserProfile.setModel(new UserProfileTableModel(Controller.getInstance().getAllUsers()));
            
            tblUserProfile.setAutoCreateRowSorter(true);
            
            tblUserProfile.getColumnModel().getColumn(0).setPreferredWidth(70);
            tblUserProfile.getColumnModel().getColumn(1).setPreferredWidth(70);
            tblUserProfile.getColumnModel().getColumn(2).setPreferredWidth(170);
            tblUserProfile.getColumnModel().getColumn(3).setPreferredWidth(170);
            tblUserProfile.getColumnModel().getColumn(4).setPreferredWidth(200);
            
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            tblUserProfile.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
