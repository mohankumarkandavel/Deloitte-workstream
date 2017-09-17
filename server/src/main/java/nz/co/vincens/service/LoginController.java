package nz.co.vincens.service;

import nz.co.vincens.model.Login;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * API endpoint(s) for logging in.
 */
@RestController
public class LoginController {
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResponseEntity login(Login login) {
        // Define user login information resource from Login Object
        String Username = login.getUsername();
        String Password = login.getPassword();
        // Define query result counter
        int Count = 0;
        if (login != null && Username != null && Password != null) {
            // Generate database query sentence
            String sql = "CALL UserLogin(" + login.getUsername() + "," + login.getPassword() + ")";
            // Call the function of database query operation
            ResultSet rs = DatabaseHelper.DatabaseExecution(sql);
            // Get the login result with the input information
            try {
                // Extract data from result set
                while (rs.next()) {
                    // Login query should only have one row of result
                    // Retrieve by column name
                    // Get the number of database query
                    Count = Integer.parseInt(rs.getString("count(*)"));
                }
                // Clean-up environment
                rs.close();
            } catch (SQLException e) {
                // Handle errors for JDBC
                e.printStackTrace();
            }
            // If it return only one row of result, return login successful notification
            if (Count == 1) {
                return ResponseEntity.ok().build();
            }
            // If it return zero row of result, return incorrect inputs notification
            else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }
        // If the user does not input any information, return warning notification
        else {
            return ResponseEntity.badRequest().build();
        }
    }
}
