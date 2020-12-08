package telran.propets.dispatcher.service.impl;

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

	@Override
	public void searchByPosts(String id) {
//		PostEntity newEntity = dtoToEntity(dto);
		PostEntity entity = repo.findById(id).orElse(null);
		if (entity.isTypePost()) {
			searcher.searchInLosts(entity);
		} else {
			searcher.searchInFounds(entity);
		}
	}

	@Override
	@Transactional
	public void addPost(LostFoundKafkaDto dto) {
		PostEntity entity = dtoToEntity(dto);
		repo.save(entity);
		System.out.println("======= ADDED SUCCESSFULLY ========");

		if (entity.isTypePost()) {
			searcher.searchInLosts(entity);
		} else {
			searcher.searchInFounds(entity);
		}

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
		// Warning! Maybe entity not owerrited
		repo.save(newEntity);
		System.out.println("======= UPDATED SUCCESSFULLY ========");
		// searcher(entity)

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
