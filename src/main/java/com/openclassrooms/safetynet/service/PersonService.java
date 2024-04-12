package com.openclassrooms.safetynet.service;

import java.util.ArrayList;
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
	
	public List<String> getEmailByCity(String city) {
		List<Person> personList = personRepository.getPersons();
		List<String> emailList = new ArrayList<>();
		for (Person person : personList) {
			if (person.getCity().equals(city)) {
				emailList.add(person.getEmail());
			}
		}
		
		return emailList;
	}
	

}
