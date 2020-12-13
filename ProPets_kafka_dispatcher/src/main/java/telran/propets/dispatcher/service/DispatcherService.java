package telran.propets.dispatcher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import telran.propets.dispatcher.dto.KafkaReqType;
import telran.propets.dispatcher.dto.LostFoundKafkaDto;
import telran.propets.dispatcher.service.impl.DispatcherServiceBonsaiImpl;

@SuppressWarnings("deprecation")
@EnableBinding(Sink.class)
public class DispatcherService {
	
	@Autowired
	DispatcherServiceBonsaiImpl service;

	@StreamListener(Sink.INPUT)
	public void handlePost(LostFoundKafkaDto post) {
		
		if(post.kafkaReqType.equals(KafkaReqType.CREATE)) {
			service.addPost(post);
		}
		if(post.kafkaReqType.equals(KafkaReqType.UPDATE)) {
			service.updatePost(post);
		}
		if(post.kafkaReqType.equals(KafkaReqType.DELETE)) {
			service.removePost(post.id);
		}
		return;
	}
	
}
