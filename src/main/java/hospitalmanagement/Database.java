package hospitalmanagement;

import java.sql.*;

public class Database {

    private static Connection connection;

    //Connect to the DB
    public static void connect() {
        try {

            connection = DriverManager.getConnection("jdbc:mysql://awsdatabase.chcn61cnzhwv.us-east-1.rds.amazonaws.com:3306/hospitalManagement", "admin", "Academia_Java_4457");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Can edit, create and delete records of a table in the database
    public static void modifyTable(String instruction) {
        try {
            PreparedStatement statement = connection.prepareStatement(instruction);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Query a table and return the desired values
    public static ResultSet queryTable(String query) {
        ResultSet resultSet;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultSet;
    }
}
