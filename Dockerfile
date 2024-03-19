# Use a base image with Java 21
FROM openjdk:21

# Set the working directory
WORKDIR /app

# Copy the JAR file into the container
COPY output/artifacts/kpi_backend_api_jar/kpi-backend-api.jar /app/kpi-backend-api.jar



# Expose the port on which your application is running (change it accordingly)
EXPOSE 8080

# Command to run your application
CMD ["java", "-jar", "kpi-backend-api.jar"]