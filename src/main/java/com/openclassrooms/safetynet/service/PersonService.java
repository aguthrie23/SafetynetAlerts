package com.openclassrooms.safetynet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.safetynet.domain.Person;
import com.openclassrooms.safetynet.repository.PersonRepository;

@Service
public class PersonService {
	
	
	PersonRepository personRepository;

	@Autowired
	public PersonService(PersonRepository personRepository) {
		super();
		this.personRepository = personRepository;
	}
	
	
	public List<Person> getPersons() {
		return personRepository.getPersons();
	}

	public Person getPersonByFirstLast(String firstName, String lastName) {
		return personRepository.findPersonByFirstLast(firstName, lastName);
	}
	

}