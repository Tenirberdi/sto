FROM maven:3.9.5-eclipse-temurin-21-alpine AS build
COPY ./pom.xml /app/pom.xml
COPY ./bid/pom.xml /app/bid/pom.xml
COPY ./bid/src /app/bid/src
WORKDIR /app/bid
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=build /app/bid/target/*.jar app.jar
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "app.jar"]