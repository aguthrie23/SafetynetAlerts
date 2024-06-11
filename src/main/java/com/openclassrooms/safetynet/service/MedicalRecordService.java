package com.openclassrooms.safetynet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.safetynet.domain.MedicalRecord;
import com.openclassrooms.safetynet.repository.MedicalRecordsRepository;

@Service
public class MedicalRecordService {
	
	MedicalRecordsRepository medicalRecordsRepository;

	@Autowired
	public MedicalRecordService(MedicalRecordsRepository medicalRecordsRepository) {
		super();
		this.medicalRecordsRepository = medicalRecordsRepository;
	}
	
	public List<MedicalRecord> getMedicalRecords () {
		return medicalRecordsRepository.getMedicalRecords();
	}
	

	public MedicalRecord findMedicalRecordByFirstLastName(String firstName, String lastName) {
	return medicalRecordsRepository.findMedicalRecordByFirstLastName(firstName, lastName);

	}
	
//	public List<MedicalRecord> getMedicalRecordsByFirstLast (String firstName, String lastName) {
//		return medicalRecordsRepository.getMedicalRecordsByFirstLast(firstName,lastName);
//	}

	public void addMedicalRecord(MedicalRecord medicalRecord) {
		medicalRecordsRepository.addMedicalRecord(medicalRecord);
		
	}

	public void removeMedicalRecord(String firstName, String lastName) {
		medicalRecordsRepository.removeMedicalRecord(firstName, lastName);
		
	}

	public void updateMedicalRecord(MedicalRecord medicalRecord) {
		medicalRecordsRepository.updateMedicalRecord(medicalRecord);		
	}
	
	
	
	
	

}
