package com.openclassrooms.safetynet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinylog.Logger;

import com.openclassrooms.safetynet.domain.MedicalRecord;
import com.openclassrooms.safetynet.repository.MedicalRecordsRepository;

@Service
public class MedicalRecordService {
	
	MedicalRecordsRepository medicalRecordsRepository;
	
	Logger logger;
	

	@Autowired
	public MedicalRecordService(MedicalRecordsRepository medicalRecordsRepository) {
		super();
		this.medicalRecordsRepository = medicalRecordsRepository;
	}
	
	public List<MedicalRecord> getMedicalRecords () {
		
		logger.info("MedicalRecord Service getMedicalRecords");
		
		return medicalRecordsRepository.getMedicalRecords();
	}
	

	public MedicalRecord findMedicalRecordByFirstLastName(String firstName, String lastName) {
		
		logger.info("MedicalRecord Service findMedicalRecordByFirstLastName");
		
	return medicalRecordsRepository.findMedicalRecordByFirstLastName(firstName, lastName);

	}
	
//	public List<MedicalRecord> getMedicalRecordsByFirstLast (String firstName, String lastName) {
//		return medicalRecordsRepository.getMedicalRecordsByFirstLast(firstName,lastName);
//	}

	public void addMedicalRecord(MedicalRecord medicalRecord) {
		
		logger.info("MedicalRecord Service addMedicalRecord");
		medicalRecordsRepository.addMedicalRecord(medicalRecord);
		
	}

	public void removeMedicalRecord(String firstName, String lastName) {
		
		logger.info("MedicalRecord Service removeMedicalRecord");
		medicalRecordsRepository.removeMedicalRecord(firstName, lastName);
		
	}

	public void updateMedicalRecord(MedicalRecord medicalRecord) {
		
		logger.info("MedicalRecord Service updateMedicalRecord");
		medicalRecordsRepository.updateMedicalRecord(medicalRecord);		
	}
	
	
	
	
	

}
