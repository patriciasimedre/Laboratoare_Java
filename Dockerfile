# Folosim o imagine OpenJDK pentru a rula aplicația
FROM openjdk:17-jdk-slim

# Setăm directorul de lucru în container
WORKDIR /app

# Copiem fișierul JAR generat în container
COPY target/Laborator11_Java-0.0.1-SNAPSHOT.jar app.jar

# Expunem portul pe care rulează aplicația
EXPOSE 8080

# Comanda de rulare a aplicației
ENTRYPOINT ["java", "-jar", "app.jar"]
