#!/bin/bash

if test -z "$*" 
then
     echo "Error : Missing parameters"
     echo "Usage : : ./create_accessEvent.sh <Room ID> <Username> <IN/OUT>"
     exit 1
fi

[[ -z "$1" ]] && { echo "Error : Room ID cannot be empty" ; exit 1; }
[[ -z "$2" ]] && { echo "Error : Username cannot be empty" ; exit 1; }
[[ -z "$3" ]] && { echo "Error : Type cannot be empty" ; exit 1; }

roomId=$1
username=$2
type=$3

if [ "$type" = "IN" ]; then
    echo "${username} in entering room ${roomId}..."
else
    echo "${username} in leaving room ${roomId}..."
fi

curl -X POST \
-H "Accept: application/json" \
-H "Content-Type: application/json" \
-d "{\"userName\":\"${username}\",\"type\":\"${type}\"}" \
"http://localhost:8080/room-manager-api/rooms/${roomId}/events"

exit 0