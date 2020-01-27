package com.bridgelabz.fundoonotes.utility;

//@Configuration
//@Component
//public class JmsProvider {
//
//	// private static JavaMailSender javaMailSender = new JavaMailSenderImpl();
//
//	public static void sendEmail(String toEmail, String subject, String body) {
//		try {
////			String fromEmail = "er.nancysoni@gmail.com";
////			String password = "beti1cs11043@446";
//
//			Properties prop = new Properties();
//			prop.put("mail.smtp.auth", "true");
//			prop.put("mail.smtp.starttls.enable", "true");
//			prop.put("mail.smtp.host", "smtp.gmail.com");
//			prop.put("mail.smtp.port", "587");
//
//			Authenticator auth = new Authenticator() {
//				@Override
//				protected PasswordAuthentication getPasswordAuthentication() {
//					return new PasswordAuthentication(fromEmail, password);
//				}
//			};
//			Session session = Session.getInstance(prop, auth);
//			send(session, fromEmail, toEmail, subject, body);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//	}
//
//	private static void send(Session session, String fromEmail, String toEmail, String subject, String body) {
//		try {
//			MimeMessage message = new MimeMessage(session);
//			message.setFrom(new InternetAddress(fromEmail, "nancy"));
//			System.out.println("1");
//
//			//System.out.println("==>" + toEmail);
//			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
//			message.setSubject(subject);
//			message.setText(body);
//			Transport.send(message);
//			// javaMailSender.send(message);
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("can not send mail  ");
//
//		}
//	}
//}
