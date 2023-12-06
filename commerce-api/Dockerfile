FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY . .

RUN chmod +x gradlew

RUN ./gradlew build -x test

EXPOSE 8080

ENTRYPOINT ["java","-jar","build/libs/commerce-api-0.0.1-SNAPSHOT.jar"]