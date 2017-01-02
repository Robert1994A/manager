package ro.inf.ucv.admitere.controller.json;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import ro.inf.ucv.admitere.entity.User;
import ro.inf.ucv.admitere.mailer.Mail;

@Controller
@EnableWebMvc
public class LoginRegisterRecoverControllerGET extends BaseController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginGET() {
		return "/login";
	}

	@RequestMapping(value = "/recover", method = RequestMethod.GET)
	public String recoverGET() {
		return "/recover";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerGET(Model model) {
		model.addAttribute("user", new User());
		return "/register";
	}

	@RequestMapping(value = "/validateStudentAccount", method = RequestMethod.GET)
	public String validateStudentAccount(Model model, @RequestParam("registerToken") String registerToken) {
		User user = userService.findByRegisterToken(registerToken);
		if (user == null) {
			return "redirect:/login?validatedAccount=false";
		}
		user.setEnabled(true);
		user.setRegisterToken(generator.getRandomString());
		userService.save(user);
		Mail mail = new Mail();
		mail.setMailFrom("robertgherlan@gmail.com");
		mail.setMailTo(user.getEmail());
		mail.setMailSubject("Your Admitere UCV Account was validated succesfully.");
		mail.setTemplateName("mailAcountValidated.vm");
		HashMap<String, String> velocityContext = new HashMap<>();
		velocityContext.put("username", user.getUsername());
		velocityContext.put("message", "Your Admitere UCV Account was validated succesfully.");
		mailer.sendMail(mail, velocityContext);
		return "redirect:/login?validatedAccount=true";
	}

	@RequestMapping(value = "/recoverPassword", method = RequestMethod.GET)
	public String recoverPasswordPOST(@RequestParam(value = "recoverToken", required = true) String recoverToken) {
		if (recoverToken == null) {
			return "redirect:/login?recover=false";
		}
		try {
			User user = userService.findByRecoverPaswordToken(recoverToken);
			if (user != null) {
				return "/recoverPassword";
			}
		} catch (Exception e) {
			return "redirect:/login?recover=false";
		}
		return "redirect:/login?recover=false";
	}
}
