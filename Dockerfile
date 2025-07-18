# ---------- Сборка всех модулей ----------
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app
COPY . .
RUN mvn clean install -DskipTests

# ---------- Runtime для credit-application-service ----------
FROM eclipse-temurin:17-jre AS appservice
WORKDIR /app
COPY --from=builder /app/credit-application-service/target/credit-application-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]

# ---------- Runtime для credit-processing-service ----------
FROM eclipse-temurin:17-jre AS procservice
WORKDIR /app
COPY --from=builder /app/credit-processing-service/target/credit-processing-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "app.jar"]