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
		
		Logger.info("MedicalRecord Service getMedicalRecords");
		
		return medicalRecordsRepository.getMedicalRecords();
	}
	

	public MedicalRecord findMedicalRecordByFirstLastName(String firstName, String lastName) {
		
		Logger.info("MedicalRecord Service findMedicalRecordByFirstLastName");
		
	return medicalRecordsRepository.findMedicalRecordByFirstLastName(firstName, lastName);

	}
	

	public void addMedicalRecord(MedicalRecord medicalRecord) {
		
		Logger.info("MedicalRecord Service addMedicalRecord");
		medicalRecordsRepository.addMedicalRecord(medicalRecord);
		
	}

	public void removeMedicalRecord(String firstName, String lastName) {
		
		Logger.info("MedicalRecord Service removeMedicalRecord");
		try {
			medicalRecordsRepository.removeMedicalRecord(firstName, lastName);
		} catch (Exception e) {
			Logger.error("exception in removeMedicalRecord " + e);
		}
		
	}

	public void updateMedicalRecord(MedicalRecord medicalRecord) {
		
		Logger.info("MedicalRecord Service updateMedicalRecord");
		try {
			medicalRecordsRepository.updateMedicalRecord(medicalRecord);
		} catch (Exception e) {
			Logger.error("exception in updateMedicalRecord " + e);
		}		
	}
	
}
