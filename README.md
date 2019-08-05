## Overview 
This is a sample application created as part of a recruitment process. 

## Goals
* Construct a full-stack application that allows a company to see their customer information.
* A user is able to view the list of customers.
* Filter the customers based on status or name.
* Sort the customers by status and name.
* Add/Update notes for a customer.
* View customer details with the notes.

## Modules

This project consists of two modules -

## Frontend (demoappclient)
Frontend is developed using React JS. The frontend app with its `README.md` can be found [here](demoappclient/).

## Backend (demoapp)
This is a Spring Boot Application with REST APIs for customers and notes.
The embedded H2 database is used to store the data. I could have chosen some any other relational or non-relational database if the requirements were vast or required performance. 

The backend app with its `README.md` can be found [here](demoapp/)

## Hosted Application 
This application has been hosted on ec2. Please visit [here](http://ec2-3-106-139-242.ap-southeast-2.compute.amazonaws.com:3000/) to view the application.

## Swagger 
The API definition can be found on [here] (swagger/customer_swagger.yaml/)

## Postman Collection
Postman collection along with the environment configuration files can be imported from [here] (postman/)

## Data
* Initial data has been loaded to the application using [data.sql](https://github.com/bhumika2407/demo-customer-application/blob/master/demoapp/src/main/resources/data.sql). 
* Create customer end point has been provided in Postman application to create more customers.

## Improvements 
As this is just a small project, I have just focused on the MVP, I feel following things could be added or improved -

* Security needs to be enhanced by enabling CORS, OAuth, HTTPs etc. 
* Validation on both UI and API level.
* Pagniation on API Response and handle it on UI.
* Dockerize it.
* Test coverage can be improved.
* Can make it serverless.
* Versioning of REST API.
