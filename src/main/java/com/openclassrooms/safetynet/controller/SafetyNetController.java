package com.openclassrooms.safetynet.controller;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.safetynet.domain.FireStations;
import com.openclassrooms.safetynet.domain.MedicalRecord;
import com.openclassrooms.safetynet.domain.Person;
import com.openclassrooms.safetynet.service.FireStationService;
import com.openclassrooms.safetynet.service.MedicalRecordService;
import com.openclassrooms.safetynet.service.PersonService;

@RestController
@RequestMapping("/myapi")
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


//http://localhost:8080/myapi/persons
@RequestMapping(value="/persons", method=RequestMethod.GET)	
public List<Person> getAllPersons () {
	// System.out.println("here");
	return personService.getPersons();	
}

//http://localhost:8080/myapi/firestations
@RequestMapping(value="/firestations", method=RequestMethod.GET)	
public List<FireStations> getAllFireStations () {
	// System.out.println("here for fire");
	return fireStationService.getFireStations();	
}

//http://localhost:8080/myapi/medicalrecords
@RequestMapping(value="/medicalrecords", method=RequestMethod.GET) 
public List<MedicalRecord> getAllMedicalRecords () {
	System.out.println("here for medical Recs");
	return medicalRecordService.getMedicalRecords();
}

//http://localhost:8080/myapi/testmedicalrecords
@RequestMapping(value="/testmedicalrecords", method=RequestMethod.GET) 
public List<MedicalRecord> getAllMedicalRecordsTest () {
	System.out.println("here for medical RecsTEST");
	List<MedicalRecord> testList = new ArrayList<MedicalRecord>();
	testList = medicalRecordService.getMedicalRecords();
	
	for (MedicalRecord medicalRecord : testList) {
		System.out.println(medicalRecord.getFirstName() 
				+ "\t" + medicalRecord.getLastName() 
				+ "\t" + medicalRecord.getMedicationsList()
				+ "\t" + medicalRecord.getAllergiesList()) ;			
	}
	return testList;
}

//http://localhost:8080/myapi/testmedicalrecords
@RequestMapping(value="/testmedicalrecordstwo", method=RequestMethod.GET) 
public List<MedicalRecord> getAllMedicalRecordsTestTwo () {
	System.out.println("here for medical RecsTEST2");
	List<MedicalRecord> testList = new ArrayList<MedicalRecord>();
	testList = medicalRecordService.getMedicalRecordsTwo();
	
	for (MedicalRecord medicalRecord : testList) {
		System.out.println(medicalRecord.getFirstName() 
				+ "\t" + medicalRecord.getLastName() 
				+ "\t" + medicalRecord.getMedicationsList()
				+ "\t" + medicalRecord.getAllergiesList()) ;			
	}
	return testList;
}



// http://localhost:8080/myapi/person?firstName=Roger&lastName=Boyd
@RequestMapping(value="/person", method=RequestMethod.GET)
public Person getPersonByFirstLast(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
	
	return personService.getPersonByFirstLast(firstName, lastName);
}

// http://localhost:8080/myapi/communityEmail?city=Culver
@RequestMapping(value="/communityEmail", method=RequestMethod.GET)
public List<String> getEmailByCity(@RequestParam("city") String city) {
	return personService.getEmailByCity(city);
}



}
