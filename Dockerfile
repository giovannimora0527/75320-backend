# Dockerfile para Backend Spring Boot
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copiar el archivo JAR
COPY target/clinica-*.jar app.jar

# Exponer el puerto
EXPOSE 8000

# Variables de entorno
ENV SPRING_PROFILES_ACTIVE=prod
ENV SERVER_PORT=8000

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]

