FROM openjdk:8-jdk-alpine
LABEL maintainer="alesia.pavliuchenkova2@gmail.com"
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/task-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} task.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/task.jar"]