# Elevator Simulator

Application to simulate an elevator state machine.

## Requirements

The Elevator

1. Provide code that simulates an elevator. You may use any language (recommend using Java or Python).
2. Please upload your code Git Hub for a discussion during your interview with our team.
3. Additionally, document all assumptions and any features that weren't implemented.
4. Please be prepared to discuss the assumptions and features that were not implemented during your interview!

## Running the application

### Build with Docker

Run the following command at the root directory of the repository to build the docker compose:
```docker compose build```

### Run with Docker

Run the following command at the root directory of the repository to run the docker compose:
```docker compose up -d```

This will bring up two containers - one for the spring boot application and one for a test client shell script of the
application

### Build with Maven and run from jar file

Install the dependencies
```./mvnw clean install```

Run the application
```java -jar target/elevator-0.0.1-SNAPSHOT.jar```

**You can now use the API to interact with the application**

Check the status of the elevator:
```curl http://localhost:8080/api/status```

Request the elevator to go to floor 5:

```
curl -s -X POST "http://localhost:8080/api/request" \
  -H "Content-Type: application/json" \
  -H "Accept: application/json" \
  -d '{"requestFloor": 5}'
```

Request the elevator to go to floors 3 and 2:

```
curl -s -X POST "http://localhost:8080/api/select_floors" \
  -H "Content-Type: application/json" \
  -H "Accept: application/json" \
  -d '{"floors": [3,2]}'
```

# Assumptions and Additional Features

## Assumptions

There is only one elevator for now - Singleton
There are 10 floors - this could be changed to be configurable
There are no time delays for door opening or closing, or time delays for the elevator to move to a floor
Currently, the elevator will move to the requested floor and won't stop for elevator requests along the way

## Additional Features

Make the elevator a runnable and poll for requests added to a queue
once a request is added to the queue, the elevator should process that request in the order it is received
if the elevator moving and it passes a floor where there is a request it should stop and let the user at that floor in
