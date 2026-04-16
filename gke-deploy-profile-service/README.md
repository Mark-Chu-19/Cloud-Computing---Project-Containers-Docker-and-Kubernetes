# Profile Service: Spring Boot & GKE Orchestration

This module demonstrates the deployment of a containerized Spring Boot microservice on a Google Kubernetes Engine (GKE) cluster, leveraging Google Artifact Registry (GAR) and Kubernetes LoadBalancers.

## 🚀 Features
- **Kubernetes Orchestration**: Managed with 3 replicas for high availability.
- **Auto-Scaling Ready**: Configured with Deployment and Service manifests.
- **GCP Integrated**: Optimized for Google Cloud Artifact Registry and GKE.
- **External Load Balancing**: Exposed via a LoadBalancer Service for public access.

## 🛠️ Tech Stack
- **Framework**: Spring Boot 2.0.5
- **Containerization**: Docker
- **Orchestration**: Kubernetes (GKE)
- **Image Registry**: Google Artifact Registry (GAR)
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

## 🚀 GKE Deployment

### Prerequisites
- [Docker](https://www.docker.com/) installed.
- [Google Cloud SDK (gcloud)](https://cloud.google.com/sdk) configured.
- Active GCP Project with GKE API enabled.

### 1. Build and Push to GAR
```bash
# Set variables
export PROJECT_ID="<YOUR_GCP_PROJECT_ID>"
export REPO_NAME="<YOUR_REPOSITORY_NAME>"
export LOCATION="us-east1"

# Build and Push
docker build -t ${LOCATION}-docker.pkg.dev/${PROJECT_ID}/${REPO_NAME}/profile-service:gke -f docker/Dockerfile .
docker push ${LOCATION}-docker.pkg.dev/${PROJECT_ID}/${REPO_NAME}/profile-service:gke
```

### 2. Deploy to GKE
Apply the Kubernetes manifests:
```bash
# Update deployment.yaml image path before running
kubectl apply -f k8s/
```

### 3. Verify Deployment
Wait for the external IP and test:
```bash
kubectl get services
curl http://<EXTERNAL_IP>/profile?username=alice
```

---
