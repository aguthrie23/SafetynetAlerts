package com.openclassrooms.safetynet;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import com.openclassrooms.safetynet.domain.Person;
import com.openclassrooms.safetynet.exception.DataNotFoundException;
import com.openclassrooms.safetynet.repository.PersonRepository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;



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
		
		List <Person> retPersons = new ArrayList<Person>();
		
		personRepository.addPerson(person1);
		retPersons = personRepository.getPersons();
		assertEquals(personRepository.getPersons().size(), 1);
		assertEquals(retPersons.get(0).getFirstName(), "Abe");

		
	}
	
	@Test
	public void testRemovePerson() {
		personRepository.addPerson(person1);
		personRepository.addPerson(person2);
		try {
			personRepository.removePerson(person2.getFirstName(), person2.getLastName());
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(personRepository.getPersons().size(), 1);
		
	}
	
	@Test
	public void testRemovePersonException() {
	
			Throwable exception = assertThrows(Exception.class, () -> personRepository.removePerson(person2.getFirstName(), person2.getLastName()));
			assertNotNull(exception);
		
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
	
	
	@Test 
	public void testUpdatePerson () {
		try {
			Person person = new Person("New","Guy","123-456-7890","23059","123 main st","Richmond","richmond@test.com");
			personRepository.addPerson(person);
			personRepository.addPerson(person1);
			person.setEmail("newEmail@test.com");
			personRepository.updatePerson(person);
			assertEquals("newEmail@test.com", personRepository.getPersons().get(0).getEmail());
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	
	@Test 
	public void testUpdatePersonException () {
	//	try {
			Person person = new Person("New","Guy","123-456-7890","23059","123 main st","Richmond","richmond@test.com");
			personRepository.addPerson(person);
		//	personRepository.addPerson(person1);
			person.setEmail("newEmail@test.com");
		//	personRepository.updatePerson(person1);
			
		Throwable exception = assertThrows(Exception.class, () -> personRepository.updatePerson(person1));
		assertNotNull(exception);
			
		
	}	
	
}
