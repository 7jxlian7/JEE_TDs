#!/bin/bash

if test -z "$*" 
then
     echo "Error : Missing parameters"
     echo "Usage : : ./create_rooms.sh <Number of rooms to create>"
     exit 1
fi

[[ -z "$1" ]] && { echo "Error : Please specify a number a rooms to create" ; exit 1; }

if [[ ! -f "lastRoomId.txt" ]]
then
    echo "" > lastRoomId.txt
fi

input="lastRoomId.txt"

while IFS= read -r line
do
  lastId=$line
done < "$input"

if [ -z ${lastId} ]; then lastId=0; fi

nbSalles=$1
max=10
i=0

if (($nbSalles > $max)); then
    nbSalles=10
fi

while ((nbSalles != i))
do
    i=$((i+1))
    numSalle=$((i+100))
    numSalle=$(($numSalle+$lastId))
    echo "Creating room number ${numSalle}..."
    curl -X POST \
    -H "Accept: application/json" \
    -H "Content-Type: application/json" \
    -d "{\"name\":\"Salle ${numSalle}\",\"description\":\"Petite description de la salle...\"}" \
    "http://localhost:8080/room-manager-api/rooms"
done

echo "$(($numSalle-100))" > lastRoomId.txt

exit 0