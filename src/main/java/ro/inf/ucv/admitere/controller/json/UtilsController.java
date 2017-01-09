package ro.inf.ucv.admitere.controller.json;

import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ro.inf.ucv.admitere.entity.Country;
import ro.inf.ucv.admitere.populate.PopulateDatabase;

@RestController
public class UtilsController extends BaseController {

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/update", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Country>> saveUserPersonalDataJSON(Principal principal) {

		PopulateDatabase populateDatabase = new PopulateDatabase();

		List<Country> countries = populateDatabase.getAllCountryNames();

		if (countries != null) {
			for (Country country : countries) {
				Country existCountry = countryService.findByCode(country.getCode());
				if (existCountry == null) {
					countryService.save(country);
				}
			}
		}
		return new ResponseEntity<List<Country>>(countryService.findAll(), HttpStatus.OK);
	}
}
