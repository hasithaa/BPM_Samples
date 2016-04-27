/**
 * PlaceOrderServiceSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.1-wso2v14  Built on : Jul 25, 2015 (11:19:54 IST)
 */
package org.wso2.samples.placeorderservice;

import java.util.Calendar;
import java.util.Date;

/**
 * PlaceOrderServiceSkeleton java skeleton for the axisService
 */
public class PlaceOrderServiceSkeleton {

	/**
	 * Auto generated method signature
	 * 
	 * @param placeOrder
	 * @return placeOrderResponse
	 */

	public PlaceOrderResponse placeOrder(PlaceOrder placeOrder) {
		PlaceOrderResponse placeOrderResponse = new PlaceOrderResponse();
		System.out.println("Confirming order :" + placeOrder.getOrderID()
				+ ", from warehouse " + placeOrder.getWarehouseLocation()
				+ ", to customer address " + placeOrder.getCustomerAddress());
		
		placeOrderResponse.setStatus("Confirmed");
		
		Date dt = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt); 
		// 7 days.
		c.add(Calendar.DATE, 7);
		dt = c.getTime();
		
		placeOrderResponse.setEstimatedShippingDate(dt);

		return placeOrderResponse;
	}

}
