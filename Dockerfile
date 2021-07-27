FROM openjdk:8
ADD target/service-0.0.1.jar service-0.0.1.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar","service-0.0.1.jar"]