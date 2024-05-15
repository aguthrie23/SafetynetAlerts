package com.openclassrooms.safetynet.controller;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.safetynet.domain.FireStations;
import com.openclassrooms.safetynet.domain.HouseHoldInfo;
import com.openclassrooms.safetynet.domain.PersonInfo;
import com.openclassrooms.safetynet.domain.PersonMedicalRecord;
import com.openclassrooms.safetynet.domain.PersonServiced;
import com.openclassrooms.safetynet.domain.MedicalRecord;
import com.openclassrooms.safetynet.domain.Person;
import com.openclassrooms.safetynet.service.FireStationService;
import com.openclassrooms.safetynet.service.MedicalRecordService;
import com.openclassrooms.safetynet.service.PersonService;

@RestController
// @RequestMapping("/myapi")
public class SafetyNetController {

	private PersonService personService;
	private FireStationService fireStationService;
	private MedicalRecordService medicalRecordService;

	@Autowired
	public SafetyNetController( PersonService personService, FireStationService fireStationService, MedicalRecordService medicalRecordService) {
		super();
		this.personService = personService;
		this.fireStationService = fireStationService;
		this.medicalRecordService = medicalRecordService;
	}


//http://localhost:8080/persons
@RequestMapping(value="/persons", method=RequestMethod.GET)
public List<Person> getAllPersons () {
	// System.out.println("here");
	return personService.getPersons();
}

//http://localhost:8080/firestations
@RequestMapping(value="/firestations", method=RequestMethod.GET)
public List<FireStations> getAllFireStations () {
	// System.out.println("here for fire");
	return fireStationService.getFireStations();
}

//http://localhost:8080/medicalrecords
@RequestMapping(value="/medicalrecords", method=RequestMethod.GET)
public List<MedicalRecord> getAllMedicalRecords () {
	System.out.println("here for medical Recs");
	return medicalRecordService.getMedicalRecords();
}




// http://localhost:8080/person?firstName=Roger&lastName=Boyd
@RequestMapping(value="/person", method=RequestMethod.GET)
public Person getPersonByFirstLast(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {

	return personService.getPersonByFirstLast(firstName, lastName);
}

//http://localhost:8080/personinfo?firstName=Roger&lastName=Boyd
//http://localhost:8080/personinfo?firstName=Sophia&lastName=Zemicks
@RequestMapping(value="/personinfo", method=RequestMethod.GET)
public List<PersonInfo> collectPersonMedRecordByFirstLast(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {

	return personService.getPersonMedRecordByFirstLast(firstName, lastName);
}

// http://localhost:8080/medicalRecord?firstName=Roger&lastName=Boyd
// http://localhost:8080/medicalRecord?firstName=Sophia&lastName=Zemicks
@RequestMapping(value="/medicalRecord", method=RequestMethod.GET)
public MedicalRecord getMedicalRecordByFirstLast(@RequestParam("firstName") String firstName,
		@RequestParam("lastName") String lastName) {
	return medicalRecordService.findMedicalRecordByFirstLastName(firstName, lastName);
}

// http://localhost:8080/communityEmail?city=Culver
@RequestMapping(value="/communityEmail", method=RequestMethod.GET)
public List<String> getEmailByCity(@RequestParam("city") String city) {
	return personService.getEmailByCity(city);
}

//http://localhost:8080/fire?address=1509%20Culver%20St
//http://localhost:8080/fire?address=489%20Manchester%20St
@RequestMapping(value="/fire", params="address" ,method=RequestMethod.GET)
public HashMap<String, List<HouseHoldInfo>> getFireStationNumberAndHousehold(@RequestParam("address") String address) {
return fireStationService.getFireNumberandHouseHoldByAddress(address);
}

//http://localhost:8080/flood/stations?stationvalues=1
// @RequestMapping(value="/flood/stations", method=RequestMethod.GET)
@RequestMapping(value="/flood/stations", params="stationvalues" ,method=RequestMethod.GET)
public HashMap<String, List<HouseHoldInfo>> getHouseHoldsByFireHouse(@RequestParam("stationvalues") List<String> stationvalues) {
return fireStationService.getHouseholdsByFireStation(stationvalues);
}

//http://localhost:8080/firestation?stationNumber=1
@RequestMapping(value="/firestation", method=RequestMethod.GET)
public List<PersonServiced> getServicedByFirestation(@RequestParam("stationNumber") String stationNumber) {
return fireStationService.getServicedByFirestation(stationNumber);
}

//http://localhost:8080/phoneAlert?firestation=1
@RequestMapping(value="/phoneAlert", method=RequestMethod.GET)
public List<String> getPhoneNumbersPhoneAlert(@RequestParam("firestation") String firestation) {
return fireStationService.getPhoneNumbersPhoneAlert(firestation);
}

@RequestMapping(value="/combinePersonMed", method=RequestMethod.GET)
public List<PersonMedicalRecord> getPersonMed() {
    return personService.getPersonMedicalRecord();
}




}
