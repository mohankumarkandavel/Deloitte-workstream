package nz.co.vincens.service;

import nz.co.vincens.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class UserHelper {


    /**
     * The function of database execution.
     * Database queries use call procedure functions.
     *
     * @param Username The username
     * @param Password The password
     * @return The quantity of rows after database querying
     * @throws SQLException Handle errors for JDBC
     */
    public static int Login(String Username, String Password) {
        int count = 0;
        // Generate database query sentence
        String sql = "CALL UserLogin(" + Username + "," + Password + ")";
        // Call the function of database query operation
        ResultSet rs = DatabaseHelper.DatabaseExecution(sql);
        try {
            // Extract data from result set
            while (rs.next()) {
                // Login query should only have one row of result
                count = Integer.parseInt(rs.getString("count(*)"));
            }
            return count;
        } catch (SQLException e) {
            // Handle errors for JDBC
            e.printStackTrace();
            return count;
        } finally {
            try {
                // Clean-up environment
                if (!rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static int GetUserId(String Username, String Password) {
        // Generate database query sentence
        String sql = "CALL UserLogin(" + Username + "," + Password + ")";
        int id = 0;
        // Call the function of database query operation
        ResultSet rs = DatabaseHelper.DatabaseExecution(sql);
        try {
            // Extract data from result set
            while (rs.next()) {
                // Login query should only have one row of result
                id = Integer.parseInt(rs.getString("id"));
            }
            return id;
        } catch (SQLException e) {
            // Handle errors for JDBC
            e.printStackTrace();
            return 0;
        } finally {
            try {
                // Clean-up environment
                if (!rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static User GetUserInfoById(int id) {
        String username;
        String name;
        String password;
        String email;
        int roleid;
        // Generate database query sentence
        String sql = "CALL GetUserInfoById(" + id + ")";
        // Call the function of database query operation
        ResultSet rs = DatabaseHelper.DatabaseExecution(sql);
        try {
            // Extract data from result set
            while (rs.next()) {
                // Login query should only have one row of result
                username = rs.getString("username");
                name = rs.getString("name");
                password = rs.getString("password");
                email = rs.getString("email");
                roleid = Integer.parseInt(rs.getString("roleid"));
            }
        } catch (SQLException e) {
            // Handle errors for JDBC
            e.printStackTrace();
            return null;
        } finally {
            try {
                // Clean-up environment
                if (!rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void GetUserWeightById() {
    }

    public static ArrayList<User> LoadAllManagers() {
        return null;
    }

    public static User GetManagerById(int id) {
        return null;
    }

    public static ArrayList<User> LoadAllEmployees() {
        return null;
    }

    public static User GetEmployeeById(int id) {
        return null;
    }

    public static ArrayList LoadAllUserRoles() {
        return null;
    }

    public static ArrayList GetUserRoleById(int id) {
        return null;
    }

}
