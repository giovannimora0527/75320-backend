# Dockerfile para el backend Spring Boot
# Multi-stage build para optimizar el tamaño de la imagen

# Etapa 1: Build - Compilar la aplicación
FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /app

# Copiar archivos de configuración de Maven
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .

# Descargar dependencias (se cachean si no cambia el pom.xml)
RUN mvn dependency:go-offline -B

# Copiar el código fuente
COPY src ./src

# Compilar y empaquetar la aplicación
RUN mvn clean package -DskipTests

# Etapa 2: Runtime - Imagen final con solo el JAR
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Instalar wget para healthcheck y crear usuario
RUN apk add --no-cache wget && \
    addgroup -S spring && adduser -S spring -G spring

# Copiar el JAR antes de cambiar de usuario
COPY --from=build /app/target/clinica-0.0.1-SNAPSHOT.jar app.jar

# Cambiar a usuario no-root para seguridad
USER spring:spring

# Exponer el puerto de la aplicación
EXPOSE 8000

# Variables de entorno por defecto (pueden ser sobrescritas)
ENV SPRING_PROFILES_ACTIVE=prod
ENV SERVER_PORT=8000
ENV SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/clinica
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=1234

# Health check para verificar que la aplicación está funcionando
HEALTHCHECK --interval=30s --timeout=3s --start-period=40s --retries=3 \
  CMD wget --no-verbose --tries=1 --spider http://localhost:8000/clinica/v1/swagger-ui/index.html 2>/dev/null || exit 1

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]

