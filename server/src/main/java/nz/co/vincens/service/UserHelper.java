package nz.co.vincens.service;

import nz.co.vincens.login.UserService;
import nz.co.vincens.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserHelper {

    /**
     * The function of database execution.
     * Database queries use call procedure functions.
     *
     * @param username The username
     * @param password The password
     * @return If the no matched user, return 0, or return user id.
     * @throws SQLException Handle errors for JDBC
     */
    public static int login(String username, String password) {
        int count = 0;
        int id = 0;
        // Generate database query sentence
        String sql = "CALL User_login(" + "'" + username + "'" + "," + "'" + password + "'" + ")";
        // Call the function of database query operation
        ResultSet rs = DatabaseHelper.databaseExecution(sql);
        try {
            // Extract data from result set
            while (rs.next()) {
                // Login query should only have one row of result
                count = Integer.parseInt(rs.getString("count(*)"));
                if (count != 0) {
                    id = Integer.parseInt(rs.getString("id"));
                }
            }
            if (count == 0) {
                return 0;
            } else {
                return id;
            }
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

    public static User getUserDetails(int id) {
        User actualUser = new User();
        String username = null;
        String name = null;
        String password = null;
        String email = null;
        String role = null;
        String sql = "CALL User_getUserDetails(" + id + ")";
        // Call the function of database query operation
        ResultSet rs = DatabaseHelper.databaseExecution(sql);
        try {
            // Extract data from result set
            while (rs.next()) {
                // Login query should only have one row of result
                username = rs.getString("username");
                name = rs.getString("name");
                password = rs.getString("password");
                email = rs.getString("email");
                role = rs.getString("role");
            }
        } catch (SQLException e) {
            // Handle errors for JDBC
            e.printStackTrace();
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
        actualUser.setId(String.valueOf(id));
        actualUser.setUsername(username);
        actualUser.setName(name);
        actualUser.setPassword(password);
        actualUser.setEmail(email);
        actualUser.setRole(role);
        return actualUser;
    }

    public static Manager getManagerById(int id) {
        Manager actualManager = null;
        String username = null;
        String name = null;
        String password = null;
        String email = null;
        String sql = "CALL User_getManagerById(" + id + ")";
        // Call the function of database query operation
        ResultSet rs = DatabaseHelper.databaseExecution(sql);
        try {
            // Extract data from result set
            while (rs.next()) {
                // Login query should only have one row of result
                username = rs.getString("username");
                name = rs.getString("name");
                password = rs.getString("password");
                email = rs.getString("email");
            }
        } catch (SQLException e) {
            // Handle errors for JDBC
            e.printStackTrace();
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
        new Manager(String.valueOf(id), username, name, email, password);
        System.out.println(String.valueOf(id) + username);
        return actualManager;
    }
    

}