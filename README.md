# 🧠 Habit Tracker – Microservices-Based Backend

A scalable, modular **Habit Tracker** application backend built using Java and Spring Boot, following microservices architecture. Designed to track, manage, and monitor daily habits securely and efficiently.

---

## 📌 Project Status

🚧 **Under Development**  
Currently implemented modules: `User Service`, `Habit Service`, `Keycloak Integration`, `API Gateway`, `Service Discovery (Eureka)`.

---

## 🚀 Tech Stack

| Layer              | Technology                  |
|--------------------|-----------------------------|
| Language           | Java 17+                    |
| Framework          | Spring Boot                 |
| API Gateway        | Spring Cloud Gateway        |
| Service Discovery  | Eureka Server               |
| Security           | Keycloak (OIDC)             |
| Build Tool         | Maven + Jib (Docker image)  |
| Containerization   | Docker                      |
| Database           | MySQL (MongoDB upcoming)    |

---

## 🧱 Microservice Modules

### 1. **API Gateway**
- Uses Spring Cloud Gateway to route requests to appropriate services.
- Entry point for all client requests.
- Includes global filters, CORS config, and routes for `/habit`, `/user`, etc.

### 2. **Eureka Server**
- Service registry to allow microservices to discover each other.
- All services register here and communicate via logical names.

### 3. **User Service**
- Manages user profile data.
- Registers users via Keycloak.
- Handles user-specific operations.

### 4. **Habit Service**
- Core logic for tracking and managing habits.
- Create, update, delete, and fetch habits per user.
- Future support: reminders, analytics.

### 5. **Keycloak SPI Service**
- Contains custom event listeners and user sync logic for Keycloak.
- Handles post-login syncs and other integration needs.

---

## 🔐 Authentication & Authorization with Keycloak

Keycloak is used for managing authentication and authorization across microservices. It acts as the centralized Identity Provider (IdP).

### 🛠 Keycloak Setup (Docker)

Keycloak is used as the centralized Identity Provider (IdP) for authentication and role-based access control.

### 🛠 Keycloak Setup (Custom Docker Image with SPI)

This project uses a **custom Keycloak image** (`aniruddhadev/key-clock-spi-service:latest`) that includes a **Service Provider Interface (SPI)** for external DB event handling.
