package nz.co.vincens.login;

import nz.co.vincens.model.Login;
import nz.co.vincens.model.User;
import nz.co.vincens.service.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    private UserService userService;

    /**
     * Endpoint: <code>GET /login</code>
     * <br/>
     * Login api - requires a username and password
     *
     * @param login the {@link Login} model
     * @return 200 ok if login is successful, 401 Unauthorized if incorrect credentials, 400 bad request if fields
     * are empty
     */
    @CrossOrigin(exposedHeaders = "role")
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResponseEntity login(Login login) {
        // Define user login information resource from Login Object
        // Define query result
        int result = 0; // if it is 0, it means no matched user. Otherwise it is the user id
        if (login != null && login.getPassword() != null && login.getUsername() != null && !login.getPassword().isEmpty()
                && !login.getUsername().isEmpty()) {
            User actualUser;
            // Query the user info by the database
            result = UserHelper.login(login.getUsername(), login.getPassword());
            if (result != 0) {
                actualUser = UserHelper.getUserDetails(result);
                return ResponseEntity.ok().header("role", actualUser.getRole()).body("{\"id\": " + actualUser.getId
                        () + ", \"name\": \"" + actualUser.getName() + "\"}");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }
        // If the user does not input any information, return warning notification
        else {
            return ResponseEntity.badRequest().build();
        }
    }
}