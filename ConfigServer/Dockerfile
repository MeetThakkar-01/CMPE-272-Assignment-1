# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the target directory into the container at /app
COPY ./target/ConfigServer-0.0.1-SNAPSHOT.jar /app/ConfigServer.jar

# Make port 8088 available to the world outside this container
EXPOSE 8088

# Run the application
CMD ["java", "-jar", "/app/ConfigServer.jar"]
