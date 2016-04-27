# Order Processing Sample

This sample illustrates usage of WS-BPEL 2.0, WS-HumanTask 1.1 and Rule capabilities in WSO2 Business Process Server and WSO2 Business Rule Server. 

## Introduction 

![alt text](https://raw.githubusercontent.com/hasithaa/BPM_Samples/master/OrderProcessingSample/FlowChart.png "Order Processing Flow Chart")

* The client place an order by providing client ID, item IDs, quantity, shipping address and shipping city.
* Then Process submits order information to invoicing web service, which generates order ID and calculate total order value.
* If total order value is greater than USD 500, process requires a human interaction to proceed. When a order requires a human interaction process creates a Review HumanTask for regional clerks. If review task is rejected by one of regional clerk user, workflow terminates after notifying the client.
* Once the regional clerk approve the review task, workflow invokes Warehouse Locater rule service to calculate nearest warehouse.
* Once receiving nearest warehouse, process invokes Place Order web service to finalize the order. 
* Finally user will be notified with the estimated delivery date. 
  
This sample contains

* Workflow :[Order_Processing_Process](https://github.com/hasithaa/BPM_Samples/tree/master/OrderProcessingSample/Order_Processing_Sample/Order_Processing_Process) is written using WS-BPEL 2.0.
* Human task & notification : [Order_Processing_Approval_HumanTask](https://github.com/hasithaa/BPM_Samples/tree/master/OrderProcessingSample/Order_Processing_Sample/Order_Processing_Approval_HumanTask) is defined using WS-HumanTask 1.1.
* Rules service : [Warehouse_Locator_Service](https://github.com/hasithaa/BPM_Samples/tree/master/OrderProcessingSample/Order_Processing_Sample/Warehouse_Locator_Service) is written using Drools. 
* Web Service : [Invoicing_Service_Axis2](https://github.com/hasithaa/BPM_Samples/tree/master/OrderProcessingSample/Order_Processing_Sample/Invoicing_Service_Axis2) and [Place_Order_Axis2](https://github.com/hasithaa/BPM_Samples/tree/master/OrderProcessingSample/Order_Processing_Sample/Place_Order_Axis2) are written using Axis2.

## Products used 

* WSO2 Business Process Server 3.5.1 (WS-BPEL 2.0, WS-HumanTask 1.1, BPMN 2.0 runtime.)
* WSO2 Business Rule Server 2.2.0 (Rule engine )
* WSO2 Developer studio 3.8.0 - Developer tool

## Building Artifacts 

1. Clone this repo and change directory to `OrderProcessingSample/Order_Processing_Sample/`
2. Build `Order_Processing_Sample` using maven. ```mvn clean install```
3. Built artifacts will be copied `Artifacts/target`
  * target/bps/bpel
    * Order_Processing_Process-1.0.0.zip
  * target/bps/humantasks
    * Order_Processing_Approval_HumanTask.zip
  * target/bps/axis2services
    * Invoicing_Service_Axis2-1.0.0.aar
    * Place_Order_Axis2-1.0.0.aar
  * target/brs/ruleservices
    * Warehouse_Locator_Service-1.0.0.aar

## Setting up environments. 

### Setting up WSO2 BPS 3.5.1

* Start WSO2 Business Process Server 3.5.1 ```./wso2server.sh```
* Copy all folders in `Artifacts/target/bps/` to `<BPS_HOME>/repository/deployment/server/`
* Create following roles and users

role | users | permissions 
--- | --- | --- 
customers | user1, user2 | Login, HumanTask 
regionalClerksRole | clerk1, clerk2 | Login, HumanTask 
regionalManagerRole | manager1, manager3 | Login, Admin 

### Setting up WSO2 BRS 2.2.0

* Start WSO2 Business Rule Server 2.2.0 with 100 port offset. ```./wso2server.sh -DportOffset=100```
* Copy the folder in `Artifacts/target/brs/` to `<BRS_HOME>/repository/deployment/server/`


## Testing Sample

* Invoke OrderProcessingProcess web service [http://localhost:9763/services/OrderProcessingProcess](http://localhost:9763/services/OrderProcessingProcess) using following request. 

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ord="http://www.wso2.org/samples/OrderProcessingProcess/">
   <soapenv:Header/>
   <soapenv:Body>
      <ord:process>
         <ord:customerID>user1</ord:customerID>
         <ord:items>
            <ord:item>
               <ord:itemID>100</ord:itemID>
               <ord:quantity>2</ord:quantity>
            </ord:item>
            <ord:item>
               <ord:itemID>2001</ord:itemID>
               <ord:quantity>3</ord:quantity>
            </ord:item>
            <ord:item>
               <ord:itemID>6002</ord:itemID>
               <ord:quantity>2</ord:quantity>
            </ord:item>
            <ord:item>
               <ord:itemID>10500</ord:itemID>
               <ord:quantity>4</ord:quantity>
            </ord:item>
         </ord:items>
         <ord:shippingAddress>No 500, Main street, New York</ord:shippingAddress>
         <ord:city>New York</ord:city>
      </ord:process>
   </soapenv:Body>
</soapenv:Envelope>
 ```
* This request generates total value, which is grater than USD 500. Hence this will a required manual user approval. 

  > Note !
  > Total value calculation logic can be found from [here](https://github.com/hasithaa/BPM_Samples/blob/master/OrderProcessingSample/Order_Processing_Sample/Invoicing_Service_Axis2/src/main/java/org/wso2/samples/invoicingservice/InvoicingServiceSkeleton.java). 

* Process generates a notification for user1 by including status of the order. After that process creates a humantask for regional clerk roles.

 > user1 can view the notification under `Notification` tab in [humantask-explorer](https://localhost:9443/humantask-explorer/)
 > clker1 user can view `Order Approval task` under `Claimable Task` tab in [humantask-explorer](https://localhost:9443/humantask-explorer/)

* Once Clerk 1 user `Approve` or `Reject` the `Order Approval Task` process continues its execution. 

## Importing sample to WSO2 Developer studio

* Open an eclipse workspace.
* Select `File` -> `Import` -> `Existing Projects into Workspace` 
* Select `Order_Processing_Sample` directory as root directory.
* Select `Search for nested projects`
* Click Finish.
 


