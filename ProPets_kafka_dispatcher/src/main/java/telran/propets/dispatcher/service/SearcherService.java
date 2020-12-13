package telran.propets.dispatcher.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.propets.dispatcher.domain.dao.PostRepository;
import telran.propets.dispatcher.domain.entity.PostEntity;

@Service
public class SearcherService {
	
	@Autowired
	PostRepository repo;
	
	public List<PostEntity> searchInLostOrFounds(PostEntity entity) {

		List<PostEntity> listResult = repo.findByTypePostAndTypeAndSex(!entity.isTypePost(), entity.getType(), entity.getSex());
		
		return listResult;
	}

}
