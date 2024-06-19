package com.openclassrooms.safetynet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import com.openclassrooms.safetynet.controller.SafetyNetController;
import com.openclassrooms.safetynet.domain.ChildAndPerson;
import com.openclassrooms.safetynet.domain.FireStations;
import com.openclassrooms.safetynet.domain.HouseHoldInfo;
import com.openclassrooms.safetynet.domain.MedicalRecord;
import com.openclassrooms.safetynet.domain.Person;
import com.openclassrooms.safetynet.domain.PersonInfo;
import com.openclassrooms.safetynet.domain.PersonMedicalRecord;
import com.openclassrooms.safetynet.domain.PersonServiced;
import com.openclassrooms.safetynet.repository.PersonRepository;
import com.openclassrooms.safetynet.service.FireStationService;
import com.openclassrooms.safetynet.service.MedicalRecordService;
import com.openclassrooms.safetynet.service.PersonService;

public class SafetyNetControllerTest {
	
	
	@Test
	public void testGetServicedByFirestation() {

		
		PersonService personService = Mockito.mock(PersonService.class);
		FireStationService fireStationService = Mockito.mock(FireStationService.class);
		MedicalRecordService medicalRecordService = Mockito.mock(MedicalRecordService.class);
		
		SafetyNetController safetyNetController = new SafetyNetController(personService, fireStationService, medicalRecordService);

		List<PersonServiced> retSvcPersonAndMedRecList = new ArrayList<PersonServiced>();
		
		Map<String, List<PersonServiced>>  summaryMap= new HashMap<String,List <PersonServiced>>();
		int countAdults = 1;
		int countChildren = 0;
		
		
		String fireHouseNumber = "1";
		String address = "123 Main St"; 
		String fNameString = "Test";
		

		String med1 = "ibupurin:200mg";
		String med2 = "advil:10mg";
		List<String> medicationsList = new ArrayList<String>();
		medicationsList.add(med1);
		medicationsList.add(med2);
		
		String allergy1 = "peanut";
		String allergy2 = "shellfish";
		
		List<String> allergiesList = new ArrayList<String>();
		allergiesList.add(allergy1);
		allergiesList.add(allergy2);
		MedicalRecord medicalRecord = new MedicalRecord(fNameString, "Tester", "08/21/1947", medicationsList, allergiesList);		
		
		Person person = new Person(fNameString,"Tester","123-456-7890","23059",address,"richmond","tester@test.com");
		
        PersonMedicalRecord personMedicalRecord = new PersonMedicalRecord(person, medicalRecord);
        PersonServiced personServiced = new PersonServiced(personMedicalRecord);
        retSvcPersonAndMedRecList.add(personServiced);	
        
		
        String testString = "count Adults:" + countAdults + " count Children " + countChildren;
        
        summaryMap.put(testString, retSvcPersonAndMedRecList);
		
		when(fireStationService.getServicedByFirestation(fireHouseNumber)).thenReturn(summaryMap);
		
		summaryMap = safetyNetController.getServicedByFirestation(fireHouseNumber);
		assertNotNull(summaryMap);
		assertTrue(summaryMap.containsKey("count Adults:1 count Children 0") && summaryMap.get("count Adults:1 count Children 0") != null);
						
	}
	
	@Test
	public void testGetChildandPersons() {
		
		PersonService personService = Mockito.mock(PersonService.class);
		FireStationService fireStationService = Mockito.mock(FireStationService.class);
		MedicalRecordService medicalRecordService = Mockito.mock(MedicalRecordService.class);
		
		SafetyNetController safetyNetController = new SafetyNetController(personService, fireStationService, medicalRecordService);

		String fNameString = "Test";
		String lNameString = "Tester";
		String address = "789 Main St";
		String med1 = "ibupurin:200mg";
		String med2 = "advil:10mg";
		List<String> medicationsList = new ArrayList<String>();
		medicationsList.add(med1);
		medicationsList.add(med2);
		
		String allergy1 = "peanut";
		String allergy2 = "shellfish";
		
		List<String> allergiesList = new ArrayList<String>();
		allergiesList.add(allergy1);
		allergiesList.add(allergy2);
		MedicalRecord medicalRecord = new MedicalRecord(fNameString, lNameString, "08/21/1947", medicationsList, allergiesList);		
		
		Person person = new Person(fNameString,lNameString,"123-456-7890","23059",address,"richmond","tester@test.com");
		
        PersonMedicalRecord personMedicalRecord = new PersonMedicalRecord(person, medicalRecord);
	    
	    HouseHoldInfo houseHoldInfo = new HouseHoldInfo(personMedicalRecord);
	    ChildAndPerson childAndPerson = new ChildAndPerson(houseHoldInfo);
	    
	    List <String> otherPersonsString = new ArrayList<String>();
	    otherPersonsString.add("Adult One");
	    otherPersonsString.add("Adult Two");
	    childAndPerson.setOtherPersons(otherPersonsString);
	    
	    List <ChildAndPerson> childPersonList = new ArrayList<ChildAndPerson>();
	    childPersonList.add(childAndPerson);
	    

		
		when(personService.getChildandPersons(address)).thenReturn(childPersonList);

		

		List <ChildAndPerson> childPersonListRet = new ArrayList<ChildAndPerson>();
				
		childPersonListRet = safetyNetController.getChildandPersons(address);
		assertNotNull(childPersonListRet);
		assertEquals(childPersonListRet.size(), 1);
		assertEquals(childPersonListRet.get(0).getFirstName(), fNameString);		
		
	}
	
	@Test
	public void testGetPhoneNumbersPhoneAlert() {
		
		PersonService personService = Mockito.mock(PersonService.class);
		FireStationService fireStationService = Mockito.mock(FireStationService.class);
		MedicalRecordService medicalRecordService = Mockito.mock(MedicalRecordService.class);
		
		SafetyNetController safetyNetController = new SafetyNetController(personService, fireStationService, medicalRecordService);
		
		String fireHouseNumber = "3";
		String phone1 = "123-456-7890";
		String phone2 = "111-222-3456";

		List <String> phoneNumList = new ArrayList<String>();
		phoneNumList.add(phone1);
		phoneNumList.add(phone2);
		
		when(fireStationService.getPhoneNumbersPhoneAlert(fireHouseNumber)).thenReturn(phoneNumList);
		
		List<String> retListPhoneNumbersList  = safetyNetController.getPhoneNumbersPhoneAlert(fireHouseNumber);
		assertNotNull(retListPhoneNumbersList);
		assertEquals(retListPhoneNumbersList.get(0), phone1);		
	}
	
	@Test
	public void testGetFireStationNumberAndHousehold() {
		
		PersonService personService = Mockito.mock(PersonService.class);
		FireStationService fireStationService = Mockito.mock(FireStationService.class);
		MedicalRecordService medicalRecordService = Mockito.mock(MedicalRecordService.class);
		
		SafetyNetController safetyNetController = new SafetyNetController(personService, fireStationService, medicalRecordService);
		
		HashMap<String,List <HouseHoldInfo>> personAddressMap= new HashMap<String,List <HouseHoldInfo>>();
		List<HouseHoldInfo> retSvcPersonAndMedRecList = new ArrayList<HouseHoldInfo>();

				
		
		String fireHouseNumber = "1";
		String address = "123 Main St"; 
		String fNameString = "Test";
		
		
		String med1 = "ibupurin:200mg";
		String med2 = "advil:10mg";
		List<String> medicationsList = new ArrayList<String>();
		medicationsList.add(med1);
		medicationsList.add(med2);
		
		String allergy1 = "peanut";
		String allergy2 = "shellfish";
		
		List<String> allergiesList = new ArrayList<String>();
		allergiesList.add(allergy1);
		allergiesList.add(allergy2);
		MedicalRecord medicalRecord = new MedicalRecord(fNameString, "Tester", "08/21/1947", medicationsList, allergiesList);		
		
		Person person = new Person(fNameString,"Tester","123-456-7890","23059",address,"richmond","tester@test.com");
		
        PersonMedicalRecord personMedicalRecord = new PersonMedicalRecord(person, medicalRecord);
        HouseHoldInfo houseHoldInfo = new HouseHoldInfo(personMedicalRecord);
        retSvcPersonAndMedRecList.add(houseHoldInfo);
		
        personAddressMap.put(fireHouseNumber, retSvcPersonAndMedRecList);
		
		when(fireStationService.getFireNumberandHouseHoldByAddress(address)).thenReturn(personAddressMap);
		
		
		HashMap<String,List <HouseHoldInfo>> personAddressMapRet= new HashMap<String,List <HouseHoldInfo>>();
		personAddressMapRet = safetyNetController.getFireStationNumberAndHousehold(address);


		assertNotNull(personAddressMapRet);
		assertTrue(personAddressMapRet.containsKey(fireHouseNumber) && personAddressMapRet.get(fireHouseNumber) != null);
		assertEquals(personAddressMapRet.get(fireHouseNumber).get(0).getFirstName(), fNameString);		
		
	}
	
	@Test 
	public void testGetHouseHoldsByFireHouse() {
	
		PersonService personService = Mockito.mock(PersonService.class);
		FireStationService fireStationService = Mockito.mock(FireStationService.class);
		MedicalRecordService medicalRecordService = Mockito.mock(MedicalRecordService.class);
		
		SafetyNetController safetyNetController = new SafetyNetController(personService, fireStationService, medicalRecordService);
		
		List <String> stationValsList = new ArrayList<String>();
		stationValsList.add("1");

		
		HashMap<String,List <HouseHoldInfo>> personAddressMap= new HashMap<String,List <HouseHoldInfo>>();
		List<HouseHoldInfo> retSvcPersonAndMedRecList = new ArrayList<HouseHoldInfo>();
		

		String fireHouseNumber = "1";
		String address = "123 Main St"; 
		String fNameString = "Test";
		
		List <String> fireHouseNumbersList = new ArrayList<String>();
		fireHouseNumbersList.add(fireHouseNumber);		
		
		
		String med1 = "ibupurin:200mg";
		String med2 = "advil:10mg";
		List<String> medicationsList = new ArrayList<String>();
		medicationsList.add(med1);
		medicationsList.add(med2);
		
		String allergy1 = "peanut";
		String allergy2 = "shellfish";
		
		List<String> allergiesList = new ArrayList<String>();
		allergiesList.add(allergy1);
		allergiesList.add(allergy2);
		MedicalRecord medicalRecord = new MedicalRecord(fNameString, "Tester", "08/21/1947", medicationsList, allergiesList);		
		
		Person person = new Person(fNameString,"Tester","123-456-7890","23059",address,"richmond","tester@test.com");
		
        PersonMedicalRecord personMedicalRecord = new PersonMedicalRecord(person, medicalRecord);
        HouseHoldInfo houseHoldInfo = new HouseHoldInfo(personMedicalRecord);
        retSvcPersonAndMedRecList.add(houseHoldInfo);

        
        personAddressMap.put(address, retSvcPersonAndMedRecList);
        
        when(fireStationService.getHouseholdsByFireStation(stationValsList)).thenReturn(personAddressMap);
        
        
        
        HashMap<String,List <HouseHoldInfo>> personAddressMapRet= new HashMap<String,List <HouseHoldInfo>>();
        personAddressMapRet = safetyNetController.getHouseHoldsByFireHouse(fireHouseNumbersList);
        
		assertNotNull(personAddressMapRet);
		assertTrue(personAddressMapRet.containsKey(address) && personAddressMapRet.get(address) != null);
		assertEquals(personAddressMapRet.get(address).get(0).getFirstName(), fNameString);        		
		
	
	}
	
	@Test
	public void testCollectPersonMedRecordByFirstLast() {
		
		PersonService personService = Mockito.mock(PersonService.class);
		FireStationService fireStationService = Mockito.mock(FireStationService.class);
		MedicalRecordService medicalRecordService = Mockito.mock(MedicalRecordService.class);
		
		SafetyNetController safetyNetController = new SafetyNetController(personService, fireStationService, medicalRecordService);
		
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

		
		List<Person> personRetList = new ArrayList<Person>();
		personRetList.add(person);
		PersonMedicalRecord personMedicalRecord = new PersonMedicalRecord(person, medicalRecord1);
		
		PersonInfo personInfo = new PersonInfo(personMedicalRecord);
		List <PersonInfo> personInfoList = new ArrayList<PersonInfo>();
		personInfoList.add(personInfo);
		
		when(personService.getPersonMedRecordByFirstLast(fNameString,lNameString)).thenReturn(personInfoList);

		
		List <PersonInfo> personInfoRetList = safetyNetController.collectPersonMedRecordByFirstLast(fNameString, lNameString);
		assertNotNull(personInfoRetList);
		assertEquals(personInfoRetList.get(0).getEmail(), person.getEmail());
		assertEquals(personInfoRetList.get(0).getMedicationsList(), medicationsList);		
		
	}
	
	@Test
	public void testAddPerson() {
		
		PersonService personService = Mockito.mock(PersonService.class);
		FireStationService fireStationService = Mockito.mock(FireStationService.class);
		MedicalRecordService medicalRecordService = Mockito.mock(MedicalRecordService.class);
		
		SafetyNetController safetyNetController = new SafetyNetController(personService, fireStationService, medicalRecordService);		
		
		Person person = new Person("firstname","lastname","123-456-7890","23059","123 main st","richmond","tester@test.com");
		
		safetyNetController.addPerson(person);
		verify(personService,times(1)).addPerson(person);				
		
	}
	
	@Test
	public void testRemovePerson() {
		PersonService personService = Mockito.mock(PersonService.class);
		FireStationService fireStationService = Mockito.mock(FireStationService.class);
		MedicalRecordService medicalRecordService = Mockito.mock(MedicalRecordService.class);
		
		SafetyNetController safetyNetController = new SafetyNetController(personService, fireStationService, medicalRecordService);	
		
		String fName = "first";
		String lName = "last";
		
	//	Person person = new Person(fName,lName,"123-456-7890","23059","123 main st","richmond","tester@test.com");
		

		safetyNetController.removePerson(fName, lName);
		verify(personService,times(1)).removePerson(fName, lName);		
	}
	
	
	@Test
	public void testUpdatePerson() {
		
		PersonService personService = Mockito.mock(PersonService.class);
		FireStationService fireStationService = Mockito.mock(FireStationService.class);
		MedicalRecordService medicalRecordService = Mockito.mock(MedicalRecordService.class);
		
		SafetyNetController safetyNetController = new SafetyNetController(personService, fireStationService, medicalRecordService);	
		
		Person person = new Person("firstname","lastname","123-456-7890","23059","123 main st","richmond","tester@test.com");
		
		safetyNetController.updatePerson(person);
		verify(personService,times(1)).updatePerson(person);		
	}
	
	@Test 
	public void testAddMedicalRecord() {
		
		PersonService personService = Mockito.mock(PersonService.class);
		FireStationService fireStationService = Mockito.mock(FireStationService.class);
		MedicalRecordService medicalRecordService = Mockito.mock(MedicalRecordService.class);
		
		SafetyNetController safetyNetController = new SafetyNetController(personService, fireStationService, medicalRecordService);	
		
		String med1 = "ibupurin:200mg";
		String med2 = "advil:10mg";
		List<String> medicationsList = new ArrayList<String>();
		medicationsList.add(med1);
		medicationsList.add(med2);
		
		String allergy1 = "peanut";
		String allergy2 = "shellfish";
		
		List<String> allergiesList = new ArrayList<String>();
		allergiesList.add(allergy1);
		allergiesList.add(allergy2);		
		MedicalRecord medicalRecord1 = new MedicalRecord("Med", "Tester", "08/21/1947", medicationsList, allergiesList);		
		
		safetyNetController.addMedicalRecord(medicalRecord1);
		verify(medicalRecordService,times(1)).addMedicalRecord(medicalRecord1);			
		
	}
	
	@Test
	public void testRemoveMedicalRecord() {
		
		PersonService personService = Mockito.mock(PersonService.class);
		FireStationService fireStationService = Mockito.mock(FireStationService.class);
		MedicalRecordService medicalRecordService = Mockito.mock(MedicalRecordService.class);
		
		SafetyNetController safetyNetController = new SafetyNetController(personService, fireStationService, medicalRecordService);			
		String fName = "first";
		String lName = "last";
		
		String med1 = "ibupurin:200mg";
		String med2 = "advil:10mg";
		List<String> medicationsList = new ArrayList<String>();
		medicationsList.add(med1);
		medicationsList.add(med2);
		
		String allergy1 = "peanut";
		String allergy2 = "shellfish";
		
		List<String> allergiesList = new ArrayList<String>();
		allergiesList.add(allergy1);
		allergiesList.add(allergy2);					

		safetyNetController.removeMedicalRecord(fName, lName);
		verify(medicalRecordService,times(1)).removeMedicalRecord(fName, lName);
	}
	
	@Test
	public void testUpdateMedicalRecord() {
		
		PersonService personService = Mockito.mock(PersonService.class);
		FireStationService fireStationService = Mockito.mock(FireStationService.class);
		MedicalRecordService medicalRecordService = Mockito.mock(MedicalRecordService.class);
		
		SafetyNetController safetyNetController = new SafetyNetController(personService, fireStationService, medicalRecordService);	
		
		String fName = "first";
		String lName = "last";
		
		String med1 = "ibupurin:200mg";
		String med2 = "advil:10mg";
		List<String> medicationsList = new ArrayList<String>();
		medicationsList.add(med1);
		medicationsList.add(med2);
		
		String allergy1 = "peanut";
		String allergy2 = "shellfish";
		
		List<String> allergiesList = new ArrayList<String>();
		allergiesList.add(allergy1);
		allergiesList.add(allergy2);		
		MedicalRecord medicalRecord1 = new MedicalRecord(fName, lName, "08/21/1947", medicationsList, allergiesList);			
		

		safetyNetController.updateMedicalRecord(medicalRecord1);
		verify(medicalRecordService,times(1)).updateMedicalRecord(medicalRecord1);		
	}
	
	@Test
	public void testAddFireStation() {
		
		PersonService personService = Mockito.mock(PersonService.class);
		FireStationService fireStationService = Mockito.mock(FireStationService.class);
		MedicalRecordService medicalRecordService = Mockito.mock(MedicalRecordService.class);
		
		SafetyNetController safetyNetController = new SafetyNetController(personService, fireStationService, medicalRecordService);
		
		String stationNum = "1";
		String address = "123 Main St";

		safetyNetController.addFireStation(stationNum,address);
		verify(fireStationService,times(1)).addFireStation(stationNum, address);	
	
	}
	
	@Test
	public void testUpdateFireStation() {
		
		PersonService personService = Mockito.mock(PersonService.class);
		FireStationService fireStationService = Mockito.mock(FireStationService.class);
		MedicalRecordService medicalRecordService = Mockito.mock(MedicalRecordService.class);
		
		SafetyNetController safetyNetController = new SafetyNetController(personService, fireStationService, medicalRecordService);
		
		String stationNum = "1";
		String address = "123 Main St";

		safetyNetController.updateFireStation(stationNum,address);
		verify(fireStationService,times(1)).updateFirestation(stationNum, address);			
		
	}
	
	@Test
	public void testRemoveFireStation() {
		
		try {
			PersonService personService = Mockito.mock(PersonService.class);
			FireStationService fireStationService = Mockito.mock(FireStationService.class);
			MedicalRecordService medicalRecordService = Mockito.mock(MedicalRecordService.class);
			
			SafetyNetController safetyNetController = new SafetyNetController(personService, fireStationService, medicalRecordService);		

			String address = "123 Main St";

			safetyNetController.removeFireStationAddress(address);
			verify(fireStationService,times(1)).removeFireStation(address);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		
	}
		
	
	
	@Test
	public void testGetAllPersons() {
	
		
		PersonService personSvc = Mockito.mock(PersonService.class);
		FireStationService fireStationSvc = Mockito.mock(FireStationService.class);
		MedicalRecordService medicalRecordSvc = Mockito.mock(MedicalRecordService.class);
		
		SafetyNetController safetyNetController = new SafetyNetController(personSvc, fireStationSvc, medicalRecordSvc);

		Person person = new Person("firstname","lastname","123-456-7890","23059","123 main st","richmond","tester@test.com");
		
		List<Person> mockPersonList = new ArrayList<Person>();
		mockPersonList.add(person);

        when(personSvc.getPersons()).thenReturn(mockPersonList);

        List<Person> retList =  safetyNetController.getAllPersons();

        // Verify the result
        assertNotNull(retList);
        assertEquals(mockPersonList, retList);
        	
	}
	
	@Test
	public void testGetEmailByCity() {
		
				
		PersonService personSvc = Mockito.mock(PersonService.class);
		FireStationService fireStationSvc = Mockito.mock(FireStationService.class);
		MedicalRecordService medicalRecordSvc = Mockito.mock(MedicalRecordService.class);
		
		SafetyNetController safetyNetController = new SafetyNetController(personSvc, fireStationSvc, medicalRecordSvc);
		
		String cityString = "Boston";
		List<String> retEmailString = new ArrayList<>();
		retEmailString.add("boston@test.com");
		
		Person person1 = new Person("Abe","lastname","123-456-7890","23059","123 main st","Richmond","richmond@test.com");
		Person person2 = new Person("firstname","lastname","123-456-7890","23059","123 main st",cityString,"boston@test.com");
		List<Person> mockPersonList = new ArrayList<Person>();
		mockPersonList.add(person1);
		mockPersonList.add(person2);

 when(personSvc.getEmailByCity(cityString)).thenReturn(retEmailString);
List <String> emailsList = safetyNetController.getEmailByCity(cityString);	
assertEquals(retEmailString, emailsList);
assertNotNull(emailsList);
		
		
	}
	
	@Test
	public void testGetAllFireStations() {
		
				
		PersonService personSvc = Mockito.mock(PersonService.class);
		FireStationService fireStationSvc = Mockito.mock(FireStationService.class);
		MedicalRecordService medicalRecordSvc = Mockito.mock(MedicalRecordService.class);
		
		SafetyNetController safetyNetController = new SafetyNetController(personSvc, fireStationSvc, medicalRecordSvc);		
		FireStations fireStations = new FireStations("1");
		fireStations.addAddress("123 Main St");
		
		List <FireStations> listFireStations = new ArrayList<FireStations>();
		listFireStations.add(fireStations);
		
		when(fireStationSvc.getFireStations()).thenReturn(listFireStations);
	
		List<FireStations> retFireStationsList = safetyNetController.getAllFireStations();
		   assertNotNull(retFireStationsList);
		   assertEquals(retFireStationsList, listFireStations);
	}
	
	@Test
	public void testGetAllMedicalRecords() {
		
				
		PersonService personSvc = Mockito.mock(PersonService.class);
		FireStationService fireStationSvc = Mockito.mock(FireStationService.class);
		MedicalRecordService medicalRecordSvc = Mockito.mock(MedicalRecordService.class);
		
		SafetyNetController safetyNetController = new SafetyNetController(personSvc, fireStationSvc, medicalRecordSvc);		

		String med1 = "ibupurin:200mg";
		String med2 = "advil:10mg";
		List<String> medicationsList = new ArrayList<String>();
		medicationsList.add(med1);
		medicationsList.add(med2);	
		String allergy1 = "peanut";
		String allergy2 = "shellfish";
		
		List<String> allergiesList = new ArrayList<String>();
		allergiesList.add(allergy1);
		allergiesList.add(allergy2);		
		
		List<MedicalRecord> medRecsList = new ArrayList<MedicalRecord>();
		MedicalRecord medicalRecord1 = new MedicalRecord("Med", "Tester", "08/21/1947", medicationsList, allergiesList);
		MedicalRecord medicalRecord2 = new MedicalRecord("Jacob", "Boyd", "08/21/1947", medicationsList, allergiesList);

	
		
		medRecsList.add(medicalRecord1);
		medRecsList.add(medicalRecord2);
		
		
		when(medicalRecordSvc.getMedicalRecords()).thenReturn(medRecsList);		
		List<MedicalRecord> retMedicalRecordList = safetyNetController.getAllMedicalRecords();
		   assertNotNull(retMedicalRecordList);
		   assertEquals(retMedicalRecordList, medRecsList);
		
	}
	
	@Test
	public void testGetPersonMed() {

				
		PersonService personSvc = Mockito.mock(PersonService.class);
		FireStationService fireStationSvc = Mockito.mock(FireStationService.class);
		MedicalRecordService medicalRecordSvc = Mockito.mock(MedicalRecordService.class);
		
		SafetyNetController safetyNetController = new SafetyNetController(personSvc, fireStationSvc, medicalRecordSvc);		


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
		
		PersonMedicalRecord personMedicalRecord = new PersonMedicalRecord(person, medicalRecord1);
		
		
		List<PersonMedicalRecord> personMedicalRecordList = new ArrayList<PersonMedicalRecord>();
		personMedicalRecordList.add(personMedicalRecord);		
		
		when(personSvc.getPersonMedicalRecord()).thenReturn(personMedicalRecordList);
		
		List<PersonMedicalRecord> retPersonMedicalRecord = safetyNetController.getPersonMed();
		
		   assertNotNull(retPersonMedicalRecord);
		   assertEquals(retPersonMedicalRecord, personMedicalRecordList);		
		
	}
	
	@Test
	public void testGetMedicalRecordByFirstLast() {

	
		
		PersonService personSvc = Mockito.mock(PersonService.class);
		FireStationService fireStationSvc = Mockito.mock(FireStationService.class);
		MedicalRecordService medicalRecordSvc = Mockito.mock(MedicalRecordService.class);
		
		SafetyNetController safetyNetController = new SafetyNetController(personSvc, fireStationSvc, medicalRecordSvc);		

		String med1 = "ibupurin:200mg";
		String med2 = "advil:10mg";
		List<String> medicationsList = new ArrayList<String>();
		medicationsList.add(med1);
		medicationsList.add(med2);
		
		String allergy1 = "peanut";
		String allergy2 = "shellfish";
		
		List<String> allergiesList = new ArrayList<String>();
		allergiesList.add(allergy1);
		allergiesList.add(allergy2);
		MedicalRecord medicalRecord1 = new MedicalRecord("Med", "Tester", "08/21/1947", medicationsList, allergiesList);		
		
		String fNameString = medicalRecord1.getFirstName();
		String lNameString = medicalRecord1.getLastName();

		
		when(medicalRecordSvc.findMedicalRecordByFirstLastName(fNameString,lNameString)).thenReturn(medicalRecord1);
		
		MedicalRecord retMedicalRecord = safetyNetController.getMedicalRecordByFirstLast(fNameString, lNameString);
		
		assertNotNull(retMedicalRecord);
		assertEquals(medicalRecord1, retMedicalRecord);				
		
		
	}
	
	
}
