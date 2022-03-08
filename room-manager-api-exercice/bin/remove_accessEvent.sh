#!/bin/bash

if test -z "$*" 
then
     echo "Error : Missing parameters"
     echo "Usage : : ./remove_accessEvents.sh <Room ID> <AccessEvent ID>"
     exit 1
fi

[[ -z "$1" ]] && { echo "Error : Room ID cannot be empty" ; exit 1; }
[[ -z "$2" ]] && { echo "Error : AccessEvent ID cannot be empty" ; exit 1; }

roomId=$1
aeId=$2

echo "Deleting access event ${aeId} on room ${roomId}"

curl -X DELETE "http://localhost:8080/room-manager-api/rooms/${roomId}/events/${aeId}"

exit 0