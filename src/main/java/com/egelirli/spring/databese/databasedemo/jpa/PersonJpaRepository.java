package com.egelirli.spring.databese.databasedemo.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.egelirli.spring.databese.databasedemo.entity.Person;

@Repository
@Transactional
public class PersonJpaRepository {

    @PersistenceContext
    EntityManager entityManager;
	
    public List<Person> findAllPerson(){
    	 TypedQuery<Person>  query = 
    			    entityManager.createNamedQuery("find_all_person", Person.class);
    	 return query.getResultList();
    }
	
	public Person  findPersonById(int id){
    	return  entityManager.find(Person.class, id);
    }

	public Person update(Person person){
    	return  entityManager.merge(person);
	
    }

	public Person insert(Person person){
    	return  entityManager.merge(person);
    }
	
	public void deleteById(int id) {
		Person pers = findPersonById(id);
		entityManager.remove(pers);
	
		
	}
}
