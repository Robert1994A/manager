package ro.inf.ucv.admitere.controller.json;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ro.inf.ucv.admitere.entity.Contract;
import ro.inf.ucv.admitere.entity.User;
import ro.inf.ucv.admitere.exceptions.UserNotFound;

@RestController
public class ContractPageController extends BaseController {

	private static final int PER_PAGE = 10;

	private static final String SORT_BY = "id";

	private static final int PAGE_NUMBER = 1;

	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/json/contractPage/{id}.json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Contract findOneJSON(@PathVariable("id") Long id) {
		Contract contractPage = contractPageService.findOne(id);
		if (contractPage == null) {
			return null;
		}
		return contractPage;
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/json/contractPage/contractsPage.json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Contract> findAllJSON() {
		List<Contract> contractPage = contractPageService.findAll();
		if (contractPage == null) {
			return null;
		}
		return contractPage;
	}

	/**
	 * 
	 * @param contractPage
	 * @return
	 */
	@RequestMapping(value = "/json/contractPage/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Contract saveContractPageJSON(@RequestBody Contract contractPage, Principal principal) {
		if (contractPage != null) {
			if (contractPage.getContent().length() > 0) {
				try {
					User authenticateUser = userService.findByUsername(principal.getName());
					if(authenticateUser != null){
						contractPage.setUser(authenticateUser);
						contractPage.setPublishedDate(new Date());
						contractPageService.save(contractPage);
					}
				} catch (UserNotFound e) {
					e.printStackTrace();
				}
				
				
			}

		}
		return contractPage;
	}

	/**
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @param sortBy
	 * @return
	 */
	@RequestMapping(value = "/json/contractPage/contractsPagePaginate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Contract> findContractsPaginateJSON(
			@RequestParam(value = "pageNumber", required = true) int pageNumber,
			@RequestParam(value = "pageSize", required = true) int pageSize,
			@RequestParam(value = "sortBy", required = true) String sortBy) {

		if (pageNumber == 0) {
			pageNumber = PAGE_NUMBER;
		}

		if (pageSize == 0) {
			pageSize = PER_PAGE;
		}

		if (sortBy.equals("") && sortBy.length() == 0) {
			sortBy = SORT_BY;
		}

		List<Contract> contractPage = this.contractPageService
				.findAll(new PageRequest(pageNumber, pageSize, Direction.ASC, sortBy)).getContent();
		if (contractPage == null) {
			return null;
		}
		return contractPage;
	}

}
