FROM openjdk:8-jdk-alpine

WORKDIR "/home"

COPY . .

RUN apk add maven

RUN mvn clean package

WORKDIR "/home/demo"

EXPOSE 8080

CMD [ "java", "-jar", "/home/demo/target/demo-0.0.1-SNAPSHOT.jar"]
