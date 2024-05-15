package com.openclassrooms.safetynet;

import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.openclassrooms.safetynet.domain.Person;
import com.openclassrooms.safetynet.repository.PersonRepository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class PersonRepositoryTest {
	
	public PersonRepository personRepository;
	public Person person1;
	public Person person2;
	
	
	@BeforeEach
	void init () {
		personRepository = new PersonRepository();	
		person1 = new Person("Abe","One","123-456-7890","23059","123 main st","Richmond","richmond@test.com");
		person2 = new Person("Bill","Two","123-456-7890","23059","456 main st","Richmond","richmond@test.com");
	}
	
	@Test
	public void testAddPerson() {
		
		personRepository.addPerson(person1);
		assertEquals(personRepository.getPersons().size(), 1);
		
	}
	
	@Test
	public void testRemovePerson() {
		personRepository.addPerson(person1);
		personRepository.addPerson(person2);
		personRepository.removePerson(person2.getFirstName(), person2.getLastName());
		assertEquals(personRepository.getPersons().size(), 1);
		
	}
	
	@Test
	public void testGetPersonByFirstLastName() {
		
		personRepository.addPerson(person1);
		personRepository.addPerson(person2);
		Person personRet  = personRepository.findPersonByFirstLast(person1.getFirstName(), person1.getLastName());
		
		assertEquals(person1, personRet);
		assertNotNull(personRet);		
	}
	
	@Test
	public void testGetPersons() {

		personRepository.addPerson(person1);
		personRepository.addPerson(person2);

		List<Person> personRetList = personRepository.getPersons();
		assertNotNull(personRetList);
		assertEquals(personRetList.size(), 2);
		
	}
	
	@Test 
	public void testGetPersonByAddress () {
		personRepository.addPerson(person1);
		personRepository.addPerson(person2);
		
		List<Person> personRetList = personRepository.getPersonByAddress("456 main st");
		assertNotNull(personRetList);
		assertEquals(personRetList.size(), 1);
		
	}

}