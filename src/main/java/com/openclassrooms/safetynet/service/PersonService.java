package com.openclassrooms.safetynet.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PlaceholderConfigurerSupport;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitterReturnValueHandler;

import com.openclassrooms.safetynet.domain.PersonInfo;
import com.openclassrooms.safetynet.domain.PersonInfoArchive;
import com.openclassrooms.safetynet.domain.PersonMedicalRecord;
import com.openclassrooms.safetynet.domain.PersonServiced;
import com.openclassrooms.safetynet.domain.HouseHoldInfo;
import com.openclassrooms.safetynet.domain.MedicalRecord;
import com.openclassrooms.safetynet.domain.Person;
import com.openclassrooms.safetynet.repository.PersonRepository;

@Service
public class PersonService {
	
	
	PersonRepository personRepository;
	MedicalRecordService medicalRecordService;

	@Autowired
	public PersonService(PersonRepository personRepository
			, MedicalRecordService medicalRecordService
			) {
		super();
		this.personRepository = personRepository;
		this.medicalRecordService = medicalRecordService;
	}
	
	
	public List<Person> getPersons() {
		 return personRepository.getPersons();
	}
	
	public List<String> getEmailByCity(String city) {
		List<Person> personList = personRepository.getPersons();
		List<String> emailList = new ArrayList<>();
		for (Person person : personList) {
			if (person.getCity().equals(city)) {
				emailList.add(person.getEmail());
			}
		}
		
		return emailList;
	}	

	public Person getPersonByFirstLast(String firstName, String lastName) {
		return personRepository.findPersonByFirstLast(firstName, lastName);
	}
	
	// personinfo
	public List<PersonInfo> getPersonMedRecordByFirstLast (String firstName, String lastName) {
	
		List<PersonInfo> personInfoList = new ArrayList<PersonInfo>();
		
		// get all personMedical recs 
		List <PersonMedicalRecord> personMedRecList = getPersonMedicalRecord();
		
		if (personMedRecList.size() > 0) {
			System.out.println("Here");
		for (PersonMedicalRecord personMedicalRecord : personMedRecList) {
			
			if (personMedicalRecord.getPerson().getFirstName().equals(firstName) && personMedicalRecord.getPerson().getLastName().equals(lastName)) {
				System.out.println("Here2");
				PersonInfo joinerClass = new PersonInfo(personMedicalRecord);
				personInfoList.add(joinerClass);			
			}			
		}		
		return personInfoList;
		}
		
		return null;		
	}
	
	public List<Person> getPersonsByAddress(String address) {
		return personRepository.getPersonByAddress(address);
		
	}
	// search template class
	public List<HouseHoldInfo> getPersonAndMedRecordByPersonAddress(String address) {
		List <PersonMedicalRecord> personMedRecList = getPersonMedicalRecord();
		List <HouseHoldInfo> houseHoldInfoList = new ArrayList<HouseHoldInfo>();
		
		if (personMedRecList.size() > 0) {
			for (PersonMedicalRecord personMedicalRecord : personMedRecList) {
				if (personMedicalRecord.getPerson().getAddress().equals(address)) {
					HouseHoldInfo joinerClass = new HouseHoldInfo(personMedicalRecord);
					houseHoldInfoList.add(joinerClass);
				}
			}
			
			return houseHoldInfoList;			
	}
	
		return null;
	}
	
	public List<PersonServiced> getPersonAndMedRecordByPersonAddressSvc(String address) {
		List <PersonMedicalRecord> personMedRecList = getPersonMedicalRecord();
		List <PersonServiced> personServicedList = new ArrayList<PersonServiced>();
		
		if (personMedRecList.size() > 0) {
			for (PersonMedicalRecord personMedicalRecord : personMedRecList) {
				if (personMedicalRecord.getPerson().getAddress().equals(address)) {
					PersonServiced joinerClass = new PersonServiced(personMedicalRecord);
					personServicedList.add(joinerClass);
				}
			}
			
			return personServicedList;			
	}
	
		return null;
	}	
	
	// combine person and medical record
	public List<PersonMedicalRecord> getPersonMedicalRecord () {
		List<Person> listGetPersons = personRepository.getPersons();
		List<MedicalRecord> listMedicalRecords = medicalRecordService.getMedicalRecords();
		List<PersonMedicalRecord> listPersonMedicalRecord = new ArrayList<PersonMedicalRecord>();
		
		if (listGetPersons.size() > 0) {
			for (Person person : listGetPersons) {
				
				 for (MedicalRecord medicalRecord : listMedicalRecords) {
					 if (medicalRecord.getFirstName().concat(medicalRecord.getLastName())
							 .equals(person.getFirstName()
									 .concat(person.getLastName()))) {
						 PersonMedicalRecord joinerClass = new PersonMedicalRecord(person, medicalRecord);
						 listPersonMedicalRecord.add(joinerClass);	
					 }
				}			
			}
		
			return listPersonMedicalRecord;
		
		}
	    
		return null;
		
	}
	
	
	///// archive stuff
	
	// personinfo
	public List<PersonInfoArchive> getPersonMedRecordByFirstLastArchive (String firstName, String lastName) {
	
		List <Person> personList = personRepository.getPersonsByFirstLast(firstName, lastName);
		List <MedicalRecord> medicalRecordList = medicalRecordService.getMedicalRecordsByFirstLast(firstName, lastName);
		List<PersonInfoArchive> personInfoList = new ArrayList<PersonInfoArchive>();

		if (personList.size() > 0 || medicalRecordList.size() > 0) {
			for (MedicalRecord medicalRecord : medicalRecordList) {
			
				for (Person person : personList) {
				
					PersonInfoArchive joinerClass = new PersonInfoArchive(person, medicalRecord);
					personInfoList.add(joinerClass);					
				}
				
			}			
			return personInfoList;	
		}	
		return null;		
	}
	
	public List<PersonInfo> getPersonAndMedRecordByPersonAddressArchive(String address) {
		List<PersonInfo> personInfoList = new ArrayList<PersonInfo>();
		List<Person> personList = getPersonsByAddress(address);
		
		for (Person person : personList) {
			List <PersonInfo> personInfoListToAdd = getPersonMedRecordByFirstLast(person.getFirstName(), person.getLastName());
			personInfoList.addAll(personInfoListToAdd);
		}
		
		
		
		
		return personInfoList;
		
		
	}	
	
	
}
