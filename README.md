# Domus Back-End Developer Challenge.

## Overview 

This project implements the Domus technical challenge by providing a REST endpoint that returns directors who have directed more movies than a specified threshold. The solution uses clean architecture principles, and efficient pagination handling.

## Project Guidelines
The project was designed with maintainability, clarity, and scalability in mind.
The following best practices were applied:

- Clean layered architecture
  - Controller → Exposes REST endpoints and Swagger documentation 
  - Service → Business logic
  - Client → WebClient to consume the external API 
  - Exception Handling → Global ControllerAdvice layer
- Structured logging
  - External API calls 
  - Exceptions 
  - Performance and processing time
- Automated tests
  - Unit tests for services



## Environment Configuration
The application supports multiple environments through application-*.yml.

Each environment defines:
- External API base URL
- Logging settings

## Swagger configuration

Includes:
- API metadata
- Endpoint descriptions
- Request/response examples
- Automatic model generation

Swagger endpoints:

- /swagger-ui.html 
- /v3/api-docs
