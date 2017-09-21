package nz.co.vincens.service;

import nz.co.vincens.model.Login;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * API endpoint(s) for logging in.
 * <code>
 * <ul>
 * <li>GET /login</li>
 * </ul>
 * </code>
 */
@RestController
public class LoginController {

    /**
     * Endpoint: <code>GET /login</code>
     * <br/>
     * Login api - requires a username and password
     *
     * @param login the {@link Login} model
     * @return 200 ok if login is successful, 401 Unauthorized if incorrect credentials, 400 bad request if fields
     * are empty
     */
    @CrossOrigin
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResponseEntity login(Login login) {
        // Define user login information resource from Login Object
        String Username = login.getUsername();
        String Password = login.getPassword();
        // Define query result counter
        int Count = 0;
        if (login != null && Username != null && Password != null) {
            // Execute the database query and get the result
            Count = UserHelper.Login(Username, Password);
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
