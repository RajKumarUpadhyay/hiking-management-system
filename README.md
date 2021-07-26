# hiking management service
Hiking management service has designed to book the available trails. This service has many capabilities
like booking trail, cancel booking, find all available trails and many more.

# Project structure
* Backend - Spring boot framework used to build the backend service and for content deliver used inbuilt tomcat apache
  server which us running on default port 8080. It's a containerized service which can be deployed in any
  env like Kubernetes, docker swarm etc.
* Database - Use h2 memory database used for this project

Note: Other databases like postgres or mongodb can be used. 

# How to use this?
- User has need to clone this repo at host machine. Navigate to project folder and execute below commands.
  mvn clean install
  docker build -t hiking-management-service .
  docker run --name hiking-management-service -d  hiking-management-service:latest

# Features
   - This service has provided below listed operation to manage the hiking management. Below is the list of service 
     and corresponding the definition.

|  HTTP METHOD | END POINT   |  DESCRIPTION |
|---|---|---|
|  POST | http://localhost:8080/api/v1/hiking  | User can book any available trails using this endpoint.This endpoint will accept BookingRequest as input payload|
|  GET | http://localhost:8080/api/v1/hiking/booking/{id}  | This endpoint will search the booking for given id if available |
|  GET | http://localhost:8080/api/v1/hiking  | This endpoint will return a list of all available Trails. |
|  GET | http://localhost:8080/api/v1/hiking/{id}  | This endpoint will return specific trail by given trail id. |
|  DELETE | http://localhost:8080/api/v1/hiking/{id}  | This endpoint will cancel/delete booking if booking is present in db. |
|  GET | http://127.0.01:9001/actuator/health  | This will provide service heath status. |

# Swagger documentation:
User can open below link for more detail information about services and request parameters.
http://localhost:8080/swagger-ui.htm

### Maintainer
Raj K Upadhyay

