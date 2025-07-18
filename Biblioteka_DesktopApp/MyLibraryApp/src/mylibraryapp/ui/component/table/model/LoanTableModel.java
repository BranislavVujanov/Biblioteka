/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mylibraryapp.ui.component.table.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import mylibraryapp.domain.Loan;

/**
 *
 * @author Branislav Vujanov
 */
public class LoanTableModel extends AbstractTableModel{

    private List<Loan> loans;
    private final String[] columns = new String[]{"ID", "Datum zaduzenja", "Datum isteka zaduzenja", "ID knjige", "Naslov", "ID korisnika", "Ime korisnika", "Prezime korisnika", "Akttivno"};

    public LoanTableModel(List<Loan> loans) {
        this.loans = loans;
    }
    
    @Override
    public int getRowCount() {
        if  (loans == null) return 0;
        return loans.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        
        Loan loan = loans.get(rowIndex);
        switch (columnIndex){
            case 0:
                return loan.getId();
            case 1:
                return df.format(loan.getIssuingDate());
            case 2:
                return df.format(loan.getReturnDate());
            case 3:
                return loan.getBook().getId();
            case 4:
                return loan.getBook().getTitle();
            case 5:
                return loan.getUserProfile().getId();
            case 6:
                return loan.getUserProfile().getFirstName();
            case 7:
                return loan.getUserProfile().getLastName();
            case 8:
                return loan.isValid();
            default :
                return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
    
    public Loan getLoan(int index){
        if (loans == null) return null;
        return loans.get(index);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 8) return Boolean.class;
        else return super.getColumnClass(columnIndex);
    }
    
    
}
