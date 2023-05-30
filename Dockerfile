FROM gradle:7.6.1-jdk17-alpine as builder
WORKDIR /app
COPY . .
RUN ./gradlew clean build

FROM openjdk:17-alpine
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar /app/app.jar
ENTRYPOINT java -jar app.jar