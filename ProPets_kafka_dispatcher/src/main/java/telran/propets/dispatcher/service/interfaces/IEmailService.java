package telran.propets.dispatcher.service.interfaces;

import javax.mail.MessagingException;

public interface IEmailService {
	
	void sendSimpleMessage(String[] to, String subject, String text);
	
	void sendMimeMessage(String[] to, String subject, String text) throws MessagingException;

}
