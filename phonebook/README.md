Phonebook : Rest Application to manage mobile numbers.

Implemented with :

Java 11
Spring Boot
Spring Data JPA

Database Used : Embedded DB HSQLDB
Note : Each time the application is started db is refreshed.
Please change the property spring.jpa.hibernate.ddl-auto from create to update in application.properties to suppress refresh.

With this application, one can use Rest APIs in order to create/edit/delete and search mobile numbers.

Mobile Schema :

Id -> Primary key which is auto generated. Need not be provided during creation.
Msisdn -> The mobile number in E164 format, ex. 35699123456. Only E164 formatted phone numbers will be accepted.
This field is mandatory and there can't be duplicates in the database.
Owner -> Owner of the mobile number, Mandatory field.
User -> User of the mobile number, Mandatory field.
Type -> Can be either MOBILE_PREPAID or MOBILE_POSTPAID. Mandatory field
ServiceStartDate -> Time in the form of unix millis. Mandatory field.

Customer Schema :

Id -> Id field.
Firstname -> Firstname of the customer. Mandatory field
Lastname -> Lastname of the customer. Mandatory field
Emailid -> Emailid of the customer. Unique, Mandatory field

Swagger OAS3 Definition of APIS : http://localhost:8080/swagger-ui/index.html

Mobile APIS :

GET,POST
/api/v1/mobiles

GET,PUT,DELETE
​/api​/v1​/mobiles​/{id}

Search apis available for mobile schema :

GET
/api/v1/mobiles/msisdn/{msisdn}
/api/v1/mobiles/owner/{ownerId}
/api/v1/mobiles/user/{userId}
/api/v1/mobiles/servicetype/{servicetype}
/api/v1/mobiles/owneridanduserid
/api/v1/mobiles/useridandservicetype

Example Post Payload:

    {   
	    "msisdn": "91965464346",
        "serviceStartDate": "16377733342345",
        "owner": 8594,
        "type": "MOBILE_POSTPAID",
        "user": 8593
    }


Examples of Search : 
http://localhost:8080/api/v1/mobiles/servicetype/MOBILE_POSTPAID
http://localhost:8080/api/v1/mobiles/useridandservicetype?userid=8593&servicetype=MOBILE_POSTPAID
http://localhost:8080/api/v1/mobiles/owneridanduserid?ownerid=8594&userid=8593


Customer APIS :

GET,POST
/api/v1/customers

GET,PUT,DELETE
/api/v1/customers/{id}

Search apis available for customer schema :

GET
/api/v1/customers/lastname/{lastName}
/api/v1/customers/email/{emailId}

Example Payload :

    {
        "firstName": "Daniel",
        "lastName": "James",
        "emailId": "djames@gmail.com"
    }

Examples of Search :
http://localhost:8080/api/v1/customers?lastName=James
http://localhost:8080/api/v1/customers/email/djames@gmail.com

