/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libraryapp.server.repository.impl;

import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import libraryapp.common.domain.Book;
import libraryapp.common.domain.Loan;
import libraryapp.common.domain.UserProfile;
import libraryapp.common.domain.UserRole;
import libraryapp.common.exception.UserMessageException;
import libraryapp.server.repository.GenericRepository;
import libraryapp.server.repository.connection.MyDatabaseConnection;

/**
 *
 * @author Branislav Vujanov
 */
public class LoanRepository implements GenericRepository<Loan, Integer> {

    @Override
    public List<Loan> getAll() throws Exception {
        try {
            List<Loan> loans = new ArrayList<>();

            Connection connection = MyDatabaseConnection.getInstance().getConnection();

            String query = """
                           SELECT l.id, l.issuing_date, l.return_date, l.valid, l.user_profile_id, 
                           up.first_name, up.last_name, up.email, up.user_role,l.book_id, b.title, b.printing_year, b.quantity
                           FROM loan l
                           JOIN user_profile up ON l.user_profile_id = up.id
                           JOIN book b ON l.book_id = b.id
                           ORDER BY l.issuing_date DESC""";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                Date issuingDate = resultSet.getDate("issuing_date");
                Date returnDate = resultSet.getDate("return_date");
                boolean valid = resultSet.getBoolean("valid");
                
                int userProfileId = resultSet.getInt("user_profile_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                UserRole userRole = UserRole.valueOf(resultSet.getString("user_role"));

                UserProfile userProfile = new UserProfile(userProfileId, firstName, lastName, userRole, email);

                int bookId = resultSet.getInt("book_id");
                String title = resultSet.getString("title");
                int printingYear = resultSet.getInt("printing_year");
                int quantity = resultSet.getInt("quantity");

                Book book = new Book(bookId, title, printingYear, quantity, null);

                Loan loan = new Loan(id, issuingDate, returnDate, userProfile, book, valid);
                loans.add(loan);
            }
            resultSet.close();
            statement.close();

            return loans;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new Exception("Greska u izvrsavanju metode getAll() klase LoanRepository" + ex.getMessage());
        }
    }

    @Override
    public void add(Loan loan) throws Exception {
        try {
            Connection connection = MyDatabaseConnection.getInstance().getConnection();

            String query = "INSERT INTO loan (issuing_date, return_date, user_profile_id, book_id, valid) VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setDate(1, new java.sql.Date (loan.getIssuingDate().getTime()));
            preparedStatement.setDate(2, new java.sql.Date (loan.getReturnDate().getTime()));
            preparedStatement.setInt(3, loan.getUserProfile().getId());
            preparedStatement.setInt(4, loan.getBook().getId());
            preparedStatement.setBoolean(5, loan.isValid());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                loan.setId(resultSet.getInt(1));
            }
            preparedStatement.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new Exception("Greska u izvrsavanju metode add() klase UserProfileRepository" + ex.getMessage());
        }
    }

    @Override
    public List<Loan> findByQuery(String query) throws Exception {
        try {
            List<Loan> loans = new ArrayList<>();

            Connection connection = MyDatabaseConnection.getInstance().getConnection();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                Date issuingDate = resultSet.getDate("issuing_date");
                Date returnDate = resultSet.getDate("return_date");
                boolean valid = resultSet.getBoolean("valid");

                int userProfileId = resultSet.getInt("user_profile_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                UserRole userRole = UserRole.valueOf(resultSet.getString("user_role"));

                UserProfile userProfile = new UserProfile(userProfileId, firstName, lastName, userRole, email);

                int bookId = resultSet.getInt("book_id");
                String title = resultSet.getString("title");
                int printingYear = resultSet.getInt("printing_year");
                int quantity = resultSet.getInt("quantity");

                Book book = new Book(bookId, title, printingYear, quantity, null);

                Loan loan = new Loan(id, issuingDate, returnDate, userProfile, book, valid);
                loans.add(loan);
            }
            resultSet.close();
            statement.close();

            return loans;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new Exception("Greska u izvrsavanju metode findByQuery() klase LoanRepository" + ex.getMessage());
        }
    }

    @Override
    public void connectEntities(int bookId, int authorId) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Loan t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
    @Override
    public void delete(Loan t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Loan> findConnectingEntities(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Loan findById(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void updateLoanStatus(Loan loan) throws Exception {
        try { 
            int status;
            if (loan.isValid())
                status = 0;
            else
                status = 1;
            
            Connection connection = MyDatabaseConnection.getInstance().getConnection();

            String query = """
                           UPDATE loan 
                           SET valid = ?
                           WHERE id = ?""";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, status);
            preparedStatement.setInt(2,loan.getId());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows != 1) throw new UserMessageException("Status pozajmice nije promenjen!");
             
        } catch (SQLException ex) {
               System.out.println(ex.getMessage());
               throw new Exception("Greska u izvrsenju metode update() klase LoanRepository" + ex.getMessage());
        }
    }

    @Override
    public void deleteConnectingEntities(Loan t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
