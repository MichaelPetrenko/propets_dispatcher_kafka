package telran.propets.dispatcher.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import telran.propets.dispatcher.domain.dao.PostRepository;
import telran.propets.dispatcher.domain.entity.PostEntity;
import telran.propets.dispatcher.dto.LostFoundKafkaDto;
import telran.propets.dispatcher.service.EmailServiceImpl;
import telran.propets.dispatcher.service.SearcherService;
import telran.propets.dispatcher.service.interfaces.IDispatcherServiceBonsai;

@Service
public class DispatcherServiceBonsaiImpl implements IDispatcherServiceBonsai {

	@Autowired
	PostRepository repo;

	@Autowired
	SearcherService searcher;

	@Autowired
	EmailServiceImpl email;
	
	@Override
	@Transactional
	public void handlerSendingEmail(LostFoundKafkaDto dto) {
		
		// Getting from Searcher
		List<PostEntity> result = searchByMatches(dto);
		if(result==null)
			return;

		// Sending res to Converter
		List<String> afterConvert = convertPostsToListLinks(result);

		// Sending converted to emailNotifyer
		String[] respondents = {dto.userLogin};
		String subj = "These posts may be relevant to your request";
		sendSimpleEmail(respondents, afterConvert, subj);
		
		// Sending emails to relevant persons
		String[] addresses = convertPostsToEmailAddresses(result);
		String relevantPost = "https://propetsproj.herokuapp.com/lostfound/en/v1/post/"+dto.id;
		List<String> post = new ArrayList<String>();
		post.add(relevantPost);
		subj = "This post may be relevant to your request";
		sendSimpleEmail(addresses, post, subj);
	}

	private List<PostEntity> searchByMatches(LostFoundKafkaDto dto) {
		PostEntity entity = dtoToEntity(dto);
		List<PostEntity> result = searcher.searchInLostOrFounds(entity);
		if(result.size()==0) {
			return null;
		}
		return result;
	}

	@Override
	public void searchByPosts(String id) {
		PostEntity entity = repo.findById(id).orElse(null);
		List<PostEntity> result = searcher.searchInLostOrFounds(entity);
		System.out.println(convertPostsToListLinks(result));
	}

	@Override
	@Transactional
	public void addPost(LostFoundKafkaDto dto) {
		PostEntity entity = dtoToEntity(dto);
		repo.save(entity);
		handlerSendingEmail(dto);

	}

	@Override
	@Transactional
	public void updatePost(LostFoundKafkaDto dto) {
		PostEntity newEntity = dtoToEntity(dto);
		PostEntity oldEntity = repo.findById(dto.id).orElse(null);
		if (oldEntity == null) {
			return;
		}
		repo.save(newEntity);
		if(isExistsImportantDifference(oldEntity, newEntity)) {
			handlerSendingEmail(dto);
		}
	}

	private boolean isExistsImportantDifference(PostEntity oldEntity, PostEntity newEntity) {
		//bool typePost, bool sex, str type, str[] tags
		if(Boolean.compare(oldEntity.isTypePost(), newEntity.isTypePost())!=0) {
			return true;
		}
		if(!oldEntity.getSex().equals(newEntity.getSex())) {
			return true;
		}
		if(!oldEntity.getBreed().equals(newEntity.getBreed())) {
			return true;
		}
		if(!Arrays.equals(oldEntity.getTags(), newEntity.getTags())) {
			return true;
		}
		return false;
	}

	@Override
	public void removePost(String id) {
		repo.deleteById(id);
	}

	@Override
	public PostEntity dtoToEntity(LostFoundKafkaDto dto) {
		PostEntity entity = new PostEntity(dto);
		return entity;
	}

	private List<String> convertPostsToListLinks(List<PostEntity> list) {
		return list.stream()
				.map(e -> "https://propetsproj.herokuapp.com/lostfound/en/v1/post/" + e.getId())
				.collect(Collectors.toList());
	}
	
	private String[] convertPostsToEmailAddresses(List<PostEntity> list) {
		return list.stream()
				.map(e -> e.getUserLogin().toString())
				.collect(Collectors.toList())
				.toArray(new String[list.size()]);	
	}
	
	private void sendSimpleEmail(String[] respondents, List<String> links, String subj) {
		if(links.size()>0) {
			email.sendSimpleMessage(respondents, subj, links.toString());
		}
	}

}
