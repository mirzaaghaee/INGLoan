
# ING Loan Service Take-Home Task

Loan Request Data Structure
unique identifier
amount of the loan which must be between 500 and 12000.50 
customer full name
customer id (unique for each customer)
All of this data is required to create a loan request.

Business Requirements for Loan Request Service:
Create a loan request. The service won’t receive many of these requests.
Calculate how much money a given customer identified by customer id has applied for, i.e. if a customer has applied for three loans with amounts 1000, 2000 and 3000.25 then this operation should return 6000.25. The service will receive many of these requests.

The Service We Would Like You To Implement
A web service that provides the operations mentioned above.
The operations should be provided via a REST API.
The service should be able to run locally. 




## System Design
loan.png

+ Design Decesions and Assumptions:

        1- Form simplicity Customer-Service is not implemented as in       
        produciton-ready service CustomerId must validate with this service.

        2- It would be better to define an Abstract Loan Calss because there will be
        lots of diffrent Loans now and also probable new ones in future.
        
        3- For tunning performance a local cache has beed designed and leveraged but
        again for a production-ready service it's bettr to use distributed 
        Cache Service like Reis.
## Tech Stack



**Server:** 
SpringBoot
Local MySql 
H2 


## Used By

This project is used by the following companies:

- ING



## Authors

- [@Mirzaaghaee](https://github.com/mirzaaghaee/INGLoan)


![Logo](https://logowik.com/content/uploads/images/614_ingbank.jpg)


## Badges

Add badges from somewhere like: [shields.io](https://shields.io/)

[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](https://choosealicense.com/licenses/mit/)
[![GPLv3 License](https://img.shields.io/badge/License-GPL%20v3-yellow.svg)](https://opensource.org/licenses/)
[![AGPL License](https://img.shields.io/badge/license-AGPL-blue.svg)](http://www.gnu.org/licenses/agpl-3.0)


## API Reference

#### Register New Loan Request

curl --location 'http://localhost:8787/api/v1/loan-provider/loan' \
--header 'Content-Type: application/json' \
--data '{
  "amount": 12000.20,
  "customerId": 12345,
  "customerFullName": "John Doe"
  
}'

#### Get Sum Of Customer Loan

curl --location 'http://localhost:8787/api/v1/loan-provider/12345/sum' \
--header 'Content-Type: application/json'

## Installation

1- Config MySql in Properties File of Project:
    spring.datasource.username=user
    spring.datasource.password=password

2-Run resources:/init-db/initial-schema.sql

Caution:

    In production-Ready should environment these config are in ENV var and also
    running change script should handle by a proper Tool like liquibase.