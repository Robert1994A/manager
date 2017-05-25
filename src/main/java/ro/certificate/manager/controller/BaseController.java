package ro.certificate.manager.controller;

import java.util.Locale;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

import ro.certificate.manager.captcha.AttemptsService;
import ro.certificate.manager.captcha.CaptchaService;
import ro.certificate.manager.service.KeystoreService;
import ro.certificate.manager.service.RoleService;
import ro.certificate.manager.service.UserService;
import ro.certificate.manager.service.utils.CertificateUtils;
import ro.certificate.manager.service.utils.Mailer;
import ro.certificate.manager.service.utils.StringGenerator;
import ro.certificate.manager.utils.ExtensionsUtils;

@Controller
public class BaseController {

	@Autowired
	protected ServletContext servletContext;

	@Autowired
	protected RoleService roleService;

	@Autowired
	protected UserService userService;

	@Autowired
	protected Mailer mailer;

	@Autowired
	protected CertificateUtils certificateGeneratorUtils;

	@Autowired
	protected KeystoreService keystoreService;

	@Autowired
	protected ExtensionsUtils extensionsUtils;

	@Autowired
	protected AttemptsService attemptsService;

	@Autowired
	protected CaptchaService captchaService;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	protected StringGenerator stringGenerator;

	protected BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(4);

	private Locale locale = LocaleContextHolder.getLocale();

	public String getMessage(String key, Object[] args, Locale locale) {
		return messageSource.getMessage(key, args, locale);
	}

	public String getMessage(String key, Object args, Locale locale) {
		Object[] objects = { args };
		return messageSource.getMessage(key, objects, locale);
	}

	public Locale getLocale() {
		return locale;
	}

	@Value("${captcha.siteKey}")
	protected String captchaSiteKey;

	@Value("${file.maxUploadSize}")
	protected Long maxUploadSize;

	@Value("${email.username}")
	protected String email;

	@Value("${site.homeURL}")
	protected String siteHomeURL;

	@Value("${contact.email}")
	protected String contactEmail;
	
	@Value("${email.title}")
	protected String emailTitle;

}
