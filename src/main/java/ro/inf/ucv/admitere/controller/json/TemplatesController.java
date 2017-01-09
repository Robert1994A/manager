package ro.inf.ucv.admitere.controller.json;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import ro.inf.ucv.admitere.entity.Address;
import ro.inf.ucv.admitere.entity.Contract;
import ro.inf.ucv.admitere.entity.File;
import ro.inf.ucv.admitere.entity.IdentityCard;
import ro.inf.ucv.admitere.entity.LegalParentFather;
import ro.inf.ucv.admitere.entity.LegalParentMother;
import ro.inf.ucv.admitere.entity.PreviousFaculty;
import ro.inf.ucv.admitere.entity.PreviousHighSchool;
import ro.inf.ucv.admitere.entity.User;
import ro.inf.ucv.admitere.entity.UserPersonalData;

@Controller
@EnableWebMvc
public class TemplatesController extends BaseController {

	@RequestMapping(value = "/templates/home")
	public String getHomeTemplatePage() {
		return "/templates/home";
	}

	@RequestMapping(value = "/templates/users")
	public String getUsersTemplatePage() {
		return "/templates/users";
	}

	@RequestMapping(value = "/templates/userDetails")
	public String getUserDetailsTemplatePage() {
		return "/templates/userDetails";
	}

	@RequestMapping(value = "/templates/forms")
	public String getFormsTemplatePage() {
		return "/templates/forms";
	}

	@RequestMapping(value = "/templates/add_form")
	public String getAddFormTemplatePage() {
		return "/templates/add_form";
	}

	@RequestMapping(value = "/templates/addContract")
	public String getAddContractTemplatePage() {
		return "/templates/addContract";
	}

	@RequestMapping(value = "/templates/contracts")
	public String getContractsTemplatePage(Model model) {
		List<Contract> contracts = this.contractPageService.findAll();
		if (contracts.size() > 0) {
			model.addAttribute("existContracts", true);
			model.addAttribute("contracts", contracts);
		} else {
			model.addAttribute("existContracts", false);
		}
		return "/templates/contracts";
	}

	@RequestMapping(value = "/templates/contract")
	public String getContractTemplatePage(Model model) {
		return "/templates/contract";
	}

	@RequestMapping(value = "/templates/profile")
	public String getProfileTemplatePage() {
		return "/templates/profile/profile";
	}

	@RequestMapping(value = "/templates/profile/personalData")
	public String getPersonalDataProfileTemplatePage(Model model, Principal principal) throws Exception {
		UserPersonalData userPersonalData = null;
		if (principal != null && principal.getName() != null && principal.getName().trim().length() > 0) {
			User authenticatedUser = userService.findByUsername(principal.getName());
			userPersonalData = authenticatedUser.getUserPersonalData();
		}
		if (userPersonalData != null && userPersonalData.getCnp() != null) {
			model.addAttribute("userPersonalData", userPersonalData);
		} else {
			model.addAttribute("userPersonalData", new UserPersonalData());
		}
		return "/templates/profile/personalData";
	}

	@RequestMapping(value = "/templates/profile/address")
	public String getPersonalAddressProfileTemplatePage(Model model) {
		model.addAttribute("address", new Address());
		return "/templates/profile/address";
	}

	@RequestMapping(value = "/templates/profile/identityCard")
	public String getPersonalIdentityCardProfileTemplatePage(Model model) {
		model.addAttribute("identityCard", new IdentityCard());
		return "/templates/profile/identityCard";
	}

	@RequestMapping(value = "/templates/profile/previousHighSchool")
	public String getPreviousHighSchoolProfileTemplatePage(Model model) {
		model.addAttribute("previousHighSchool", new PreviousHighSchool());
		return "/templates/profile/previousHighSchool";
	}

	@RequestMapping(value = "/templates/profile/previousFaculty")
	public String getPreviousFacultyProfileTemplatePage(Model model) {
		model.addAttribute("previousFaculty", new PreviousFaculty());
		return "/templates/profile/previousFaculty";
	}

	@RequestMapping(value = "/templates/profile/legalParentFather")
	public String getLegalParentFatherProfileTemplatePage(Model model) {
		model.addAttribute("legalParentFather", new LegalParentFather());
		return "/templates/profile/legalParentFather";
	}

	@RequestMapping(value = "/templates/profile/legalParentMother")
	public String getLegalParentMotherProfileTemplatePage(Model model) {
		model.addAttribute("legalParentMother", new LegalParentMother());
		return "/templates/profile/legalParentMother";
	}

	@RequestMapping(value = "/templates/profile/personalFiles")
	public String getPersonalFilesProfileTemplatePage(Model model, Principal principal) {
		if (principal != null) {
			String authenticatedUsername = principal.getName();
			if (authenticatedUsername != null && authenticatedUsername.trim().length() > 0) {
				try {
					User authenticatedUser = userService.findByUsername(authenticatedUsername);
					if (authenticatedUser != null) {
						UserPersonalData userPersonalData = authenticatedUser.getUserPersonalData();
						if (userPersonalData != null) {
							List<File> files = userPersonalData.getFiles();
							if (files != null && files.size() > 0) {
								model.addAttribute("existFiles", true);
								model.addAttribute("files", files);
							}
						}
					}
				} catch (Exception e) {
					model.addAttribute("existFiles", false);
				}

			} else {
				model.addAttribute("existFiles", false);
			}
		}
		return "/templates/profile/personalFiles";
	}
}
