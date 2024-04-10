package com.openclassrooms.safetynet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.safetynet.domain.FireStations;
import com.openclassrooms.safetynet.repository.FireStationsRepository;

@Service
public class FireStationService {
	
	FireStationsRepository fireStationsRepository;

	@Autowired
	public FireStationService(FireStationsRepository fireStationsRepository) {
		super();
		this.fireStationsRepository = fireStationsRepository;
	}
public List<FireStations> getFireStations(){
	return fireStationsRepository.getFireStations();
	
	
}	

}
