package ro.inf.ucv.admitere.controller.json;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ro.inf.ucv.admitere.entity.Role;
import ro.inf.ucv.admitere.entity.User;
import ro.inf.ucv.admitere.mailer.Mail;

@Controller
public class LoginRegisterRecoverControllerPOST extends BaseController {

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPOST(@Valid @ModelAttribute("user") User user, BindingResult result) {
		if (result.hasErrors()) {
			return "register";
		} else {
			Role roleUser = roleService.findByName("USER");
			List<Role> roles = new ArrayList<>();
			roles.add(roleUser);
			String registerToken = generator.getRandomString();
			user.setEnabled(false);
			user.setCreationDate(new Date());
			user.setExpiredDate(DateUtils.addMonths(new Date(), 3));
			user.setRecoverPaswordToken(registerToken);
			user.setRegisterToken(registerToken);
			user.setRoles(roles);
			user.setPassword(encoder.encode(user.getPassword()));
			userService.save(user);
			Mail mail = new Mail();
			mail.setMailFrom("robertgherlan@gmail.com");
			mail.setMailTo(user.getEmail());
			mail.setMailSubject("Validate Admitere UCV Account");
			mail.setTemplateName("mailValidateAccount.vm");
			HashMap<String, String> velocityContext = new HashMap<>();
			velocityContext.put("linkToValidate",
					"http://localhost:8080/admitere/validateStudentAccount?registerToken=" + registerToken);
			mailer.sendMail(mail, velocityContext);

		}
		return "redirect:/register?registered=true";
	}

	@RequestMapping(value = "/recover", method = RequestMethod.POST)
	public String recoverPOST(@RequestParam(value = "username", required = true) String username) {
		if (username == null) {
			return "redirect:/recover?error=true";
		}

		try {
			User user = userService.findByUsernameOrEmail(username, username);
			if (user != null) {
				String recoverToken = user.getRecoverPaswordToken();
				user.setEnabled(false);
				userService.save(user);
				Mail mail = new Mail();
				mail.setMailFrom("robertgherlan@gmail.com");
				mail.setMailTo(user.getEmail());
				mail.setMailSubject("Recover Password Admitere UCV Account");
				mail.setTemplateName("mailRecoverPassword.vm");
				HashMap<String, String> velocityContext = new HashMap<>();
				velocityContext.put("linkToRecover",
						"http://localhost:8080/admitere/recoverPassword?recoverToken=" + recoverToken);
				mailer.sendMail(mail, velocityContext);
				return "redirect:/recover?error=false";
			}
		} catch (Exception e) {
			return "redirect:/recover?error=true";
		}

		return "redirect:/recover?error=true";
	}

	@RequestMapping(value = "/recoverPassword", method = RequestMethod.POST)
	public String recoverPasswordPOST(@RequestParam(value = "recoverToken", required = true) String recoverToken,
			@RequestParam(value = "newPassword", required = true) String newPassword,
			@RequestParam(value = "retypeNewPassword", required = true) String retypeNewPassword) {
		try {
			if (newPassword.equals(retypeNewPassword)) {
				User user = userService.findByRecoverPaswordToken(recoverToken);
				if (user != null) {
					user.setPassword(encoder.encode(newPassword));
					user.setRecoverPaswordToken(generator.getRandomString());
					user.setEnabled(true);
					userService.save(user);
					return "redirect:/login?recoverPassword=true";
				}
			} else {
				return "redirect:/recoverPassword?fields=false&recoverToken=" + recoverToken;
			}

		} catch (Exception e) {
			return "redirect:/recoverPassword?error=true&recoverToken=" + recoverToken;
		}
		return "redirect:/recoverPassword?error=true&recoverToken=" + recoverToken;
	}

}
