FROM openjdk:17-jdk-slim

WORKDIR /be

ARG JAR_FILE=build/libs/be-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} ./app.jar

EXPOSE 8090
ENV SPRING_PROFILES_ACTIVE=docker

ENTRYPOINT ["java", "-jar", "./app.jar"]
