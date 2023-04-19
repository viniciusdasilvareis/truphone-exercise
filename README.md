
# Truphone Technical Coding Challenge - Java REST CRUD API with Spring Boot, H2 and JPA

## Steps to Setup

**1. Clone the application from git repository.**

```bash

https://github.com/viniciusdasilvareis/truphone-exercise.git

```


**2. This technical coding challenge was built with the following technologies/frameworks: Java, Spring Boot, H2 (database in memory) and JPA.**




**3. This application requires Java 17 and Maven 3.9.1 as prerequisites.**

```bash

It is important to check the Maven and Java versions using the commands:

java - version

mvn - version

It is important that maven is at version 3.9.1 and uses the correct Java version in the output of the mvn -version command.


```


**4. Build and run the app using the following commands in the root folder:**

```bash

mvn clean package


java -jar target/device-0.0.1-SNAPSHOT.jar

```

The app will start running at <http://localhost:8080>.


## Explore Rest APIs


The app defines following APIs.

    Add device - POST /api/device
    
    Get device by identifier - GET /api/device/{deviceId}
    
    List all devices - GET /api/device
    
    Uodate device - PUT /api/device/{deviceId}
    
    Delete a device - DELETE /api/device/{deviceId}

    Search device by brand - GET /api/device?brand={brandName}


## Additional Endpoints

   It is possible to monitor the application from the endpoints available through SpringBoot Actuator. With this API it will be possible to monitor the application from different perspectives.
   
   The list of endpoints can be consulted at:
   
   /actuator


