# Profile Service: Spring Boot & Docker Containerization

This module demonstrates the containerization of a Spring Boot microservice. It focuses on creating an optimized Docker image and running the service in an isolated container environment.

## 🚀 Features
- **RESTful API**: Handles profile data retrieval via GET requests.
- **Embedded Database**: Uses H2 for zero-config deployment.
- **Optimized Docker Image**: Built with a multi-stage-ready Maven wrapper approach.
- **MVC Architecture**: Clean separation of Controller, Model, and Repository layers.

## 🛠️ Tech Stack
- **Language**: Java 1.8
- **Framework**: Spring Boot 2.0.5
- **Database**: H2 (In-memory)
- **Containerization**: Docker
- **Build Tool**: Maven

## 📖 API Usage

### Get Profile
Retrieve a user profile by username.

- **URL**: `/profile`
- **Method**: `GET`
- **Query Params**: `username=[string]`
- **Success Response**:
  ```json
  {
    "username": "alice",
    "name": "Alice Smith",
    "gender": "Female",
    "age": 25
  }

  ```

## 🚀 Getting Started

### Prerequisites
- Java 1.8
- Maven 3.x
- Docker

### Local Development
```bash
./mvnw spring-boot:run
```
The service will be available at `http://localhost:8080`.

### Docker Deployment
1. **Build the image**:
   ```bash
   docker build -t profile-service:docker -f docker/Dockerfile .
   ```
2. **Run the container**:
   ```bash
   docker run -p 8000:8080 profile-service:docker
   ```
   Access the service at `http://localhost:8000`.

---
