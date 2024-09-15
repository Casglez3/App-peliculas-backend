FROM maven:latest
COPY . .
RUN mvn clean package -DskipTests

# Usar una imagen base de Java 17
FROM openjdk:17-jdk-slim

# Crear un directorio para la aplicación
WORKDIR /app

# Copiar el archivo JAR generado por Maven al contenedor
COPY target/peliculas-0.0.1-SNAPSHOT.jar /app/peliculas-0.0.1-SNAPSHOT.jar

# Exponer el puerto en el que Spring Boot se ejecuta
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/peliculas-0.0.1-SNAPSHOT.jar"]
