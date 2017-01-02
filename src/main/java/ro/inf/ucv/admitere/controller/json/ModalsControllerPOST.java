package ro.inf.ucv.admitere.controller.json;

import java.util.ArrayList;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ro.inf.ucv.admitere.entity.Address;
import ro.inf.ucv.admitere.entity.UserPersonalData;
import ro.inf.ucv.admitere.service.UserPersonalDataService;

@RestController
@Validated
public class ModalsControllerPOST {

	@Autowired
	private UserPersonalDataService userPersonalDataService;

	@RequestMapping(value = "/modals/addPersonalData.html", method = RequestMethod.POST)
	@ResponseBody
	public UserPersonalData saveProfilePersonalDataModal(@RequestBody UserPersonalData userPersonalData) {
		userPersonalData.setAddress(new Address());
		userPersonalData.setIdentityCard(new ArrayList<>());
		userPersonalData.setPrevoiusHighschool(new HashSet<>());
		userPersonalData.setPreviousFaculty(new HashSet<>());
		userPersonalData = userPersonalDataService.save(userPersonalData);
		return userPersonalData;
	}
}
