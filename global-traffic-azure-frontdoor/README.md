# Global Traffic Management: Azure Front Door & Terraform

This module demonstrates the implementation of **Global Load Balancing** and **Path-based Routing** for a multi-cloud microservices architecture. It utilizes **Terraform (IaC)** to provision and configure **Azure Front Door (Standard)**, providing a unified entry point for services distributed across GCP and Azure.

## 🚀 Key Features
- **Global Entry Point**: Consolidates multiple cloud-specific IP addresses into a single, high-performance domain name.
- **Path-based Routing**: Intelligently routes traffic based on URL patterns:
  - `/chat` -> Directed to **GKE (GCP)**.
  - `/login` & `/profile` -> Load balanced between **GKE (GCP)** and **AKS (Azure)**.
- **Health Probes**: Configured with active HEAD probes to monitor origin health every 30 seconds, ensuring instant failover if a cluster becomes unresponsive.
- **Latency-Sensitive Load Balancing**: Implements round-robin routing with a 50ms latency sensitivity, optimizing traffic distribution across heterogeneous cloud environments.
- **Infrastructure as Code (IaC)**: The entire global network stack is fully automated and version-controlled via Terraform.

## 🏗️ Routing Logic
| Route Name | Pattern | Origin Group | Strategy |
| :--- | :--- | :--- | :--- |
| `loginprofilerouting` | `/login`, `/profile` | GCP + Azure | Round Robin (HA) |
| `chatrouting` | `/chat/*` | GCP Only | Direct |

## 🛠️ Tech Stack
- **IaC**: Terraform
- **Cloud Service**: Azure Front Door (Standard)
- **Origins**: GKE Ingress (GCP), AKS Ingress (Azure)
- **Monitoring**: Azure Front Door Health Probes

## 🚀 Provisioning Guide

### 1. Configure Variables
Copy the example variables file and fill in your environment-specific values:
```bash
cp terraform.tfvars.example terraform.tfvars
# Edit terraform.tfvars with your Subscription ID and Cluster IPs
```

### 2. Initialize and Apply
```bash
terraform init
terraform apply
```

### 3. Verify Global Connectivity
Access the application via the Front Door endpoint:
- `http://<frontdoor-endpoint>/login`
- `http://<frontdoor-endpoint>/chat`
- `http://<frontdoor-endpoint>/profile`

## ⚙️ Engineering Best Practices
- **Security Isolation**: Uses `terraform.tfvars` (ignored by Git) to manage sensitive identifiers, preventing credential leakage.
- **High Availability (HA)**: By leveraging Origin Groups, the architecture survives even if an entire cloud provider's region goes offline.
- **Standardized Probes**: Uses `HEAD` requests for health probes to minimize overhead on backend microservices while ensuring connectivity.

---
*Demonstrates global-scale traffic engineering and infrastructure automation.*
