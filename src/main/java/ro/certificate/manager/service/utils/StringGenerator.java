package ro.certificate.manager.service.utils;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
public class StringGenerator {

	public String getRandomString() {
		return RandomStringUtils.random(128, true, true);
	}

	public String getUsernameString() {
		return RandomStringUtils.random(13, false, true);
	}

	public String getRandomNumberForPageTitle() {
		return RandomStringUtils.random(2, false, true);
	}

	public String getRandomNumberForFileName() {
		return RandomStringUtils.random(4, false, true);
	}

	public String generateRandomNumber() {
		return RandomStringUtils.random(6, false, true);
	}
}
