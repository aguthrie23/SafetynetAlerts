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
	
	public List<MedicalRecord> getMedicalRecordsTwo () {
		return medicalRecordsRepository.getMedicalRecordsTwo();
	}
	
	
	

}
