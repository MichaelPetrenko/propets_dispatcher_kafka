package telran.propets.dispatcher.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import telran.propets.dispatcher.domain.dao.PostRepository;
import telran.propets.dispatcher.domain.entity.PostEntity;
import telran.propets.dispatcher.dto.LostFoundKafkaDto;
import telran.propets.dispatcher.service.SearcherService;
import telran.propets.dispatcher.service.interfaces.IDispatcherServiceBonsai;

@Service
public class DispatcherServiceBonsaiImpl implements IDispatcherServiceBonsai {

	@Autowired
	PostRepository repo;

	@Autowired
	SearcherService searcher;
	
//	@Autowired
//	EmailService email;

	@Override
	public void searchByPosts(String id) {
		PostEntity entity = repo.findById(id).orElse(null);
		List<PostEntity> result = searcher.searchInLostOrFounds(entity);
		System.out.println(convertToEmail(result));
	}

	private List<String> convertToEmail(List<PostEntity> list) {
//		https://propetsproj.herokuapp.com/lostfound/en/v1/post/5fa5949711ffe54ec940be68
		return list.stream()
		.map(e->"https://propetsproj.herokuapp.com/lostfound/en/v1/post/"+e.getId())
		.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public void addPost(LostFoundKafkaDto dto) {
		PostEntity entity = dtoToEntity(dto);
		repo.save(entity);
//		System.out.println("======= ADDED SUCCESSFULLY ========");

		List<PostEntity> result = searcher.searchInLostOrFounds(entity);
		System.out.println(convertToEmail(result));
		//TODO call mail service(result);

	}

	@Override
	@Transactional
	public void updatePost(LostFoundKafkaDto dto) {
		PostEntity newEntity = dtoToEntity(dto);
		PostEntity oldEntity = repo.findById(dto.id).orElse(null);
		if (oldEntity == null) {
			System.out.println("===== OLD ENTITY IS NULL =======");
			return;
		}
		
		repo.save(newEntity);
		System.out.println("======= UPDATED SUCCESSFULLY ========");
		List<PostEntity> result = searcher.searchInLostOrFounds(newEntity);
		System.out.println(convertToEmail(result));
		//TODO call mail service(result);

	}

	@Override
	public void removePost(String id) {
		repo.deleteById(id);
		System.out.println("======== DELETED ? ==========");

	}

	@Override
	public PostEntity getPostById(String id) {
		return repo.findById(id).get();

	}

	@Override
	public PostEntity dtoToEntity(LostFoundKafkaDto dto) {
		PostEntity entity = new PostEntity(dto);
		return entity;
	}

}
