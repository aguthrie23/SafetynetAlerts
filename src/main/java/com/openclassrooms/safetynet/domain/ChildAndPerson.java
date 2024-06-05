package com.openclassrooms.safetynet.domain;

import java.util.ArrayList;
import java.util.List;

public class ChildAndPerson {
	
	private String firstName;
	private String lastName;
	private Integer age;
	private List<String> otherPersons = new ArrayList<String>();
	
	Person person;
	
	public ChildAndPerson(HouseHoldInfo houseHoldInfo) {
		
		this.firstName = houseHoldInfo.getFirstName();
		this.lastName = houseHoldInfo.getLastName();
		this.age = houseHoldInfo.getAge();		
				
		
	}
	

	public List<String> getOtherPersons() {
		return otherPersons;
	}


	public void setOtherPersons(List<String> otherPersons) {
		this.otherPersons = otherPersons;
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}



	public void setPerson(Person person) {
		this.person = person;
	}
	
	

}
