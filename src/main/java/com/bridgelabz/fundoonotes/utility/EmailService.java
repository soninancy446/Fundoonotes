package com.bridgelabz.fundoonotes.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import lombok.Data;
@Component
public class EmailService {

    @Autowired
	private JavaMailSender emailSender;

	public void sendSimpleMessage( Mail mail) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setSubject(mail.getSubject());
		message.setText(mail.getContent());
		message.setTo(mail.getTo());
		message.setFrom(mail.getFrom());

		emailSender.send(message);
	}

	
	
	
	
//	@RabbitListener(queues = "rmq.rube.queue")
//	public void recievedMessage(final Mail mail) {
//		sendSimpleMessage(mail);
//		System.out.println("Reciecved msg from rabbitmq:");
//	}

}
