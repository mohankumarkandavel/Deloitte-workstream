package nz.co.vincens.service;

import nz.co.vincens.model.Login;
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
        if (login != null && login.getPassword()!= null && login.getUsername()!= null && !login.getPassword().isEmpty() 
              && !login.getUsername().isEmpty()) {
            if (login.getUsername().equals("James") && login.getPassword().equals("123")) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
		if (login != null && login.getPassword()!= null && login.getUsername()!= null && !login.getPassword().isEmpty()
}
