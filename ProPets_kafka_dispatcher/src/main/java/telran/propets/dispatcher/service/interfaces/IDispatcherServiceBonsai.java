package telran.propets.dispatcher.service.interfaces;

import telran.propets.dispatcher.domain.entity.PostEntity;
import telran.propets.dispatcher.dto.LostFoundKafkaDto;

public interface IDispatcherServiceBonsai {
	//Creating post:
	public void addPost(LostFoundKafkaDto dto);
	
	//Updating post:
	public void updatePost(LostFoundKafkaDto dto);
	
	//Deleting post:
	public void removePost(String id);
	
	//Inner:
	public PostEntity getPostById(String id);
	public PostEntity dtoToEntity(LostFoundKafkaDto dto);

}
