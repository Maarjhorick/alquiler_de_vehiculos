# --- Etapa 1: Construcción ---
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copiamos primero solo el pom.xml para aprovechar la cache de dependencias
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiamos el resto del código y compilamos
COPY src ./src
RUN mvn clean package -DskipTests -B

# --- Etapa 2: Imagen final, liviana ---
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

# Render define su propio $PORT; la app ya está configurada para respetarlo
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]