package com.openclassrooms.safetynet.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.openclassrooms.safetynet.domain.MedicalRecord;
import com.openclassrooms.safetynet.exception.DataNotFoundException;

@Repository
public class MedicalRecordsRepository {
	
	private List<MedicalRecord> medicalRecords = new ArrayList<>();

	public List<MedicalRecord> getMedicalRecords() {
		return medicalRecords;
	}
	

	public void addMedicalRecord (MedicalRecord medicalRecord) {
		medicalRecords.add(medicalRecord);
	}
	
	public void removeMedicalRecord (String firstName, String lastName) throws DataNotFoundException {
		MedicalRecord medicalRecord = findMedicalRecordByFirstLastName(firstName, lastName);
		
		if (medicalRecord != null) {
		medicalRecords.remove(medicalRecord);
		}
		else {
			throw new DataNotFoundException("Remove Medical Record Not found with data first name " + firstName + " and last name " + lastName);
		}
	}
	
	public MedicalRecord findMedicalRecordByFirstLastName(String firstName, String lastName) {
		
		String concatString = firstName.concat(lastName);
		
		for (MedicalRecord medicalRecord : medicalRecords) {
			if (medicalRecord.getFirstName().concat(medicalRecord.getLastName())
					.equals(concatString)) {
				return medicalRecord;
			}
		}
		
		return null;
	}
	

	
	public void updateMedicalRecord(MedicalRecord medicalRecord) throws DataNotFoundException {
		int index = findIndexByFirstLast(medicalRecord.getFirstName(), medicalRecord.getLastName());
		
		if (index >= 0) {
			medicalRecords.set(index,medicalRecord);
		}
		else {
			throw new DataNotFoundException("Remove Medical Record Not found with data first name " + medicalRecord.getFirstName() + " and last name " + medicalRecord.getLastName());
		}
	}
	
	public Integer findIndexByFirstLast(String firstName, String lastName) {
		int count = 0;
		String concatString = firstName.concat(lastName);
		for (MedicalRecord medicalRecord : medicalRecords) {
			
			if (medicalRecord.getFirstName().concat(medicalRecord.getLastName())
					.equals(concatString))  {
				return count;
			}	
			
			count++;
		}
		
		return -1;
	}
	

}
