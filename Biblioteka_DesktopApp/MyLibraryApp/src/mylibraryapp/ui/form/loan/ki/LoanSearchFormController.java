/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mylibraryapp.ui.form.loan.ki;

import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import mylibraryapp.domain.Loan;
import mylibraryapp.service.impl.LoanServiceImpl;
import mylibraryapp.ui.component.table.model.LoanTableModel;

/**
 *
 * @author Branislav Vujanov
 */
public class LoanSearchFormController {
    
    
    public static void filter(JTable tblLoan, String query){
        LoanTableModel model =  (LoanTableModel) tblLoan.getModel();
        TableRowSorter<LoanTableModel> tr = new TableRowSorter<>(model);
        tblLoan.setRowSorter(tr);
        
        tr.setRowFilter(RowFilter.regexFilter("(?i)" + query));
    }

    public static void search(JTable tblLoan, JTextField txtFilter){
        String query = txtFilter.getText();
        filter(tblLoan, query);
    }
    
    public static void prepareView(List<Loan> loans, JTable tblLoan){
        try {

            TableModel model = new LoanTableModel(loans);
            tblLoan.setModel(model);
            
            tblLoan.setAutoCreateRowSorter(true);
            
            tblLoan.getColumnModel().getColumn(0).setPreferredWidth(130);
            tblLoan.getColumnModel().getColumn(1).setPreferredWidth(160);
            tblLoan.getColumnModel().getColumn(2).setPreferredWidth(200);
            tblLoan.getColumnModel().getColumn(3).setPreferredWidth(90);
            tblLoan.getColumnModel().getColumn(4).setPreferredWidth(320);
            tblLoan.getColumnModel().getColumn(5).setPreferredWidth(150);
            tblLoan.getColumnModel().getColumn(6).setPreferredWidth(150);
            tblLoan.getColumnModel().getColumn(7).setPreferredWidth(150);
            
            tblLoan.getColumnModel().getColumn(8).setPreferredWidth(90);
            
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            tblLoan.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
            tblLoan.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
            tblLoan.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
            tblLoan.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
            tblLoan.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void displayCurrentLoans(LoanServiceImpl loanService, JTable tblLoan){
        try {
            prepareView(loanService.displayCurrentLoans(), tblLoan);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
