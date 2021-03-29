FROM openjdk:15.0.2-jdk-slim

COPY target/github-webscrapper*.jar github-webscrapper-1.0.0.jar
ENTRYPOINT ["java","-jar","/github-webscrapper-1.0.0.jar"]