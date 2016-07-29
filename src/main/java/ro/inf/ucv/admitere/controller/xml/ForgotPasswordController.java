package ro.inf.ucv.admitere.controller.xml;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ForgotPasswordController {

    @RequestMapping(value = "/recover", method = RequestMethod.GET)
    public String forgot() {
        return "recover";
    }
}