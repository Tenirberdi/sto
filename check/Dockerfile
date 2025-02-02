FROM maven:3.9.5-eclipse-temurin-21-alpine AS build
COPY ./pom.xml /app/pom.xml
COPY ./check/pom.xml /app/check/pom.xml
COPY ./check/src /app/check/src
WORKDIR /app/check
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=build /app/check/target/*.jar app.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "app.jar"]