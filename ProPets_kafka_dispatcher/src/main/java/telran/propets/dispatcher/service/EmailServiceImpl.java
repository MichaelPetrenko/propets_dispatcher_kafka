package telran.propets.dispatcher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import telran.propets.dispatcher.service.interfaces.IEmailService;

@Service
public class EmailServiceImpl implements IEmailService{
	
	@Autowired
	JavaMailSender mailSender;

	@Override
	public void sendSimpleMessage(String[] to, String subject, String text) {
		
		System.out.println("start sendSimple");
		SimpleMailMessage msg = new SimpleMailMessage();
		
//		msg.setTo(to[0], to[1]);
		msg.setTo(to[0]);
		msg.setSubject(subject);
		msg.setText(text);
		mailSender.send(msg);//JavaMailSender
		
	}
	
}
