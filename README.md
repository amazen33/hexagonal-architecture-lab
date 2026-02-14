# Hexagonal Architecture Project

## Overview
This project implements a hexagonal architecture (ports and adapters) pattern to create a maintainable, testable, and loosely coupled application. The architecture separates the core domain logic from external concerns like databases, user interfaces, and third-party services.

## Architecture Layers

### 1. Domain Layer (/domain)
Contains the core business logic, entities, and domain services. This layer has no external dependencies.

### 2. Application Layer (/application)
Orchestrates the domain layer and defines ports (interfaces) for the adapters to implement.

### 3. Adapters Layer (/adapters)
Implements the ports defined in the application layer. Contains both inbound (driving) and outbound (driven) adapters.

### 4. Shared Layer (/shared)
Contains cross-cutting concerns, utilities, and shared components.

### 5. Configuration Layer (/configuration)
Handles application configuration and bean definitions.

## Key Principles
- Domain layer is independent of external frameworks
- Dependencies point inward (towards domain)
- Use interfaces to define boundaries between layers
- Easy to test and maintain

## Getting Started
1. Clone the repository
2. Configure database connection in `application.properties`
3. Run `./mvnw spring-boot:run`

## Building and Testing
```bash
./mvnw clean install
./mvnw test
```
