package telran.propets.dispatcher.service.interfaces;

import telran.propets.dispatcher.domain.entity.PostEntity;
import telran.propets.dispatcher.dto.LostFoundKafkaDto;

public interface IDispatcherServiceBonsai {
	
	public void handlerSendingEmail(LostFoundKafkaDto dto);
	//Creating post:
	public void addPost(LostFoundKafkaDto dto);
	
	//Updating post:
	public void updatePost(LostFoundKafkaDto dto);
	
	//Deleting post:
	public void removePost(String id);
	
	//Inner:
	public PostEntity dtoToEntity(LostFoundKafkaDto dto);
	
	public void searchByPosts(String id);

}
