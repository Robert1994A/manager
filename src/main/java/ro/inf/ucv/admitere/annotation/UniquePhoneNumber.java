package ro.inf.ucv.admitere.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = { UniquePhoneNumberValidator.class })
public @interface UniquePhoneNumber {

	String message();

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
