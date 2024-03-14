FROM openjdk

WORKDIR /app

COPY target/ctferro-1.0.jar /app/ctferro.jar

ENTRYPOINT ["java" , "-jar", "ctferro.jar"]