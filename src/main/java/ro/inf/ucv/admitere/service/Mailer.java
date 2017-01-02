package ro.inf.ucv.admitere.service;

import java.io.StringWriter;
import java.util.HashMap;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import ro.inf.ucv.admitere.mailer.Mail;

@Service
public class Mailer {

	@Autowired
	private MailSender mailSender;

	@Autowired
	private VelocityEngine velocityEngine;

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	public void sendMail(Mail mail, HashMap<String, String> velocityContextMap) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(mail.getMailFrom());
		message.setTo(mail.getMailTo());
		message.setSubject(mail.getMailSubject());

		Template template = velocityEngine.getTemplate("./templates/" + mail.getTemplateName());
		VelocityContext velocityContext = new VelocityContext(velocityContextMap);

		StringWriter stringWriter = new StringWriter();

		template.merge(velocityContext, stringWriter);

		message.setText(stringWriter.toString());

		mailSender.send(message);
	}
}