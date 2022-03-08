#!/bin/bash

echo "Deleting all rooms..."

curl -X DELETE "http://localhost:8080/room-manager-api/rooms"

echo "" > lastRoomId.txt

exit 0