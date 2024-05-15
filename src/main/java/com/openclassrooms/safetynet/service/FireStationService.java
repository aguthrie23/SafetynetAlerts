package com.openclassrooms.safetynet.service;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.safetynet.domain.FireStations;
import com.openclassrooms.safetynet.domain.HouseHoldInfo;
import com.openclassrooms.safetynet.domain.Person;
import com.openclassrooms.safetynet.domain.PersonInfo;
import com.openclassrooms.safetynet.domain.PersonServiced;
import com.openclassrooms.safetynet.repository.FireStationsRepository;

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
	return fireStationsRepository.getFireStationsByStationNumber(stationNumber);
	}
	
	public List<String> getPhoneNumbersPhoneAlert(String stationNumber) {
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
	public List<PersonServiced> getServicedByFirestation(String stationNumber) {
		Set<String> fireHouseAddresses = getFireStationsByStationNumber(stationNumber);
		List <PersonServiced> personInfoList = new ArrayList<PersonServiced>(); 
		int countAdults = 0;
		int countChildren = 0;
		
		for (String string : fireHouseAddresses) {
			List<PersonServiced> retSvcPersonAndMedRecList =  personService.getPersonAndMedRecordByPersonAddressSvc(string);
			personInfoList.addAll(retSvcPersonAndMedRecList);
		}
		
		for (PersonServiced personInfo : personInfoList) {
			int result = (personInfo.getAge() > 18) ? ++countAdults :  ++countChildren;
			
		}
		
		System.out.println("count children " + countChildren);
		System.out.println("count adults " + countAdults);
		
		return personInfoList;
		
	}
	
	// flood/stations
	public HashMap<String, List<HouseHoldInfo>> getHouseholdsByFireStation(List<String> stationNumbers) {
		
	
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
		
		HashMap<String,List <HouseHoldInfo>> personAddressMap= new HashMap<String,List <HouseHoldInfo>>();
		
	    String fireHouseNumber = fireStationsRepository.getFireStationNumberByAddress(address);
	    
	    List<HouseHoldInfo> retSvcPersonAndMedRecList =  personService.getPersonAndMedRecordByPersonAddress(address);
	    personAddressMap.put(fireHouseNumber, retSvcPersonAndMedRecList);
		
		return personAddressMap;
	}
	
	
public HashMap<String, List<PersonInfo>> getHouseholdsByFireStationArchive(List<String> stationNumbers) {
		
		// This should return a list of all the households in each fire station’s jurisdiction. 
		// This list needs to group
		// people by household address, include name, phone number, and age of each person, and any
		// medications (with dosages) and allergies beside each person’s name.
	
	//	System.out.println("size " + stationNumbers.size());
		Set<String> allFireHouseAddresses = new HashSet<String>();
		HashMap<String,List <PersonInfo>> personAddressMap= new HashMap<String,List <PersonInfo>>();
	for (String stations : stationNumbers) {
	//	System.out.println("stations " + stations);
		Set<String> fireHouseAddresses = getFireStationsByStationNumber(stations);
	//	System.out.println(fireHouseAddresses);
		allFireHouseAddresses.addAll(fireHouseAddresses);
	}
	
	
	for (String string : allFireHouseAddresses) {
		System.out.println("1 Here " + string);
		
		List<PersonInfo> retSvcPersonAndMedRecList =  personService.getPersonAndMedRecordByPersonAddressArchive(string);
		personAddressMap.put(string, retSvcPersonAndMedRecList);
	}
	return personAddressMap;
	
	}	
		
	

	

}
