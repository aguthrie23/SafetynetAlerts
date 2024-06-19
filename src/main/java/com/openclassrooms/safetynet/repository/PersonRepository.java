package com.openclassrooms.safetynet.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.openclassrooms.safetynet.domain.Person;
import com.openclassrooms.safetynet.exception.DataNotFoundException;

@Repository
public class PersonRepository {
	
	private List<Person> persons = new ArrayList<Person>();
	

	public void addPerson (Person person) {
		persons.add(person);
		
	}
	
	
	
	public void removePerson (String firstName, String lastName) throws DataNotFoundException {		
	Person person = findPersonByFirstLast(firstName, lastName);
	
	if (person != null) {
	persons.remove(person);}
	else {
		throw new DataNotFoundException("Remove Person Not found with data first name " + firstName + " and last name " + lastName);
	}
	
	}
	
	public void updatePerson(Person person) throws DataNotFoundException {
		int index = findIndexByFirstLast(person.getFirstName(), person.getLastName());
		System.out.println("index " + index);
		if (index >= 0) {
			
			persons.set(index,person);
		}
		else {
			throw new DataNotFoundException("Update Person Not found with data first name " + person.getFirstName() + " and last name " + person.getLastName());	
		}
	}
	
	public Integer findIndexByFirstLast(String firstName, String lastName) {
		int count = 0;
		String concatString = firstName.concat(lastName);
		for (Person person : persons) {
			
			if (person.getFirstName().concat(person.getLastName())
					.equals(concatString))  {
				return count;
			}	
			
			count++;
		}
		
		return -1;
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
	


	public List<Person> getPersonByAddress(String address) {
		List <Person> personList = new ArrayList<Person>();
		
		for (Person person : persons) {
			if (person.getAddress().equals(address)) {
				personList.add(person);
			}
			
		}
		return personList;
	}
	
}
