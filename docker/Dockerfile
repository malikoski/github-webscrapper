FROM maven:3.6.3-openjdk-15-slim AS build

COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package

FROM openjdk:15.0.2-jdk-slim

COPY --from=build /usr/src/app/target/github-webscrapper-1.0.0.jar /usr/app/github-webscrapper-1.0.0.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/github-webscrapper-1.0.0.jar"]