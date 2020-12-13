package telran.propets.dispatcher;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProPetsKafkaDispatcherApplication {
	
	private static Logger logger = LogManager.getLogger("logspropets");

	public static void main(String[] args) {
		SpringApplication.run(ProPetsKafkaDispatcherApplication.class, args);
		
        logger.debug("Debug log message");
        logger.info("Info log message");
        logger.error("Error log message");
        
	}

}
