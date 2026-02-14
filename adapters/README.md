# Adapters Layer

## Purpose
The adapters layer implements the ports defined in the application layer. It contains concrete implementations for external concerns like REST APIs, database access, messaging, and third-party services.

## Structure

### Inbound Adapters (/inbound)
Handle incoming requests from external actors:
- **REST**: HTTP controllers exposing REST APIs
- **gRPC**: gRPC service implementations
- **Messaging**: Message consumers for async communication

### Outbound Adapters (/outbound)
Handle outgoing communication to external systems:
- **Persistence**: Database implementations
- **External**: Third-party service integrations
- **Messaging**: Message publishers

## Key Principles
- Adapters depend on ports, not the other way around
- Multiple adapters can implement the same port
- Adapters can be swapped without affecting core logic
