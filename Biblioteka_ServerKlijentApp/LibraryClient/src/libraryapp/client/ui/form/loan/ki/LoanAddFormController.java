/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libraryapp.client.ui.form.loan.ki;

import java.util.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.JLabel;
import static javax.swing.JOptionPane.PLAIN_MESSAGE;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import libraryapp.client.controller.Controller;
import libraryapp.common.domain.Author;
import libraryapp.common.domain.Book;
import libraryapp.common.domain.Loan;
import libraryapp.common.domain.UserProfile;
import libraryapp.common.exception.UserMessageException;
import libraryapp.client.ui.component.table.model.BookTableModel;
import libraryapp.client.ui.component.table.model.LoanTableModel;
import libraryapp.client.ui.form.user.profile.UserProfileSelectForm;


/**
 *
 * @author Branislav Vujanov
 */
public class LoanAddFormController {
    
    public static void filter(JTable tblBook, String query){
        BookTableModel model = (BookTableModel) tblBook.getModel();
        TableRowSorter<BookTableModel> tr = new TableRowSorter<>(model);
        tblBook.setRowSorter(tr);

        tr.setRowFilter(RowFilter.regexFilter("(?i)" + query));
    }

    public static void search(JTable tblBook, JTextField txtFilter){
        String query = txtFilter.getText();
        filter(tblBook, query);
    }
    
    public static void prepareLoanTable(List<Loan> loans, JTable tblLoan){
        try {
            TableModel loanTableModel = new LoanTableModel(loans);
            tblLoan.setModel(loanTableModel);

            tblLoan.setAutoCreateRowSorter(true);

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            tblLoan.getColumnModel().getColumn(0).setPreferredWidth(85);
            tblLoan.getColumnModel().getColumn(1).setPreferredWidth(120);
            tblLoan.getColumnModel().getColumn(2).setPreferredWidth(160);
            tblLoan.getColumnModel().getColumn(3).setPreferredWidth(70);
            tblLoan.getColumnModel().getColumn(4).setPreferredWidth(260);
            tblLoan.getColumnModel().getColumn(5).setPreferredWidth(90);
            tblLoan.getColumnModel().getColumn(6).setPreferredWidth(130);
            tblLoan.getColumnModel().getColumn(7).setPreferredWidth(130);
            tblLoan.getColumnModel().getColumn(8).setPreferredWidth(55);

            tblLoan.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
            tblLoan.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
            tblLoan.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
            tblLoan.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

            tblLoan.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void prepareBookTable(JTable tblBook) throws Exception{
        try {
            TableModel model = new BookTableModel( Controller.getInstance().getAllBooks());
            tblBook.setModel(model);

            tblBook.setAutoCreateRowSorter(true);

            tblBook.getColumnModel().getColumn(0).setPreferredWidth(20);
            tblBook.getColumnModel().getColumn(1).setPreferredWidth(350);
            tblBook.getColumnModel().getColumn(2).setPreferredWidth(50);
            tblBook.getColumnModel().getColumn(3).setPreferredWidth(33);

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            tblBook.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
            tblBook.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
            tblBook.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

        } catch (Exception ex) {
            throw ex;
        }
    }
    
    
    public static UserProfile selectUser(UserProfile selectedUserProfile, JDialog LoanAddForm, JList listUser,
             JTable tblLoan, JButton btnUpdateLoan, JButton btnMakeLoan){
        try {
            UserProfileSelectForm form = new UserProfileSelectForm(null, true);
            form.setVisible(true);

            selectedUserProfile = form.getSelectedUserProfile();
            
            DefaultListModel model = new DefaultListModel();
            if (selectedUserProfile == null) {
                JOptionPane.showMessageDialog(LoanAddForm, "Niste odabrali clana biblioteke");
                return null;
            } else {
                model.addElement(selectedUserProfile);
                listUser.setModel(model);
            }
            int id = selectedUserProfile.getId();
            prepareLoanTable(Controller.getInstance().displayUserLoans(id), tblLoan);

            if (tblLoan.getModel().getRowCount() == 0) {
                btnUpdateLoan.setEnabled(false);
            } else {
                btnUpdateLoan.setEnabled(true);
            }

            LoanTableModel loanTableModel = (LoanTableModel) tblLoan.getModel();
            if (loanTableModel.getRowCount() >= 5) {
                btnMakeLoan.setEnabled(false);
            } else {
                btnMakeLoan.setEnabled(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
     return selectedUserProfile;
    }

    public static Book selectBook(JTable tblBook,Book selectedBook, JDialog LoanAddForm){
        try {
            String authorName = "";
            int index = tblBook.convertRowIndexToModel​(tblBook.getSelectedRow());
            
                BookTableModel bookTableModel = (BookTableModel) tblBook.getModel();
                selectedBook = bookTableModel.getBook(index);
                List<Author> authors = selectedBook.getAuthors();
                for (Author author : authors) {
                    authorName = authorName + author.getFirstName() + " " + author.getLastName() + "\n";
                }
                JOptionPane.showMessageDialog(LoanAddForm, authorName, "Autor(i): ", PLAIN_MESSAGE);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
      return selectedBook;  
    }

    public static void updateLoan(UserProfile selectedUserProfile, JDialog LoanAddForm, JTable tblLoan,
             JButton btnUpdateLoan, JButton btnMakeLoan, JTable tblBook){
        
        try {
            if (selectedUserProfile == null) {
                JOptionPane.showMessageDialog(LoanAddForm, "Niste odabrali clana biblioteke");
                return;
            }
            
            int index = tblLoan.getSelectedRow();
            if (index == -1) {
                JOptionPane.showMessageDialog(LoanAddForm, "Niste odabrali zaduzenje");
            } else {
                LoanTableModel loanTableModel = (LoanTableModel) tblLoan.getModel();
                Loan selectedLoan = loanTableModel.getLoan(index);
                

                Controller.getInstance().cancelLoan(selectedLoan);

                prepareBookTable(tblBook);

                prepareLoanTable(Controller.getInstance().displayUserLoans(selectedUserProfile.getId()), tblLoan);

                LoanTableModel newLoanTableModel = (LoanTableModel) tblLoan.getModel();
                if (newLoanTableModel.getRowCount() >= 5) {
                    btnMakeLoan.setEnabled(false);
                } else {
                    btnMakeLoan.setEnabled(true);
                }

                if (newLoanTableModel.getRowCount() == 0) {
                    btnUpdateLoan.setEnabled(false);
                } else {
                    btnUpdateLoan.setEnabled(true);
                }
            }
        } catch (UserMessageException e) {
            JOptionPane.showMessageDialog(LoanAddForm, e.getMessage(), "Greska!", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void makeLoan(UserProfile selectedUserProfile, JDialog LoanAddForm, JTable tblLoan, JTable tblBook,
             JButton btnUpdateLoan, JButton btnMakeLoan,Book selectedBook){
        try {

            if (selectedUserProfile == null) {
                JOptionPane.showMessageDialog(LoanAddForm, "Niste odabrali clana biblioteke");
                return;
            }
            if (selectedBook == null) {
                JOptionPane.showMessageDialog(LoanAddForm, "Niste odabrali knjigu");

                return;
            }
            if (selectedBook.getQuantity() < 1) {
                JOptionPane.showMessageDialog(LoanAddForm, "Nema slobodnih primeraka odabrane knjige");
                return;
            }

            Date issuingDate = new Date();
            Date returnDate = new Date(issuingDate.getTime() + 1209600000);

            Loan loan = new Loan(issuingDate, returnDate, selectedUserProfile, selectedBook, true);
            Controller.getInstance().addLoan(loan);

            prepareBookTable(tblBook);
            prepareLoanTable(Controller.getInstance().displayUserLoans(selectedUserProfile.getId()), tblLoan);
            LoanTableModel loanTableModel = (LoanTableModel) tblLoan.getModel();
            if (loanTableModel.getRowCount() >= 5) {
                btnMakeLoan.setEnabled(false);
            } else {
                btnMakeLoan.setEnabled(true);
            }
            if (loanTableModel.getRowCount() == 0) {
                btnUpdateLoan.setEnabled(false);
            } else {
                btnUpdateLoan.setEnabled(true);
            }

        } catch (UserMessageException e) {
            JOptionPane.showMessageDialog(LoanAddForm, e.getMessage(), "Greska!", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
