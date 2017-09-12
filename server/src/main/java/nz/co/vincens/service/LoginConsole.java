package nz.co.vincens.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created on 06/09/2017 by Shenghong Huang .
 * Last modified on 13/09/2017 by Shenghong Huang.
 */
public class LoginConsole {
    private static String Input_UserName = null;
    private static String Input_Password = null;
    private static String Output_Name = null;
    private static String Output_RoleName = null;
    private static String Output_RoleID = null;
    private static int Output_Count = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter UserID");
        Input_UserName = sc.nextLine();
        System.out.println("Please enter Password");
        Input_Password = sc.nextLine();
        String sql = "CALL UserLogin(" + Input_UserName + "," + Input_Password + ")";
        ResultSet rs = DatabaseHelper.DatabaseExecution(sql);
        System.out.println("QueryResult：");
        try {
            while (rs.next()) {
                Output_Name = rs.getString("name");
                Output_RoleName = rs.getString("rolename");
                Output_RoleID = rs.getString("roleid");
                System.out.println("Hi " + Output_Name);
                System.out.println("You are: " + Output_RoleName);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
