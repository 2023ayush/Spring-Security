Spring Security with JWT and Role-Based Access Control

This repository demonstrates the implementation of Spring Security in a Spring Boot application using JWT (JSON Web Token) for authentication and role-based access control for securing APIs. It includes:

✅ JWT-based Authentication
Secure login mechanism that generates and validates JWT tokens for stateless authentication.

✅ Custom Login & Logout Endpoints
Defined custom endpoints for user login and logout with token invalidation logic.

✅ Role-Based Access Control (RBAC)
Access to endpoints is restricted based on user roles (e.g., ROLE_USER, ROLE_ADMIN), ensuring granular permission control.

✅ Secure API Endpoints
Public, authenticated, and role-restricted endpoints are clearly separated using security configuration.

✅ Stateless Session Handling
The application is stateless; no session is stored on the server.
