# HealthHub Backend Project Documentation

## High-Level Project Description

HealthHub is a robust backend application built using a scalable and secure microservices architecture with Java and Spring Boot. It integrates AI capabilities via the OpenAI API to provide advanced medical diagnosis and specialist recommendations based on user-provided symptoms. Security is enforced through JWT authentication, role-based access control, secure configuration management, and efficient CORS handling. Eureka Server facilitates service discovery and communication between microservices.

## Technology Stack

- **Java**
- **Spring Boot**
- **OpenAI API (GPT-4o)**
- **MongoDB**
- **JWT (JSON Web Token)**
- **Spring Cloud Gateway**
- **Eureka Server**

## Project Features

- **AI-Driven Diagnosis:** Automated symptom analysis to suggest medical specialists.
- **Secure Authentication:** JWT-based authentication with secure cookie management.
- **Microservices Architecture:** Scalable and modular design with Eureka service registry and discovery.
- **Centralized API Routing:** Efficient request handling using Spring Cloud Gateway.

## API Endpoint Documentation

### AI Service (`/api/ai`)

#### POST `/api/ai/chat`

- **Purpose:** Receive AI-based recommendations from user prompts.
- **Request Body:**

```json
{
  "prompt": "symptom description"
}
```

- **Response:** AI-generated response suggesting a specialist.

#### GET `/api/ai/diagnose`

- **Description:** Provides a basic diagnosis based on the authenticated user's role.
- **Security:** JWT token required.

#### GET `/api/ai/specialist?symptoms=<symptoms>`

- **Description:** Suggests the most appropriate medical specialist based on provided symptoms.
- **Security:** JWT token required.

### User Service (`/api/user`)

#### POST `/api/user/signup`

- **Description:** User registration endpoint.
- **Request:**

```json
{
  "userName": "example",
  "email": "example@mail.com",
  "password": "password"
}
```

- **Response:** Newly created user details or error message.

#### POST `/api/user/login`

- **Description:** User login endpoint.
- **Response:** JWT token as HttpOnly secure cookie and login confirmation message.

### Email Service (`/api/email`)

#### POST `/api/email/sendMail`

- **Description:** Endpoint to send emails.
- **Request:**

```json
{
  "recipient": "user@example.com",
  "subject": "Subject line",
  "body": "Email content"
}
```

- **Response:** Confirmation message upon successful email sending.

## Security Features

### JWT Authentication

- JWT tokens with secure secret keys.
- Tokens stored securely using HttpOnly cookies.
- Tokens expire after 1 hour.

### Role-Based Access Control

- User roles included in JWT claims.

### Security Filters

- `JwtFilter` ensures endpoint security by validating JWT tokens.

### CORS Configuration

- Configured for frontend requests from allowed origins.
- Supports methods: GET, POST, PUT, DELETE, OPTIONS.
- Allows secure credentials and headers.

### Service Discovery and API Gateway

- Eureka Server manages service registry and discovery.
- Spring Cloud Gateway routes API requests based on configuration.

