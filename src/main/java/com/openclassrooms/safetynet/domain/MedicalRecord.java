package com.openclassrooms.safetynet.domain;

import java.util.List;

import com.jsoniter.any.Any;

public class MedicalRecord {
	
	private String firstName;
	private String lastName;
	private String birthDate;
	private List<Any> medicationsList;
	private List<Any> allergiesList;
	
	
	
	
	public MedicalRecord(String firstName, String lastName, String birthDate, List<Any> medicationsList,
			List<Any> allergiesList) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.medicationsList = medicationsList;
		this.allergiesList = allergiesList;
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
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public List<Any> getMedicationsList() {
		return medicationsList;
	}
	public void setMedicationsList(List<Any> medicationsList) {
		this.medicationsList = medicationsList;
	}
	public List<Any> getAllergiesList() {
		return allergiesList;
	}
	public void setAllergiesList(List<Any> allergiesList) {
		this.allergiesList = allergiesList;
	}
	
	
	

}
