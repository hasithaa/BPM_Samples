package org.wso2.sample.warehouselocator;

import java.util.HashMap;
import java.util.Map;

public class WarehouseStore {

	private static Map<String, Warehouse> warehouseList = new HashMap<String, Warehouse>();
	
	public WarehouseStore() {
		populateWarehouseList();
	}
	
	public static void addWarehouse(String name, String city, String address, String postcode, int capacity){
		Warehouse defaultWarehouse = new Warehouse();
		
		defaultWarehouse.setName(name);
		defaultWarehouse.setPostCode(postcode);
		defaultWarehouse.setCity(city);
		defaultWarehouse.setCapacityLevel(capacity);
		defaultWarehouse.setAddress(address);
		
		warehouseList.put(city, defaultWarehouse);
	}
	
	public static void populateWarehouseList(){
		if (warehouseList.size() <= 0) {
			System.out.println( "Populating warehouse list");
			addWarehouse("Los Angeles Warehouse", 	"Los Angeles", 	"17 State Street", 	"10007", 10);
			addWarehouse("Chicago Warehouse", 		"Chicago", 		"17 State Street", 	"10007", 10);
			addWarehouse("Houston Warehouse", 		"Houston", 		"17 State Street", 	"10007", 10);
			addWarehouse("Philadelphia Warehouse", 	"Philadelphia", "17 State Street", 	"10007", 10);
			addWarehouse("Phoenix Warehouse", 		"Phoenix", 		"17 State Street", 	"10007", 10);
			addWarehouse("San Antonio Warehouse", 	"San Antonio", 	"17 State Street", 	"10007", 10);
			addWarehouse("San Diego Warehouse", 	"San Diego", 	"17 State Street", 	"10007", 10);
			addWarehouse("New York Warehouse", 		"New York", 	"#10, block 21", 	"10007", 10);
		}
	}
	
	public static Warehouse getWarehouse(String city) {
		return warehouseList.get(city);
	}
	
	public static boolean isExists(String city) {
		populateWarehouseList();
		if (warehouseList.get(city) == null) {
			return false;
		}
		return true;
	}
	
	
}
