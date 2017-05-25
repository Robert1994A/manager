package ro.certificate.manager.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ro.certificate.manager.entity.User;
import ro.certificate.manager.exceptions.ReCaptchaInvalidException;
import ro.certificate.manager.exceptions.ReCaptchaUnavailableException;
import ro.certificate.manager.mailer.Mail;

@Controller
public class RecoverPasswordContoller extends BaseController {

	private static final Logger logger = Logger.getLogger(RecoverPasswordContoller.class);

	@RequestMapping(value = "/recover", method = RequestMethod.GET)
	public String recoverGET(Model model) {
		model.addAttribute("captchaSiteKey", captchaSiteKey);
		return "/recover";
	}

	@RequestMapping(value = "/recover", method = RequestMethod.POST)
	public String recoverPOST(@RequestParam(value = "username", required = true) String username,
			HttpServletRequest request, Model model) {
		boolean success = true;
		model.addAttribute("captchaSiteKey", captchaSiteKey);
		try {
			String responseToken = request.getParameter("g-recaptcha-response");
			captchaService.processResponse(responseToken);
			User user = userService.findByUsernameOrEmail(username, username);
			if (user != null) {
				user.setEnabled(false);
				userService.save(user);
				Mail mail = new Mail();
				mail.setMailFrom(email);
				mail.setMailTo(user.getEmail());
				mail.setMailSubject("Recover password " + emailTitle);
				mail.setTemplateName("mailRecoverPassword.vm");
				HashMap<String, String> velocityContext = new HashMap<>();
				velocityContext.put("linkToRecover", siteHomeURL + "/recoverPassword?recoverToken="
						+ user.getRecoverPaswordToken() + "&email=" + user.getEmail());
				mailer.sendMail(mail, velocityContext);
			} else {
				model.addAttribute("userNotFound", true);
			}
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
		model.addAttribute("recover", success);
		
		return "/recover";
	}

	@RequestMapping(value = "/recoverPassword", method = RequestMethod.GET)
	public String recoverPasswordPOST(@RequestParam(value = "recoverToken", required = true) String recoverToken,
			@RequestParam(value = "email", required = true) String email) {
		if (recoverToken == null) {
			return "redirect:/login?recover=false";
		}
		try {
			User user = userService.findByRecoverPaswordTokenAndEmail(recoverToken, email);
			if (user != null) {
				return "/recoverPassword";
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return "redirect:/login?recover=false";
		}
		return "redirect:/login?recover=false";
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
					user.setRecoverPaswordToken(stringGenerator.getRandomString());
					user.setEnabled(true);
					userService.save(user);
					Mail mail = new Mail();
					mail.setMailFrom(email);
					mail.setMailTo(user.getEmail());
					mail.setMailSubject("Password was changed successfully.");
					mail.setTemplateName("passwordChanged.vm");
					HashMap<String, String> velocityContext = new HashMap<>();
					velocityContext.put("username", user.getUsername());
					velocityContext.put("password", newPassword);
					velocityContext.put("loginURL", siteHomeURL + "/login");
					mailer.sendMail(mail, velocityContext);
					return "redirect:/login?recoverPassword=true";
				}
			} else {
				return "redirect:/recoverPassword?fields=false&recoverToken=" + recoverToken;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return "redirect:/recoverPassword?error=true&recoverToken=" + recoverToken;
		}
		return "redirect:/recoverPassword?error=true&recoverToken=" + recoverToken;
	}

	@RequestMapping(value = "/validateAccount", method = RequestMethod.GET)
	public String validateStudentAccount(Model model,
			@RequestParam(value = "registerToken", required = true) String registerToken,
			@RequestParam(value = "email", required = true) String email) {
		User user = userService.findByRegisterTokenAndEmail(registerToken, email);
		if (user == null) {
			return "redirect:/login?validatedAccount=false";
		}
		user.setEnabled(true);
		user.setRegisterToken(stringGenerator.getRandomString());
		userService.save(user);
		Mail mail = new Mail();
		mail.setMailFrom("robertgherlan@gmail.com");
		mail.setMailTo(user.getEmail());
		mail.setMailSubject("Your account was validated successfully.");
		mail.setTemplateName("mailAcountValidated.vm");
		HashMap<String, String> velocityContext = new HashMap<>();
		velocityContext.put("loginURL", siteHomeURL + "/login");
		mailer.sendMail(mail, velocityContext);

		return "redirect:/login?validatedAccount=true";
	}
}
