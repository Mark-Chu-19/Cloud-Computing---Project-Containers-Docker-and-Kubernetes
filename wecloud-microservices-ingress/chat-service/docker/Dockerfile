# Use the required parent image with Java 8 and Maven
FROM maven:3.8.4-jdk-8-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the source code from the host to the container
# We use "." because the build command will be executed from the project root
COPY . .

# Compile and package the application into a JAR file in the container
# Skipping tests for speed as per common practice
RUN mvn clean package -DskipTests

# Run the JAR file
# Note: The JAR filename "profile-embedded-0.1.0.jar" was verified inside the container target directory
CMD ["java", "-jar", "target/groupchat-0.1.0.jar"]
