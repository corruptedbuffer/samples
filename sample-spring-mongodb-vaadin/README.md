# sample-spring-mongodb-vaadin

## Overview

A simple Spring project that demonstrates CRUD operations for a simple Employee entity by means of Spring Boot, String Data (mongodb) and Vaadin.

We are using default configurations for mongodb. They can be modified in /resources/application.properties

```
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.uri=mongodb://localhost/test
spring.data.mongodb.database=test
spring.data.mongodb.authentication-database=
spring.data.mongodb.grid-fs-database=
spring.data.mongodb.username=
spring.data.mongodb.password=
spring.data.mongodb.repositories.enabled=true
```

## How do I run it?

### STS

* Clone prject
* Import it into Spring STS
* Run the application as Spring Boot Application.
* Navigate to localhost:8080/

### Maven

```
git clone https://github.com/javi-more-garc/spring-samples/sample-spring-mongodb-vaadin.git
cd sample-spring-mongodb-vaadin
mvn spring-boot:run
```

## Disclaimer

Using https://github.com/mstahv/spring-data-vaadin-crud
