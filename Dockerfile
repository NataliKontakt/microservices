ARG MODULE_NAME=credit-application-service

FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app
COPY . .
RUN mvn clean install -DskipTests

FROM eclipse-temurin:17-jre AS appservice
WORKDIR /app
COPY --from=builder /app/credit-application-service/target/credit-application-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
ENTRYPOINT ["sh", "-c", "java -jar app.jar --server.port=8081"]

FROM eclipse-temurin:17-jre AS procservice
WORKDIR /app
COPY --from=builder /app/credit-processing-service/target/credit-processing-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8090
ENTRYPOINT ["sh", "-c", "java -jar app.jar --server.port=8090"]

# В конце — выбрать целевой образ по MODULE_NAME
FROM ${MODULE_NAME} AS final
