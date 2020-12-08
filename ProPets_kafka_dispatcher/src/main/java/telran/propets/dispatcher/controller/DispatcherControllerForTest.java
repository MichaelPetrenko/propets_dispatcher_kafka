package telran.propets.dispatcher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import telran.propets.dispatcher.domain.dao.PostRepository;
import telran.propets.dispatcher.service.interfaces.IDispatcherServiceBonsai;

@RestController
public class DispatcherControllerForTest {

	@Autowired
	IDispatcherServiceBonsai dispatcherBonsai;
	
	@Autowired
	PostRepository repo;
	
	@GetMapping(value = "/search/fortest/{id}")
	void  searchByPosts(@PathVariable("id") String id) {
		System.out.println(" = = = = CONTROLLER STARTED = = = = ");
		dispatcherBonsai.searchByPosts(id);
	}
	
}
