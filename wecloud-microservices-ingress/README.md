# WeCloud: Distributed Microservices Architecture with Ingress-Nginx

This module demonstrates a fully integrated microservices application orchestrated with **Kubernetes (GKE)** and **Helm**. It features a real-time distributed chat service synchronized via **Redis Pub/Sub**, a secure login service with **Spring Security**, and a unified entry point managed by **Ingress-Nginx**.

## 🚀 Key Features
- **Unified Ingress Routing**: Utilizes **Ingress-Nginx** to handle external traffic, routing requests to specific microservices (`/chat`, `/login`, `/profile`) via a single public IP.
- **Distributed Real-time Chat**: Implements **WebSockets (STOMP)** for full-duplex communication, with **Redis Pub/Sub** ensuring message synchronization across multiple chat replicas.
- **Microservices Orchestration**: Managed by a single, centralized **Helm chart** for consistent deployment of deployments, services, configMaps, and secrets.
- **Security-First Design**: The Login service leverages **Spring Security** with CSRF protection, ensuring secure user authentication and session management.
- **Persistent Storage**: Each microservice is backed by a dedicated **MySQL** instance for robust data persistence.

## 🏗️ System Components
- **Chat Service**: Real-time messaging hub using Redis for horizontal scalability.
- **Login Service**: Authentication provider with Spring Security.
- **Profile Service**: User profile management service.
- **Redis Instance**: In-memory message broker for cross-replica synchronization.
- **Ingress Controller**: Traffic manager acting as a Layer 7 load balancer.

## 🛠️ Tech Stack
- **Framework**: Spring Boot 2.0.5 (Java 1.8)
- **Containerization**: Docker
- **Orchestration**: Kubernetes & Helm 3
- **Ingress**: Ingress-Nginx (Bitnami OCI)
- **Database**: MySQL 8.0 & Redis

## 📖 API Usage (Standardized)
All services return high-quality profile data:
```json
{
  "username": "alice",
  "name": "Alice Smith",
  "gender": "Female",
  "age": 25
}
```

## 🚀 Deployment Guide

### 1. Provision Nginx Ingress Controller
```bash
helm upgrade --install my-nginx oci://registry-1.docker.io/bitnamicharts/nginx-ingress-controller \
  --set image.repository=bitnamilegacy/nginx-ingress-controller \
  --set image.tag=1.13.1-debian-12-r1
```

### 2. Deploy the WeCloud Stack via Helm
Install the unified chart to deploy all microservices (Profile, Login, Chat) and Redis:
```bash
helm install wecloud ./helm
```

### 3. Apply Ingress Rules
Configure the routing rules for the ingress controller:
```bash
kubectl apply -f Ingress/ingress.yaml
```

### 4. Verification
Access the integrated application via the Ingress LoadBalancer IP:
- `http://<INGRESS_IP>/login`
- `http://<INGRESS_IP>/chat`

## ⚙️ Engineering Best Practices
- **Resource Optimization**: Switched service types to **NodePort** to utilize the Ingress controller, reducing the cost of multiple external public IPs.
- **Stateful Synchronization**: Solved the WebSocket multi-replica data consistency problem using a Pub/Sub pattern with Redis.
- **Labels & Selectors**: Implemented strict labeling conventions to ensure accurate traffic routing within the cluster.

---
*Demonstrates sophisticated microservices integration and distributed systems orchestration.*
