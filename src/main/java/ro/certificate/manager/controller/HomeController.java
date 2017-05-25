package ro.certificate.manager.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ro.certificate.manager.entity.Role;
import ro.certificate.manager.entity.User;

@Controller
public class HomeController extends BaseController {

	private void initDatabase() {
		Role role = new Role();
		role.setName("USER");

		role = roleService.save(role);
		List<Role> roles = new ArrayList<Role>();
		roles.add(role);
		for (int i = 0; i < 50; i++) {
			User user = new User();
			user.setCreationDate(new Date());
			user.setEmail("robert@gmail.com" + i);
			user.setEnabled(true);
			user.setExpired(false);
			user.setExpiredDate(new Date());
			user.setRoles(roles);
			user.setPassword(new BCryptPasswordEncoder().encode("user" + i));
			user.setUsername("user" + i);
			user.setRecoverPaswordToken(stringGenerator.getRandomString());
			user.setRegisterToken(stringGenerator.getRandomString());
			userService.save(user);
		}
	}
	
	@RequestMapping(value = "/add_users", method = RequestMethod.GET)
	public String addUsers() {
		try {
			if (userService.findAll().isEmpty()) {
				initDatabase();
			}
		} catch (Exception e) {
			initDatabase();
		}
		return "/home";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		try {
			if (userService.findAll().isEmpty()) {
				initDatabase();
			}
		} catch (Exception e) {
			initDatabase();
		}
		return "/home";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String adminHome() {
		return home();
	}
}
