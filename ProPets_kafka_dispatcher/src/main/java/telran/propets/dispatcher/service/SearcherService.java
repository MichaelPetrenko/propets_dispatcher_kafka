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
	
//	public Entity[]
	public void searchInLosts(PostEntity entity) {
		System.out.println("========== Searcher service - losts ===============");
		//hardcode
		String[] arr = new String[5];
		arr[0] = "tag1";
		arr[1] = "tag2";
		arr[2] = "tag3";
		arr[3] = "color1";
		arr[4] = "color2";
		
//		"tag1", "tag2", "tag3", "color1", "color2"
//		List<PostEntity> list = repo.findByTypePostAndTypeAndSexAndTags(false, "dog", "male", arr);
		List<PostEntity> list = repo.findByTypePost(false);
		System.out.println("=========================== TYPEPOST"+list.size());
		list = repo.findByType("dog");
		System.out.println("=========================== TYPE"+list.size());
		list = repo.findBySex("male");
		System.out.println("=========================== SEX"+list.size());
		list = repo.findByTags(arr);
		System.out.println("=========================== TAGS"+list.size());
		repo.findByTagUsingDeclaredQuery("color1");
		System.out.println("=========================== ONE TAG QUERY "+list.size());
		
		return;
	}
	
	public void searchInFounds(PostEntity entity) {
		System.out.println("========== Searcher service - founds ===============");
		return;
	}

}
