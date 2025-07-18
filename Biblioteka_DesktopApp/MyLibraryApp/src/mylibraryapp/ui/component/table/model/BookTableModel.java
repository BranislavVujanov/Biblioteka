/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mylibraryapp.ui.component.table.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import mylibraryapp.domain.Book;

/**
 *
 * @author Branislav Vujanov
 */
public class BookTableModel extends AbstractTableModel{

    private final String[] columns = new String[]{"ID", "Naslov", "Godina izdanja", "Broj primeraka"};
    private List<Book> books;

    
    public BookTableModel(List<Book> books) {
        this.books = books;
    }

    @Override
    public int getRowCount() {
        if (books == null) return 0;
        else return books.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Book book = books.get(rowIndex);
        switch (columnIndex){
            case 0:
                return book.getId();
            case 1:
                return book.getTitle();
            case 2:
                return book.getPrintingYear();
            case 3:
                return book.getQuantity();
            default :
                return "n/a";
        }
    }
    
    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
    
    public Book getBook(int index){
        if (books == null) return null;
        return books.get(index);
    }
}
