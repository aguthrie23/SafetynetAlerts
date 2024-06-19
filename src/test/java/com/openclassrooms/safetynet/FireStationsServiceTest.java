package com.openclassrooms.safetynet;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.openclassrooms.safetynet.domain.FireStations;
import com.openclassrooms.safetynet.domain.HouseHoldInfo;
import com.openclassrooms.safetynet.domain.MedicalRecord;
import com.openclassrooms.safetynet.domain.Person;
import com.openclassrooms.safetynet.domain.PersonMedicalRecord;
import com.openclassrooms.safetynet.domain.PersonServiced;
import com.openclassrooms.safetynet.exception.DataNotFoundException;
import com.openclassrooms.safetynet.repository.FireStationsRepository;
import com.openclassrooms.safetynet.service.FireStationService;

import com.openclassrooms.safetynet.service.PersonService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;

public class FireStationsServiceTest {
	
	
	@Test
	public void testGetFireStations() {
		
		FireStationsRepository fireStationsRepository = Mockito.mock(FireStationsRepository.class);
		
		FireStationService fireStationSvc = new FireStationService(fireStationsRepository, null);
		
		FireStations fireStations = new FireStations("1");
		fireStations.addAddress("123 Main St");
		
		List <FireStations> listFireStations = new ArrayList<FireStations>();
		listFireStations.add(fireStations);
		
		when(fireStationsRepository.getFireStations()).thenReturn(listFireStations);
		
		List<FireStations> retList = new ArrayList<FireStations>();
		
		retList = fireStationSvc.getFireStations();
		
		assertEquals(listFireStations, retList);	
		
	}
	
	@Test
	public void testRemoveFireStation() {

		try {
			String address = "testAddress";
			FireStationsRepository fireStationsRepository = Mockito.mock(FireStationsRepository.class);
			FireStationService fireStationSvc = new FireStationService(fireStationsRepository, null);
			

			fireStationSvc.removeFireStation(address);
			verify(fireStationsRepository,times(1)).removeFireStationAddress(address);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateFireStation() {
		
		try {
			FireStationsRepository fireStationsRepository = Mockito.mock(FireStationsRepository.class);
			FireStationService fireStationSvc = new FireStationService(fireStationsRepository, null);
			
			String stationNum = "1";
			String address1 = "123 Main St"; 
			String address2 = "999 filler";
			String newStationNumString = "2";
			FireStations fireStations1 = new FireStations(stationNum);
			fireStations1.addAddress(address1);
					
			fireStations1.addAddress(address2);
			
			FireStations fireStations2 = new FireStations(newStationNumString);
			fireStations2.addAddress(address2);
			

			
			List <FireStations> listFireStations = new ArrayList<FireStations>();
			listFireStations.add(fireStations1);
			listFireStations.add(fireStations2);
			
			when(fireStationsRepository.getFireStations()).thenReturn(listFireStations);
			
			fireStationSvc.updateFirestation(newStationNumString,address1);
			//verify(fireStationsRepository,times(1)).addFireStation(fireStations);
			verify(fireStationsRepository,times(1)).removeFireStationAddress(address1);
			//	verify(fireStationsRepository, times(1)).addFireStation(new FireStations(newStationNumString).addAddress(address1));
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddFireStation() {
		FireStationsRepository fireStationsRepository = Mockito.mock(FireStationsRepository.class);
		FireStationService fireStationSvc = new FireStationService(fireStationsRepository, null);	
		String stationNum = "1";
		String address1 = "123 Main St";
		
		FireStations fireStations1 = new FireStations(stationNum);
		fireStations1.addAddress(address1);		

		List <FireStations> listFireStationsForGet = new ArrayList<FireStations>();
		listFireStationsForGet.add(fireStations1);

		String newStationAddress = "999 filler";
		String newStationNum = "2";

		
		FireStations addNewFirestation = new FireStations(newStationNum);
		addNewFirestation.addAddress(newStationAddress);
		
        when(fireStationsRepository.getFireStations()).thenReturn(listFireStationsForGet);		
		
		fireStationSvc.addFireStation(newStationNum,newStationAddress);
		verify(fireStationsRepository,times(1)).addFireStation(any());
	}
	
	

	
	@Test
	public void testUpdateFireStationNewFireStation() {
		
		
		try {
			FireStationsRepository fireStationsRepository = Mockito.mock(FireStationsRepository.class);
			FireStationService fireStationSvc = new FireStationService(fireStationsRepository, null);
			
			String stationNum = "1";
			String address1 = "123 Main St"; 
			
			FireStations fireStations1 = new FireStations(stationNum);
			fireStations1.addAddress(address1);
			
			List <FireStations> listFireStations = new ArrayList<FireStations>();
			listFireStations.add(fireStations1);		
			
//	String address2 = "999 filler";
			String newStationNumString = "2";


			// FireStations addNewFirestation = new FireStations(newStationNumString).addAddress(address1);
					
			when(fireStationsRepository.getFireStations()).thenReturn(listFireStations);
			
			fireStationSvc.updateFirestation(newStationNumString,address1);
			
			verify(fireStationsRepository,times(1)).removeFireStationAddress(address1);
			verify(fireStationsRepository, times(1)).addFireStation(any());
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
	@Test
	public void testGetFireNumberandHouseHoldByAddress() {
		
		FireStationsRepository fireStationsRepository = Mockito.mock(FireStationsRepository.class);
		PersonService personService = Mockito.mock(PersonService.class);
		FireStationService fireStationSvc = new FireStationService(fireStationsRepository, personService);
		
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
		
		
		when(fireStationsRepository.getFireStationNumberByAddress(address)).thenReturn(fireHouseNumber);
		
		when(personService.getPersonAndMedRecordByPersonAddress(address)).thenReturn(retSvcPersonAndMedRecList);
		
		personAddressMap = fireStationSvc.getFireNumberandHouseHoldByAddress(address);


		assertNotNull(personAddressMap);
		assertTrue(personAddressMap.containsKey(fireHouseNumber) && personAddressMap.get(fireHouseNumber) != null);
		assertEquals(personAddressMap.get(fireHouseNumber).get(0).getFirstName(), fNameString);

		}
	
//	@Test
//	public void testGetFireNumberandHouseHoldByAddressException() {
//		
//		FireStationsRepository fireStationsRepository = Mockito.mock(FireStationsRepository.class);
//		PersonService personService = Mockito.mock(PersonService.class);
//		FireStationService fireStationSvc = new FireStationService(fireStationsRepository, personService);
//		
//		HashMap<String,List <HouseHoldInfo>> personAddressMap= new HashMap<String,List <HouseHoldInfo>>();
//		List<HouseHoldInfo> retSvcPersonAndMedRecList = new ArrayList<HouseHoldInfo>();
//		
//		
//		String fireHouseNumber = "1";
//		String address = "123 Main St"; 
//		String fNameString = "Test";
//		
//		MedicalRecordsRepository medicalRecordsRepository = new MedicalRecordsRepository();
//		String med1 = "ibupurin:200mg";
//		String med2 = "advil:10mg";
//		List<String> medicationsList = new ArrayList<String>();
//		medicationsList.add(med1);
//		medicationsList.add(med2);
//		
//		String allergy1 = "peanut";
//		String allergy2 = "shellfish";
//		
//		List<String> allergiesList = new ArrayList<String>();
//		allergiesList.add(allergy1);
//		allergiesList.add(allergy2);
//		MedicalRecord medicalRecord = new MedicalRecord(fNameString, "Tester", "08/21/1947", medicationsList, allergiesList);		
//		
//		Person person = new Person(fNameString,"Tester","123-456-7890","23059",address,"richmond","tester@test.com");
//		
//        PersonMedicalRecord personMedicalRecord = new PersonMedicalRecord(person, medicalRecord);
//        HouseHoldInfo houseHoldInfo = new HouseHoldInfo(personMedicalRecord);
//        retSvcPersonAndMedRecList.add(houseHoldInfo);
//		
//        when(fireStationsRepository.getFireStationNumberByAddress(address)).thenThrow(NullPointerException.class);
//		//when(fireStationsRepository.getFireStationNumberByAddress(address)).thenReturn(fireHouseNumber);
//		
//		when(personService.getPersonAndMedRecordByPersonAddress(address)).thenReturn(retSvcPersonAndMedRecList);
//		
//		personAddressMap = fireStationSvc.getFireNumberandHouseHoldByAddress(address);
//
//       assertThrows(NullPointerException.class, () -> fireStationsRepository.getFireStationNumberByAddress(address));
//
//
//		}	
		
	
	@Test
	public void testGetHouseholdsByFireStation() {
		
		
		
		FireStationsRepository fireStationsRepository = Mockito.mock(FireStationsRepository.class);
		PersonService personService = Mockito.mock(PersonService.class);
		FireStationService fireStationSvc = new FireStationService(fireStationsRepository, personService);
		
		HashMap<String,List <HouseHoldInfo>> personAddressMap= new HashMap<String,List <HouseHoldInfo>>();
		List<HouseHoldInfo> retSvcPersonAndMedRecList = new ArrayList<HouseHoldInfo>();
		
		String addressforSet1 = "123 Main St";
		String addressforSet2 = "456 Main St";
		Set <String> fireStationsSet = new HashSet<String>();
		fireStationsSet.add(addressforSet1);
		fireStationsSet.add(addressforSet2);
		
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
        
        when(fireStationsRepository.getFireStationsByStationNumber(fireHouseNumber)).thenReturn(fireStationsSet);
        when(personService.getPersonAndMedRecordByPersonAddress(address)).thenReturn(retSvcPersonAndMedRecList);
        
        
        
        personAddressMap = fireStationSvc.getHouseholdsByFireStation(fireHouseNumbersList);
        
		assertNotNull(personAddressMap);
		assertTrue(personAddressMap.containsKey(address) && personAddressMap.get(address) != null);
		assertEquals(personAddressMap.get(address).get(0).getFirstName(), fNameString);        
	}
	
	@Test
	public void testGetServicedByFirestation () {
		FireStationsRepository fireStationsRepository = Mockito.mock(FireStationsRepository.class);
		PersonService personService = Mockito.mock(PersonService.class);
		FireStationService fireStationSvc = new FireStationService(fireStationsRepository, personService);

		List<PersonServiced> retSvcPersonAndMedRecList = new ArrayList<PersonServiced>();
		
		Map<String, List<PersonServiced>>  summaryMap= new HashMap<String,List <PersonServiced>>();
		
		String addressforSet1 = "123 Main St";
		String addressforSet2 = "456 Main St";
		Set <String> fireStationsSet = new HashSet<String>();
		fireStationsSet.add(addressforSet1);
		fireStationsSet.add(addressforSet2);
		
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
        PersonServiced personServiced = new PersonServiced(personMedicalRecord);
        retSvcPersonAndMedRecList.add(personServiced);	
        
     
      //  List <PersonServiced> personInfoList = new ArrayList<PersonServiced>(); 		
		
		when(fireStationsRepository.getFireStationsByStationNumber(fireHouseNumber)).thenReturn(fireStationsSet);
		
		when(personService.getPersonAndMedRecordByPersonAddressSvc(address)).thenReturn(retSvcPersonAndMedRecList);
		
		summaryMap = fireStationSvc.getServicedByFirestation(fireHouseNumber);
		assertNotNull(summaryMap);
		assertTrue(summaryMap.containsKey("count Adults:1 count Children 0") && summaryMap.get("count Adults:1 count Children 0") != null);
		
	}
	
	@Test 
	public void testGetPhoneNumbersPhoneAlert() {
		FireStationsRepository fireStationsRepository = Mockito.mock(FireStationsRepository.class);
		PersonService personService = Mockito.mock(PersonService.class);
		FireStationService fireStationSvc = new FireStationService(fireStationsRepository, personService);
		
		String address = "123 Main St";
		String addressforSet1 = "123 Main St";
		String addressforSet2 = "456 Main St";
		Set <String> fireStationsSet = new HashSet<String>();
		fireStationsSet.add(addressforSet1);
		fireStationsSet.add(addressforSet2);
		
		String fireHouseNumber = "1";
		String phoneNumString = "123-456-7890";
		
		Person person = new Person("Test","Tester",phoneNumString,"23059",address,"richmond","tester@test.com");
		List <Person> personRetList = new ArrayList<Person>();
		personRetList.add(person);
		
		when(fireStationsRepository.getFireStationsByStationNumber(fireHouseNumber)).thenReturn(fireStationsSet);
		when(personService.getPersonsByAddress(address)).thenReturn(personRetList);
		
		List<String> retListPhoneNumbersList = new ArrayList<String>();
		
		retListPhoneNumbersList = fireStationSvc.getPhoneNumbersPhoneAlert(fireHouseNumber);
		assertNotNull(retListPhoneNumbersList);
		assertEquals(retListPhoneNumbersList.get(0), phoneNumString);
	}
	
	


}
