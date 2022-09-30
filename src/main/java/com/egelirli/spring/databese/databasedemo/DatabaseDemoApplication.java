package com.egelirli.spring.databese.databasedemo;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.egelirli.spring.databese.databasedemo.entity.Person;
import com.egelirli.spring.databese.databasedemo.jdbc.PersonJdbcDao;

@SpringBootApplication
public class DatabaseDemoApplication  implements CommandLineRunner{
	
	private Logger  logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PersonJdbcDao personDao;
	
	public static void main(String[] args) {
		SpringApplication.run(DatabaseDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("All Person List: {}", personDao.findAll() );
		logger.info("Person(10001): {}", personDao.findPersonById(10001) );
		//logger.info("Deleting Person(10001) - Number Of Rows Deleted {}", personDao.deletePersonById(10001) );
		
		logger.info("Inserting  Person(10004) - Number Of Rows Affected {}", 
				                     personDao.insertPerson(
				                    		      new Person(10004, "Cangu", "Calcutta", new Date())) );
		logger.info("Updating  Person(10003) - Number Of Rows Affected {}", 
                personDao.updatePerson(
               		      new Person(10003, "Kangu", "Calcutta", new Date())) );
		
	}

}
