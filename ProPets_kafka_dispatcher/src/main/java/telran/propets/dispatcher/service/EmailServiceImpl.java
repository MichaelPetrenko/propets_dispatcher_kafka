package telran.propets.dispatcher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import telran.propets.dispatcher.service.interfaces.IEmailService;

@Service
public class EmailServiceImpl implements IEmailService {

	@Autowired
	JavaMailSender mailSender;

	@Override
	public void sendSimpleMessage(String[] to, String subject, String text) {

		System.out.println("start sendSimple");
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setSubject(subject);
		msg.setText(text);
		for(int i=0 ; i < to.length; i++) {
			msg.setTo(to[i]);
			mailSender.send(msg);// JavaMailSender
		}
	}

	/**
	 * MimeMessage message = mailSender.createMimeMessage(); message.setFrom(from);
	 * message.setRecipients(Message.RecipientType.TO, to);
	 * message.setSubject(subject);
	 * 
	 * MimeMessageHelper helper = new MimeMessageHelper(message, true);
	 * 
	 * helper.setText(body, true); helper.addInline(attachmentName,
	 * attachmentInputStream, attachmentContentType); mailSender.send(message); В
	 * теле письма добавить <img src="cid:picture.png"/>. picture.png это
	 * attachmentName.
	 * 
	 * mailSender это org.springframework.mail.javamail.JavaMailSender.
	 */

}
