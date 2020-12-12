package telran.propets.dispatcher.domain.dao;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import telran.propets.dispatcher.domain.entity.PostEntity;

public interface PostRepository extends ElasticsearchRepository<PostEntity, String> {

	List<PostEntity> findByTypePostAndTypeAndSex(boolean typePost, String type, String sex);

//	List<PostEntity> findByTypePostAndTypeAndSexAndTags(boolean typePost, String type, String sex, String[] tags);
//	@Query("{\"bool\": {\"must\": [{\"match\": {\"tags\": \"?0\"}}]}}")
//	List<PostEntity> findByTagUsingDeclaredQuery(String tag);

}
