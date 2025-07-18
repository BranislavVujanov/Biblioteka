/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mylibraryapp.ui.component.table.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import mylibraryapp.domain.Author;

/**
 *
 * @author Branislav Vujanov
 */
public class AuthorTableModel extends AbstractTableModel {

    private final String[] columns = new String[]{"ID", "Ime", "Prezime"};
    private List<Author> authors;

    public AuthorTableModel(List<Author> authors) {
        this.authors = authors;
    }

    @Override
    public int getRowCount() {
        if (authors != null) {
            return authors.size();
        } else {
            return 0;
        }
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Author author = authors.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return author.getId();
            case 1:
                return author.getFirstName();
            case 2:
                return author.getLastName();
            default:
                return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    public Author getAuthor(int index){
        if (authors == null) return null;
        return authors.get(index);
    }
            
    
}
