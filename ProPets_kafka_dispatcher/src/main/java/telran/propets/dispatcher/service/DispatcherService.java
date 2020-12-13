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
		//TODO clean
		System.out.println(post.toString());
		/**
		 * Here we recieving post.
		 * If (kafkaReqType==CREATE && typePost==false) => new post of lost pet => DB LostPets Elastic
		 * If (kafkaReqType==CREATE && typePost==true) => new post of found pet => DB FoundPets Elastic
		 * If (kafkaReqType==UPDATE && typePost==false) => update of lost pet => DB LostPets Elastic
		 * If (kafkaReqType==UPDATE && typePost==true) => update of found pet => DB FoundPets Elastic
		 * If (kafkaReqType==DELETE && typePost==false) => delete of lost pet => DB LostPets Elastic
		 * If (kafkaReqType==DELETE && typePost==true) => delete of found pet => DB FoundPets Elastic
		 */
		
		if(post.kafkaReqType.equals(KafkaReqType.CREATE)) {
			service.handlerNewPost(post);
			System.out.println("--- Controller --- Post Added -------");
		}
		if(post.kafkaReqType.equals(KafkaReqType.UPDATE)) {
			service.updatePost(post);
			System.out.println("--- Controller --- Post Updated -------");
		}
		if(post.kafkaReqType.equals(KafkaReqType.DELETE)) {
			service.removePost(post.id);
			System.out.println("--- Controller --- Post Removed -------");
		}
		return;
	}
	
}
