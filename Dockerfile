FROM openjdk:8
ADD target/log-agg-service-0.0.1.jar log-agg-service-0.0.1.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","log-agg-service-0.0.1.jar"]