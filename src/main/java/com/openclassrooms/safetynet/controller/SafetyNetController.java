package com.openclassrooms.safetynet.controller;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	



//http://localhost:8080/firestation?stationNumber=1
@RequestMapping(value="/firestation", method=RequestMethod.GET)
public Map<String, List<PersonServiced>> getServicedByFirestation(@RequestParam("stationNumber") String stationNumber) {
return fireStationService.getServicedByFirestation(stationNumber);
}

// childAlert
// http://localhost:8080/childAlert?address=<address>
@RequestMapping(value="/childAlert", params="address", method=RequestMethod.GET)
public List<ChildAndPerson> getChildandPersons(@RequestParam("address") String address) {
return personService.getChildandPersons(address);
}


//http://localhost:8080/phoneAlert?firestation=1
@RequestMapping(value="/phoneAlert", method=RequestMethod.GET)
public List<String> getPhoneNumbersPhoneAlert(@RequestParam("firestation") String firestation) {
return fireStationService.getPhoneNumbersPhoneAlert(firestation);
}


//http://localhost:8080/fire?address=1509%20Culver%20St
//http://localhost:8080/fire?address=489%20Manchester%20St
@RequestMapping(value="/fire", params="address" ,method=RequestMethod.GET)
public HashMap<String, List<HouseHoldInfo>> getFireStationNumberAndHousehold(@RequestParam("address") String address) {
return fireStationService.getFireNumberandHouseHoldByAddress(address);
}

//http://localhost:8080/flood/stations?stationvalues=1
//@RequestMapping(value="/flood/stations", method=RequestMethod.GET)
@RequestMapping(value="/flood/stations", params="stationvalues" ,method=RequestMethod.GET)
public HashMap<String, List<HouseHoldInfo>> getHouseHoldsByFireHouse(@RequestParam("stationvalues") List<String> stationvalues) {
return fireStationService.getHouseholdsByFireStation(stationvalues);
}



//http://localhost:8080/personinfo?firstName=Roger&lastName=Boyd
//http://localhost:8080/personinfo?firstName=Sophia&lastName=Zemicks
@RequestMapping(value="/personinfo", method=RequestMethod.GET)
public List<PersonInfo> collectPersonMedRecordByFirstLast(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {

	return personService.getPersonMedRecordByFirstLast(firstName, lastName);
}

//http://localhost:8080/communityEmail?city=Culver
@RequestMapping(value="/communityEmail", method=RequestMethod.GET)
public List<String> getEmailByCity(@RequestParam("city") String city) {
	return personService.getEmailByCity(city);
}


// Person Endpoints
@PostMapping("/person")
public void addPerson(Person person) {
	personService.addPerson(person);
}

@DeleteMapping("/person")
public void removePerson(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
	personService.removePerson(firstName, lastName);
}

@PutMapping("/person")
public void updatePerson(Person person) {
	personService.updatePerson(person);
}

// MedicalRecord Endpoints
@PostMapping("/medicalRecord")
public void addMedicalRecord(MedicalRecord medicalRecord) {
	medicalRecordService.addMedicalRecord(medicalRecord);
}

@DeleteMapping("/medicalRecord")
public void removeMedicalRecord(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
	medicalRecordService.removeMedicalRecord(firstName, lastName);
}

@PutMapping("/medicalRecord")
public void updateMedicalRecord(MedicalRecord medicalRecord) {
	medicalRecordService.updateMedicalRecord(medicalRecord);
}



@PostMapping("/fireStation")
public void addFireStation(@RequestParam("stationNumber") String stationNumber, @RequestParam("address") String address) {
	
	//System.out.println("Here " + fireStations.getAddresses());
	fireStationService.addFireStation(stationNumber,address);
}

@PutMapping("/fireStation")
public void updateFireStation(@RequestParam("stationNumber") String newStationNumber,@RequestParam("address") String address) {
	fireStationService.updateFirestation(newStationNumber,address);
}

@DeleteMapping("/fireStation")
public void removeFireStationAddress(@RequestParam("address") String address) {
	fireStationService.removeFireStation(address);
}


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



////////////////
///////////////
/// Other Random to Test get methods working correctly
@RequestMapping(value="/combinePersonMed", method=RequestMethod.GET)
public List<PersonMedicalRecord> getPersonMed() {
    return personService.getPersonMedicalRecord();
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




//http://localhost:8080/person?firstName=Roger&lastName=Boyd
@RequestMapping(value="/person", method=RequestMethod.GET)
public Person getPersonByFirstLast(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {

	return personService.getPersonByFirstLast(firstName, lastName);
}



//http://localhost:8080/medicalRecord?firstName=Roger&lastName=Boyd
//http://localhost:8080/medicalRecord?firstName=Sophia&lastName=Zemicks
@RequestMapping(value="/medicalRecord", method=RequestMethod.GET)
public MedicalRecord getMedicalRecordByFirstLast(@RequestParam("firstName") String firstName,
		@RequestParam("lastName") String lastName) {
	return medicalRecordService.findMedicalRecordByFirstLastName(firstName, lastName);
}



}
