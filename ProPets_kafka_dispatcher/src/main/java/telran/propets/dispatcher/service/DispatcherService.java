package telran.propets.dispatcher.service;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(Sink.class)
public class DispatcherService {

	@StreamListener(Sink.INPUT)
	public void handlePost(String post) {
		System.out.println(post);
	}
	
}
