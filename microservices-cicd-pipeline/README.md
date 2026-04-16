# Microservices: Multi-Cloud CI/CD Pipeline (GCP & Azure)

This project demonstrates a robust, enterprise-grade **CI/CD pipeline** using **GitHub Actions** to automate the build, containerization, and deployment of a microservices architecture across multiple cloud providers: **Google Cloud Platform (GCP)** and **Microsoft Azure**.

## 🚀 Pipeline Overview
The automated workflow is triggered on every push to the `main` branch and consists of four distinct, coordinated jobs:

1.  **🔍 Change Detection**: Identifies which microservices (Profile, Login, Chat) or Helm configurations have been modified.
2.  **🏗️ Parallel Build & Push**: Rebuilds Docker images only for changed services, tagging them with the **Git Commit Hash**, and pushing them simultaneously to **Google Artifact Registry (GAR)** and **Azure Container Registry (ACR)**.
3.  **☁️ GCP Deployment**: Updates GKE cluster configurations via Helm using `sed` for dynamic image tag injection.
4.  **🟦 Azure Deployment**: Seamlessly deploys the same containerized services to an AKS cluster.

## 🛠️ Tech Stack
- **CI/CD**: GitHub Actions (OIDC Authentication)
- **Cloud Providers**: Google Cloud Platform (GKE, GAR), Microsoft Azure (AKS, ACR)
- **Orchestration**: Kubernetes & Helm
- **Authentication**: OpenID Connect (OIDC) for Azure, Service Account Keys for GCP
- **Tagging Strategy**: Git Commit Hash (Immutable versioning)

## 📖 Microservices Architecture
- **Login Service**: Handles user authentication.
- **Profile Service**: Manages user profile data with a persistent backend.
- **Chat Service**: Facilitates real-time messaging using STOMP and Redis.

## 🚀 Setup & Deployment

### 1. GitHub Secrets Configuration
To enable cross-cloud authentication, configure the following secrets in your GitHub repository:

| Secret | Description |
| :--- | :--- |
| `AZ_CONTAINER_REGISTRY` | Azure ACR Login Server (.azurecr.io) |
| `AZ_REGISTRY_USERNAME` | ACR Admin Username |
| `AZ_REGISTRY_PASSWORD` | ACR Admin Password |
| `AZURE_CLIENT_ID` | Azure Managed Identity Client ID (OIDC) |
| `GCP_SA_KEY` | GCP Service Account Key (Base64) |

### 2. Triggering the Workflow
The pipeline is fully automated. Simply push code changes to trigger the build:
```bash
git add .
git commit -m "feat: update chat-service scaling logic"
git push origin main
```

## ⚙️ Engineering Best Practices
- **OIDC Integration**: Implements passwordless authentication for Azure resources, significantly reducing the security risk of long-lived secrets.
- **Immutable Tagging**: Uses Git commit hashes for Docker tags, ensuring a 1:1 mapping between source code and deployed artifacts.
- **Selective Build Strategy**: Optimizes pipeline execution time by only rebuilding services that have detected code changes.
- **GitOps-Lite**: Automates Helm chart updates via `sed` stream editing for seamless infrastructure-as-code updates.

---
*Demonstrates advanced DevOps orchestration across heterogeneous cloud environments.*
