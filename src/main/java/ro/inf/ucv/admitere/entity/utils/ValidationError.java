package ro.inf.ucv.admitere.entity.utils;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

public class ValidationError {

	private HttpStatus status;
	private String message;
	private List<ValidationFieldError> errors;

	public ValidationError(HttpStatus status, String message, List<ValidationFieldError> errors) {
		super();
		this.status = status;
		this.message = message;
		this.errors = errors;
	}

	public ValidationError(HttpStatus status, String message, ValidationFieldError error) {
		super();
		this.status = status;
		this.message = message;
		errors = Arrays.asList(error);
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<ValidationFieldError> getErrors() {
		return errors;
	}

	public void setErrors(List<ValidationFieldError> errors) {
		this.errors = errors;
	}

}
