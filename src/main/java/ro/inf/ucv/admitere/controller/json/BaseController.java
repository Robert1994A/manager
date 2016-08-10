package ro.inf.ucv.admitere.controller.json;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ro.inf.ucv.admitere.service.ContractPageService;
import ro.inf.ucv.admitere.service.RoleService;
import ro.inf.ucv.admitere.service.UserService;

@Controller
public class BaseController {

	@Autowired
	protected ContractPageService contractPageService;

    @Autowired
    protected RoleService roleService;

    @Autowired
    protected UserService userService;

}
