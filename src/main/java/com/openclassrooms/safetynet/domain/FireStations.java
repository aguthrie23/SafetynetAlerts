package com.openclassrooms.safetynet.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class FireStations {

	
	private Set<String> addresses = new HashSet<>();
	private String stationNumber;
	
	public FireStations(String stationNumber) {
		this.stationNumber = stationNumber;

	}
	
	public FireStations addAddress(String address) {
		addresses.add(address);
		return this;
	}
	
	
	public String getStationNumber() {
		return stationNumber;
	}
	
	public Set<String> getAddresses() {
		return addresses.stream().collect(Collectors.toSet());
	}
	
	@Override
	public String toString() {
		return stationNumber.concat(": ") + String.join(", ", addresses);
	}
	
	
	
}
