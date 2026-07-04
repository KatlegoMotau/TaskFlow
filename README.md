# 🚀 TaskFlow – Full Stack Task Management System

A secure full-stack Task Management System built using **Spring Boot**, **React**, and **PostgreSQL**. TaskFlow enables users to securely manage their personal tasks with JWT authentication, dashboard analytics, PDF/CSV exports, and role-based access control.

This project was developed to demonstrate my backend development skills, REST API design, secure authentication, and full-stack application integration.

---

## 📸 Screenshots


# Register
# Login 
# Dashboard 
# Create Task
# Tasks 

---

# ✨ Features

### 🔐 Authentication & Security
- JWT Authentication
- Secure Login & Registration
- BCrypt Password Encryption
- Spring Security
- Protected API Endpoints
- Role-Based Authorization (USER / ADMIN)

### 📋 Task Management
- Create Tasks
- Update Tasks
- Delete Tasks
- View Personal Tasks
- Search Tasks
- Filter by Status
- Filter by Priority

### 📊 Dashboard
- Total Tasks
- Tasks In Progress
- Completed Tasks
- Overdue Tasks
- To-Do Tasks

### 📄 Reports
- Export Tasks to CSV
- Export Tasks to PDF

### 💻 Frontend
- Responsive Dashboard
- Protected Routes
- Sidebar Navigation
- Task CRUD Interface
- Axios API Integration

---

# 🛠️ Technologies Used

## Backend

- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- JWT Authentication
- PostgreSQL
- Maven

## Frontend

- React
- Vite
- React Router
- Axios
- CSS

## Database

- PostgreSQL

---

# 🏗️ System Architecture

```
React Frontend
       │
       │ Axios + JWT
       ▼
Spring Boot REST API
       │
       │ Spring Security
       ▼
Business Logic
       │
       ▼
Spring Data JPA
       │
       ▼
PostgreSQL
```

---

# 📂 Project Structure

```
TaskFlow

├── Backend (Spring Boot)
│   ├── Controllers
│   ├── Services
│   ├── Repositories
│   ├── DTOs
│   ├── Security
│   ├── Entities
│   ├── Exceptions
│   └── Configuration
│
├── Frontend (React)
│   ├── Components
│   ├── Pages
│   ├── Services
│   ├── Layout
│   └── Routing
│
└── PostgreSQL Database
```

---

# 🔐 Authentication Flow

```
User Login

      │

      ▼

Spring Security

      │

      ▼

JWT Generated

      │

      ▼

React stores JWT

      │

      ▼

JWT sent with every API request

      │

      ▼

Spring Security validates token

      │

      ▼

Authorized Request
```

---

# 📌 REST API

## Authentication

| Method | Endpoint |
|---------|----------|
| POST | /api/auth/register |
| POST | /api/auth/login |

---

## Tasks

| Method | Endpoint |
|---------|----------|
| GET | /api/tasks |
| GET | /api/tasks/{id} |
| POST | /api/tasks |
| PUT | /api/tasks/{id} |
| DELETE | /api/tasks/{id} |

---

## Dashboard

| Method | Endpoint |
|---------|----------|
| GET | /api/tasks/dashboard |

---

## Export

| Method | Endpoint |
|---------|----------|
| GET | /api/tasks/export/pdf |
| GET | /api/tasks/export/csv |

---

# 🚀 Running the Project

## Backend

```bash
git clone https://github.com/YOUR_USERNAME/TaskFlow.git

cd taskflow

mvn spring-boot:run
```

---

## Frontend

```bash
cd taskflow-frontend

npm install

npm run dev
```

---

## Database

Create a PostgreSQL database named:

```
taskflow_db
```

Update your `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/taskflow_db
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
```

---

# 🎯 Learning Outcomes

Through this project I gained practical experience in:

- Designing RESTful APIs
- JWT Authentication
- Spring Security
- Role-Based Access Control
- Layered Backend Architecture
- React API Integration
- PostgreSQL Database Design
- CRUD Operations
- DTO Pattern
- Exception Handling
- Validation
- PDF & CSV Report Generation
- Git & GitHub Version Control

## 📌 Project Status

> **Note:** This project is currently configured to run in a **local development environment (localhost)** and has not yet been deployed to a public hosting platform.

The application was developed and tested using the following technologies:

- **Frontend:** React (Vite)
- **Backend:** Java 17, Spring Boot 3
- **Security:** Spring Security + JWT Authentication
- **Database:** PostgreSQL
- **Build Tool:** Maven
- **API Testing:** REST Client / Postman
- **IDE:** Visual Studio Code
- **Version Control:** Git & GitHub

To run the application locally:

- **Frontend:** `http://localhost:5173`
- **Backend API:** `http://localhost:8080`
- **Database:** PostgreSQL (`taskflow_db`)
