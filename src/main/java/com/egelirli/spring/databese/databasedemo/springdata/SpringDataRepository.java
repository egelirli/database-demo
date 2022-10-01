package com.egelirli.spring.databese.databasedemo.springdata;

import org.springframework.data.jpa.repository.JpaRepository;

import com.egelirli.spring.databese.databasedemo.entity.Person;

public interface SpringDataRepository extends JpaRepository<Person, Integer> {

}
