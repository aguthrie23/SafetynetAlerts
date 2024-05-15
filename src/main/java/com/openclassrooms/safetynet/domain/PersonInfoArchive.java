package com.openclassrooms.safetynet.domain;

import java.time.LocalDate;
import java.util.List;


import java.time.Period;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public class PersonInfoArchive {
	
	Person person;
	MedicalRecord medicalRecord;
	
	private String firstName;
	private String lastName;
	private Integer age;
	private String email;
	private List<String> medicationsList;
	private List<String> allergiesList;
	
	


	public PersonInfoArchive(Person person, MedicalRecord medicalRecord) {
		super();

		this.person = person;
		this.medicalRecord = medicalRecord;
		
		this.firstName = person.getFirstName();
		this.lastName = person.getLastName();
		this.email = person.getEmail();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate date = LocalDate.parse(medicalRecord.getBirthDate(), formatter);
	    this.age = Period.between(date, LocalDate.now()).getYears();

		this.medicationsList = medicalRecord.getMedicationsList();
		this.allergiesList = medicalRecord.getAllergiesList();
	}
	
	
	public Integer getAge() {
		return age;
	}


	public void setAge(Integer age) {
		this.age = age;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public List<String> getMedicationsList() {
		return medicationsList;
	}


	public void setMedicationsList(List<String> medicationsList) {
		this.medicationsList = medicationsList;
	}


	public List<String> getAllergiesList() {
		return allergiesList;
	}


	public void setAllergiesList(List<String> allergiesList) {
		this.allergiesList = allergiesList;
	}


	public void setPerson(Person person) {
		this.person = person;
	}


	public void setMedicalRecord(MedicalRecord medicalRecord) {
		this.medicalRecord = medicalRecord;
	}


//	public Person getPerson() {
//		return person;
//	}
//
//
//	public MedicalRecord getMedicalRecord() {
//		return medicalRecord;
//	}

}
