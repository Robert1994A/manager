package ro.certificate.manager.controller;

import java.util.HashMap;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ro.certificate.manager.mailer.Mail;
import ro.certificate.manager.wrapper.Contact;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ContactController extends BaseController {

	private static final Logger logger = Logger.getLogger(ContactController.class);

	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public String contact(Model model) {
		model.addAttribute("contact", new Contact());
		return "/contact";
	}

	@RequestMapping(value = "/contact", method = RequestMethod.POST)
	public String sendContact(Model model, @Valid @ModelAttribute("contact") Contact contact, BindingResult result) {
		if (result.hasErrors()) {
			return "/contact";
		}
		try {
			Mail mail = new Mail();
			mail.setMailFrom(contact.getEmail());
			mail.setMailTo(contactEmail);
			mail.setMailSubject("Feedback");
			mail.setTemplateName("feedback.vm");
			HashMap<String, String> velocityContext = new HashMap<>();
			velocityContext.put("name", contact.getName());
			velocityContext.put("phone", contact.getPhone());
			velocityContext.put("email", contact.getEmail());
			velocityContext.put("message", contact.getMessage());
			mailer.sendMail(mail, velocityContext);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return "/contact?success=false";
		}

		return "/contact?success=true";
	}
}
