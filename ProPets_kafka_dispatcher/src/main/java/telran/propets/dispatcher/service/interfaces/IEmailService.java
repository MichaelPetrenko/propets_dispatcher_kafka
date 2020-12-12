package telran.propets.dispatcher.service.interfaces;

public interface IEmailService {
	
	void sendSimpleMessage(String[] to, String subject, String text);

}
