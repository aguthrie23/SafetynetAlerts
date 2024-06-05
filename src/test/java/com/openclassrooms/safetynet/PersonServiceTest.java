package com.openclassrooms.safetynet;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockitoSession;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.openclassrooms.safetynet.domain.MedicalRecord;
import com.openclassrooms.safetynet.domain.Person;
import com.openclassrooms.safetynet.domain.PersonInfo;
import com.openclassrooms.safetynet.domain.PersonMedicalRecord;
import com.openclassrooms.safetynet.repository.MedicalRecordsRepository;
import com.openclassrooms.safetynet.repository.PersonRepository;
import com.openclassrooms.safetynet.service.MedicalRecordService;
import com.openclassrooms.safetynet.service.PersonService;

// @ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
	
	
//	@Mock
//	PersonRepository personRepository;
	
//	@InjectMocks 
//	PersonService personService;
	
	
	@Test
    public void testGetPersons() {
		PersonRepository personRepository = Mockito.mock(PersonRepository.class);
		
		PersonService personSvc = new PersonService(personRepository,null);

		Person person = new Person("firstname","lastname","123-456-7890","23059","123 main st","richmond","tester@test.com");
		
		List<Person> mockPersonList = new ArrayList<Person>();
		mockPersonList.add(person);

        when(personRepository.getPersons()).thenReturn(mockPersonList);

        List<Person> retList =  personSvc.getPersons();

        // Verify the result
        assertEquals(mockPersonList, retList);
        assertNotNull(retList);
    }
	
//	@Test
//	public void testGetPersonByFirstLast() {
//		
//		PersonRepository personRepository = Mockito.mock(PersonRepository.class);
//		
//		PersonService personSvc = new PersonService(personRepository);
//		
//		String fNameString = "Test";
//		String lNameString = "Tester";
//
//		Person person = new Person(fNameString,lNameString,"123-456-7890","23059","123 main st","richmond","tester@test.com");
//		
//		
//		when(personRepository.findPersonByFirstLast(fNameString, lNameString)).thenReturn(person);
//		
//		Person personReturn = personSvc.getPersonByFirstLast(fNameString, lNameString);
//		
//		assertEquals(person, personReturn);
//		assertNotNull(personReturn);
//				
//	}

	@Test
	public void testGetEmailByCity() {
		
		PersonRepository personRepository = Mockito.mock(PersonRepository.class);
		
		PersonService personSvc = new PersonService(personRepository,null);
		String cityString = "Boston";
		List<String> retEmailString = new ArrayList<>();
		retEmailString.add("boston@test.com");
		
		Person person1 = new Person("Abe","lastname","123-456-7890","23059","123 main st","Richmond","richmond@test.com");
		Person person2 = new Person("firstname","lastname","123-456-7890","23059","123 main st",cityString,"boston@test.com");
		List<Person> mockPersonList = new ArrayList<Person>();
		mockPersonList.add(person1);
		mockPersonList.add(person2);

        when(personRepository.getPersons()).thenReturn(mockPersonList);
List <String> emailsList = personSvc.getEmailByCity(cityString);	
assertEquals(retEmailString, emailsList);
assertNotNull(emailsList);
		
		
	}
	
	@Test
	public void testAddPerson() {
		
	}
	
	@Test
	public void testGetPersonsByAddress() {
		
		PersonRepository personRepository = Mockito.mock(PersonRepository.class);
		
		PersonService personSvc = new PersonService(personRepository,null);
		
		String fNameString = "Test";
		String lNameString = "Tester";
		String addressString = "789 Main St";

		Person person = new Person(fNameString,lNameString,"123-456-7890","23059",addressString,"richmond","tester@test.com");
		List <Person> personList = new ArrayList<Person>();		
		personList.add(person);
		
		when(personRepository.getPersonByAddress(addressString)).thenReturn(personList);
		
		List<Person> personReturn = personSvc.getPersonsByAddress(addressString);
		
		assertEquals(personList, personReturn);
		assertNotNull(personReturn);
		
	}
	
	@Test
	public void testGetPersonMedRecordByFirstLast () {
		PersonRepository personRepository = Mockito.mock(PersonRepository.class);
		MedicalRecordsRepository medicalRecordsRepository = Mockito.mock(MedicalRecordsRepository.class);
		
		MedicalRecordService medicalRecordSvc = new MedicalRecordService(medicalRecordsRepository);
		
		PersonService personSvc = new PersonService(personRepository,medicalRecordSvc);
		
		String fNameString = "Test";
		String lNameString = "Tester";
		String addressString = "789 Main St";
		Person person = new Person(fNameString,lNameString,"123-456-7890","23059",addressString,"richmond","tester@test.com");
		
		String med1 = "ibupurin:200mg";
		List<String> medicationsList = new ArrayList<String>();
		medicationsList.add(med1);
		
		String allergy1 = "peanut";		
		List<String> allergiesList = new ArrayList<String>();
		allergiesList.add(allergy1);
		
		MedicalRecord medicalRecord1 = new MedicalRecord(fNameString, lNameString, "08/21/1947", medicationsList, allergiesList);
		List<MedicalRecord> medicalRecordRetList = new ArrayList<MedicalRecord>();
		medicalRecordRetList.add(medicalRecord1);
		
		List<Person> personRetList = new ArrayList<Person>();
		personRetList.add(person);
		
		when(personRepository.getPersonsByFirstLast(fNameString, lNameString)).thenReturn(personRetList);
		when(medicalRecordSvc.getMedicalRecordsByFirstLast(fNameString, lNameString)).thenReturn(medicalRecordRetList);
		
		List <PersonInfo> personInfoRetList = personSvc.getPersonMedRecordByFirstLast(fNameString, lNameString);
		assertNotNull(personInfoRetList);
		assertEquals(personInfoRetList.get(0).getEmail(), person.getEmail());
		assertEquals(personInfoRetList.get(0).getMedicationsList(), medicationsList);
		

		
	}
	
	@Test 
	public void testGetPersonMedicalRecord() {
		
		PersonRepository personRepository = Mockito.mock(PersonRepository.class);
		MedicalRecordsRepository medicalRecordsRepository = Mockito.mock(MedicalRecordsRepository.class);
		
		MedicalRecordService medicalRecordSvc = new MedicalRecordService(medicalRecordsRepository);
		
		PersonService personSvc = new PersonService(personRepository,medicalRecordSvc);
		
		String fNameString = "Test";
		String lNameString = "Tester";
		String addressString = "789 Main St";
		Person person = new Person(fNameString,lNameString,"123-456-7890","23059",addressString,"richmond","tester@test.com");
		
		String med1 = "ibupurin:200mg";
		List<String> medicationsList = new ArrayList<String>();
		medicationsList.add(med1);
		
		String allergy1 = "peanut";		
		List<String> allergiesList = new ArrayList<String>();
		allergiesList.add(allergy1);
		
		MedicalRecord medicalRecord1 = new MedicalRecord(fNameString, lNameString, "08/21/1947", medicationsList, allergiesList);
		List<MedicalRecord> medicalRecordRetList = new ArrayList<MedicalRecord>();
		medicalRecordRetList.add(medicalRecord1);
		
		List<Person> personRetList = new ArrayList<Person>();
		personRetList.add(person);
		
		when(personRepository.getPersons()).thenReturn(personRetList);
		when(medicalRecordSvc.getMedicalRecords()).thenReturn(medicalRecordRetList);
		
		List<PersonMedicalRecord> retPersonMedicalRecordList = new ArrayList<PersonMedicalRecord>();
		retPersonMedicalRecordList = personSvc.getPersonMedicalRecord();
		
		assertNotNull(retPersonMedicalRecordList);
		assertEquals(retPersonMedicalRecordList.get(0).getPerson(), person);
		assertEquals(retPersonMedicalRecordList.get(0).getMedicalRecord(), medicalRecord1);
		
	//	for (PersonMedicalRecord personMedicalRecord : retPersonMedicalRecordList) {
	//		System.out.println(personMedicalRecord.getMedicalRecord().getFirstName());
	//		System.out.println(personMedicalRecord.getPerson().getLastName());
	//		System.out.println(personMedicalRecord.getMedicalRecord().getAllergiesList());
			
	//	}
		
		
	}
	
	
}
