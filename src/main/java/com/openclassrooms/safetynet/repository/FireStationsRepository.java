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

	private List<FireStations> fireStations = new ArrayList<>();


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
	

	public void removeFireStationAddress(String address) {
		
		Set<String> set = new HashSet<>();
		String existStationNum = null;
		List<FireStations> allFireStations = getFireStations();
		FireStations fireToRemove = null;
		
		
			for (FireStations loopfireStations : allFireStations) {
			
				if (loopfireStations.getAddresses().contains(address)) {
					existStationNum = loopfireStations.getStationNumber();
					set = loopfireStations.getAddresses();	
					set.remove(address);
					fireToRemove = loopfireStations;
					
			     }
		}
		
			if (fireToRemove != null) {
				fireStations.remove(fireToRemove);
			//}
			
			
			FireStations addNewFirestation = new FireStations(existStationNum);
			set.forEach(a -> addNewFirestation.addAddress(a));
			addFireStation(addNewFirestation);
			}
			
	}
	


	
	
	

}
