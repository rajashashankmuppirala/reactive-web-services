FROM openjdk:8-jdk-alpine

COPY ./wait-until-start.sh wait-until-start

RUN chmod +x wait-until-start

COPY account-service/target/account-service-0.0.1-SNAPSHOT.jar app.jar
