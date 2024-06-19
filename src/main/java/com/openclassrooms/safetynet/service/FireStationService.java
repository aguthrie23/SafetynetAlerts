package com.openclassrooms.safetynet.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.openclassrooms.safetynet.domain.FireStations;
import com.openclassrooms.safetynet.domain.HouseHoldInfo;
import com.openclassrooms.safetynet.domain.Person;
import com.openclassrooms.safetynet.domain.PersonServiced;
import com.openclassrooms.safetynet.repository.FireStationsRepository;

import org.tinylog.Logger;


@Service
public class FireStationService {
	
	FireStationsRepository fireStationsRepository;
	PersonService personService;
	

	@Autowired
	public FireStationService(FireStationsRepository fireStationsRepository,
			PersonService personService) {
		super();
		this.fireStationsRepository = fireStationsRepository;
		this.personService = personService;
	}

	public List<FireStations> getFireStations(){
		
		
		
	return fireStationsRepository.getFireStations();	
	}	


	public Set<String> getFireStationsByStationNumber(String stationNumber) {
		
		Logger.info("FireStation Service getFireStationsByStationNumber");
		
	return fireStationsRepository.getFireStationsByStationNumber(stationNumber);
	}
	
	public List<String> getPhoneNumbersPhoneAlert(String stationNumber) {
		
		Logger.info("FireStation Service getPhoneNumbersPhoneAlert");
		
		Set<String> fireAddress = getFireStationsByStationNumber(stationNumber);
		List<String> phoneNumberList = new ArrayList<String>();
		List<Person> personList = new ArrayList<>();
		
		for (String string : fireAddress) {
			List <Person> personRetList = personService.getPersonsByAddress(string);
			personList.addAll(personRetList);			
		}
	
		if (personList.size() >0) {
			for (Person person : personList) {
				
				if (!phoneNumberList.contains(person.getPhone())) {
				phoneNumberList.add(person.getPhone());
				}
			}
		}
		
		return phoneNumberList;
	
	}
	
	
	//firestation
	public Map<String, List<PersonServiced>> getServicedByFirestation(String stationNumber) {
		
		Logger.info("FireStation Service getServicedByFirestation");
		
		Set<String> fireHouseAddresses = getFireStationsByStationNumber(stationNumber);
		List <PersonServiced> personInfoList = new ArrayList<PersonServiced>(); 
		HashMap<String,List <PersonServiced>> summaryMap= new HashMap<String,List <PersonServiced>>();
		int countAdults = 0;
		int countChildren = 0;
		
		for (String string : fireHouseAddresses) {
			List<PersonServiced> retSvcPersonAndMedRecList =  personService.getPersonAndMedRecordByPersonAddressSvc(string);
			personInfoList.addAll(retSvcPersonAndMedRecList);
		}
		
		for (PersonServiced personInfo : personInfoList) {
			int result = (personInfo.getAge() > 18) ? ++countAdults :  ++countChildren;
			
		}

		
		String summaryString = "count Adults:" + countAdults + " count Children " + countChildren;
		

		summaryMap.put(summaryString, personInfoList);
	
		return summaryMap;
		
	}	
	
	// flood/stations
	public HashMap<String, List<HouseHoldInfo>> getHouseholdsByFireStation(List<String> stationNumbers) {
		
		Logger.info("FireStation Service getHouseholdsByFireStation");
	
		Set<String> allFireHouseAddresses = new HashSet<String>();
		HashMap<String,List <HouseHoldInfo>> personAddressMap= new HashMap<String,List <HouseHoldInfo>>();
	for (String stations : stationNumbers) {
		Set<String> fireHouseAddresses = getFireStationsByStationNumber(stations);
		allFireHouseAddresses.addAll(fireHouseAddresses);
	}
	
	
	for (String string : allFireHouseAddresses) {
		System.out.println("1 Here " + string);
		
		
		List<HouseHoldInfo> retSvcPersonAndMedRecList =  personService.getPersonAndMedRecordByPersonAddress(string);
		personAddressMap.put(string, retSvcPersonAndMedRecList);
	}
	return personAddressMap;
	
	}	
	
	
	// fire
	public HashMap<String, List<HouseHoldInfo>> getFireNumberandHouseHoldByAddress(String address) {
		
		Logger.info("FireStation Service getFireNumberandHouseHoldByAddress");
		
		HashMap<String,List <HouseHoldInfo>> personAddressMap= new HashMap<String,List <HouseHoldInfo>>();
		
	    String fireHouseNumber = fireStationsRepository.getFireStationNumberByAddress(address);
	    
	    List<HouseHoldInfo> retSvcPersonAndMedRecList =  personService.getPersonAndMedRecordByPersonAddress(address);
	    personAddressMap.put(fireHouseNumber, retSvcPersonAndMedRecList);
		
		return personAddressMap;
	}
	
	
// add a firestation
public void addFireStation(String stationNumber, String address) {
	
	Logger.info("FireStation Service addFireStation");
	
List<FireStations> allFireStations = getFireStations();

for (FireStations fireStations : allFireStations) {
	
	if (fireStations.getStationNumber().equals(stationNumber)) {
		if (fireStations.getAddresses().contains(address)) {
			
		// do nothing
			Logger.warn("FireStation already exists for {} ", stationNumber + " " + address);
			return;		
		} else {
			fireStations.addAddress(address);
			return;
		}
		
	}
}

// if here nothing was returned
FireStations addNewFirestation = new FireStations(stationNumber).addAddress(address);
fireStationsRepository.addFireStation(addNewFirestation);	
}

// update an existing firestation from one mapping station number to another
public void updateFirestation(String newStationNumber,String address) {

	Logger.info("FireStation Service updateFirestation");

		// remove the firestation address from the list
		try {
			removeFireStation(address);
		    // add address to new station
			addFireStation(newStationNumber,address);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Logger.error("exception in updateFirestation " + e);
		}
	

	
}

public void removeFireStation(String address) throws Exception {

	Logger.info("FireStation Service removeFireStation");
	
	//try {
		fireStationsRepository.removeFireStationAddress(address);
	//} catch (Exception e) {
		
	//	logger.error("exception in removeFireStation " + e);
	//	throw new RuntimeException();
		
//	}
	
}

}
