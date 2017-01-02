package ro.inf.ucv.admitere.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import ro.inf.ucv.admitere.repository.UserPersonalDataRepository;

public class UniqueCnpValidator implements ConstraintValidator<UniqueCnp, String> {

	@Autowired
	private UserPersonalDataRepository userPersonalDataRepository;

	@Override
	public void initialize(UniqueCnp constraintAnnotation) {
	}

	@Override
	public boolean isValid(String cnp, ConstraintValidatorContext context) {
		if (userPersonalDataRepository == null) {
			return true;
		}
		return userPersonalDataRepository.findByCnp(cnp) == null;
	}

}