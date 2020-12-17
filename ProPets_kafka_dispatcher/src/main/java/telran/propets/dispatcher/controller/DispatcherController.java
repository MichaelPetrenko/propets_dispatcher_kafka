package telran.propets.dispatcher.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DispatcherController {
	
	@GetMapping(value = "/wakeup")
	void wakeUp() {
		return;
	}
	
}