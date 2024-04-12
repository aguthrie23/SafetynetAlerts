package com.openclassrooms.safetynet.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.openclassrooms.safetynet.domain.MedicalRecord;

@Repository
public class MedicalRecordsRepository {
	
	private List<MedicalRecord> medicalRecords = new ArrayList<>();

	public List<MedicalRecord> getMedicalRecords() {
		return medicalRecords;
	}
	
	public List<MedicalRecord> getMedicalRecordsTwo() {
		List<MedicalRecord> retList = new ArrayList<>();
		for (MedicalRecord medicalRecord : medicalRecords) {
     		MedicalRecord med = new MedicalRecord(medicalRecord.getFirstName(), medicalRecord.getLastName(), 
     				medicalRecord.getBirthDate(), medicalRecord.getMedicationsList(), medicalRecord.getAllergiesList());
     		retList.add(med);
			
		}
		return retList;
	}

	public void addMedicalRecord (MedicalRecord medicalRecord) {
		medicalRecords.add(medicalRecord);
	}
	
	public void removeMedicalRecord (String firstName, String lastName) {
		MedicalRecord medicalRecord = findMedicalRecordByFirstLastName(firstName, lastName);
		medicalRecords.remove(medicalRecord);	
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
	

}
