package ro.inf.ucv.admitere.controller.json;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import ro.inf.ucv.admitere.entity.Role;
import ro.inf.ucv.admitere.entity.User;
import ro.inf.ucv.admitere.exceptions.UserNotFound;
import ro.inf.ucv.admitere.utils.Generator;

/**
 * Handles requests for the application home page.
 */
@Controller
@EnableWebMvc
public class HomeControllerRest extends BaseController {

	public void initDatabase() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Generator myGenerator = new Generator();

		Role roleAdmin = new Role();
		roleAdmin.setName("ADMIN");
		roleService.save(roleAdmin);

		Role roleModerator = new Role();
		roleModerator.setName("MODERATOR");
		roleService.save(roleModerator);

		Role roleUser = new Role();
		roleUser.setName("USER");
		roleService.save(roleUser);

		List<Role> rolesAdmin = new ArrayList<Role>();
		rolesAdmin.add(roleModerator);
		rolesAdmin.add(roleAdmin);
		rolesAdmin.add(roleUser);

		List<Role> rolesModerator = new ArrayList<Role>();
		rolesModerator.add(roleModerator);
		rolesModerator.add(roleUser);

		List<Role> rolesUser = new ArrayList<Role>();
		rolesUser.add(roleUser);

		for (int i = 0; i < 100; i++) {
			User userAdmin = new User();
			userAdmin.setEnabled(true);
			userAdmin.setEmail("admin@gmail.com" + i);
			userAdmin.setRegisterToken("tokenAdmin" + i);
			userAdmin.setUsername("admin" + i);
			userAdmin.setPassword(encoder.encode("admin" + i));
			userAdmin.setRoles(rolesAdmin);
			userAdmin.setRecoverPaswordToken(encoder.encode(String.valueOf(i)));
			userService.save(userAdmin);
		}

		for (int i = 0; i < 200; i++) {
			User moderator = new User();
			moderator.setEnabled(true);
			moderator.setEmail("moderator@gmail.com" + i);
			moderator.setRegisterToken("tokenModerator" + i);
			moderator.setUsername(myGenerator.getGeneratedString());
			moderator.setPassword(encoder.encode("moderator" + i));
			moderator.setRoles(rolesModerator);
			moderator.setRecoverPaswordToken(encoder.encode(String.valueOf(i)));
			userService.save(moderator);
		}

		for (int i = 0; i < 300; i++) {
			User user1 = new User();
			user1.setEnabled(true);
			user1.setEmail("user@gmail.com" + i);
			user1.setRegisterToken("tokenUser" + i);
			user1.setUsername("user" + i);
			user1.setPassword(encoder.encode("user" + i));
			user1.setRoles(rolesUser);
			user1.setRecoverPaswordToken(encoder.encode(String.valueOf(i)));
			userService.save(user1);
		}

	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() throws UserNotFound {
		if (userService.findAll().isEmpty()) {
			//initDatabase();
		}
		return "home";
	}

}
