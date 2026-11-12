# Employee Management System

Full-stack app to manage employees and departments.

**Tech:** Java 17, Spring Boot 3, Spring Data JPA (Hibernate), MySQL, REST APIs, HTML/CSS/JavaScript

## Features
- Add, view, edit and delete employees
- Departments with a one-to-many relationship (one department, many employees)
- Search employees by first or last name
- Server-side validation (required fields, valid email, non-negative salary)
- Global exception handling with clear error messages (404, 400, 409)

## REST API
| Method | Endpoint | Purpose |
|---|---|---|
| GET | /api/employees?search= | List or search employees |
| GET | /api/employees/{id} | Get one employee |
| POST | /api/employees | Create |
| PUT | /api/employees/{id} | Update |
| DELETE | /api/employees/{id} | Delete |
| GET/POST | /api/departments | List or create departments |
| PUT/DELETE | /api/departments/{id} | Update or delete a department |

## Run locally
1. Install JDK 17, Maven and MySQL
2. Set your MySQL username and password in `src/main/resources/application.properties`
3. Run `mvn spring-boot:run`
4. Open http://localhost:8080

The database `ems_db` and its tables are created automatically on first run.

## Project structure
```
controller/   REST endpoints
model/        JPA entities (Employee, Department)
repository/   Spring Data JPA interfaces
exception/    Custom exception and global handler
static/       Frontend (index.html)
```
