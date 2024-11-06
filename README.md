Blog Application - Spring Boot
This is a Spring Boot-based blog application that allows users to create blog posts, comment on them, and manage their content. The application also includes user authentication, role-based access control, and JWT-based security. Various libraries such as ModelMapper, Lombok, and others are used to streamline development.

Features
User Authentication: Secure login and user authentication using JWT (JSON Web Tokens).
Role-Based Access Control: Different user roles (Admin, User, etc.) with restricted access based on permissions.
Blog Post Management: Users can create, edit, and delete blog posts.
Comment Management: Users can add, edit, and delete comments on blog posts.
Forgot Password Feature: Users can reset their password if they forget it.
Exception Handling: Comprehensive error handling for common scenarios (e.g., user not found, resource conflicts).
ModelMapper Integration: Mapping between DTOs and entities to ensure clean code and reduce redundancy.
Lombok Integration: Reduced boilerplate code for getters, setters, constructors, etc.

Technologies Used
Spring Boot: The main framework for building the backend.
Spring Security: Used for securing the application with JWT-based authentication.
JWT (JSON Web Tokens): For stateless authentication.
Spring Data JPA: For database interactions with JPA.
H2 Database: Used for in-memory database (can be replaced with MySQL or other databases).
Lombok: Used to reduce boilerplate code (getters, setters, constructors).
ModelMapper: For mapping DTOs to entities and vice versa.
Maven: For dependency management and build automation.
