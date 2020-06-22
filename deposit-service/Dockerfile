FROM openjdk:8-jdk-alpine

COPY ./wait-until-start.sh wait-until-start

RUN chmod +x wait-until-start

COPY deposit-service/target/deposit-service-0.0.1-SNAPSHOT.jar app.jar
