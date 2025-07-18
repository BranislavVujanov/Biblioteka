/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mylibraryapp.repository.impl;

import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import mylibraryapp.domain.Author;
import mylibraryapp.domain.Loan;
import mylibraryapp.exception.UserMessageException;
import mylibraryapp.repository.GenericRepository;
import mylibraryapp.repository.connection.MyDatabaseConnection;

/**
 *
 * @author Branislav Vujanov
 */
public class AuthorRepository implements GenericRepository<Author, Integer>{

    @Override
    public List<Author> getAll() throws Exception {
    
        try {
            List<Author> authors = new ArrayList<>();
            Connection connection = MyDatabaseConnection.getInstance().getConnection();
            
            String query = "SELECT * FROM  author ORDER BY last_name";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                Author author = new Author(id, firstName, lastName);
                authors.add(author);
            }
            resultSet.close();
            statement.close();
            
            return authors;
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new Exception("Greska u izvrsavanju metode getAll() klase AuthorRepository" + ex.getMessage());
        }
    }

    @Override
    public void add(Author author) throws Exception {
        try {
            Connection connection = MyDatabaseConnection.getInstance().getConnection();
            
            String query = "INSERT INTO author (first_name, last_name) VALUES (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, author.getFirstName());
            preparedStatement.setString(2,author.getLastName());
            preparedStatement.executeUpdate();
            
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) 
                author.setId(resultSet.getInt(1));
            
            preparedStatement.close();
           
                        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new Exception("Greska u izvrsavanju metode add() klase AuthorRepository" + ex.getMessage());
        }
    }

    @Override
    public List<Author> findByQuery(String query) throws Exception {
        try {
            List<Author> authors = new ArrayList<>();
            Connection connection = MyDatabaseConnection.getInstance().getConnection();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                Author author = new Author(id, firstName, lastName);
                authors.add(author);
            }
            resultSet.close();
            statement.close();
            
            return authors;
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new Exception("Greska u izvrsavanju metode findByQuery() klase AuthorRepository" + ex.getMessage());
        }
    }

    @Override
    public void connectEntities(int bookId, int authorId) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Author author) throws Exception {
         try { 
            Connection connection = MyDatabaseConnection.getInstance().getConnection();

            String query = """
                           UPDATE author
                           SET first_name = '"""+ author.getFirstName()+"', last_name = '"+ author.getLastName()+"'\n" +
                            "WHERE id = "+ author.getId();
            Statement statement = connection.createStatement() ;
            int affectedRows = statement.executeUpdate(query);
            if (affectedRows != 1) throw new UserMessageException("Podaci o autoru nisu uspesno izmenjeni!");
             
                
        } catch (SQLException ex) {
               System.out.println(ex.getMessage());
               throw new Exception("Greska u izvrsenju metode update() klase CountryRepository" + ex.getMessage());
        }
    }

    @Override
    public void delete(Author entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Author> findConnectingEntities(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Author findById(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    @Override
    public void updateLoanStatus(Loan loan) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   

    @Override
    public void deleteConnectingEntities(Author t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   

   

  

   
    
    
    
}
