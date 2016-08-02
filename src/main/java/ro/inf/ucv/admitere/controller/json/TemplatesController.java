package ro.inf.ucv.admitere.controller.json;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
@EnableWebMvc
public class TemplatesController {

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
}
