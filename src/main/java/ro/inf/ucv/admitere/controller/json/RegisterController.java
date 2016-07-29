package ro.inf.ucv.admitere.controller.json;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ro.inf.ucv.admitere.entity.User;

@Controller
public class RegisterController {

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerGET() {
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String doRegisterPOST(@ModelAttribute("user") User user) {


        return "register";
    }
}
