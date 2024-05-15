package com.openclassrooms.safetynet;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;


import com.openclassrooms.safetynet.domain.FireStations;
import com.openclassrooms.safetynet.domain.Person;
import com.openclassrooms.safetynet.repository.FireStationsRepository;
import com.openclassrooms.safetynet.repository.PersonRepository;

public class FireStationsRepositoryTest {
	
	public FireStationsRepository fireStationsRepository;
	
	@BeforeEach
	void init () {
		fireStationsRepository = new FireStationsRepository();	
		FireStations fireStations = new FireStations("1");
		fireStations.addAddress("123 Test Address");
		fireStations.addAddress("456 Test Address");
		fireStationsRepository.addFireStation(fireStations);
	}
	

	
	@Test
	public void testGetFireStations() {
		
	//	fireStationsRepository = new FireStationsRepository();
				
		
//		FireStations fireStations = new FireStations("1");
	//	fireStations.addAddress("123 Test Address");
//		fireStationsRepository.addFireStation(fireStations);
		
		List <FireStations> retFireStationsList = fireStationsRepository.getFireStations();
		
		System.out.println(retFireStationsList);
		
		assertNotNull(retFireStationsList);
	//	 assertEquals(fireStations, retFireStationsList);
			
	}
	
	@Test
	public void testGetFireStationsByStationNumber() {
		
		String stationNumber = "1";
		Set<String> retStationAddressesSet = fireStationsRepository.getFireStationsByStationNumber(stationNumber);
		System.out.println("Here" + retStationAddressesSet);
		assertNotNull(retStationAddressesSet);
		assertEquals(retStationAddressesSet.size(), 2);
		
	}
	
	@Test
	public void testGetFireStationNumberByAddress() {
		
		String address = "456 Test Address";
		String expectedResultString = "1";
		String fireStationNumRetString = fireStationsRepository.getFireStationNumberByAddress(address);
		System.out.println("Return is " + fireStationNumRetString);
		assertNotNull(fireStationNumRetString);
		assertEquals(expectedResultString, fireStationNumRetString);
		
	}
	

}
