FROM openjdk:11-jdk
WORKDIR /app
Add . /app
RUN ./mvnw package
CMD ["java", "-jar", "target/backend-0.0.1-SNAPSHOT.jar"]
