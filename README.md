# Plant Statistics Tracker

## Overview
This project, upon completion, will leverage several custom-built and modified off-the-shelf sensors to measure and track statistics vital to the health of house plants. These include light level, light spectrum, temperature, soil moisture, and soil nutrients, as well as periodic, automatic photographs to track growth and diagnose illness. The current focus is on implementing a REST API to enable clients to perform the full spectrum of CRUD operations. This will provide scalability both in terms of the number and type of sensors that can easily write their readings to the database and the number and type of devices that can retrieve those readings to perform tailored analyses and create customized displays. It will also provide for a much wider physical distribution of nodes and, consequently, the ability to manage resources remotely.

### Notes on the current state of the project as of 6 September 2022:
#### - The light level sensor works and has been consistently submittings readings at a chosen interval of 10 minutes to a database on an Amazon EC2 instance. This separate project, accessible here (the data portion of which is password protected), does not use a REST API. However, it will soon be updated and merged with the current project.
#### - I am currently experiencing technical difficulties with the soil moisture sensor. I am using a "Flower Care" sensor purchased through SwitchDoc Labs as part of their "SmartGarden3" system. The primary difficulty at the moment is establishing a reliable connection with the Grove Wireless Extender, which (when functional) should enable the bluetooth sensor to submit readings to the EC2 database via wireless internet.
#### - I have acquired the components to build all of the other necessary sensors. I will just need time to tinker.

## REST Endpoints

### Plants

| HTTP Verb | URI                      | Request Body             | Response Body  | Purpose                             |
|-----------|--------------------------|--------------------------|----------------|-------------------------------------|
| GET       | `/api/plants `           |                          | List of plants | **List** or **collection** endpoint |
| GET       | `/api/plants/{id}`       |                          | Single plant   | **Retrieve** endpoint               |
| POST      | `/api/plants`            | JSON for a new plant     | Created plant  | **Create** endpoint                 |
| PUT       | `/api/plants/{id}`       | JSON for updating plant  | Updated plant  | **Replace** endpoint                |
| DELETE    | `/api/plants/{id}`       |                          | Boolean value  | **Delete** route                    |

### Soil Moisture Readings

| HTTP Verb | URI                                              | Request Body                              | Response Body                              | Purpose                             |
|-----------|--------------------------------------------------|-------------------------------------------|--------------------------------------------|-------------------------------------|
| GET       | `/api/readings/moisture/{plantId}`               |                                           | List of soil moisture readings by plant id | **List** or **collection** endpoint |
| POST      | `/api/readings/moisture/{plantId}`               | JSON for a new soil moisture reading      | Created soil moisture reading              | **Create** endpoint                 |
| PUT       | `/api/readings/moisture/{soilMoistureReadingId}` | JSON for updating a soil moisture reading | Updated soil moisture reading              | **Replace** endpoint                |
| DELETE    | `/api/readings/moisture/{soilMoistureReadingId}` |                                           | Boolean value                              | **Delete** route                    |

## How to Run
### The base path for this API is http://44.196.254.37:8083/EventTrackerProject

## Technologies Used
* Java
* Spring, Spring Boot
* Python
* RaspberryPi (4B)
* Flower Care soil moisture sensor
* Grove Wireless Extender

## Lessons Learned
### - Projects are a great way to grow (no pun intended). This projects requires skills beyond my current level, but a methodical approach has enabled me to rise to the challenge and, in doing so, to learn a lot very quickly.
### - The programming/developer/maker community has collecticely created extensive online guidance on how to accomplish most tasks and use most technologies. A major component of this comes by way of fora in which people present the issues they have faced and the community helps them work through it. This vast amount of readily available information has been invaluable for me, and I am grateful to have so many shoulders to stand on.
