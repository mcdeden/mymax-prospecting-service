FROM openjdk:8-jdk-alpine
MAINTAINER mcdeden <mcdeden@gmail.com>
VOLUME /tmp
EXPOSE 8111
ADD target/*.jar Prospecting.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/Prospecting.jar"]