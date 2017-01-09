package ro.inf.ucv.admitere.service;

import java.io.StringWriter;
import java.util.HashMap;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import ro.inf.ucv.admitere.mailer.Mail;

@Service
public class Mailer {

	@Autowired
	private JavaMailSenderImpl mailSender;

	@Autowired
	private VelocityEngine velocityEngine;

	public void setMailSender(JavaMailSenderImpl mailSender) {
		this.mailSender = mailSender;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	public void sendMail(Mail mail, HashMap<String, String> velocityContextMap) {

		Template template = velocityEngine.getTemplate("./templates/" + mail.getTemplateName());
		VelocityContext velocityContext = new VelocityContext(velocityContextMap);
		StringWriter stringWriter = new StringWriter();
		template.merge(velocityContext, stringWriter);
		MimeMessage message = mailSender.createMimeMessage();

		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message, true);
			helper.setTo(mail.getMailTo());
			helper.setSubject(mail.getMailSubject());
			helper.setText(stringWriter.toString(), true);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		mailSender.send(message);
	}
}