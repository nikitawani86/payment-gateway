# Payment Gateway Simulator

A production-inspired Payment Gateway built using **Java, Spring Boot, Microservices, PostgreSQL, Kafka, Redis, Docker, and Kubernetes**.

This project is designed to simulate how modern payment gateways process online transactions while following clean architecture and industry best practices.

---

# Project Goal

The objective of this project is to build a scalable payment platform similar to real-world systems used by fintech companies.

The project focuses on:

* Microservices Architecture
* Secure Payment Processing
* Merchant Management
* Fraud Detection
* Event-Driven Communication
* Distributed Systems
* Production-Ready Design

---

# Architecture

```text
                    Client
                       │
                       ▼
                  API Gateway
                       │
         ┌─────────────┴─────────────┐
         ▼                           ▼
 Merchant Service              Payment Service
                                     │
                  ┌──────────────────┼──────────────────┐
                  ▼                  ▼                  ▼
            Fraud Service      PSP Simulator        Redis
                                     │
                                     ▼
                                   Kafka
                                     │
          ┌──────────────────────────┼──────────────────────────┐
          ▼                          ▼                          ▼
 Notification Service      Settlement Service        Reporting Service
```

---

# Tech Stack

### Backend

* Java 21
* Spring Boot 3
* Spring Data JPA
* Spring Cloud
* Spring Cloud Gateway
* Eureka Discovery Server

### Database

* PostgreSQL

### Messaging

* Apache Kafka

### Cache

* Redis

### DevOps

* Docker
* Docker Compose
* Kubernetes (Planned)

### Monitoring

* Spring Boot Actuator
* Prometheus (Planned)
* Grafana (Planned)

---

# Microservices

| Service                 | Status         |
| ----------------------- | -------------- |
| Discovery Server        | ✅ Completed    |
| API Gateway             | ✅ Completed    |
| Merchant Service        | ✅ Completed    |
| Payment Service         | 🚧 In Progress |
| Fraud Detection Service | ⏳ Planned      |
| Notification Service    | ⏳ Planned      |
| Settlement Service      | ⏳ Planned      |

---

# Merchant Service Features

* Merchant Registration
* Merchant Lookup
* UUID-based Merchant Reference
* Email Validation
* Global Exception Handling
* Standard API Response
* Eureka Registration
* PostgreSQL Integration

---

# Upcoming Payment Service Features

* Payment Initiation
* Payment Status Tracking
* Payment State Machine
* Merchant Validation
* Idempotency
* Refunds
* Transaction History
* Kafka Event Publishing

---

# Functional Requirements

* Merchant Management
* Payment Processing
* Refund Management
* Settlement Processing
* Fraud Detection
* Webhook Notifications
* Transaction History
* API Key Validation

---

# Non-Functional Requirements

* Microservices Architecture
* Horizontal Scalability
* 99.99% Availability Target
* Distributed Tracing
* Centralized Logging
* Secure API Communication
* Event-Driven Processing
* Fault Tolerance
* Rate Limiting

---

# Project Status

Current Progress

* Discovery Server
* API Gateway
* Merchant Service

Next Milestone

* Payment Service

---

# Future Enhancements

* JWT Authentication
* Merchant API Keys
* Docker Support
* Kubernetes Deployment
* CI/CD Pipeline
* AWS Deployment
* Distributed Tracing
* Monitoring Dashboard
* Load Testing

---

# Author

**Nikita**

Java Backend Developer

Tech Stack:
Java | Spring Boot | Microservices | PostgreSQL | Kafka | Redis | Docker

This project is being developed as a learning initiative to understand how modern payment gateways are designed and implemented using production-oriented backend engineering practices.
