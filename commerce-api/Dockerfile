FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY . .

#ENV SPRING_DATASOURCE_URL=jdbc:mariadb://mariadb:3306/db_fabiano

RUN chmod +x gradlew
RUN ./gradlew build -x test

EXPOSE 8080

ENTRYPOINT ["java","-jar","build/libs/commerce-api-0.0.1-SNAPSHOT.jar"]