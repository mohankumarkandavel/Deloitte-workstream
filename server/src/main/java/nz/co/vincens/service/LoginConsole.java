package nz.co.vincens.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * This class is to test database connection.
 * Some variables are not been used in LoginController.java which is backup for the further usage.
 * <p>
 * Created on 06/09/2017 by Shenghong Huang .
 * Last modified on 14/09/2017 by Shenghong Huang.
 */
public class LoginConsole {
    // The input variables for login
    private static String Input_Username = null;
    private static String Input_Password = null;
    // The return variables after success login
    private static int Output_Id = 0;
    private static String Output_Name = null;
    private static String Output_Rolename = null;
    private static String Output_Roleid = null;
    private static int Output_Count = 0;

    /**
     * The function of database execution.
     * Database queries use call procedure functions.
     *
     * @throws SQLException Handle errors for JDBC
     */
    public static void main(String[] args) {
        // Input login user login information
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter Username");
        Input_Username = sc.nextLine();
        System.out.println("Please enter Password");
        Input_Password = sc.nextLine();
        // Generate database query sentence
        String sql = "CALL UserLogin(" + Input_Username + "," + Input_Password + ")";
        // Call the function of database query operation
        ResultSet rs = DatabaseHelper.DatabaseExecution(sql);
        System.out.println("QueryResult：");
        try {
            // Extract data from result set
            while (rs.next()) {
                // Login query should only have one row of result
                Output_Count = Integer.parseInt(rs.getString("count(*)"));
                System.out.println("Count: " + Output_Count);
                // If it return zero row of result, return incorrect inputs notification
                if (Output_Count == 0) {
                    System.out.println("Incorrect Username or Password");
                    return;
                }
                // If it return more than one result, return incorrect data notification
                else if (Output_Count > 1) {
                    System.out.println("System error");
                    return;
                }
                // If it return only one row of result, return login successful notification
                else if (Output_Count == 1) {
                    // Retrieve by column name
                    Output_Id = Integer.parseInt(rs.getString("id"));
                    Output_Name = rs.getString("name");
                    Output_Roleid = rs.getString("roleid");
                    Output_Rolename = rs.getString("rolename");
                    // Display values
                    System.out.println("Login successful");
                    System.out.println("Id: " + Output_Id);
                    System.out.println("Username: " + Input_Username);
                    System.out.println("Name: " + Output_Name);
                    System.out.println("Roleid: " + Output_Roleid);
                    System.out.println("Rolename: " + Output_Rolename);
                }
            }
            // Clean-up environment
            rs.close();
        } catch (SQLException e) {
            // Handle errors for JDBC
            e.printStackTrace();
        }
    }
}