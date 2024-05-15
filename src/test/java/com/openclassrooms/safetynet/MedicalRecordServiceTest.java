package com.openclassrooms.safetynet;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;


import com.openclassrooms.safetynet.domain.MedicalRecord;
import com.openclassrooms.safetynet.repository.MedicalRecordsRepository;
import com.openclassrooms.safetynet.service.MedicalRecordService;

public class MedicalRecordServiceTest {
	
	public MedicalRecordsRepository medicalRecordsRepository;
	public MedicalRecordService medicalRecordService;
	public MedicalRecord medicalRecord1;
	public MedicalRecord medicalRecord2;
	
	@BeforeEach
	void init () {
		medicalRecordsRepository = new MedicalRecordsRepository();
		String med1 = "ibupurin:200mg";
		String med2 = "advil:10mg";
		List<String> medicationsList = new ArrayList<String>();
		medicationsList.add(med1);
		medicationsList.add(med2);
		
		String allergy1 = "peanut";
		String allergy2 = "shellfish";
		
		List<String> allergiesList = new ArrayList<String>();
		allergiesList.add(allergy1);
		allergiesList.add(allergy2);
		medicalRecord1 = new MedicalRecord("Med", "Tester", "08/21/1947", medicationsList, allergiesList);
		medicalRecord2 = new MedicalRecord("Jacob", "Boyd", "08/21/1947", medicationsList, allergiesList);
		
	}
	
	@Test
	public void testGetMedicalRecords () {
		
		medicalRecordsRepository = Mockito.mock(MedicalRecordsRepository.class);
		medicalRecordService = new MedicalRecordService(medicalRecordsRepository);
		
		List<MedicalRecord> medRecsList = new ArrayList<MedicalRecord>();
		medRecsList.add(medicalRecord1);
		medRecsList.add(medicalRecord2);
		
		
		when(medicalRecordsRepository.getMedicalRecords()).thenReturn(medRecsList);
		
		List<MedicalRecord> retMedicalRecordsList =  medicalRecordService.getMedicalRecords();
		
		assertNotNull(retMedicalRecordsList);
		assertEquals(retMedicalRecordsList, medRecsList);
		
	}
	
	@Test
	public void testFindMedicalRecordByFirstLastName() {
		
		medicalRecordsRepository = Mockito.mock(MedicalRecordsRepository.class);
		medicalRecordService = new MedicalRecordService(medicalRecordsRepository);

		String fNameString = medicalRecord1.getFirstName();
		String lNameString = medicalRecord1.getLastName();

		
		when(medicalRecordsRepository.findMedicalRecordByFirstLastName(fNameString,lNameString)).thenReturn(medicalRecord1);
		
		MedicalRecord retMedicalRecord = medicalRecordService.findMedicalRecordByFirstLastName(fNameString, lNameString);
		
		assertNotNull(retMedicalRecord);
		assertEquals(medicalRecord1, retMedicalRecord);		
		
	}
	
}
