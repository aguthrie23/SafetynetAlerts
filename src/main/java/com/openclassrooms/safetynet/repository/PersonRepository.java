package com.openclassrooms.safetynet.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.openclassrooms.safetynet.domain.Person;

@Repository
public class PersonRepository {
	
	private List<Person> persons = new ArrayList<Person>();
	

	public void addPerson (Person person) {
		persons.add(person);
		
	}
	
	
	
	public void removePerson (String firstName, String lastName) {		
	Person person = findPersonByFirstLast(firstName, lastName);
	persons.remove(person);
	
	}
	
	public void updatePerson(Person person) {
		int index = findIndexByFirstLast(person.getFirstName(), person.getLastName());
		
		if (index > 0) {
			persons.set(index,person);
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
		
		return 0;
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
