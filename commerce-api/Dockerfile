
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY . .

RUN chmod +x gradlew

ENV SPRING_DATASOURCE_URL=jdbc:mariadb://mariadb:3306/db_agencia

ENV SPRING_DATASOURCE_USERNAME=root

ENV SPRING_DATASOURCE_PASSWORD=root

RUN ./gradlew build -x test

EXPOSE 8080

ENTRYPOINT ["java","-jar","build/libs/commerce-api-0.0.1-SNAPSHOT.jar"]

 
