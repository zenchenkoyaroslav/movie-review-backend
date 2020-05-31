# Movie Review (backend)
Application created for reviewing and rating films. 

Application based on REST architecture.

Movie Review is open-source project.

Demo version of project: https://movie-review-upce.herokuapp.com/

User: test 
Password: password

**Don't use your private data on demo version**

## Technologies
* Java
* Spring boot
* Liquibase
* H2 database

## Installation

1. Clone project to your PC or server
2. Go to project folder
3. Modify ***movie-review-backend/src/main/resources/application-xxx.properties*** if needed
4. Run one of next commands in terminal:

## Run

For run just backend:
 ```
spring-boot:run -P development-backend 
```
For run frontend and backend
```
spring-boot:run -P development 
```

All dependencies and libraries will download automatically.

## Test

For run UI tests on local machine:
1. Set development profile
2. Make build
3. Run tests

## Project structure
* This repository contains backend
* Repository https://github.com/zenchenkoyaroslav/movie-review-frontend contains frontend
