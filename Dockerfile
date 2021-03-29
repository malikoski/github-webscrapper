FROM maven:3.6.3-openjdk-15-slim AS build

ADD . /github-webscrapper
WORKDIR /github-webscrapper

RUN ls -l

RUN mvn clean install

FROM openjdk:15.0.2-jdk-slim

VOLUME /tmp

COPY --from=build "/github-webscrapper/target/github-webscrapper*.jar" app.jar
CMD [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]