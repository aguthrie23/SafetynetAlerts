package com.openclassrooms.safetynet.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.openclassrooms.safetynet.domain.FireStations;

@Repository
public class FireStationsRepository {
	
	//private List<FireStations> fireStations = new ArrayList<FireStations>();
	
//	private static int index = 1;
//	private HashMap<String, FireStations> fireStations = new HashMap<>();

	private List<FireStations> fireStations = new ArrayList<>();
//	FireStations fireStations;
	
	
	
    public List<FireStations> getFireStations() {
	return fireStations;
}

    public Set<String> getFireStationsByStationNumber(String stationNumber) {
    	  	
    	Set <String> fireStationsSet = new  HashSet<>();
    	for (FireStations fireStation : fireStations) {
			if (fireStation.getStationNumber().equals(stationNumber)) {
				fireStationsSet = fireStation.getAddresses();
			}
    	}
		return fireStationsSet;
    	
    }
    
    public String getFireStationNumberByAddress (String address) {
    	for (FireStations fireStation : fireStations) {
			if (fireStation.getAddresses().contains(address)) {
				return fireStation.getStationNumber();
			}
		}
    	return null;
    	
    }
    
	public void addFireStation(FireStations fireStation) {
		fireStations.add(fireStation);
	}
	
	public void removeFireStation (Integer id) {
		fireStations.remove(id);
	}

	

}
