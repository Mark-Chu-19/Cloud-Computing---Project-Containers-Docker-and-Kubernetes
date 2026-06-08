# Microservices: Multi-Cloud High Availability & Auto-scaling

This module demonstrates a robust, fault-tolerant microservices architecture deployed across **Google Cloud Platform (GCP)** and **Microsoft Azure**. It features **Horizontal Pod Autoscaling (HPA)** to handle fluctuating traffic and a **Multi-Cloud Disaster Recovery (DR)** strategy to ensure zero downtime during regional or platform-specific outages.

## 🚀 Key Features
- **Horizontal Pod Autoscaling (HPA)**: Dynamically scales replicas (1-5 pods) for all microservices based on real-time CPU utilization (Target: 50%).
- **Multi-Cloud Disaster Recovery**: Replicates core services (Login, Profile) across **GKE (GCP)** and **AKS (Azure)** to mitigate provider-level failures.
- **Unified Configuration Management**: Leverages a single Helm chart with environment-specific overrides (`values-gcp.yaml`, `values-azure.yaml`) for cross-cloud consistency.
- **Conditional Cloud Deployment**: Uses **Helm Flow Control** (Conditionals) to manage cloud-specific workloads (e.g., Chat & Redis deployed only on GKE).
- **Ingress Orchestration**: Configured with NGINX Ingress Controllers on both GCP and Azure for secure traffic management.

## 🏗️ Deployment Architecture
- **Primary (GCP/GKE)**: Full stack deployment including Login, Profile, Chat, and Redis.
- **Secondary (Azure/AKS)**: Replicated Login and Profile services for global redundancy.
- **Auto-scaling Logic**: Resource requests are precisely tuned (`cpu: 200m`) to enable reliable HPA triggers.

## 🛠️ Tech Stack
- **Cloud Providers**: Google Cloud Platform (GCP), Microsoft Azure
- **Orchestration**: Kubernetes (GKE, AKS) & Helm
- **Auto-scaling**: Kubernetes HPA (autoscaling/v1)
- **Ingress**: NGINX Ingress Controller
- **Backend**: Java (Spring Boot), MySQL (Persistent), Redis (Cache/PubSub)

## 📖 API Usage (Unified Response)
All services across both clouds return standardized profile data for seamless user experience:
```json
{
  "username": "alice",
  "name": "Alice Smith",
  "gender": "Female",
  "age": 25
}
```

## 🚀 Orchestration Guide

### 1. Provision Multi-Cloud Clusters
Ensure you have active GKE and AKS clusters with properly configured contexts:
```bash
kubectl config use-context GCP_CONTEXT
kubectl config use-context AZURE_CONTEXT
```

### 2. GKE Deployment (Full Stack + HPA)
```bash
helm install wecloud ./helm -f ./helm/values-gcp.yaml
```

### 3. AKS Deployment (Replication + HPA)
```bash
helm install wecloud ./helm -f ./helm/values-azure.yaml
```

### 4. Verify Auto-scaling Performance
Simulate traffic load and monitor the HPA status:
```bash
kubectl get hpa
kubectl describe hpa spring-profile-autoscaling
```

## ⚙️ Engineering Best Practices
- **Infrastructure as Code (IaC)**: Eliminates environment drift by using shared Helm templates across different clouds.
- **Resource Profiling**: Implements strict CPU/Memory resource requests to ensure Kubernetes scheduler and HPA function predictably.
- **Provider-Agnostic Design**: Decouples logic from cloud-specific managed services to allow easy migration between platforms.

---
*Focuses on system resilience, scalability, and cross-platform orchestration.*
