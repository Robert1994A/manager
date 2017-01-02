package ro.inf.ucv.admitere.controller.json;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

import ro.inf.ucv.admitere.entity.User;
import ro.inf.ucv.admitere.service.ContractPageService;
import ro.inf.ucv.admitere.service.Mailer;
import ro.inf.ucv.admitere.service.RoleService;
import ro.inf.ucv.admitere.service.UserService;
import ro.inf.ucv.admitere.utils.Generator;

@Controller
public class BaseController {

	@Autowired
	protected ContractPageService contractPageService;

	@Autowired
	protected RoleService roleService;

	@Autowired
	protected UserService userService;

	@Autowired
	protected Mailer mailer;

	User user = null;

	Generator generator = new Generator();
	
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

}
