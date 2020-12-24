package telran.propets.dispatcher.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import telran.propets.dispatcher.service.interfaces.IEmailService;

@Service
public class EmailServiceImpl implements IEmailService {

	@Autowired
	JavaMailSender mailSender;

	@Override
	public void sendSimpleMessage(String[] to, String subject, String text) {

		SimpleMailMessage msg = new SimpleMailMessage();

		msg.setSubject(subject);
		msg.setText(text);

		for (int i = 0; i < to.length; i++) {
			msg.setTo(to[i]);

			mailSender.send(msg);
		}
	}

	@Override
	public void sendMimeMessage(String[] to, String subject, String text) throws MessagingException {

		MimeMessage message = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");

		// message.setFrom(from);
		String htmlMsg = "<h3>Im testing send a HTML email</h3>"
				+ "<a href=\"https://imgbb.com/\"><img src=\"https://i.ibb.co/Kh4B3fW/picturemessage-1qpuujtb-n02.png\" "
				+ "alt=\"picturemessage-1qpuujtb-n02\" border=\"0\" /></a>";
		
		for (int j = 0; j < to.length; j++) {
			message.setContent(htmlMsg, "text/html");
//			message.setRecipients(Message.RecipientType.TO, to[j]);
			helper.setTo(to);
//			message.setSubject(subject);
			helper.setSubject(subject);
			mailSender.send(message);

		}

	}

}
