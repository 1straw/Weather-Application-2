# 1. Basimage med Java 21
FROM openjdk:21-jdk

# 2. Skapa arbetskatalog
WORKDIR /app

# 3. Kopiera byggd JAR-fil från target
COPY target/WeatherAPI-0.0.1-SNAPSHOT.jar app.jar

# 4. Exponera port som Spring Boot använder
EXPOSE 8080

# 5. Starta applikationen
ENTRYPOINT ["java","-jar","app.jar"]
