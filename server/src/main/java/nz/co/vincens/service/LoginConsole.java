package nz.co.vincens.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created on 06/09/2017 by Shenghong Huang .
 * Last modified on 14/09/2017 by Shenghong Huang.
 */
public class LoginConsole {
    private static String Input_Username = null;
    private static String Input_Password = null;
    private static int Output_Id = 0;
    private static String Output_Name = null;
    private static String Output_Rolename = null;
    private static String Output_Roleid = null;
    private static int Output_Count = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter Username");
        Input_Username = sc.nextLine();
        System.out.println("Please enter Password");
        Input_Password = sc.nextLine();
        String sql = "CALL UserLogin(" + Input_Username + "," + Input_Password + ")";
        ResultSet rs = DatabaseHelper.DatabaseExecution(sql);
        System.out.println("QueryResult：");
        try {
            while (rs.next()) {
                Output_Count = Integer.parseInt(rs.getString("count(*)"));
                System.out.println("Count: " + Output_Count);
                // Login faild
                // No result
                if (Output_Count == 0) {
                    System.out.println("Incorrect Username or Password");
                    return;
                }
                // More than one
                else if (Output_Count > 1) {
                    System.out.println("System error");
                    return;
                }
                // Correct
                else if (Output_Count == 1) {
                    Output_Id = Integer.parseInt(rs.getString("id"));
                    Output_Name = rs.getString("name");
                    Output_Roleid = rs.getString("roleid");
                    Output_Rolename = rs.getString("rolename");
                    System.out.println("Login successful");
                    System.out.println("Id: " + Output_Id);
                    System.out.println("Username: " + Input_Username);
                    System.out.println("Name: " + Output_Name);
                    System.out.println("Roleid: " + Output_Roleid);
                    System.out.println("Rolename: " + Output_Rolename);
                }
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}