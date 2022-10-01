package com.egelirli.spring.databese.databasedemo.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.egelirli.spring.databese.databasedemo.entity.Person;

@Repository
public class PersonJdbcDao {
  
		@Autowired
	    private JdbcTemplate jdbcTemplate; 
	    
		
		class PersonRowMapper implements RowMapper<Person> {

			@Override
			public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			Person person = new Person();
			person.setId(rs.getInt("Id")); 
			person.setName(rs.getString("Name"));
			person.setLocation(rs.getString("Location"));
			person.setBirthDate(rs.getTimestamp("birth_date"));
			 
			return person;  
			}

//			@Override
//			public Person extractData(ResultSet rs) throws SQLException, DataAccessException {
//				Person person = new Person();
//				person.setId(rs.getInt("Id")); 
//				person.setName(rs.getString("Name"));
//				person.setLocation(rs.getString("Location"));
//				person.setBirthDate(rs.getTimestamp("birth_date"));
//				 
//				return person;  
//			}
			
		}
		
		
	    public List<Person>  findAll(){
	    	
//	    	return jdbcTemplate.query("Select * from Person", 
//	    							new BeanPropertyRowMapper<Person>(Person.class));
	    	return jdbcTemplate.query("Select * from Person", 
	    							    new PersonRowMapper());
	    	
	    }

	    public Person  findPersonById(int id){
	    	
	    	return jdbcTemplate.queryForObject("Select * from Person Where id=?",
	    							new Object[] { id },
	    							new BeanPropertyRowMapper<Person>(Person.class));
	    	
	    }
	    

	    public int  deletePersonById(int id){
	    	
	    	return jdbcTemplate.update("delete from Person Where id=?",
	    												new Object[] { id });
	    	
	    }

	    
	    public int  insertPerson(Person person){
	    	return jdbcTemplate.update(
   						"insert into person(id, name, location, birth_date) "
			                      + "values(?, ? ,? ,?);",
    									new Object[] { 
    											person.getId(), 
    											person.getName(), 
     										    person.getLocation(), 
     										    new Timestamp(person.getBirthDate().getTime()) });
	    } 

	    public int  updatePerson(Person person){
	    	return jdbcTemplate.update(
   						"update person "
			                      + " set name = ?, location = ?, birth_date = ? "
			                      + " where id = ? ",
    									new Object[] {  
    											person.getName(), 
    											person.getLocation(), 
    											new Timestamp(person.getBirthDate().getTime()),
    											person.getId() 
    										});
	    } 

}