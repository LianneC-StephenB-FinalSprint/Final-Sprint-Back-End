Flight Management System

Overview
This project is a Flight Management System that includes a RESTful API server built with Spring Boot, connecting to a MySQL database, and a client application that communicates with the API using HTTP requests. The application allows you to manage and query flight data, including airports, aircraft, cities, and passengers.

Setup Instructions

Prerequisites
- Java 17 or newer
- Maven (for building the project)
- MySQL (for database setup)
- Postman or similar tool (optional, for API testing)

Database Setup

1. Open MySQL Workbench or a command-line interface to connect to your MySQL server.

2. Create a new database and user
   
3. Update your MySQL settings in the API Server's application.properties file.

API Server Setup

1. Clone the repository
   
2. Navigate to the API Server
   
3. Configure Database Connection in application.properties
   
4. Build and run the server.

Client Setup

1. Navigate to the Client Directory

2. Build the client

3. Run the client

Usage

Running the API Server
- Start server using mvn spring-boot:run in the API server directory
- Use Postman to test endpoints manually

Running the Client
- Start the client using the Java command shown above. This will open a CLI interface that prompts you to select operations for different entities

Command Line Interface

The client's CLI provides commands for managing and querying data:
- "View Airports in Cities": Lists all airports in specific cities
  
- "List Aircraft for Passengers": Shows all aircraft a passenger has traveled on.
  
- "View Airports for Aircraft Operations": Lists airports an aircraft can take off from or land at.
  
- "Check Airports Used by Passengers:: Lists airports that passengers have used.
  
The client provides options for creating, updating, retrieving, and deleting data from the server.

Testing

Testing with Postman
1. Import the API endpoints into Postman using the base URL http://localhost:8080
2. Test CRUD operations for each entity (Aircraft, Passenger, City, Airport).

Unit Testing
- Server Tests: Run unit tests for the API server using Maven: mvn test
- Client Tests: For the client application, mock HTTP responses and enable GitHub Actions to run tests automatically.

Mock HTTP Responses for Client Testing

To fully test the client, use mocked HTTP responses in JUnit tests to simulate API server responses. This ensures the client logic works independently of the actual server.



API Documentation

Overview
This API allows you to interact with data related to airports, aircraft, cities, and passengers. The endpoints provide CRUD operations for each of these entities, along with custom queries for specific questions.

Endpoints
1. Airports
Get All Airports
   Endpoint: GET /airports
   Description: Retrieve a list of all airports.

Get Airport ID
   Endpoint: GET /airports/{id}
   Description: Retrieve a specific airport by its ID

Create Airport
   Endpoint: POST /airports
   Description: Add a new airport to the database

Update Airport
   Endpoint: PUT /airports/{id}
   Description: Update the details of an existing airport.

Delete Airport
   Endpoint: DELETE /airports/{id}
   Description: Delete a specific airport by its ID.


2. Aircraft
Get All Aircraft
   Endpoint: GET /aircraft
   Description: Retrieve a list of all aircraft.

Get Aircraft by ID
   Endpoint: GET /aircraft/{id}
   Description: Retrieve a specific aircraft by its ID.

3. Passengers
Get All Passengers
   Endpoint: GET /passengers
   Description: Retrieve a list of all passengers.

Get Passenger by ID
   Endpoint: GET /passengers/{id}
   Description: Retrieve a specific passenger by their ID.

4. Cities
Get All Cities
   Endpoint: GET /cities
   Description: Retrieve a list of all cities.

Get City by ID
   Endpoint: GET /cities/{id}
   Description: Retrieve a specific city by its ID


Custom Queries

What airports are in what cities?
   Endpoint: GET /custom/airportsByCity
   Description: Retrieve airports grouped by their respective cities

List all aircraft passengers have traveled on
   Endpoint: GET /custom/aircraftByPassenger
   Description: Retrieve a list of aircraft associated with each passenger.

Which airports can aircraft take off from and land at?
   Endpoint: GET /custom/airportsForAircraft
   Description: Retrieve airports where each aircraft can operate.

What airports have passengers used?
   Endpoint: GET /custom/airportsByPassenger
   Description: Retrieve airports used by each passenger.


Error Codes
400 Bad Request: Invalid input or malformed request.
404 Not Found: Resource not found (e.g., ID does not exist).
500 Internal Server Error: Unexpected server error.
