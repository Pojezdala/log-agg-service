# log-agg-service

**Tech**

- [Maven] 
- [Docker]
- [Spring Boot] 
- [REST] 


**Building for source**

Set the /path to your project home directory.


**Maven**

Create library

```sh
mvn package
```

Run application with default port 8080

```sh
mvn spring-boot:run
```

Run application with 'rate' parameter (The number of login records to be generated.)

```sh
mvn spring-boot:run -Dspring-boot.run.arguments=--rate=5
```


**Docker**


Build docker image

```sh
docker build -f Dockerfile -t log-agg-service .
```

Run application with port 8080

```sh
docker run -p 8080:8080 log-agg-service
```

Run application with port 8080 and 'rate' parameter (The number of login records to be generated.)

```sh
docker run -p 8080:8080 log-agg-service --rate=5
```

**Docker-compose**


Run application with port 8080

```sh
docker-compose run -p 8080:8080 log-agg-service
```

Run application with port 8080 and 'rate' parameter (The number of login records to be generated.)

```sh
docker-compose run -p 8080:8080 log-agg-service --rate=5
```

**Test Application**

To test the application, you can use any rest client.

The call specification is in the 'ApplicationController.yaml' and file is located: /src/main/resources/rest

A test json collection for postman is located: /src/test/rest
