/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libraryapp.server.repository.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import libraryapp.common.domain.Book;
import libraryapp.common.domain.Loan;
import libraryapp.common.exception.UserMessageException;
import libraryapp.server.repository.GenericRepository;
import libraryapp.server.repository.connection.MyDatabaseConnection;

/**
 *
 * @author Branislav Vujanov
 */
public class BookRepository implements GenericRepository<Book, Integer> {

    @Override
    public List<Book> getAll() throws Exception {
        
        try {
            List<Book> books = new ArrayList<>();

            Connection connection = MyDatabaseConnection.getInstance().getConnection();

            String query = "SELECT * FROM  book ORDER BY title";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                int printingYear = resultSet.getInt("printing_year");
                int quantity = resultSet.getInt("quantity");
             
                Book book = new Book(id, title, printingYear, quantity, null);
                books.add(book);
            }
            
            resultSet.close();
            statement.close();
            
            return books; 

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Greska u izvrsavanju metode getAll() klase BookRepository" + ex.getMessage());
        }
        
    }

    @Override
    public void add(Book book) throws Exception {
        try{
            Connection connection = MyDatabaseConnection.getInstance().getConnection();

            String query = "INSERT INTO book (title, printing_year, quantity) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setInt(2,book.getPrintingYear());
            preparedStatement.setInt(3,book.getQuantity());
            preparedStatement.executeUpdate();
            
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    book.setId(resultSet.getInt(1));
                }
            
            preparedStatement.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Greska u izvrsavanju metode add() klase BookRepository" + ex.getMessage());
        }
    }

    @Override
    public List<Book> findByQuery(String query) throws Exception {
        try {
            List<Book> books = new ArrayList<>();

            Connection connection = MyDatabaseConnection.getInstance().getConnection();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                int printingYear = resultSet.getInt("printing_year");
                int quantity = resultSet.getInt("quantity");
             
                Book book = new Book(id, title, printingYear, quantity, null);
                books.add(book);
            }
            
            resultSet.close();
            statement.close();
            
            return books; 

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Greska u izvrsavanju metode findByQuery() klase BookRepository" + ex.getMessage());
        }
    }

    @Override
    public void connectEntities(int bookId, int authorId) throws Exception {
         try{
            Connection connection = MyDatabaseConnection.getInstance().getConnection();

            String query = "INSERT INTO writing (book_id, author_id) VALUES (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, bookId);
            preparedStatement.setInt(2,authorId);
            preparedStatement.executeUpdate();
            
            preparedStatement.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Greska u izvrsavanju metode connectEntities() klase BookRepository" + ex.getMessage());
        }   
    }

    @Override
    public void update(Book book) throws Exception {
         try { 
            Connection connection = MyDatabaseConnection.getInstance().getConnection();

            String query = "UPDATE book SET title = ?, printing_year = ?, quantity = ? WHERE id = ?";
            
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setInt(2,book.getPrintingYear());
            preparedStatement.setInt(3,book.getQuantity());
            preparedStatement.setInt(4, book.getId());
            int affectedRows = preparedStatement.executeUpdate();
            
            if (affectedRows != 1) throw new UserMessageException("Podaci o knjizi nisu azurirani");
            
        } catch (SQLException ex) {
               System.out.println(ex.getMessage());
               throw new Exception("Greska u izvrsenju metode update() klase BookRepository" + ex.getMessage());
        }
    }


    @Override
    public void delete(Book book) throws Exception {
        try { 
            Connection connection = MyDatabaseConnection.getInstance().getConnection();

            String query = "DELETE FROM book WHERE id= " + book.getId();
            Statement statement = connection.createStatement() ;
            int affectedRows = statement.executeUpdate(query);
            if (affectedRows != 1) throw new UserMessageException("Podaci o knjizi nisu uspesno obrasini\nPokusajte ponoovo!");
             
                
        } catch (SQLException ex) {
               System.out.println(ex.getMessage());
               throw new Exception("Greska u izvrsenju metode delete() klase BookRepository" + ex.getMessage());
        }
    }

    @Override
    public List<Book> findConnectingEntities(Integer id) throws Exception {
        try {
            List<Book> books = new ArrayList<>();

            Connection connection = MyDatabaseConnection.getInstance().getConnection();

            String query = "SELECT book_id  FROM writing WHERE author_id = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int bookId = resultSet.getInt("book_id");
                
                Book book = new Book();
                book.setId(bookId);
                books.add(book);
            }
            
            resultSet.close();
            preparedStatement.close();
            
            if (books == null) throw new UserMessageException("U biblioteci ne postoje knjige odabranog autora");

            return books; 
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Greska u izvrsavanju metode findConnectingEntities() klase BookRepository" + ex.getMessage());
        }
    }

    @Override
    public Book findById(Integer id) throws Exception {
        try{
            Connection connection = MyDatabaseConnection.getInstance().getConnection();

            Book book = null;
            String query = "SELECT * FROM  book WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                int printingYear = resultSet.getInt("printing_year");
                int quantity = resultSet.getInt("quantity");
             
                book = new Book(id, title, printingYear, quantity, null);
            }
            
            resultSet.close();
            preparedStatement.close();
             
            if (book == null) throw new UserMessageException("U biblioteci ne postoja sa zadatim ID");
            
            return book;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Greska u izvrsavanju metode findById() klase BookRepository" + ex.getMessage());
        }
    }

    @Override
    public void updateLoanStatus(Loan loan) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    @Override
    public void deleteConnectingEntities(Book book) throws Exception {
        try { 
            Connection connection = MyDatabaseConnection.getInstance().getConnection();

            String query = "DELETE FROM writing WHERE book_id= ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,book.getId());
            preparedStatement.executeUpdate();
            
            preparedStatement.close();
         
        } catch (SQLException ex) {
               System.out.println(ex.getMessage());
               throw new Exception("Greska u izvrsenju metode deleteBooksAuthor() klase BookRepository" + ex.getMessage());
        }
    }

   
}
