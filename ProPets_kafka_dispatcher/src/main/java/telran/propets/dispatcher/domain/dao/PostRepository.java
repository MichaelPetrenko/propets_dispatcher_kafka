package telran.propets.dispatcher.domain.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import telran.propets.dispatcher.domain.entity.PostEntity;

public interface PostRepository extends ElasticsearchRepository<PostEntity, String>{

}
