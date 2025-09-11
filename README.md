# Elevator Simulator

Application to simulate an elevator state machine.

## Usage

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

### You can now use the API to interact with the application

Check the status of the elevator:
```curl http://localhost:8080/api/status```

Request the elevator to go to floor 5:

```
curl -s -X POST "http://localhost:8080/api/request" \
  -H "Content-Type: application/json" \
  -H "Accept: application/json" \
  -d '{"requestFloor": 5}'
```

```
curl -s -X POST "http://localhost:8080/api/select_floors" \
  -H "Content-Type: application/json" \
  -H "Accept: application/json" \
  -d '{"floors": [3,2]}'
```

Request floor 3 from inside the elevator: curl -X POST "http://localhost:8080/api/elevator/request?floor=3"