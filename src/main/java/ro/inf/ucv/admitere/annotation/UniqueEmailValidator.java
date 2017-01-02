package ro.inf.ucv.admitere.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import ro.inf.ucv.admitere.repository.UserRepository;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void initialize(UniqueEmail constraintAnnotation) {
	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		if (userRepository == null) {
			return true;
		}
		return userRepository.findByEmail(email) == null;
	}
}