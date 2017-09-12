package nz.co.vincens.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created on 06/09/2017 by Shenghong Huang .
 * Last modified on 13/09/2017 by Shenghong Huang.
 */
public class DatabaseHelper {
    private static final String DRIVE = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://rm-wz9646cu4gae4478fo.mysql.rds.aliyuncs.com:3306/workload";
    private static final String USER = "root";
    private static final String PASSWORD = "Student2017SoftwareEngineering";

    public static ResultSet DatabaseExecution(String sql) {
        Connection con = null;
        try {
            Class.forName(DRIVE);
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            if (!con.isClosed()) {
                System.out.println("Succeeded connecting to the Database!");
            }
            Statement statement = con.createStatement();
            return statement.executeQuery(sql);
        } catch (Exception e) {
            System.out.println("MYSQL Error:" + e.getMessage());
            return null;
        }
    }
}
