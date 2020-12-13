package telran.propets.dispatcher.service.interfaces;

import telran.propets.dispatcher.domain.entity.PostEntity;
import telran.propets.dispatcher.dto.LostFoundKafkaDto;

public interface IDispatcherServiceBonsai {
	
	//CRUD:
	public void addPost(LostFoundKafkaDto dto);
	public void updatePost(LostFoundKafkaDto dto);
	public void removePost(String id);
	
	//Inner:
	public PostEntity dtoToEntity(LostFoundKafkaDto dto);
	public void searchByPosts(String id);
	public void handlerSendingEmail(LostFoundKafkaDto dto);

}
