FROM maven:3.8.6-eclipse-temurin-17 AS build

WORKDIR /app

COPY . .

ARG MODULE_NAME

RUN mvn clean package -DskipTests -pl $MODULE_NAME

FROM eclipse-temurin:17-jre

WORKDIR /app

ARG MODULE_NAME

COPY --from=build /app/$MODULE_NAME/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
