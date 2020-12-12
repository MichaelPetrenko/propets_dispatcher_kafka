package telran.propets.dispatcher.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import telran.propets.dispatcher.domain.dao.PostRepository;
import telran.propets.dispatcher.domain.entity.PostEntity;
import telran.propets.dispatcher.dto.LostFoundKafkaDto;
import telran.propets.dispatcher.service.EmailService;
import telran.propets.dispatcher.service.SearcherService;
import telran.propets.dispatcher.service.interfaces.IDispatcherServiceBonsai;

@Service
public class DispatcherServiceBonsaiImpl implements IDispatcherServiceBonsai {

	@Autowired
	PostRepository repo;

	@Autowired
	SearcherService searcher;

	@Autowired
	EmailService email;

	@Override
	@Transactional
	public void handlerNewPost(LostFoundKafkaDto dto) {

		// add to db
		addPost(dto);

		// get from Searcher
		List<PostEntity> result = searchByMatches(dto);

		// send res to Converter
		List<String> afterConvert = convertToEmail(result);

		// send converted to emailNotifyer
		String[] respondents = { dto.userLogin };
		email.sendSimpleMessage(respondents, "ALLO SOBAKY NASHLI", afterConvert.toString());

	}

	private List<PostEntity> searchByMatches(LostFoundKafkaDto dto) {
		PostEntity entity = dtoToEntity(dto);
		List<PostEntity> result = searcher.searchInLostOrFounds(entity);
		return result;
	}

	@Override
	public void searchByPosts(String id) {
		PostEntity entity = repo.findById(id).orElse(null);
		List<PostEntity> result = searcher.searchInLostOrFounds(entity);
		System.out.println(convertToEmail(result));
	}

	@Override
	@Transactional
	public void addPost(LostFoundKafkaDto dto) {

		PostEntity entity = dtoToEntity(dto);
		repo.save(entity);

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
		// TODO call mail service(result);

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

	private List<String> convertToEmail(List<PostEntity> list) {
//		https://propetsproj.herokuapp.com/lostfound/en/v1/post/5fa5949711ffe54ec940be68
		return list.stream().map(e -> "https://propetsproj.herokuapp.com/lostfound/en/v1/post/" + e.getId())
				.collect(Collectors.toList());
	}

}
