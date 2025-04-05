# Use official OpenJDK base image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy JAR file from target directory
COPY target/*.jar business-rule-processor.jar

# Run the application
ENTRYPOINT ["java", "-jar", "business-rule-processor.jar"]
