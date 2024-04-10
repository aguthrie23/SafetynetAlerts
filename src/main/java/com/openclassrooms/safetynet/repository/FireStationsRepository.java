package com.openclassrooms.safetynet.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
	public void addFireStation(FireStations fireStation) {
		fireStations.add(fireStation);
	}
	
	public void removeFireStation (Integer id) {
		fireStations.remove(id);
	}

	

}
