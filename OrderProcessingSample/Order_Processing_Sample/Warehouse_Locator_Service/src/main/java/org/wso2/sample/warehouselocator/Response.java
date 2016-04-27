package org.wso2.sample.warehouselocator;

public class Response {
	private Warehouse warehouse;

	public Response() {	
	}
	
	public Response(Warehouse warehouseInfo) {
		warehouse = warehouseInfo;
	}
	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}
}
