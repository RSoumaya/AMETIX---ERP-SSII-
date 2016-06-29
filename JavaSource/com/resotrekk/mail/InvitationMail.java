package com.resotrekk.mail;

import java.io.IOException;
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

import com.resotrekk.model.Candidat;
import com.resotrekk.model.Invitation;

public class InvitationMail {

	
	// ********************************************************************************************************

		final String username = "XEROBOUTIQUE.RESOTREKK@gmail.com";
		final String password = "XEROBOUTIQUE.RESOTREKKPARIS";

	// ********************************************************************************************************
		
		
		
		
		public void send(Candidat candidat, Invitation invitation) throws IOException {

			
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
						InternetAddress.parse(candidat.getEmailCandidat()));
				message.setSubject("Invitation Groupe XEROBOUTIQUE / RESOTREKK");
				
			

		  

		        //Corps
					MimeMultipart multipart = new MimeMultipart("related");
			        // first part  (the html)
			        BodyPart messageBodyPart = new MimeBodyPart();
			        String htmlText = "Bonjour Monsieur "+candidat.getNomCandidat()+".<BR></BR><BR></BR><BR></BR>Suite � notre conultation de votre profil, nous souhaitons vous inviter dans nos locaux pour vous inviter � passer un entretien le : "+invitation.getStartdate()+"<BR></BR><BR></BR>"
			        		+"Nous voudrions bien que vous confirmez la date du rendez-vous afin que nous puissions vous donnez le lieu du rendez-vous"+"<BR></BR><BR></BR>"
			        		+"Cordialement+<BR></BR><BR></BR>"
			        		+ "<img src=\"cid:image\">";
			        messageBodyPart.setContent(htmlText, "text/html");

			        // add it
			        multipart.addBodyPart(messageBodyPart);
			        
			        // second part (the image)
			        messageBodyPart = new MimeBodyPart();
			        DataSource fds = new FileDataSource
			          ("C:\\Program Files\\Apache Software Foundation\\Tomcat 8.0\\webapps\\resotrekk\\frontOffice\\images\\LOGO_Groupe.jpg");
			        messageBodyPart.setDataHandler(new DataHandler(fds));
			        messageBodyPart.setHeader("Content-ID","<image>");

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
