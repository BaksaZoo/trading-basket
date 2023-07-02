FROM maven:3.9.3-amazoncorretto-17 AS builder

WORKDIR /app

COPY ./pom.xml .
COPY ./trading-core ./trading-core
COPY ./trading-basket ./trading-basket
COPY ./trading-store ./trading-store

WORKDIR /app

RUN mvn clean package

# -----------------

FROM openjdk:17

WORKDIR /app

COPY --from=builder /app/trading-basket/target/*.jar app.jar

EXPOSE 8082

CMD ["java", "-jar", "app.jar", "--spring.profiles.active=docker"]