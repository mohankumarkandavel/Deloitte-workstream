package nz.co.vincens.service;

import nz.co.vincens.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

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
        String sql = "CALL User_Login(" + "'" + username + "'" + "," + "'" + password + "'" + ")";
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

    /**
     * The function of database execution.
     * Database queries use call procedure functions.
     *
     * @param id The user's id
     * @return If the no matched user, return 0, or return user info(username, name,email,role).
     * @throws SQLException Handle errors for JDBC
     */
    public static User getUserDetail(int id) {
        User user = new User();
        user.setId(String.valueOf(id));
        // Generate database query sentence
        String sql = "CALL User_LoadDetail(" + id + ")";
        // Call the function of database query operation
        ResultSet rs = DatabaseHelper.databaseExecution(sql);
        try {
            // Extract data from result set
            while (rs.next()) {
                // Login query should only have one row of result
                user.setUsername(rs.getString("username"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
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
        return user;
    }
}