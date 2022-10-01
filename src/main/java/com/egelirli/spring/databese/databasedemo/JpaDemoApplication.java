package com.egelirli.spring.databese.databasedemo;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.egelirli.spring.databese.databasedemo.entity.Person;
import com.egelirli.spring.databese.databasedemo.jpa.PersonJpaRepository;

//@SpringBootApplication
public class JpaDemoApplication  implements CommandLineRunner{
	
	private Logger  logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PersonJpaRepository repo;
	
	public static void main(String[] args) {
		SpringApplication.run(JpaDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		logger.info("All Person List: {}", repo.findAllPerson() );
		logger.info("Person(10001): {}", repo.findPersonById(10001) );
//		logger.info("Deleting Person(10001) - Number Of Rows Deleted {}", personDao.deletePersonById(10001) );
//		
		logger.info("Inserting  Person -  {}", 
				                     repo.insert( new Person("Cangu", "Calcutta", new Date())) );
		logger.info("Updating  Person - {}", 
                               repo.update(new Person("Kangu", "Calcutta", new Date())) );
		
		repo.deleteById(10001);
	}

}
