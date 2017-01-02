package ro.inf.ucv.admitere.controller.json;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import ro.inf.ucv.admitere.entity.UserPersonalData;
import ro.inf.ucv.admitere.exceptions.UserNotFound;

@Controller
@EnableWebMvc
public class ModalsController extends BaseController {

	@RequestMapping(value = "/modals/singleUserModal.html/{id}")
	public String getSingleUserModal(Model model, @PathVariable("id") String id) throws UserNotFound {
		user = userService.findOne(id);
		model.addAttribute("user", user);
		return "/modals/singleUserModal";
	}

	@RequestMapping(value = "/modals/addPersonalData.html", method = RequestMethod.GET)
	public String getProfilePersonalDataModal(Model model) {
		model.addAttribute("userPersonalData", new UserPersonalData());
		return "/modals/addPersonalData";
	}

}
