FROM maven:3.9.5-eclipse-temurin-21-alpine AS build
COPY ./pom.xml /app/pom.xml
COPY ./repair/pom.xml /app/repair/pom.xml
COPY ./repair/src /app/repair/src
WORKDIR /app/repair
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=build /app/repair/target/*.jar app.jar
EXPOSE 8091
ENTRYPOINT ["java", "-jar", "app.jar"]