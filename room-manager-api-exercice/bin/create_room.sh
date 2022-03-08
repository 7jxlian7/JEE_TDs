#!/bin/bash

if test -z "$*" 
then
     echo "Error : Missing parameters"
     echo "Usage : : ./create_room.sh <Room's name>"
     exit 1
fi

[[ -z "$1" ]] && { echo "Error : Room name cannot be empty" ; exit 1; }

name=$1
desc=$2

echo "Creating room ${name}..."
curl -X POST \
-H "Accept: application/json" \
-H "Content-Type: application/json" \
-d "{\"name\":\"${name}\",\"description\":\"${desc}\"}" \
"http://localhost:8080/room-manager-api/rooms"

exit 0