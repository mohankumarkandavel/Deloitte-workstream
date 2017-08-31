package service;

import model.Login;
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
	public String login(@ModelAttribute("login") Login login) {
		if (login != null && login.getPassword() != null && login.getUsername() != null) {
			if (login.getUsername().equals("James") && login.getPassword().equals("123")) {
				return "success";
			} else {
				return "incorrect";
			}
		} else {
			return "null";
		}
	}
}
