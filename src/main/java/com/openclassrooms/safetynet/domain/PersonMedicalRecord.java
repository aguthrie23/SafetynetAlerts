package com.openclassrooms.safetynet.domain;

public class PersonMedicalRecord {
	
	Person person;
	MedicalRecord medicalRecord;
	
	
	public Person getPerson() {
		return person;
	}


	public void setPerson(Person person) {
		this.person = person;
	}


	public MedicalRecord getMedicalRecord() {
		return medicalRecord;
	}


	public void setMedicalRecord(MedicalRecord medicalRecord) {
		this.medicalRecord = medicalRecord;
	}


	public PersonMedicalRecord (Person person, MedicalRecord medicalRecord) {
		this.person = person;
		this.medicalRecord = medicalRecord;
	}
	

}
