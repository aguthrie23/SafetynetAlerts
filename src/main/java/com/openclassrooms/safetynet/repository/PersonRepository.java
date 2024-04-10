package com.openclassrooms.safetynet.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.openclassrooms.safetynet.domain.Person;

@Repository
public class PersonRepository {
	
	private List<Person> persons = new ArrayList<>();
	
	public void addPerson (Person person) {
		persons.add(person);
		
	}
	
	
	public void removePerson (String firstName, String lastName) {
		
	Person person = findPersonByFirstLast(firstName, lastName);
	persons.remove(person);
	
	}
	
	
	public Person findPersonByFirstLast(String firstName, String lastName) {
		String concatString = firstName.concat(lastName);
		for (Person person : persons) {
			if (person.getFirstName().concat(person.getLastName())
					.equals(concatString))  {
				return person;
			}			
		}
		return null;
	}


	public List<Person> getPersons() {
		return persons;
	}
	
	
	
	

}
