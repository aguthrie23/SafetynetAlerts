package com.openclassrooms.safetynet;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.openclassrooms.safetynet.domain.Person;
import com.openclassrooms.safetynet.repository.PersonRepository;
import com.openclassrooms.safetynet.service.PersonService;

// @ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
	
	
	@Mock
	PersonRepository personRepository;
	
	@InjectMocks 
	PersonService personService;
	
// @BeforeEach

	
	
	@Test
	void testGetPersons() {
		
		Person person = new Person("firstname","lastname","123-456-7890","23059","123 main st","richmond","VA");
		
		List<Person> mockPersonList = new ArrayList<Person>();
		mockPersonList.add(person);
		
		when(personRepository.getPersons()).thenReturn(mockPersonList);
		List<Person> retList =  personService.getPersons();
		assertNotNull(retList);
		assertEquals(mockPersonList,retList);
		
	}
	

}
