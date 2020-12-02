package telran.propets.dispatcher.service;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

import telran.propets.dispatcher.LostFoundKafkaDto;

@EnableBinding(Sink.class)
public class DispatcherService {

	@StreamListener(Sink.INPUT)
	public void handlePost(LostFoundKafkaDto post) {
		System.out.println(post.toString());
	}
	
}
