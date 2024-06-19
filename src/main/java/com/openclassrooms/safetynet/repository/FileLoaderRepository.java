package com.openclassrooms.safetynet.repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.openclassrooms.safetynet.domain.FireStations;
import com.openclassrooms.safetynet.domain.MedicalRecord;
import com.openclassrooms.safetynet.domain.Person;

@Service
public class FileLoaderRepository {

	PersonRepository personRepository;
	FireStationsRepository fireStationsRepository;
	MedicalRecordsRepository medicalRecordsRepository;

	@Autowired
	public FileLoaderRepository(PersonRepository personRepository,
			FireStationsRepository fireStationsRepository,
			MedicalRecordsRepository medicalRecordsRepository) {
		super();
		this.personRepository = personRepository;
		this.fireStationsRepository = fireStationsRepository;
		this.medicalRecordsRepository = medicalRecordsRepository;
	}

	@PostConstruct
	public void runLoad() throws IOException {
		String filePath = "src/main/resources/data.json";
		byte[] bytesFile = Files.readAllBytes(new File(filePath).toPath());
		JsonIterator iter = JsonIterator.parse(bytesFile);
		Any any = iter.readAny();
		Any personAny = any.get("persons");
		personAny.forEach(
				a -> personRepository.addPerson(new Person.PersonBuilder().firstName(a.get("firstName").toString())
						.address(a.get("address").toString())
						.city(a.get("city").toString())
						.lastName(a.get("lastName").toString())
						.phone(a.get("phone").toString())
						.zip(a.get("zip").toString())
						.email(a.get("email").toString())
						.build()));
		Map<String, FireStations> fireStationMap = new HashMap<>();
		Any fireStationAny = any.get("firestations");
		fireStationAny.forEach(anyStation -> {
			fireStationMap.compute(anyStation.get("station").toString(),
					(k, v) -> v == null
							? new FireStations(anyStation.get("station").toString())
									.addAddress(anyStation.get("address").toString())
							: v.addAddress(anyStation.get("address").toString()));
		});

	//	fireStationMap.forEach((k, v) -> System.out.println(k + " = " + v));

		List<FireStations> fireStations = fireStationMap.values().stream().collect(Collectors.toList());
		fireStations.forEach(fire -> fireStationsRepository.addFireStation(fire));

		Any medicalAny = any.get("medicalrecords");
	//	System.out.println("medical any" + medicalAny);

		medicalAny.forEach(medRec -> {
			MedicalRecord med = new MedicalRecord(medRec.get("firstName").toString(), medRec.get("lastName").toString(),
					medRec.get("birthdate").toString(), null, null);

			List<String> medicationsList = new ArrayList<String>();
			List<String> allergiesList = new ArrayList<String>();

			Any medications = medRec.get("medications");
			if (medications.size() > 0)
				medications.forEach(a -> medicationsList.add(a.toString()));

			Any allergies = medRec.get("allergies");
			if (allergies.size() > 0)
				allergies.forEach(a -> allergiesList.add(a.toString()));

			med.setMedicationsList(medicationsList);
			med.setAllergiesList(allergiesList);
			medicalRecordsRepository.addMedicalRecord(med);

		});
//		List<MedicalRecord> testList = medicalRecordsRepository.getMedicalRecords();
//		for (MedicalRecord medicalRecord : testList) {
//			System.out.println(medicalRecord.getFirstName() + "\t" + medicalRecord.getLastName() + "\t"
//					+ medicalRecord.getMedicationsList());
//		}
	}
}
