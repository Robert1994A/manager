package ro.inf.ucv.admitere.controller.json;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import ro.inf.ucv.admitere.exceptions.UserNotFound;

@Controller
@EnableWebMvc
public class ModalsController extends BaseController {

	@RequestMapping(value = "/modals/singleUserModal.html/{id}")
	public String getSingleUserModal(Model model, @PathVariable("id") String id) throws Exception {
		user = userService.findOne(id);
		if (user == null) {
			throw new UserNotFound("The user with id " + id + " was not found! Please reload the page!");
		}
		model.addAttribute("user", user);
		return "/modals/singleUserModal";
	}

}
