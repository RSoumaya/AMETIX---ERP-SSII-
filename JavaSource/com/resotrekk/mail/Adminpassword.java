package com.resotrekk.mail;

import com.resotrekk.model.Administrateur;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class Adminpassword {

	// ********************************************************************************************************



		final String username = "XEROBOUTIQUE.RESOTREKK@gmail.com";
		final String password = "XEROBOUTIQUE.RESOTREKKPARIS";

		// ********************************************************************************************************
		
		
		
		
		public void send(Administrateur admin,String passwordd) {

			
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");

			Session session = Session.getInstance(props,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(username, password);
						}
					});

			try {
   
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("XEROBOUTIQUE.RESOTREKK@gmail.com"));
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(admin.getEmailAdmin()));
				message.setSubject("Mot de passe oublié");
				
		        	//Corps
					MimeMultipart multipart = new MimeMultipart("related");
			        // first part  (the html)
			        BodyPart messageBodyPart = new MimeBodyPart();
			        String htmlText = "Votre nouveau mot de passe "+passwordd ;
			        messageBodyPart.setContent(htmlText, "text/html");

			        // add it
			        multipart.addBodyPart(messageBodyPart);
			        
			        
			        // put everything together
			        message.setContent(multipart);
				
				Transport.send(message);
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
			}
		



		// ********************************************************************************************************
}
