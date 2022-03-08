# Room API

Maven & Tomcat must be installed on your computer.

## How to use ?  

Run
```
mvn package
```  

Then start Tomcat and deploy the ```/target/room-manager-api.war```.  

Go to http://localhost:8080/room-manager-api/rooms.  

## Description  

Room API that allows to :

- **create** rooms
- **delete** rooms
- **create** access events
- **delete** access events
- **list** rooms
- **list** access events
- **search** rooms
  
There are script in ```/bin``` folder.
  
You can use them as a dataset to test the API.
  
To get help on bash scripts, simply run ```./script_name.sh``` without any arguments, it will shows you the arguments to put in.
  
  
Created by Julian, on 08/12/2022