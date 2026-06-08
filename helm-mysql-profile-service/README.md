# Profile Service: Spring Boot & Helm Orchestration with MySQL

This module demonstrates a production-ready migration of the Profile microservice, transitioning from an embedded H2 database to a persistent **MySQL** backend. It leverages **Helm** to manage Kubernetes complexity, following industry best practices for configuration management and security.

## 🚀 Features
- **Production Database Migration**: Fully migrated from H2 to a remote **MySQL** instance.
- **Helm Best Practices**: Utilizes `values.yaml` and Go templates to parameterize Kubernetes manifests, eliminating hardcoded values.
- **Secure Secret Management**: Implements **Kubernetes Secrets** (`profile-secret`) to manage sensitive database credentials.
- **Dynamic Configuration**: Employs **ConfigMaps** for managing environment-specific variables like database hosts and ports.
- **High Availability**: Configured with 3 replicas and orchestrated via Helm for consistent deployments and upgrades.

## 🛠️ Tech Stack
- **Framework**: Spring Boot 2.0.5 (Java 1.8)
- **Database**: MySQL 8.0 (Orchestrated via Bitnami OCI)
- **Containerization**: Docker
- **Orchestration**: Kubernetes (GKE) & Helm 3
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

## 🚀 Deployment Guide

### 1. Provision MySQL Backend
Deploy a robust MySQL instance using the Bitnami Helm chart:
```bash
export mysqlRootPassword=your_root_password
export mysqlUser=your_user
export mysqlPassword=your_password

helm install mysql-profile oci://registry-1.docker.io/bitnamicharts/mysql \
  --set image.repository=bitnamilegacy/mysql \
  --set image.tag=latest \
  --set auth.rootPassword=${mysqlRootPassword} \
  --set auth.username=${mysqlUser} \
  --set auth.password=${mysqlPassword} \
  --set auth.database=test \
  --set primary.persistence.enabled=false \
  --set secondary.persistence.enabled=false
```

### 2. Build & Push Application Image
Update the `pom.xml` with the MySQL connector and rebuild:
```bash
# Build the Docker image
docker build -t profile-service:helm-mysql -f docker/Dockerfile .

# Tag and push to Google Artifact Registry
docker tag profile-service:helm-mysql us-east1-docker.pkg.dev/${PROJECT_ID}/${REPO_NAME}/profile-service:helm-mysql
docker push us-east1-docker.pkg.dev/${PROJECT_ID}/${REPO_NAME}/profile-service:helm-mysql
```

### 3. Deploy Profile Service via Helm
Navigate to the module root and install the custom Helm chart. This chart uses `values.yaml` to dynamically inject configurations into `deployment.yaml`, `service.yaml`, `secret.yaml`, and `configMap.yaml`.

```bash
# Install the chart
helm install profile ./helm
```

### 4. Verify Orchestration
```bash
# Check Helm releases
helm list

# Monitor pod status and logs
kubectl get pods
kubectl logs <POD_NAME>
```

## ⚙️ Engineering Best Practices
- **Separation of Concerns**: Database credentials are never stored in plain text; they are injected via Kubernetes Secrets.
- **Dry-Run Validated**: All Helm templates are designed to be validated via `helm install --dry-run --debug` to ensure YAML syntax integrity.
- **Maintainability**: By using `values.yaml`, the same Helm chart can be deployed to Development, Staging, or Production environments by simply overriding the values.

---
*Focuses on managing microservice complexity through Helm parameterization and secure database migration.*
