#!/bin/bash

echo "Filling all rooms..."
curl -X POST "http://localhost:8080/room-manager-api/rooms/fill"

exit 0