package jp.abc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyController {
	@Autowired
	private JdbcUserDetailsManager userManager;

	@RequestMapping(value = "/")
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/newuser", method = RequestMethod.GET)
	public String newuser() {
		return "newuser";
	}

	@RequestMapping(value = "/newuser", method = RequestMethod.POST)
	public String register(@RequestParam("username") String username,
			@RequestParam("password") String password) {
		UserBuilder users = User.withDefaultPasswordEncoder();
		userManager.createUser(users.username(username).password(password).roles("USER").build());
		return "login";
	}

	@RequestMapping(value = "/mypage")
	public String top() {
		return "mypage";
	}
}