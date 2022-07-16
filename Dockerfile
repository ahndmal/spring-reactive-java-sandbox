FROM openjdk:17-alpine
COPY . .
RUN mvn clean package

