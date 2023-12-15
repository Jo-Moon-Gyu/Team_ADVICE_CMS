package com.smhrd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class Email {

	private final JavaMailSender javaMailSender;
	
	@Autowired
	public Email(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	 public void sendMail(String[] to, String subject, String text) {
	        SimpleMailMessage mailMessage = new SimpleMailMessage();
	        mailMessage.setTo(to);
	        mailMessage.setSubject(subject);
	        mailMessage.setText(text);
	        javaMailSender.send(mailMessage);
	    }
	
}
