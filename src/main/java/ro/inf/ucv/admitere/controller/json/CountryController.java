package ro.inf.ucv.admitere.controller.json;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ro.inf.ucv.admitere.entity.Country;

@RestController
public class CountryController extends BaseController {

	@RequestMapping(value = "/countries/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Country>> getCountryByNameJSON(@PathVariable("name") String countryName) {

		List<Country> countryNames = countryService.findByNameOrCodeContaining(countryName, countryName);

		return new ResponseEntity<List<Country>>(countryNames, HttpStatus.OK);
	}

	@RequestMapping(value = "/countries", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Country>> getAllCountriesJSON() {

		List<Country> countryNames = countryService.findAll();

		return new ResponseEntity<List<Country>>(countryNames, HttpStatus.OK);
	}
}
