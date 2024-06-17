package com.openclassrooms.safetynet.controller;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.openclassrooms.safetynet.domain.ChildAndPerson;
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

import org.tinylog.Logger;

@RestController
public class SafetyNetController {
	
 Logger logger;

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
	



//http://localhost:8080/firestation?stationNumber=1
@RequestMapping(value="/firestation", method=RequestMethod.GET)
public Map<String, List<PersonServiced>> getServicedByFirestation(@RequestParam("stationNumber") String stationNumber) {
	
	logger.info("GET firestation called with param stationNumber " + stationNumber);
	
return fireStationService.getServicedByFirestation(stationNumber);
}

// childAlert
// http://localhost:8080/childAlert?address=1509%20Culver%20St
@RequestMapping(value="/childAlert", params="address", method=RequestMethod.GET)
public List<ChildAndPerson> getChildandPersons(@RequestParam("address") String address) {
	
	logger.info("GET childAlert called with param address " + address);
	
return personService.getChildandPersons(address);
}


//http://localhost:8080/phoneAlert?firestation=1
@RequestMapping(value="/phoneAlert", method=RequestMethod.GET)
public List<String> getPhoneNumbersPhoneAlert(@RequestParam("firestation") String firestation) {
	
	logger.info("GET phoneAlert called with param firestation " + firestation);
	
return fireStationService.getPhoneNumbersPhoneAlert(firestation);
}


//http://localhost:8080/fire?address=1509%20Culver%20St
//http://localhost:8080/fire?address=489%20Manchester%20St
@RequestMapping(value="/fire", params="address" ,method=RequestMethod.GET)
public HashMap<String, List<HouseHoldInfo>> getFireStationNumberAndHousehold(@RequestParam("address") String address) {
	
	logger.info("GET fire called with param address " + address);
	
return fireStationService.getFireNumberandHouseHoldByAddress(address);
}

//http://localhost:8080/flood/stations?stationvalues=1
//@RequestMapping(value="/flood/stations", method=RequestMethod.GET)
@RequestMapping(value="/flood/stations", params="stationvalues" ,method=RequestMethod.GET)
public HashMap<String, List<HouseHoldInfo>> getHouseHoldsByFireHouse(@RequestParam("stationvalues") List<String> stationvalues) {
	
	logger.info("GET flood/stations called with param stationvalues " + stationvalues);
	
return fireStationService.getHouseholdsByFireStation(stationvalues);
}



//http://localhost:8080/personinfo?firstName=Roger&lastName=Boyd
//http://localhost:8080/personinfo?firstName=Sophia&lastName=Zemicks
@RequestMapping(value="/personinfo", method=RequestMethod.GET)
public List<PersonInfo> collectPersonMedRecordByFirstLast(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {

	logger.info("GET personinfo called with params firstName " + firstName + " and lastName " + lastName);
	
	return personService.getPersonMedRecordByFirstLast(firstName, lastName);
}

//http://localhost:8080/communityEmail?city=Culver
@RequestMapping(value="/communityEmail", method=RequestMethod.GET)
public List<String> getEmailByCity(@RequestParam("city") String city) {
	
	logger.info("GET communityEmail called with param city " + city);
	
	return personService.getEmailByCity(city);
}


// Person Endpoints
@PostMapping("/person")
public void addPerson(Person person) {
	
	logger.info("Post person add called");
	
	personService.addPerson(person);
}

@DeleteMapping("/person")
public void removePerson(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
	
	logger.info("Delete person with params first name " + firstName + " and last name" + lastName + " called");
	
	personService.removePerson(firstName, lastName);
}

@PutMapping("/person")
public void updatePerson(Person person) {
	
	logger.info("Put person update called");
	
	personService.updatePerson(person);
}

// MedicalRecord Endpoints
@PostMapping("/medicalRecord")
public void addMedicalRecord(MedicalRecord medicalRecord) {
	
	logger.info("Post medicalRecord add called");
	
	medicalRecordService.addMedicalRecord(medicalRecord);
}

@DeleteMapping("/medicalRecord")
public void removeMedicalRecord(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
	
	logger.info("Delete medicalRecord with params first name " + firstName + " and last name" + lastName + " called");
	medicalRecordService.removeMedicalRecord(firstName, lastName);
}

@PutMapping("/medicalRecord")
public void updateMedicalRecord(MedicalRecord medicalRecord) {
	
	logger.info("Put medicalRecord update called");
	
	medicalRecordService.updateMedicalRecord(medicalRecord);
}



@PostMapping("/fireStation")
public void addFireStation(@RequestParam("stationNumber") String stationNumber, @RequestParam("address") String address) {
	
	logger.info("Post fireStation add called");
	
	fireStationService.addFireStation(stationNumber,address);
}

@PutMapping("/fireStation")
public void updateFireStation(@RequestParam("stationNumber") String newStationNumber,@RequestParam("address") String address) {
	
	logger.info("Put fireStation update called with params stationNumber " + newStationNumber + " and address " + address + " called");
	fireStationService.updateFirestation(newStationNumber,address);
}

@DeleteMapping("/fireStation")
public void removeFireStationAddress(@RequestParam("address") String address) {
	
	logger.info("Delete fireStation with param address " + address + " called");
	
		fireStationService.removeFireStation(address);		
	
}


//////////////////////////////
/// Other Random to Test get methods working correctly below
///

@RequestMapping(value="/combinePersonMed", method=RequestMethod.GET)
public List<PersonMedicalRecord> getPersonMed() {
    return personService.getPersonMedicalRecord();
}


//http://localhost:8080/persons
@RequestMapping(value="/persons", method=RequestMethod.GET)
public List<Person> getAllPersons () {
	return personService.getPersons();
}

//http://localhost:8080/firestations
@RequestMapping(value="/firestations", method=RequestMethod.GET)
public List<FireStations> getAllFireStations () {
	return fireStationService.getFireStations();
}

//http://localhost:8080/medicalrecords
@RequestMapping(value="/medicalrecords", method=RequestMethod.GET)
public List<MedicalRecord> getAllMedicalRecords () {
	return medicalRecordService.getMedicalRecords();
}



//http://localhost:8080/medicalRecord?firstName=Roger&lastName=Boyd
//http://localhost:8080/medicalRecord?firstName=Sophia&lastName=Zemicks
@RequestMapping(value="/medicalRecord", method=RequestMethod.GET)
public MedicalRecord getMedicalRecordByFirstLast(@RequestParam("firstName") String firstName,
		@RequestParam("lastName") String lastName) {
	return medicalRecordService.findMedicalRecordByFirstLastName(firstName, lastName);
}



}
