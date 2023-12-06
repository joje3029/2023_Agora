package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.demo.util.MailHandler;
import com.example.demo.vo.ResultData;

@Service
public class EmailService {

	private JavaMailSender javaMailSender;
	@Value("${custom.emailFrom}")
	private String emailFrom;
	@Value("${custom.emailFromName}")
	private String emailFromName;

	public EmailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public ResultData send(String to, String subject, String text) {

		MailHandler mail;
		try {
			mail = new MailHandler(javaMailSender);
			mail.setFrom(emailFrom.replaceAll(" ", ""), emailFromName);
			mail.setTo(to);
			mail.setSubject(subject);
			mail.setText(text);
			mail.send();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultData.from("F-1", "메일 발송 실패하였습니다.");
		}

		return ResultData.from("S-1", "메일이 발송되었습니다.");
	}
}
