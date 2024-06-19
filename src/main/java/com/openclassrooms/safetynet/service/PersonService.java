package com.openclassrooms.safetynet.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinylog.Logger;

import com.openclassrooms.safetynet.domain.PersonInfo;
import com.openclassrooms.safetynet.domain.PersonMedicalRecord;
import com.openclassrooms.safetynet.domain.PersonServiced;
import com.openclassrooms.safetynet.domain.ChildAndPerson;
import com.openclassrooms.safetynet.domain.HouseHoldInfo;
import com.openclassrooms.safetynet.domain.MedicalRecord;
import com.openclassrooms.safetynet.domain.Person;
import com.openclassrooms.safetynet.repository.PersonRepository;

@Service
public class PersonService {
	
	public  static int  adultAge = 18;
	
	
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
	
	
	// used by getPersons
	public List<Person> getPersons() {
		
		Logger.info("Person Service getPersons");
		
		 return personRepository.getPersons();
	}
	
	// used by addPerson
	public void addPerson (Person person) {
		
		Logger.info("Person Service addPerson");
		
		personRepository.addPerson(person);
	}
	
	public void removePerson(String firstName, String lastName) {
		
		Logger.info("Person Service removePerson");
		
		try {
			personRepository.removePerson(firstName, lastName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Logger.error("exception in removePerson " + e);
		}
	}
	
	public void updatePerson (Person person)  {
		
		Logger.info("Person Service updatePerson");
		
		try {
			personRepository.updatePerson(person);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Logger.error("exception in updatePerson " + e);
		}
	}
	
	
	// Used by childAlert
	public List<ChildAndPerson> getChildandPersons(String address) {
		
		Logger.info("Person Service getChildandPersons");
		
		List <HouseHoldInfo> personHHList = getPersonAndMedRecordByPersonAddress(address);
		List <ChildAndPerson> childPersonList = new ArrayList<ChildAndPerson>();
		List <String> listPerson = new ArrayList<String>();
		
		if (personHHList.size() > 0) {
			
			Logger.debug("getPersonAndMedRecordByPersonAddress has data");
	
			List<HouseHoldInfo> personListSorted =
				personHHList.stream()
                  .sorted(Comparator.comparing(HouseHoldInfo::getAge))
                  .collect(Collectors.toList());
		
		
				
			for (HouseHoldInfo houseHoldInfo : personListSorted) {
	    		
				if (houseHoldInfo.getAge() < adultAge) {
					ChildAndPerson joinerClass = new ChildAndPerson(houseHoldInfo);	
					childPersonList.add(joinerClass);

				} else {
					   if (childPersonList.size() > 0) {
						   listPerson.add(houseHoldInfo.getFirstName() + " " +  houseHoldInfo.getLastName());
					   
					
					   
					   }
					}
			
			}
			
			if (childPersonList.size() > 0) {
				
				Logger.debug("child and person list has data");
				
				for (ChildAndPerson childAndPersonList : childPersonList) {
					childAndPersonList.setOtherPersons(listPerson);
					
				}
				
			}
			
					
		}
		
		
		return childPersonList;
	}	
	
	// used by communityEmail
	public List<String> getEmailByCity(String city) {
		
		Logger.info("Person Service getEmailByCity");
		
		List<Person> personList = personRepository.getPersons();
		List<String> emailList = new ArrayList<>();
		for (Person person : personList) {
			if (person.getCity().equals(city)) {
				emailList.add(person.getEmail());
			}
		}
		
		return emailList;
	}	

	
	// used by personInfo
	public List<PersonInfo> getPersonMedRecordByFirstLast (String firstName, String lastName) {
		
		Logger.info("Person Service getPersonMedRecordByFirstLast");
	
		List<PersonInfo> personInfoList = new ArrayList<PersonInfo>();
		
		// get all personMedical recs 
		List <PersonMedicalRecord> personMedRecList = getPersonMedicalRecord();
		
		if (personMedRecList.size() > 0) {
			Logger.debug("personMedRecList > 0 ");
		for (PersonMedicalRecord personMedicalRecord : personMedRecList) {
			
			if (personMedicalRecord.getPerson().getFirstName().equals(firstName) && personMedicalRecord.getPerson().getLastName().equals(lastName)) {
				Logger.debug("person med rec first and last equal to first and last passed in ");
				PersonInfo joinerClass = new PersonInfo(personMedicalRecord);
				personInfoList.add(joinerClass);			
			}			
		}		
		return personInfoList;
		}
		
		Logger.warn("no data found for getPersonMedicalRecord");
		return null;		
	}
	
	// used by phoneAlert
	public List<Person> getPersonsByAddress(String address) {
		
		Logger.info("Person Service getPersonsByAddress");
		
		return personRepository.getPersonByAddress(address);
		
	}
	// used by fire and flood/stations
	public List<HouseHoldInfo> getPersonAndMedRecordByPersonAddress(String address) {
		
		Logger.info("Person Service getPersonAndMedRecordByPersonAddress");
		
		List <PersonMedicalRecord> personMedRecList = getPersonMedicalRecord();
		List <HouseHoldInfo> houseHoldInfoList = new ArrayList<HouseHoldInfo>();
		
		if (personMedRecList.size() > 0) {			
			for (PersonMedicalRecord personMedicalRecord : personMedRecList) {
				if (personMedicalRecord.getPerson().getAddress().equals(address)) {
					Logger.debug("person med rec address equals address passed in ");
					HouseHoldInfo joinerClass = new HouseHoldInfo(personMedicalRecord);
					houseHoldInfoList.add(joinerClass);
				}
			}
			
			return houseHoldInfoList;			
	}
	
		return null;
	}
	
	// used by firestation
	public List<PersonServiced> getPersonAndMedRecordByPersonAddressSvc(String address) {
		
		Logger.info("Person Service getPersonAndMedRecordByPersonAddressSvc");
		
		List <PersonMedicalRecord> personMedRecList = getPersonMedicalRecord();
		List <PersonServiced> personServicedList = new ArrayList<PersonServiced>();
		
		if (personMedRecList.size() > 0) {
			for (PersonMedicalRecord personMedicalRecord : personMedRecList) {
				if (personMedicalRecord.getPerson().getAddress().equals(address)) {
					Logger.debug("person med rec address equals address passed in ");
					PersonServiced joinerClass = new PersonServiced(personMedicalRecord);
					personServicedList.add(joinerClass);
				}
			}
			
			return personServicedList;			
	}
	
		Logger.warn("no data found for getPersonMedicalRecord");
		return null;
	}	
	
	// combine person and medical record
	public List<PersonMedicalRecord> getPersonMedicalRecord () {
		
		Logger.info("Person Service getPersonMedicalRecord");
		
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
	    
		Logger.warn("no data found for getPersons");
		return null;
		
	}
	
	
}
