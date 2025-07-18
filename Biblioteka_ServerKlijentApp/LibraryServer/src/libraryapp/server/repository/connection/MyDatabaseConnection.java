/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libraryapp.server.repository.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Branislav Vujanov
 */
public class MyDatabaseConnection {
    
    private static MyDatabaseConnection instance;
    private final Connection connection;

    
    private MyDatabaseConnection() throws SQLException {
        String url = "jdbc:mysql://127.0.0.1:3306/library";
        String user = "root";
        String password = "root";
        connection = DriverManager.getConnection(url, user, password);
    }

    
    public static MyDatabaseConnection getInstance() throws SQLException {
        if (instance == null) instance = new MyDatabaseConnection();
        return instance;
    }
    
    
    public Connection getConnection() throws SQLException{
        return connection;
    }
    
    public void closeConnection() throws SQLException{
        if (connection != null) connection.close();
    }
}
