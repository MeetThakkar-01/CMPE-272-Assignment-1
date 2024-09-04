# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the target directory into the container at /app
COPY target/HelloWorldService-0.0.1-SNAPSHOT.jar /app/HelloWorldService.jar

# Make port 8081 available to the world outside this container
EXPOSE 8083

# Run the application
CMD ["java", "-jar", "/app/HelloWorldService.jar"]
