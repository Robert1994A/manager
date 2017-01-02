package ro.inf.ucv.admitere.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import ro.inf.ucv.admitere.repository.UserPersonalDataRepository;

public class UniquePhoneNumberValidator implements ConstraintValidator<UniquePhoneNumber, String> {

	@Autowired
	private UserPersonalDataRepository userPersonalData;

	@Override
	public void initialize(UniquePhoneNumber constraintAnnotation) {
	}

	@Override
	public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
		if (userPersonalData == null) {
			return true;
		}
		return userPersonalData.findByPhoneNumber(phoneNumber) == null;
	}

}