package ro.certificate.manager.controller;

import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ro.certificate.manager.entity.User;

@Controller
public class UsersController extends BaseController {

	private static final Logger logger = Logger.getLogger(UsersController.class);

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String usersPage(Model model) {
		PageRequest pageRequest = new PageRequest(0, 100);
		Page<User> users = userService.findAll(pageRequest);
		if (users != null && !users.getContent().isEmpty()) {
			model.addAttribute("exist", true);
			model.addAttribute("users", users);
		} else {
			model.addAttribute("exist", false);
		}
		return "/users";
	}

	@RequestMapping(value = "/users/search", method = RequestMethod.GET)
	public String searchUsersPage(Model model, @RequestParam(required = true, value = "query") String query) {
		Page<User> users = null;
		Pageable pageRequest = new PageRequest(0, Integer.MAX_VALUE);
		if (query != null && query.trim().length() > 0) {
			users = userService.findByUsernameOrEmailIgnoreCase(query.trim(), pageRequest);
		} else {
			users = userService.findAll(pageRequest);
		}
		if (users != null && !users.getContent().isEmpty()) {
			model.addAttribute("exist", true);
			model.addAttribute("users", users);
		} else {
			model.addAttribute("exist", false);
		}
		return "/users";
	}


	@RequestMapping(value = "/users/disable-account/{id}", method = RequestMethod.POST)
	public String disableAccount(@PathVariable("id") String id) {
		if (id != null && id.trim().length() > 0) {
			try {
				User user = userService.findOne(id);
				if (user != null) {
					user.setEnabled(false);
					userService.save(user);
					return "redirect:/users?disableError=false";
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
				return "redirect:/users?disableError=true";
			}
		}
		return "redirect:/users?disableError=true";

	}

	@RequestMapping(value = "/users/enable-account/{id}", method = RequestMethod.POST)
	public String enableAccount(@PathVariable("id") String id) {
		if (id != null && id.trim().length() > 0) {
			try {
				User user = userService.findOne(id);
				if (user != null) {
					user.setEnabled(true);
					userService.save(user);
					return "redirect:/users?enableError=false";
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
				return "redirect:/users?enableError=true";
			}
		}

		return "redirect:/users?enableError=true";
	}

	@RequestMapping(value = "/users/delete-account/{id}", method = RequestMethod.POST)
	public String deleteAccount(@PathVariable("id") String id) {
		if (id != null && id.trim().length() > 0) {
			try {
				User user = userService.findOne(id);
				if (user != null) {
					userService.deleteById(id);
					return "redirect:/users?deleteError=false";
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
				return "redirect:/users?deleteError=true";
			}
		}

		return "redirect:/users?deleteError=true";
	}
}
