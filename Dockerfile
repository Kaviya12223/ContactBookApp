FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/contactbook.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
