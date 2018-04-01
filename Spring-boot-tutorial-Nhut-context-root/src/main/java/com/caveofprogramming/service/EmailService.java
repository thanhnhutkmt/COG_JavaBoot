/**
 * 
 */
package com.caveofprogramming.service;

import java.util.Date;
import java.util.HashMap;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

/**
 * @author java_dev
 *
 */
@Service
public class EmailService {
	private TemplateEngine templateEngine;
	
	@Autowired
	private JavaMailSender mailSender;
	
//	@Autowired
//	private VelocityEngine velocityEngine;
	
	@Value("${mail.enable}")	
	private Boolean enable;
	
	private void send(MimeMessagePreparator preparator) {
		if (enable) mailSender.send(preparator);
	}
	
	@Autowired
	public EmailService(TemplateEngine templateEngine) {
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setPrefix("mail/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode("HTML5");
		templateResolver.setCacheable(false);		
		templateEngine.setTemplateResolver(templateResolver);		
		
		this.templateEngine = templateEngine;
	}
	
	public void sendVerificationEmail(String emailAddress) {
		//thymeleaf
		Context context = new Context();
		context.setVariable("name", "Bob");
		String emailContents = templateEngine.process("verifyemail", context);
		System.out.println(emailContents);
		
//		//velocity
//		HashMap<String, Object> model = new HashMap<>();
//		model.put("test",  "This is some dynmic data");
//		String velocitycontents = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, 
//				"/com/caveofprogramming/velocity/verifyemail.vm", model);
		
		MimeMessagePreparator preparator = new MimeMessagePreparator() {			
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setTo(emailAddress);
				message.setFrom(new InternetAddress("no-reply@caveofprogramming.com"));
				message.setSubject("Please verify you email address");
				message.setSentDate(new Date());
				
				message.setText(emailContents, true);
			}
		};
		send(preparator);
	}
}
