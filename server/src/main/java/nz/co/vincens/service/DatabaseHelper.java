package nz.co.vincens.service;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
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
 * Last modified on 18/09/2017 by Shenghong Huang.
 */
public class DatabaseHelper {
    // JDBC driver name and database URL
    private static String driver = null;
    private static String url = null;
    // Database credentials
    private static String user = null;
    private static String password = null;

    /**
     * The function of database connection parameters loading.
     * It reads the JDBC driver name and database URL from the db.xml
     * It reads the Database credentials from the db.xml
     *
     * @throws Exception Handle errors for file reading
     */
    private static void LoadDatabaseParameters() {
        try {
            // Create a DocumentBuilderFactory object
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            // Create a DocumentBuilder object
            DocumentBuilder builder = factory.newDocumentBuilder();
            // Load XML object though parse method on Document builder
            File file = new File("db.xml");
            Document doc = builder.parse(file);
            // Get the value of every variables
            driver = doc.getElementsByTagName("driver-name").item(0).getFirstChild().getNodeValue();
            url = doc.getElementsByTagName("url").item(0).getFirstChild().getNodeValue();
            user = doc.getElementsByTagName("user-name").item(0).getFirstChild().getNodeValue();
            password = doc.getElementsByTagName("password").item(0).getFirstChild().getNodeValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
        // Call a funtion to load database connection parameters;
        LoadDatabaseParameters();
        // Create the connection statement
        Connection con = null;
        // Create the statement
        Statement statement = null;
        try {
            // Register JDBC driver
            Class.forName(driver);
            // Open a connection
            con = DriverManager.getConnection(url, user, password);
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
