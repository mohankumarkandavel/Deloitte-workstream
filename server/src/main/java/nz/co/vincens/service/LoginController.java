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
        String Username = login.getUsername();
        String Password = login.getPassword();
        int Count = 0;
        if (Username != null && Password != null) {
            String sql = "CALL UserLogin(" + login.getUsername() + "," + login.getPassword() + ")";
            ResultSet rs = DatabaseHelper.DatabaseExecution(sql);
            try {
                while (rs.next()) {
                    Count = Integer.parseInt(rs.getString("count(*)"));
                    if (Count == 1) {
                        return ResponseEntity.ok().build();
                    } else {
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
                    }
                }
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
