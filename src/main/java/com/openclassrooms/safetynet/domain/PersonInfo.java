package com.openclassrooms.safetynet.domain;

import java.time.LocalDate;
import java.util.List;


import java.time.Period;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public class PersonInfo {
	
	Person person;
	MedicalRecord medicalRecord;
	PersonMedicalRecord personMedicalRecord;
	
	private String firstName;
	private String lastName;
	private String address;
	private Integer age;
	private String email;
	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	private List<String> medicationsList;
	private List<String> allergiesList;
	
	


	public PersonInfo(PersonMedicalRecord personMedicalRecord) {
		super();

		this.person = personMedicalRecord.getPerson();
		this.medicalRecord = personMedicalRecord.getMedicalRecord();
		
		
		this.firstName = personMedicalRecord.getPerson().getFirstName();
		this.lastName = personMedicalRecord.getPerson().getLastName();
		this.address = personMedicalRecord.getPerson().getAddress();
		this.email = personMedicalRecord.getPerson().getEmail();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate date = LocalDate.parse(personMedicalRecord.getMedicalRecord().getBirthDate(), formatter);
	    this.age = Period.between(date, LocalDate.now()).getYears();


		this.medicationsList =  personMedicalRecord.getMedicalRecord().getMedicationsList();
		this.allergiesList = personMedicalRecord.getMedicalRecord().getAllergiesList();
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
