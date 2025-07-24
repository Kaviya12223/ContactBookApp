
FROM eclipse-temurin:21-jdk-jammy
COPY target/contactbook.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
