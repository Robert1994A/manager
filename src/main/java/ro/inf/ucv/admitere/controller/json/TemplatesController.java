package ro.inf.ucv.admitere.controller.json;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import ro.inf.ucv.admitere.entity.ContractPage;

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
	public String getContractstTemplatePage(Model model) {

		if (contractPageService.count() == 0) {
			for (int i = 0; i < 100; i++) {
				ContractPage contractPage = new ContractPage();
				StringBuilder pageContent = new StringBuilder(UUID.randomUUID().toString());
				contractPage.setPage(pageContent);
				this.contractPageService.save(contractPage);
			}
		}
		List<ContractPage> contracts = this.contractPageService.findAll();
		if (contracts.size() > 0) {
			model.addAttribute("contracts", contracts);
		} else {

		}
		return "/templates/contracts";
	}
}
