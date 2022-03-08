#!/bin/bash

cd ~/opt/apache-tomcat-9.0.59/bin
./shutdown.sh
cd ~/opt/apache-tomcat-9.0.59/webapps
rm -rf room-manager-api
rm -rf room-manager-api.war
cd ~/opt/apache-tomcat-9.0.59/bin
./startup.sh