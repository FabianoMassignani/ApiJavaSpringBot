FROM openjdk:8-jdk-alpine

WORKDIR /app

COPY target/tu-aplicacion-spring-boot.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
