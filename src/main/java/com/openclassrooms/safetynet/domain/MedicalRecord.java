package com.openclassrooms.safetynet.domain;

import java.util.List;

public class MedicalRecord {

	private String firstName;
	private String lastName;
	private String birthDate;
	private List<String> medicationsList;
	private List<String> allergiesList;


	public MedicalRecord(String firstName, String lastName, String birthDate, List<String> medicationsList,
			List<String> allergiesList) {
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
	public List<String> getMedicationsList() {
		return medicationsList;
	}
	public void setMedicationsList(List<String> medicationsList2) {
		this.medicationsList = medicationsList2;
	}
	public List<String> getAllergiesList() {
		return allergiesList;
	}
	public void setAllergiesList(List<String> allergiesList2) {
		this.allergiesList = allergiesList2;
	}




}
