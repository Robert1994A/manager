package ro.certificate.manager.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ro.certificate.manager.entity.Role;
import ro.certificate.manager.entity.User;
import ro.certificate.manager.exceptions.ReCaptchaInvalidException;
import ro.certificate.manager.exceptions.ReCaptchaUnavailableException;
import ro.certificate.manager.mailer.Mail;
import ro.certificate.manager.wrapper.UserRegistrationForm;

@Controller
public class RegisterController extends BaseController {

	private static final Logger logger = Logger.getLogger(RegisterController.class);

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String addUserPage(Model model) {
		model.addAttribute("captchaSiteKey", captchaSiteKey);
		model.addAttribute("registerUser", new UserRegistrationForm());
		return "/register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String addUserPagePost(Model model, @Valid @ModelAttribute("registerUser") UserRegistrationForm registerUser,
			BindingResult bindingResult, HttpServletRequest request) {
		boolean success = true;
		model.addAttribute("captchaSiteKey", captchaSiteKey);
		if (bindingResult.hasErrors()) {
			return "/register";
		}
		try {
			String responseToken = request.getParameter("g-recaptcha-response");
			captchaService.processResponse(responseToken);
			Role roleUser = roleService.findByName("USER");
			if (roleUser == null) {
				roleUser = roleService.save(new Role("USER"));
			}
			List<Role> roles = new ArrayList<>();
			roles.add(roleUser);

			String registerToken = stringGenerator.getRandomString();
			String plainPassword = registerUser.getPassword();
			String recoverToken = stringGenerator.getRandomString();

			User user = new User();
			user.setRoles(roles);
			user.setEnabled(false);
			user.setCreationDate(new Date());
			user.setExpiredDate(DateUtils.addMonths(new Date(), 3));
			user.setRecoverPaswordToken(recoverToken);
			user.setRegisterToken(registerToken);
			user.setPassword(encoder.encode(plainPassword));
			user.setEmail(registerUser.getEmail());
			user.setUsername(registerUser.getUsername());

			userService.save(user);
			Mail mail = new Mail();
			mail.setMailFrom(email);
			mail.setMailTo(user.getEmail());
			mail.setMailSubject("Validate account " + emailTitle);
			mail.setTemplateName("mailValidateAccount.vm");
			HashMap<String, String> velocityContext = new HashMap<>();
			velocityContext.put("linkToValidate",
					siteHomeURL + "/validateAccount?registerToken=" + registerToken + "&email=" + email);
			velocityContext.put("username", user.getUsername());
			velocityContext.put("password", plainPassword);
			mailer.sendMail(mail, velocityContext);
			success = true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			if (e instanceof ReCaptchaInvalidException) {
				model.addAttribute("invalidCaptcha", true);
			}
			if (e instanceof ReCaptchaUnavailableException) {
				model.addAttribute("unavailableCaptcha", true);
			}
			success = false;
		}
		model.addAttribute("success", success);
		return "/register";
	}
}
