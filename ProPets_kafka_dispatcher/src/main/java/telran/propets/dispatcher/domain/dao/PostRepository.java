package telran.propets.dispatcher.domain.dao;

import java.util.List;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import telran.propets.dispatcher.domain.entity.PostEntity;

public interface PostRepository extends ElasticsearchRepository<PostEntity, String>{
	
	List<PostEntity> findByTypePostAndTypeAndSexAndTags(boolean typePost, String type, String sex, String[] tags);
	
	List<PostEntity> findByType(String type);
	List<PostEntity> findBySex(String sex);
//	List<PostEntity> findByTags(String[] tags);
	@Query("{\"bool\": {\"must\": [{\"match\": {\"tags\": \"?0\"}}]}}")
	List<PostEntity> findByTagUsingDeclaredQuery(String tag);
	
	List<PostEntity> findByTypePost(boolean typePost);
	
	String[] findByTags(String[] tags);
	List<PostEntity> findByTypePostAndTags(boolean typePost, String[] tags);

}
