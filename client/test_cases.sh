#!/bin/sh

echo "Wating for Elevator Application start..."
while ! curl -s http://elevator:8080/api/status > /dev/null; do
    sleep 5
done

echo -e "Check the initial status of the Elevator"
curl http://elevator:8080/api/status

echo -e "\n\nA user requests the elevator on floor 5"
curl -s -X POST "http://elevator:8080/api/request" \
  -H "Content-Type: application/json" \
  -H "Accept: application/json" \
  -d '{"requestFloor": 5}'

sleep 18s

echo -e "\n\nThe user Steps in the elevator and selects floors 3 and 2"
curl -s -X POST "http://elevator:8080/api/select_floors" \
  -H "Content-Type: application/json" \
  -H "Accept: application/json" \
  -d '{"floors": [3,2]}'

sleep 16s

echo -e "\n\nA user requests the elevator on floor 1"
curl -s -X POST "http://elevator:8080/api/request" \
  -H "Content-Type: application/json" \
  -H "Accept: application/json" \
  -d '{"requestFloor": 1}'

sleep 18s

 echo -e "\n\nThe user Steps in the elevator and selects floors 7"
 curl -s -X POST "http://elevator:8080/api/select_floors" \
   -H "Content-Type: application/json" \
   -H "Accept: application/json" \
   -d '{"floors": [7]}'