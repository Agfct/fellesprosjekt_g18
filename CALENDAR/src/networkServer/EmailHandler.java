package networkServer;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailHandler {

	final String username;
	final String password;
	
	final String smtpHost;
	final String smtpPort;
	
	
	
	public EmailHandler () {
		username = "fellesprosjektgruppe18@gmail.com";
		password = "rosaelefant";
		smtpHost = "smtp.gmail.com";
		smtpPort = "587";
	}
	
	public boolean sendEmail(String mail, String subject, String msg){
 
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", smtpHost);
		props.put("mail.smtp.port", smtpPort);
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
 
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(mail));
			message.setSubject(subject);
			message.setText(msg);
 
			Transport.send(message);
 
			return true;
 
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}	     
	}
}
