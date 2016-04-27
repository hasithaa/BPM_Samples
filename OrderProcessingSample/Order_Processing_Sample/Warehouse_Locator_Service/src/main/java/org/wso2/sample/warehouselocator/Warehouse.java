package org.wso2.sample.warehouselocator;

public class Warehouse {
	private String Name;
	private String Address;
	private String postCode;
	private String city;
	private int capacityLevel;
	
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public int getCapacityLevel() {
		return capacityLevel;
	}
	public void setCapacityLevel(int capacityLevel) {
		this.capacityLevel = capacityLevel;
	}
}
