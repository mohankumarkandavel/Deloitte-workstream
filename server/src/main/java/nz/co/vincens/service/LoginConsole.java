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
        UserHelper.Login(Input_Username, Input_Password);
    }
}