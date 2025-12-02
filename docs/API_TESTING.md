# API Testing Guide

This guide covers API testing using REST Assured in the Java Selenium project.

## Overview

The project uses REST Assured for API testing, providing a fluent interface for HTTP operations
with built-in assertions and response validation.

## Setup

Add REST Assured to `pom.xml`:

```xml
<dependency>
    <groupId>io.rest-assured</groupId>
    <artifactId>rest-assured</artifactId>
    <version>5.4.0</version>
    <scope>test</scope>
</dependency>
```

## Basic Usage

### GET Request

```java
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Test
void shouldGetUser() {
    given()
        .contentType(ContentType.JSON)
    .when()
        .get("/users/1")
    .then()
        .statusCode(200)
        .body("id", equalTo(1))
        .body("name", notNullValue());
}
```

### POST Request

```java
@Test
void shouldCreatePost() {
    String requestBody = """
        {
            "title": "New Post",
            "body": "Post content",
            "userId": 1
        }
        """;
    
    given()
        .contentType(ContentType.JSON)
        .body(requestBody)
    .when()
        .post("/posts")
    .then()
        .statusCode(201)
        .body("id", notNullValue());
}
```

### PUT Request

```java
@Test
void shouldUpdatePost() {
    String requestBody = """
        {
            "id": 1,
            "title": "Updated Title",
            "body": "Updated body",
            "userId": 1
        }
        """;
    
    given()
        .contentType(ContentType.JSON)
        .pathParam("id", 1)
        .body(requestBody)
    .when()
        .put("/posts/{id}")
    .then()
        .statusCode(200)
        .body("title", equalTo("Updated Title"));
}
```

### DELETE Request

```java
@Test
void shouldDeletePost() {
    given()
        .pathParam("id", 1)
    .when()
        .delete("/posts/{id}")
    .then()
        .statusCode(200);
}
```

## Response Validation

### Status Codes

```java
.then()
    .statusCode(200)          // Exact match
    .statusCode(lessThan(300)) // Range check
```

### JSON Body Assertions

```java
.then()
    .body("id", equalTo(1))
    .body("name", containsString("John"))
    .body("email", matchesPattern(".*@.*\\..*"))
    .body("$", hasSize(10))   // Array size
```

### Headers

```java
.then()
    .header("Content-Type", containsString("application/json"))
    .header("Cache-Control", notNullValue());
```

## Query Parameters

```java
given()
    .queryParam("userId", 1)
    .queryParam("_limit", 5)
.when()
    .get("/posts")
.then()
    .body("$", hasSize(5));
```

## Performance Testing

```java
@Test
void shouldRespondQuickly() {
    given()
        .contentType(ContentType.JSON)
    .when()
        .get("/posts")
    .then()
        .time(lessThan(2000L)); // Response time < 2 seconds
}
```

## Running API Tests

```bash
# Run all API tests
mvn test -Dtest="**/api/*Test"

# Run specific test
mvn test -Dtest="ApiTest#shouldGetPosts"
```

## Test Location

API tests are located in:
- `src/test/java/com/automation/api/`

