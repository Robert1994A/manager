package ro.inf.ucv.admitere.controller.json;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

import ro.inf.ucv.admitere.entity.User;
import ro.inf.ucv.admitere.service.AddressService;
import ro.inf.ucv.admitere.service.ContractService;
import ro.inf.ucv.admitere.service.CountryService;
import ro.inf.ucv.admitere.service.FileService;
import ro.inf.ucv.admitere.service.IdentityCardService;
import ro.inf.ucv.admitere.service.LegalParentFatherService;
import ro.inf.ucv.admitere.service.LegalParentMotherService;
import ro.inf.ucv.admitere.service.Mailer;
import ro.inf.ucv.admitere.service.PreviousHighSchoolService;
import ro.inf.ucv.admitere.service.RoleService;
import ro.inf.ucv.admitere.service.UserPersonalDataService;
import ro.inf.ucv.admitere.service.UserService;
import ro.inf.ucv.admitere.utils.Generator;

@Controller
public class BaseController {

	@Autowired
	protected ContractService contractPageService;

	@Autowired
	protected RoleService roleService;

	@Autowired
	protected UserService userService;

	@Autowired
	protected CountryService countryService;

	@Autowired
	protected FileService fileService;

	@Autowired
	protected LegalParentFatherService legalParentFatherService;

	@Autowired
	protected LegalParentMotherService legalParentMotherService;

	@Autowired
	protected IdentityCardService identityCardService;

	@Autowired
	protected UserPersonalDataService userPersonalDataService;

	@Autowired
	protected PreviousHighSchoolService previousHighSchoolService;

	@Autowired
	protected AddressService addressService;

	User user = null;

	Generator generator = new Generator();

	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@Autowired
	protected Mailer mailer;

}
