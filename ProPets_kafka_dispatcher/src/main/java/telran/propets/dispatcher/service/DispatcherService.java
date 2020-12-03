package telran.propets.dispatcher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

import telran.propets.dispatcher.dto.LostFoundKafkaDto;
import telran.propets.dispatcher.service.impl.DispatcherServiceBonsaiImpl;

@EnableBinding(Sink.class)
public class DispatcherService {
	
	@Autowired
	DispatcherServiceBonsaiImpl service;

	@StreamListener(Sink.INPUT)
	public void handlePost(LostFoundKafkaDto post) {
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
		
//		if(post.kafkaReqType.toString().equals("CREATE"))
	}
	
}
