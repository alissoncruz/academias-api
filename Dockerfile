FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY target/ctferro-1.0-SNAPSHOT-jar-with-dependencies.jar ctferro-1.0-SNAPSHOT-jar-with-dependencies.jar

EXPOSE 8080

CMD ["java" , "-jar", "ctferro-1.0-SNAPSHOT-jar-with-dependencies.jar"]