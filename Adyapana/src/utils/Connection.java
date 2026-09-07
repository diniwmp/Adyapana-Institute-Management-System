
package utils;

import gui.AdminLogin;
import gui.Dashboard;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

 
public class Connection {

    private static java.sql.Connection connection;

    private static void setUpConnection() {

        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/adyapana", "root", "pw");

            } catch (SQLException | ClassNotFoundException e) {
                AdminLogin.logger.log(Level.WARNING, e.getMessage(), e);
            }
        }
    }

    public static ResultSet search(String query) {

        if (!query.startsWith("SELECT")) {
            throw new IllegalArgumentException("Use ResultSet utils.Connection.iud()");
        }

        if (connection == null) {
            setUpConnection();
        }

        try {
            return connection.createStatement().executeQuery(query);
        } catch (SQLException e) {
            AdminLogin.logger.log(Level.WARNING, e.getMessage(), e);
            return null;
        }
    }

    public static boolean iud(String query) throws SQLException {

        if (query.startsWith("SELECT")) {
            throw new IllegalArgumentException("Use ResultSet utils.Connection.search()");
        }

        if (connection == null) {
            setUpConnection();
        }

        int result = connection.createStatement().executeUpdate(query);

        return result == 1;
    }

    public static void closeConnection() throws SQLException {

        if (connection != null) {
            connection.close();
        }

    }
}
