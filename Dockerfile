FROM openjdk:8-jdk-alpine

# Define o diretório de trabalho no contexto do Docker
WORKDIR /app

# Copia o arquivo JAR da aplicação para o diretório de trabalho no contêiner
COPY commerce-api/target/tu-aplicacion-spring-boot.jar app.jar

# Expõe a porta 8080 (certifique-se de que sua aplicação Spring Boot esteja configurada para escutar esta porta)
EXPOSE 8080

# Comando para executar a aplicação quando o contêiner é iniciado
CMD ["java", "-jar", "app.jar"]
