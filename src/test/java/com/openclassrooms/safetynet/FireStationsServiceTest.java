package com.openclassrooms.safetynet;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.openclassrooms.safetynet.domain.FireStations;
import com.openclassrooms.safetynet.repository.FireStationsRepository;
import com.openclassrooms.safetynet.service.FireStationService;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
	public void testGetHouseholdsByFireStation() {
		
	}

}
