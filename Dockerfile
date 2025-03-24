
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/*.jar /app/app.jar
CMD ["java", "-jar", "-Dspring.profiles.active=docker", "/app/app.jar"]
