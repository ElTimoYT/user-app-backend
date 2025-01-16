# Backend User Management API with Spring Boot 🚀

Welcome to the **Backend User Management API** built with **Spring Boot**! This powerful backend application offers a **RESTful API** that efficiently manages users, handling everything from authentication and authorization to user CRUD operations. Whether you're building a web or mobile application, this API provides all the necessary backend logic to support a user management system.

## 🌐 Full Stack Integration

This backend application seamlessly integrates with a **frontend** application, creating a complete full-stack solution. The backend handles all the user management logic, including authentication with JWT tokens, while the frontend is responsible for presenting the data to users and managing user interactions.


## 🏗️ Project Structure

The project follows a typical **Spring Boot** structure with folders for the main source code and tests. The main components include:

- **Controllers**: Handle incoming HTTP requests and route them to appropriate services.
- **Entities**: Define the structure of data models (e.g., `User`, `Role`).
- **Repositories**: Provide an abstraction layer to interact with the database.
- **Services**: Encapsulate business logic.
- **Security Configuration**: Manages authentication, authorization, and JWT handling.


## 📦 Dependencies

This project uses various **Spring Boot** dependencies and other libraries:

- `spring-boot-starter-data-jpa`: For JPA integration with the database.
- `spring-boot-starter-web`: For building the REST API.
- `spring-boot-devtools`: For automatic reloading during development.
- `mysql-connector-j`: For connecting to MySQL database.
- `spring-boot-starter-test`: For testing purposes.
- `spring-boot-starter-validation`: For validating data.
- `spring-boot-starter-security`: For handling security.
- `javaee-api`: For validation annotations.
- `jjwt-api`, `jjwt-impl`, `jjwt-jackson`: For generating and validating **JWT** (JSON Web Tokens).


## 🗄️ Database Configuration

The application uses **MySQL** as the database. The connection and related settings can be configured in `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/db_backend_users
spring.datasource.username=root
spring.datasource.password=123455
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=create
```

## ⚙️ Configuration Breakdown

- **spring.datasource.url**: Connection URL to the MySQL database.
- **spring.datasource.username** and **spring.datasource.password**: Credentials for accessing the database.
- **spring.datasource.driver-class-name**: JDBC driver for MySQL.
- **spring.jpa.database-platform**: Hibernate dialect for MySQL.
- **spring.jpa.show-sql**: Enables the display of SQL queries in the console.
- **spring.jpa.hibernate.ddl-auto**: Configures Hibernate schema generation. In this case, set to `create`, meaning the schema is created from scratch each time the application starts.

**Note**: Once you have executed the application for the first time, it's recommended to change `spring.jpa.hibernate.ddl-auto` to `update` or another value to avoid data loss.


## 🔐 Security & JWT Authentication

The application uses **Spring Security** for authentication and authorization. **JWT** is implemented for stateless authentication. The key components include:

- **JwtAuthenticationFilter**: Handles user authentication and JWT generation.
- **JwtValidationFilter**: Validates the JWT in incoming requests.
- **TokenJwtConfig**: Configures JWT settings, such as the secret key and headers.


## 🧑‍💻 Controllers

The main controller is **UserController**, which handles HTTP requests related to user management:

- **GET /api/users**: Lists all users.
- **GET /api/users/page/{page}**: Paginates the list of users.
- **GET /api/users/{id}**: Retrieves a user by their ID.
- **POST /api/users**: Creates a new user.
- **PUT /api/users/{id}**: Updates an existing user.
- **DELETE /api/users/{id}**: Deletes a user by their ID.


## 🧩 Entities

The main entities in the system are:

- **User**: Represents a user in the system.
- **Role**: Represents a user role (e.g., `ROLE_USER`, `ROLE_ADMIN`).


## 🛠️ Repositories

The repositories are interfaces that extend **CrudRepository** for performing CRUD operations on the entities:

- **UserRepository**: Repository for the `User` entity.
- **RoleRepository**: Repository for the `Role` entity.


## 💼 Services

The services encapsulate the business logic:

- **UserService**: Interface for user-related operations.
- **UserServiceImpl**: Implementation of `UserService`.
- **JpaUserDetailsService**: Implements `UserDetailsService` for user authentication.


## 🔒 Security Configuration

Security configuration is handled in the **SpringSecurityConfig** class, which:

- Defines authorization rules for different routes.
- Configures **CORS** (Cross-Origin Resource Sharing) settings.
- Adds JWT authentication and validation filters.
- Disables **CSRF** and sets the session creation policy to **stateless**.

---

# Database Structure

The MySQL database used by this application should have the following main tables:

## 🧑‍💻 Users Table

This table stores information about the users.

### Key fields:
- `id`: Unique identifier for the user.
- `username`: The username of the user.
- `password`: The hashed password for the user.
- `email`: The user's email address.
- `enabled`: A flag indicating if the user account is active or disabled.
- `created_at`: Timestamp of when the user was created.
- `updated_at`: Timestamp of when the user information was last updated.

## 🔑 Roles Table

This table stores the roles that can be assigned to users.

### Key fields:
- `id`: Unique identifier for the role.
- `name`: The name of the role (e.g., `ROLE_USER`, `ROLE_ADMIN`).

## 🔗 Users_Roles Table

This is a join table to manage the many-to-many relationship between users and roles.

### Key fields:
- `user_id`: The ID of the user.
- `role_id`: The ID of the role assigned to the user.

### Constraints:
- Foreign keys to ensure referential integrity and cascading deletes between the tables.

## 📋 Recap

The database should contain three tables: `users`, `roles`, and `users_roles`. The `users` table stores information about the users, the `roles` table stores the available roles, and the `users_roles` table manages the many-to-many relationship between users and roles. This structure ensures flexible and secure management of users and their roles within the application.

---

## 📝 Summary

In summary, this **Spring Boot** backend application provides a **REST API** for managing users, using **MySQL** as the database and **JWT** for authentication. Key aspects of the project include:

- **Database Configuration**: Configured in `application.properties` for MySQL.
- **Security**: Handled via Spring Security and JWT.
- **Business Logic**: Encapsulated in services and repositories.

Once the application has been executed and the database schema has been created, make sure to change `spring.jpa.hibernate.ddl-auto` to `update` to avoid losing data on each restart.


## 📌 Getting Started

1. Clone the repository.
2. Configure your `application.properties` for MySQL connection.
3. Run the application with `mvn spring-boot:run`.
4. The API will be accessible at `http://localhost:8080`.


## 📝 License

This project is licensed under the MIT License.

Feel free to contribute and improve this project! 🚀
