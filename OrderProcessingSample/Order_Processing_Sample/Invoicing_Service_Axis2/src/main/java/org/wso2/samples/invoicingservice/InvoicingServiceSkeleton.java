/**
 * InvoicingServiceSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.1-wso2v14  Built on : Jul 25, 2015 (11:19:54 IST)
 */
package org.wso2.samples.invoicingservice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import org.wso2.samples.orderprocessingprocess.ItemType;
import org.wso2.samples.orderprocessingprocess.ItemsType;

/**
 * InvoicingServiceSkeleton java skeleton for the axisService
 */
public class InvoicingServiceSkeleton {

	/**
	 * Auto generated method signature
	 * 
	 * @param invoice
	 * @return invoiceResponse
	 */

	public InvoiceResponse invoice(Invoice invoice) {
		InvoiceResponse invoiceResponse = new InvoiceResponse();
		invoiceResponse.setOrderID(UUID.randomUUID().toString());
		invoiceResponse.setTotalAmount("");

		long totalAmount = 0;
		if (invoice != null && invoice.getInvoice() != null
				&& invoice.getInvoice().getItems() != null
				&& invoice.getInvoice().getItems().getItem() != null) {
			ItemType[] items = invoice.getInvoice().getItems().getItem();
			for (ItemType item : items) {
				totalAmount += calculatePrice(item);
			}
		}
		invoiceResponse.setTotalAmount(String.valueOf(totalAmount));
		return invoiceResponse;
	}

	private long calculatePrice(ItemType item) {
		long itemID = 0;
		long price = 0;
		try {
			itemID = Long.parseLong(item.getItemID());
			if (itemID > 0 && itemID <= 1000) {
				price = 10;
			} else if (itemID > 1000 && itemID <= 5000) {
				price = 50;
			} else if (itemID > 5000 && itemID <= 10000) {
				price = 75;
			} else if (itemID > 10000) {
				price = 100;
			} else {
				price = 0;
			}
		} catch (Exception e) {
			// Nothing to do. Consider as invalid item ID
		}
		return price * item.getQuantity();
	}
}
