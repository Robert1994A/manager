package ro.certificate.manager.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ro.certificate.manager.entity.User;

@Controller
public class AccountController extends BaseController {

	@RequestMapping(value = "/my_account", method = RequestMethod.GET)
	public String getMyAccountTemplatePage(Model model, Principal principal) throws Exception {
		if (principal != null) {
			User authenticatedUser = userService.findByUsername(principal.getName());
			model.addAttribute("user", authenticatedUser);
		} else {
			return "redirect:/404";
		}
		return "/templates/my_account";
	}

	@RequestMapping(value = "/my_account", method = RequestMethod.POST)
	public String saveMyTest(Model model, Principal principal, @ModelAttribute("user") User user,
			@RequestParam(value = "newPassword", required = false) String newPassword,
			@RequestParam(value = "repeatPassword", required = false) String repeatPassword,
			@RequestParam(value = "profileImage", required = false) MultipartFile profileImage) throws Exception {
		User authenticatedUser = null;
		if (principal != null) {
			authenticatedUser = userService.findByUsername(principal.getName());
			if (!profileImage.isEmpty()) {

			}
			if (newPassword.trim().length() > 0 && repeatPassword.trim().length() > 0
					&& newPassword.equals(repeatPassword)) {
				if (encoder.matches(newPassword, authenticatedUser.getPassword())) {
					model.addAttribute("oldPasswordEqualNewPassword", true);
				} else {
					authenticatedUser.setPassword(encoder.encode(newPassword));
					userService.save(authenticatedUser);
					model.addAttribute("success", true);
				}

			} else {
				model.addAttribute("incorrectPasswords", true);
			}
		} else {
			return "redirect:/404";
		}
		if (authenticatedUser == null) {
			model.addAttribute("user", new User());
		} else {
			model.addAttribute("user", authenticatedUser);
		}

		return "/templates/my_account";
	}

	@RequestMapping(value = "/my_account/change-password", method = RequestMethod.POST)
	public String changeMyPassword(Model model, Principal principal,
			@RequestParam(value = "newPassword", required = false) String newPassword,
			@RequestParam(value = "repeatPassword", required = false) String repeatPassword) throws Exception {
		User authenticatedUser = null;
		if (principal != null) {
			authenticatedUser = userService.findByUsername(principal.getName());
			if (newPassword.trim().length() > 0 && repeatPassword.trim().length() > 0
					&& newPassword.equals(repeatPassword)) {
				if (encoder.matches(newPassword, authenticatedUser.getPassword())) {
					model.addAttribute("oldPasswordEqualNewPassword", true);
				} else {
					authenticatedUser.setPassword(encoder.encode(newPassword));
					userService.save(authenticatedUser);
					model.addAttribute("success", true);
				}
			} else {
				model.addAttribute("incorrectPasswords", true);
			}
		} else {
			return "redirect:/404";
		}
		model.addAttribute("user", authenticatedUser);

		return "/templates/my_account";
	}

	@RequestMapping(value = "/my_account/change-username", method = RequestMethod.POST)
	public String changeMyUsername(Model model, Principal principal,
			@RequestParam(value = "newUsername", required = false) String newUsername,
			@RequestParam(value = "repeatUsername", required = false) String repeatUsername) throws Exception {
		User authenticatedUser = null;
		if (principal != null) {
			authenticatedUser = userService.findByUsername(principal.getName());
			if (newUsername.trim().length() > 0 && repeatUsername.trim().length() > 0
					&& newUsername.equals(repeatUsername)) {
				if (newUsername.equals(authenticatedUser.getUsername())) {
					model.addAttribute("oldUsernameEqualNewUsername", true);
				} else {
					User user = userService.findByUsername(newUsername);
					if (user == null) {
						authenticatedUser.setUsername(newUsername);
						userService.save(authenticatedUser);
						model.addAttribute("success", true);
					}
					model.addAttribute("usernameAlreadyExist", true);
				}
			} else {
				model.addAttribute("incorrectPasswords", true);
			}
		} else {
			return "redirect:/404";
		}
		model.addAttribute("user", authenticatedUser);

		return "/templates/my_account";
	}

	@RequestMapping(value = "/my_account/change-email", method = RequestMethod.POST)
	public String changeMyEmail(Model model, Principal principal,
			@RequestParam(value = "newEmail", required = false) String newEmail,
			@RequestParam(value = "repeatEmail", required = false) String repeatEmail) throws Exception {
		User authenticatedUser = null;
		if (principal != null) {
			authenticatedUser = userService.findByUsername(principal.getName());
			if (newEmail.trim().length() > 0 && repeatEmail.trim().length() > 0
					&& newEmail.equals(repeatEmail)) {
				if (newEmail.equals(authenticatedUser.getEmail())) {
					model.addAttribute("oldEmailEqualNewEmail", true);
				} else {
					User user = userService.findByEmail(newEmail);
					if(user == null){
						authenticatedUser.setEmail(newEmail);
						userService.save(authenticatedUser);
						model.addAttribute("success", true);
					}else{
						model.addAttribute("emailAlreadyExist", true);
					}
				}
			} else {
				model.addAttribute("incorrectPasswords", true);
			}
		} else {
			return "redirect:/404";
		}
		model.addAttribute("user", authenticatedUser);

		return "/templates/my_account";
	}
}