# 🍽️ Restaurant Review API

A Spring Boot REST API for managing restaurant reviews with user authentication, analytics, and filtering.

## 🚀 Tech Stack
- Java 17
- Spring Boot 3.x
- Spring Data JPA + H2
- Spring Security (Basic Auth)
- Swagger UI (springdoc-openapi)
- Gradle

## 📦 API Features
- Add restaurants (Admin only)
- Submit and fetch reviews (User/Admin)
- Average rating per restaurant
- Top 3 restaurants by cuisine
- Filter reviews by status (`PENDING`, `APPROVED`, `REJECTED`)
- Basic Auth with roles

## 🔐 Credentials

| Username | Password  | Role   |
|----------|-----------|--------|
| admin    | admin123  | ADMIN  |
| user     | user123   | USER   |


## 🔗 Swagger UI

Visit: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## ▶️ Running the App

```bash
./gradlew bootRun
