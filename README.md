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