package com.openclassrooms.safetynet;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.openclassrooms.safetynet.domain.MedicalRecord;
import com.openclassrooms.safetynet.exception.DataNotFoundException;
import com.openclassrooms.safetynet.repository.MedicalRecordsRepository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class MedicalRecordsRepositoryTest {

	
	public MedicalRecordsRepository medicalRecordsRepository;
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
	public void testGetMedicalRecords() {
		
		medicalRecordsRepository.addMedicalRecord(medicalRecord1);
		medicalRecordsRepository.addMedicalRecord(medicalRecord2);
		
		List<MedicalRecord> retMedList = medicalRecordsRepository.getMedicalRecords();
		assertNotNull(retMedList);
		assertEquals(retMedList.size(), 2);
		
		
	}
	
	@Test
	public void testAddMedicalRecord() {
		medicalRecordsRepository.addMedicalRecord(medicalRecord1);
		assertEquals(medicalRecordsRepository.getMedicalRecords().size(), 1);
	}
	
	@Test
	public void testRemoveMedicalRecord() {
		try {
			medicalRecordsRepository.addMedicalRecord(medicalRecord1);
			medicalRecordsRepository.addMedicalRecord(medicalRecord2);
			medicalRecordsRepository.removeMedicalRecord(medicalRecord1.getFirstName(), medicalRecord1.getLastName());
			assertEquals(medicalRecordsRepository.getMedicalRecords().size(), 1);
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRemoveMedicalRecordException() {
	
			Throwable exception = assertThrows(Exception.class, () -> medicalRecordsRepository.removeMedicalRecord(medicalRecord2.getFirstName(), medicalRecord2.getLastName()));
			assertNotNull(exception);
		
	}		
	
	@Test
	public void testUpdateMedicalRecord() {
		try {
			medicalRecordsRepository.addMedicalRecord(medicalRecord1);
			medicalRecord1.setBirthDate("11/11/1951");
			medicalRecordsRepository.updateMedicalRecord(medicalRecord1);
			assertEquals(medicalRecordsRepository.getMedicalRecords().get(0).getBirthDate(), "11/11/1951");
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test 
	public void testUpdateMedicalRecordException () {

			
		Throwable exception = assertThrows(Exception.class, () -> medicalRecordsRepository.updateMedicalRecord(medicalRecord1));
		assertNotNull(exception);
			
		
	}		
	

	
}
