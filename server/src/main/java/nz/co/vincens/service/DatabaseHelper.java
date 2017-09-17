package nz.co.vincens.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class is used to implement specific functions for database access. Contains the variables required for the
 * database connection, and the functions required to perform the contents of the database query.
 * The current database uses the Alibaba Cloud MySQL database service.
 * Database queries use call procedure functions.
 * <p>
 * Created on 06/09/2017 by Shenghong Huang.
 * Last modified on 16/09/2017 by Shenghong Huang.
 */
public class DatabaseHelper {
    // JDBC driver name and database URL
    private static final String DRIVE = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://rm-wz9646cu4gae4478fo.mysql.rds.aliyuncs.com:3306/workload";
    // Database credentials
    private static final String USER = "root";
    private static final String PASSWORD = "Student2017SoftwareEngineering";

    /**
     * The function of database execution.
     * Database queries use call procedure functions.
     *
     * @param sql The database execution sentence
     * @return The result set of database execution
     * @throws SQLException Handle errors for JDBC
     * @throws Exception    Handle errors for Class.forName
     */
    public static ResultSet DatabaseExecution(String sql) {
        // Create the connection statement
        Connection con = null;
        // Create the statement
        Statement statement = null;
        try {
            // Register JDBC driver
            Class.forName(DRIVE);
            // Open a connection
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            // Check connection status
            if (!con.isClosed()) {
                System.out.println("Succeeded connecting to the Database!");
            }
            // State a MySQL connection object
            statement = con.createStatement();
            // Execute a query and return query result
            ResultSet rs = statement.executeQuery(sql);
            // Return query result set
            return rs;
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
            return null;
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
            return null;
        }
    }
}
