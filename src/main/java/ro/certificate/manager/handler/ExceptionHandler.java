package ro.certificate.manager.handler;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ro.certificate.manager.controller.BaseController;
import ro.certificate.manager.exceptions.InternalServerError;

@ControllerAdvice
class AppplicationExceptionHandler extends BaseController {

	private static final Logger logger = Logger.getLogger(AppplicationExceptionHandler.class);

	@ExceptionHandler(value = { InternalServerError.class })
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public String customExceptions(Exception ex, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		logger.error(ex.getMessage());
		redirectAttributes.addFlashAttribute("error", "Cannot process your request: " + ex.getMessage());

		return "redirect:/503";
	}

	/**
	 * Generic class for all types of exceptions, DEBUG scope
	 */
	@ExceptionHandler(value = { NoHandlerFoundException.class })
	@ResponseStatus(value = HttpStatus.PRECONDITION_FAILED)
	public String noHandlerDefined(Exception ex) {
		logger.error(ex.getMessage());

		return "redirect:/404";
	}

	/**
	 * Generic class for all types of exceptions, DEBUG scope
	 */
	@ExceptionHandler(value = { Exception.class })
	@ResponseStatus(value = HttpStatus.PRECONDITION_FAILED)
	public String debug(Exception ex, HttpServletRequest request) {
		logger.error(ex.getMessage());

		return "redirect:/503";

	}
}
