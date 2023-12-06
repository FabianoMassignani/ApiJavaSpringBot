FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY . .

RUN chmod +x gradlew

RUN ./gradlew build -x test

EXPOSE 8080

ENTRYPOINT ["java","-jar","build/libs/commerce-api-0.0.1-SNAPSHOT.jar"]


# #!/bin/bash

# cd commerce-api

# # Execute os comandos Docker
# docker build -t apijava:tag -f Dockerfile .
# docker run -d -p 8080:8080 apijava:tag