echo -e "Check the initial status of the Elevator"
curl http://localhost:8080/api/status

echo -e "\n\nA user requests the elevator on floor 5"
curl -s -X POST "http://localhost:8080/api/request" \
  -H "Content-Type: application/json" \
  -H "Accept: application/json" \
  -d '{"requestFloor": 5}'

echo -e "\n\nThe user Steps in the elevator and selects floors 3 and 2"
curl -s -X POST "http://localhost:8080/api/select_floors" \
  -H "Content-Type: application/json" \
  -H "Accept: application/json" \
  -d '{"floors": [3,2]}'

echo -e "\n\nA user requests the elevator on floor 2"
curl -s -X POST "http://localhost:8080/api/request" \
  -H "Content-Type: application/json" \
  -H "Accept: application/json" \
  -d '{"requestFloor": 5}'

 echo -e "\n\nThe user Steps in the elevator and selects floors 7"
 curl -s -X POST "http://localhost:8080/api/select_floors" \
   -H "Content-Type: application/json" \
   -H "Accept: application/json" \
   -d '{"floors": [7]}'