package database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseUtility {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "mysql";
    private static final String CONNECTION = "jdbc:mysql://localhost:3306/Prototype";

    public static Connection connection;

    public static Connection getConnection() throws SQLException {

        try {
            connection = DriverManager.getConnection(CONNECTION, USERNAME, PASSWORD);
            System.out.println("Connection Status: Connected");
        } catch (SQLException e) {
            System.out.println(e);
        }
        return connection;
    }

    public static void closeconnection() throws SQLException {

        try {
            connection.close();
            System.out.println("Connection Status: Closed");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }



}
