# Use Java 17 base image
FROM openjdk:17-jdk-slim

# Expose port 8080
EXPOSE 8080

# Copy the built jar file into the container
COPY target/hello_docker.jar /hello_docker.jar

USER root
RUN apt-get update && apt-get install -y docker.io

# Run the jar file
ENTRYPOINT ["java", "-jar", "/hello_docker.jar"]
