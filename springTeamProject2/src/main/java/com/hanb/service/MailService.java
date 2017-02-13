package com.hanb.service;

import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;


@Controller
public class MailService {
	private JavaMailSender sender = null;
	public MailService(){
		Properties pro = new Properties();
		pro.setProperty("mail.smtp.starttls.enable", "true");
		pro.setProperty("mail.smtp.auth", "true");
		pro.setProperty("mail.smtp.ssl.checkserveridentity", "true");
		pro.setProperty("mail.smtp.ssl.trust", "*");
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		sender.setHost("smtp.naver.com");
		sender.setPort(465);
		sender.setProtocol("smtps");
		sender.setUsername("jyc228");
		sender.setPassword("4572489a");
		sender.setDefaultEncoding("UTF-8");
		sender.setJavaMailProperties(pro);
		this.sender = sender;
	}
	
	public void send(final String m_email,final String title,final String content){
		sender.send(new MimeMessagePreparator() {
			
			@Override
			public void prepare(MimeMessage message) throws Exception {
				// TODO Auto-generated method stub
				MimeMessageHelper msg = new MimeMessageHelper(message, "UTF-8");
				msg.setFrom("jyc228@naver.com");
				msg.setTo(m_email);
				msg.setSubject(title);
				msg.setText(content,true);
			}
			
		});
	}
}
