FROM openjdk:16-jdk-alpine3.12
WORKDIR /musicstore
COPY musicstore.jar .
CMD ["java","-jar","musicstore.jar"]