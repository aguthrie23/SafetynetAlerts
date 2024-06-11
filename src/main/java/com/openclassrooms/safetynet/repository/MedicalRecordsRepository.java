package com.openclassrooms.safetynet.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.openclassrooms.safetynet.domain.MedicalRecord;
import com.openclassrooms.safetynet.domain.Person;

@Repository
public class MedicalRecordsRepository {
	
	private List<MedicalRecord> medicalRecords = new ArrayList<>();

	public List<MedicalRecord> getMedicalRecords() {
		return medicalRecords;
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
	
//	public List<MedicalRecord> getMedicalRecordsByFirstLast(String firstName, String lastName) {
//		
//		String concatString = firstName.concat(lastName);
//		List<MedicalRecord> medicalRecordsList = new ArrayList<MedicalRecord>();
//		for (MedicalRecord medicalRecord : medicalRecords) {
//			if (medicalRecord.getFirstName().concat(medicalRecord.getLastName())
//					.equals(concatString))  {
//				medicalRecordsList.add(medicalRecord);
//			}			
//		}
//		return medicalRecordsList;
//	}


	
	public void updateMedicalRecord(MedicalRecord medicalRecord) {
		int index = findIndexByFirstLast(medicalRecord.getFirstName(), medicalRecord.getLastName());
		
		if (index > 0) {
			medicalRecords.set(index,medicalRecord);
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
		
		return 0;
	}
	

}
