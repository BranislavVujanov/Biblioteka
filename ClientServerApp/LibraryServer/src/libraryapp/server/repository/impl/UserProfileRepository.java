/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libraryapp.server.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
public class UserProfileRepository implements GenericRepository<UserProfile, Integer> {

    @Override
    public List<UserProfile> getAll() throws Exception {

        try {
            List<UserProfile> users = new ArrayList<>();

            Connection connection = MyDatabaseConnection.getInstance().getConnection();

            String query = """
                           SELECT id, first_name, last_name, email, user_role
                           FROM user_profile
                           ORDER BY user_role""";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                UserRole userRole = UserRole.valueOf(resultSet.getString("user_role"));
                UserProfile userProfile = new UserProfile(id, firstName, lastName, userRole, email);
                users.add(userProfile);
            }
            resultSet.close();
            statement.close();

            return users;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new Exception("Greska u izvrsavanju metode getAll() klase UserProfileRepository" + ex.getMessage());
        }
    }

    @Override
    public void add(UserProfile userProfile) throws Exception {
        try {
            Connection connection = MyDatabaseConnection.getInstance().getConnection();

            String query = "INSERT INTO user_profile ( first_name, last_name, email, user_role, password) VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, userProfile.getFirstName());
            preparedStatement.setString(2, userProfile.getLastName());
            preparedStatement.setString(3, userProfile.getEmail());
            preparedStatement.setString(4, userProfile.getUserRole().toString());
            preparedStatement.setString(5, userProfile.getPassword());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                userProfile.setId(resultSet.getInt(1));
            }

            preparedStatement.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new Exception("Greska u izvrsavanju metode add() klase UserProfileRepository" + ex.getMessage());
        }
    }

    @Override
    public List<UserProfile> findByQuery(String query) throws Exception {
        try {
            List<UserProfile> users = new ArrayList<>();
            Connection connection = MyDatabaseConnection.getInstance().getConnection();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                UserRole userRole = UserRole.valueOf(resultSet.getString("user_role"));
                UserProfile userProfile = new UserProfile(id, firstName, lastName, userRole, email);
                users.add(userProfile);
            }
            resultSet.close();
            statement.close();

            return users;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new Exception("Greska u izvrsavanju metode findByQuery() klase UserProfileRepository" + ex.getMessage());
        }
    }

    @Override
    public void connectEntities(int bookId, int authorId) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(UserProfile userProfile) throws Exception {
        try {
            Connection connection = MyDatabaseConnection.getInstance().getConnection();

            String query = """
                           UPDATE `user_profile` 
                           SET first_name ='""" + userProfile.getFirstName() + "', last_name = '" + userProfile.getLastName() + "', email= '" + userProfile.getEmail() + "', user_role = '" + userProfile.getUserRole().toString() + "'\n"
                             + "WHERE id = " + userProfile.getId();
            Statement statement = connection.createStatement();
            int affectedRows = statement.executeUpdate(query);
            if (affectedRows != 1) {
                throw new UserMessageException("Podaci o korisniku nisu uspesno izmenjeni!");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new Exception("Greska u izvrsenju metode update() klase UserProfileRepository" + ex.getMessage());
        }
    }

    @Override
    public void delete(UserProfile userProfile) throws Exception {
        try {
            Connection connection = MyDatabaseConnection.getInstance().getConnection();

            String query = "DELETE FROM user_profile WHERE id= " + userProfile.getId();
            Statement statement = connection.createStatement();
            int affectedRows = statement.executeUpdate(query);
            if (affectedRows != 1) {
                throw new UserMessageException("Podaci o korisniku nisu uspesno obrasini\nPokusajte ponoovo!");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new Exception("Greska u izvrsenju metode delete() klase UserProfileRepository" + ex.getMessage());
        }
    }


    @Override
    public List<UserProfile> findConnectingEntities(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public UserProfile findById(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void updateLoanStatus(Loan loan) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteConnectingEntities(UserProfile t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
