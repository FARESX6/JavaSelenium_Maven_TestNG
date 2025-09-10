# Use an official Maven image with a specific JDK version
FROM maven:3.9.6-eclipse-temurin-21-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml file to download dependencies
COPY pom.xml .

# Download all dependencies from pom.xml. This is done as a separate step
# to leverage Docker layer caching. Dependencies will be re-downloaded only if pom.xml changes.
RUN mvn dependency:go-offline

# Copy the rest of the project source code
COPY src ./src

# Copy the TestNG suite file
COPY testng.xml .

# Copy Allure configuration if you have one (e.g., allure.properties)
# COPY allure.properties .

# The command to execute when the container starts.
# This will run the tests using Maven. The 'test' profile can be used for remote execution.
# The actual command will be provided by the Jenkinsfile or docker-compose.
CMD ["mvn", "clean", "test"]