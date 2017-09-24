package nz.co.vincens.service;

import nz.co.vincens.model.Login;
import nz.co.vincens.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
    @CrossOrigin(exposedHeaders = "role")
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResponseEntity login(Login login) {
        List<User> users = new ArrayList<User>();
        users.add( new User("1","James123", "James Too","James@hotmail.com","manager", "123"));
        users.add( new User("1","Bob123", "Bob Ross","Bob@ross.com","team-member", "123"));

        if (login != null && login.getPassword()!= null && login.getUsername()!= null && !login.getPassword().isEmpty() 
              && !login.getUsername().isEmpty()) {
            boolean doesUserExist = false;
            String role = null;
            for (User user: users) {
                if (user.getUsername().equals(login.getUsername()) && user.getPassword().equals(login.getPassword())) {
                    doesUserExist = true;
                    role = user.getRole();
                }
            }
            if (doesUserExist) {
                return ResponseEntity.ok().header("role", role).build();
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
