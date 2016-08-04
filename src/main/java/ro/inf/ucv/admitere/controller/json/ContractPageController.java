package ro.inf.ucv.admitere.controller.json;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ro.inf.ucv.admitere.entity.ContractPage;
import ro.inf.ucv.admitere.service.ContractPageService;

@RestController
public class ContractPageController {

	@Autowired
	private ContractPageService contractPageService;

	@RequestMapping(value = "/json/contractPage/{id}.json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ContractPage findOneJSON(@PathVariable("id") Long id) {
		ContractPage contractPage = contractPageService.findOne(id);
		if (contractPage == null) {
			return null;
		}
		return contractPage;
	}

	@RequestMapping(value = "/json/contractPage/contractsPage.json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<ContractPage> findAllJSON() {
		List<ContractPage> contractPage = contractPageService.findAll();
		if (contractPage == null) {
			return null;
		}
		return contractPage;
	}

	@RequestMapping(value = "/json/contractPage/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ContractPage saveContractPageJSON(@RequestBody ContractPage contractPage) {
		if (contractPage != null) {
			if (contractPage.getPage().length() > 0) {
				if (contractPageService.count() > 0) {
					ContractPage contractPageUpdate = contractPageService.findOne((long) 1);
					contractPageUpdate.setPage(contractPage.getPage());
					contractPageService.save(contractPageUpdate);
				} else {
					contractPageService.save(contractPage);
				}
			}

		}
		return contractPage;
	}

}
