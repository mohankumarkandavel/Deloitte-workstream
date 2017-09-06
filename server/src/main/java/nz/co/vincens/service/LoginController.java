package nz.co.vincens.service;

import nz.co.vincens.model.Login;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * API endpoint(s) for logging in.
 */
@RestController
public class LoginController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ResponseEntity login(Login login) {
		if (login != null && login.getPassword() != null && login.getUsername() != null) {
			if (login.getUsername().equals("James") && login.getPassword().equals("123")) {
				return ResponseEntity.ok().build();
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
}
