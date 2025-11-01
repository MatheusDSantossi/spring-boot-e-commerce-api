# spring-boot-e-commerce-api

A Spring Boot–based REST API for an e-commerce platform. This project is a tutorial implementation that covers the basics of building a backend for an online store: product management, user authentication (JWT), cart & order processing, and basic admin operations.

> **Repository:** `spring-boot-e-commerce-api`
> **Description:** A Spring Boot-based REST API designed for an e-commerce platform. This project serves as a tutorial implementation that covers key concepts in building a backend for an online store, including product management, user authentication, and order processing.

---

## Table of contents

* [Features](#features)
* [Tech stack](#tech-stack)
* [Getting started](#getting-started)

    * [Prerequisites](#prerequisites)
    * [Clone](#clone)
    * [Configuration](#configuration)
    * [Run (development)](#run-development)
    * [Build and run jar](#build-and-run-jar)
    * [Run with Docker (optional)](#run-with-docker-optional)
* [Environment variables / application properties](#environment-variables--application-properties)
* [API overview & example endpoints](#api-overview--example-endpoints)
* [Sample requests (curl)](#sample-requests-curl)
* [Database & migrations](#database--migrations)
* [Testing](#testing)
* [Project structure](#project-structure)
* [Contributing](#contributing)
* [License](#license)
* [Acknowledgements](#acknowledgements)

---

## Features

* User registration & login (JWT)
* Role-based access (USER / ADMIN)
* CRUD for products (admin)
* Browse products & product search
* Create and manage orders
* Basic validation & error handling
* H2 in-memory DB for quick dev, PostgreSQL recommended for production
* Example integration tests

---

## Tech stack

* Java 21+
* Spring Boot (Web, Data JPA, Security)
* Maven
* JWT for authentication
* PostgreSQL (recommended) / H2 (dev)
* Docker & Docker Compose (optional)

---

## Getting started

### Prerequisites

* JDK 21+ installed and `JAVA_HOME` set
* Maven 3.6+ (or use the included Maven Wrapper `./mvnw`)
* (Optional) Docker & Docker Compose
* (Optional) Postman / HTTP client for testing

### Clone

```bash
git clone https://github.com/<your-username>/spring-boot-e-commerce-api.git
cd spring-boot-e-commerce-api
```

### Configuration

Copy or create an `application.yml` / `application.properties` in `src/main/resources` (or use environment variables). Example `application.yml` values are shown below in the [Environment variables] section.

Create `.env` (if you use Docker Compose) or export environment variables when running.

### Run (development)

Run with Maven (recommended for development):

```bash
# from project root
./mvnw spring-boot:run
# or if you use system mvn
mvn spring-boot:run
```

Make sure your IDE Run configuration points to the correct module (the one containing `pom.xml`) or use a Spring Boot / Application run configuration pointing to the main class with `@SpringBootApplication`.

### Build and run jar

```bash
./mvnw clean package -DskipTests
java -jar target/spring-boot-ecommerce-api-0.0.1-SNAPSHOT.jar
```

### Run with Docker (optional)

Example `Dockerfile` (project root):

```dockerfile
FROM eclipse-temurin:17-jre
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```

Example `docker-compose.yml` (quick dev with Postgres):

```yaml
version: "3.8"
services:
  db:
    image: postgres:15
    environment:
      POSTGRES_DB: ecommerce
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: example
    ports:
      - "5432:5432"
  app:
    build: .
    depends_on:
      - db
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/ecommerce
      SPRING_DATASOURCE_USERNAME: postgres
      SPRING_DATASOURCE_PASSWORD: example
      JWT_SECRET: change-me
    ports:
      - "8080:8080"
```

Run:

```bash
docker-compose up --build
```

---

## Environment variables / application properties

Example `application.yml` (dev):

```yaml
server:
  port: 8080

spring:
  datasource:
    url: jdbc:h2:mem:ecommercedb;DB_CLOSE_DELAY=-1
    driver-class-name: org.h2.Driver
    username: sa
    password:
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

jwt:
  secret: change-me-to-a-secure-random-string
  expiration-ms: 86400000 # 1 day
```

For production with PostgreSQL:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/ecommerce
spring.datasource.username=postgres
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=validate
jwt.secret=${JWT_SECRET}
```

> **Security note:** Keep `jwt.secret` out of source control. Use environment variables or a secrets manager.

---

## API overview & example endpoints

> Base path: `/api`

### Authentication

* `POST /api/auth/register` — Register a new user (body: name, email, password)
* `POST /api/auth/login` — Login (body: email, password) → returns JWT token

### Products (public and admin)

* `GET /api/products` — List products (pagination & filters)
* `GET /api/products/{id}` — Get product details
* `POST /api/products` — Create product (ADMIN)
* `PUT /api/products/{id}` — Update product (ADMIN)
* `DELETE /api/products/{id}` — Delete product (ADMIN)

### Orders

* `POST /api/orders` — Create an order (USER)
* `GET /api/orders/{id}` — Get order (USER or ADMIN)
* `GET /api/orders` — List orders (ADMIN or current user)

### Users (admin)

* `GET /api/users` — List users (ADMIN)
* `GET /api/users/{id}` — Get user details (ADMIN)

---

## Sample requests (curl)

Register:

```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"name":"Alice", "email":"alice@example.com", "password":"P@ssw0rd"}'
```

Login:

```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"alice@example.com", "password":"P@ssw0rd"}'
# Response contains token: { "token": "<JWT>" }
```

List products:

```bash
curl http://localhost:8080/api/products
```

Create product (admin):

```bash
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <ADMIN_JWT>" \
  -d '{"name":"T-shirt", "description":"Cotton T-shirt", "price":29.99, "stock":100}'
```

Create order:

```bash
curl -X POST http://localhost:8080/api/orders \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <USER_JWT>" \
  -d '{"items":[{"productId":1,"quantity":2}], "shippingAddress": "123 Main St"}'
```

---

## Database & migrations

* In development the project uses H2 (in-memory) by default for quick iteration.
* For production use PostgreSQL (or another relational DB).
* Use Flyway or Liquibase for production-safe migrations (example `db/migration` with Flyway).
* `spring.jpa.hibernate.ddl-auto=update` is convenient in dev but **not recommended** in production.

---

## Testing

Run unit & integration tests:

```bash
./mvnw test
```

Example of integration tests provided for controllers and repository layers. Mock external services where possible.

---

## Project structure (suggested)

```
src/
  main/
    java/
      com.example.ecommerce/
        config/        # security, jwt, swagger
        controller/    # REST controllers
        dto/           # request/response DTOs
        exception/     # custom exceptions + handlers
        model/         # JPA entities (User, Product, Order, OrderItem)
        repository/    # Spring Data JPA repos
        security/      # JWT filter, user details
        service/       # business logic
        ECommerceApplication.java
    resources/
      application.yml
      data.sql (optional seed)
```

---

## Contributing

Contributions are welcome! Suggested steps:

1. Fork the repo
2. Create a feature branch: `git checkout -b feat/your-feature`
3. Run tests & add tests for new behavior
4. Open a pull request with a clear description

Please follow the code style of the project and write tests for new features.

---

## License

This project is provided under the **MIT License** — see `LICENSE` file. Feel free to change the license to suit your needs.

---

## Acknowledgements

This README / project was inspired by learning resources including:

* *Spring Boot Tutorial for Beginners [2025]*
* *Spring Boot Project: Build a REST API for an E-commerce Platform*

from [Programming with Mosh](https://www.youtube.com/@programmingwithmosh).
