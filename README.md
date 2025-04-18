# Habit-Tracker
This project is a **microservices-based Habit Tracker application** built with Spring Boot. It leverages **Keycloak** for authentication and authorization, **Eureka** for service discovery, and **Spring Cloud Gateway** as the API gateway.
---

## 📚 Overview

Microservices are stateless and distributed, making centralized authentication crucial. **Keycloak** provides a powerful and easy-to-integrate OAuth2/OIDC solution, managing login flows, token issuance, and role-based access control.

---

## 🔐 Security with Keycloak

- **OAuth2/OpenID Connect** based authentication
- JWT tokens issued by Keycloak
- Token propagation from Gateway to downstream services

---

## 🧰 Tech Stack

- Java 17
- Spring Boot
- Spring Security
- Spring Cloud Gateway
- Eureka Server (Service Discovery)
- Keycloak(Oauth2 Authorization)
- Docker (for Keycloak setup)
- MySQL / MongoDB
- Maven

---

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/Ani111112/Habit-Tracker.git
