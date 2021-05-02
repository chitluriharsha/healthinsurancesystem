package com.poc.healthinsurancesystem.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Component
public class Util {
	private static Logger logger = LoggerFactory.getLogger(Util.class);
	
	@Value("${spring.mail.username}")
	private String fromEmail;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private Configuration configuration;
	
	private final StringTemplateLoader stringLoader = new StringTemplateLoader();
	
	@PostConstruct
	public void init() {
		configuration.setTemplateLoader(stringLoader);
	}

	public void sendMail(String toEmail, String subject, String templateData, Map<String, Object> model, String templateName) throws MessagingException, IOException, TemplateException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message,MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,StandardCharsets.UTF_8.name());
		helper.setTo(toEmail);
		helper.setSubject(subject);
		helper.setFrom(fromEmail);
		
		stringLoader.putTemplate(templateName, templateData);
		Template template = configuration.getTemplate(templateName);
		String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
		helper.setText(html, true);
		javaMailSender.send(message);
		
		/*
		 * SimpleMailMessage msg = new SimpleMailMessage(); msg.setFrom(fromEmail);
		 * msg.setTo(toEmail); msg.setSubject(subject); msg.setText(text);
		 * javaMailSender.send(msg);
		 */
	}
	
	public Date addSevenDaysToCurrentdate() {
		logger.info("Creating sql date and adding 7 days from the current date");
		java.util.Date today = new java.util.Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		cal.add(Calendar.DATE, 7);
		Date sqlDate = new java.sql.Date(cal.getTime().getTime());
		logger.info("Created sql date and added 7 days from the current date");
		return sqlDate;
	}

}
