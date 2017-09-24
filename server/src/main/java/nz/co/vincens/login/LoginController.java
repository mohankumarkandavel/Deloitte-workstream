package nz.co.vincens.login;

import nz.co.vincens.model.Login;
import nz.co.vincens.model.User;
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
    UserService userService;

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
        if (login != null && login.getPassword() != null && login.getUsername() != null && !login.getPassword().isEmpty()
                && !login.getUsername().isEmpty()) {
            User actualUser = null;
            for (User user : userService.getUsers()) {
                if (user.getUsername().equals(login.getUsername()) && user.getPassword().equals(login.getPassword())) {
                    actualUser = user;
                }
            }
            if (actualUser != null) {
                return ResponseEntity.ok().header("role", actualUser.getRole()).body("{\"id\": " + actualUser.getId() + "}");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
