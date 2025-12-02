package com.automation.contract;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.V4Pact;
import au.com.dius.pact.core.model.annotations.Pact;
import io.qameta.allure.*;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.*;

/**
 * Consumer-Driven Contract Tests using Pact.
 * Verifies API contracts between consumer and provider.
 */
@Epic("Contract Testing")
@Feature("API Contracts")
@DisplayName("API Contract Tests")
@Tag("contract")
@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "PostsAPI")
class ApiContractTest {

    // ═══════════════════════════════════════════════════════════════════
    // PACT DEFINITIONS
    // ═══════════════════════════════════════════════════════════════════

    @Pact(consumer = "TestConsumer")
    public V4Pact getPostsPact(PactDslWithProvider builder) {
        return builder
            .given("posts exist")
            .uponReceiving("a request for all posts")
                .path("/posts")
                .method("GET")
            .willRespondWith()
                .status(200)
                .headers(java.util.Map.of("Content-Type", "application/json"))
                .body(new au.com.dius.pact.consumer.dsl.PactDslJsonArray()
                    .object()
                        .integerType("id", 1)
                        .integerType("userId", 1)
                        .stringType("title", "Sample Post Title")
                        .stringType("body", "Sample post body content")
                    .closeObject())
            .toPact(V4Pact.class);
    }

    @Pact(consumer = "TestConsumer")
    public V4Pact getSinglePostPact(PactDslWithProvider builder) {
        return builder
            .given("post with id 1 exists")
            .uponReceiving("a request for post 1")
                .path("/posts/1")
                .method("GET")
            .willRespondWith()
                .status(200)
                .headers(java.util.Map.of("Content-Type", "application/json"))
                .body(new PactDslJsonBody()
                    .integerType("id", 1)
                    .integerType("userId", 1)
                    .stringType("title", "Post Title")
                    .stringType("body", "Post body content"))
            .toPact(V4Pact.class);
    }

    @Pact(consumer = "TestConsumer")
    public V4Pact createPostPact(PactDslWithProvider builder) {
        return builder
            .given("user can create posts")
            .uponReceiving("a request to create a post")
                .path("/posts")
                .method("POST")
                .headers(java.util.Map.of("Content-Type", "application/json"))
                .body(new PactDslJsonBody()
                    .stringType("title", "New Post")
                    .stringType("body", "New post content")
                    .integerType("userId", 1))
            .willRespondWith()
                .status(201)
                .headers(java.util.Map.of("Content-Type", "application/json"))
                .body(new PactDslJsonBody()
                    .integerType("id")
                    .stringType("title", "New Post")
                    .stringType("body", "New post content")
                    .integerType("userId", 1))
            .toPact(V4Pact.class);
    }

    @Pact(consumer = "TestConsumer")
    public V4Pact getPostNotFoundPact(PactDslWithProvider builder) {
        return builder
            .given("post with id 99999 does not exist")
            .uponReceiving("a request for non-existent post")
                .path("/posts/99999")
                .method("GET")
            .willRespondWith()
                .status(404)
            .toPact(V4Pact.class);
    }

    // ═══════════════════════════════════════════════════════════════════
    // CONTRACT TESTS
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Story("GET /posts")
    @Description("Verify GET /posts contract returns array of posts")
    @DisplayName("GET /posts should return posts array")
    @PactTestFor(pactMethod = "getPostsPact")
    void getPostsShouldReturnPostsArray(MockServer mockServer) {
        var response = given()
            .baseUri(mockServer.getUrl())
            .contentType(ContentType.JSON)
        .when()
            .get("/posts")
        .then()
            .statusCode(200)
            .extract().response();
        
        assertThat(response.jsonPath().getList("$")).isNotEmpty();
        assertThat(response.jsonPath().getInt("[0].id")).isPositive();
    }

    @Test
    @Story("GET /posts/{id}")
    @Description("Verify GET /posts/1 contract returns single post")
    @DisplayName("GET /posts/1 should return single post")
    @PactTestFor(pactMethod = "getSinglePostPact")
    void getSinglePostShouldReturnPost(MockServer mockServer) {
        var response = given()
            .baseUri(mockServer.getUrl())
            .contentType(ContentType.JSON)
        .when()
            .get("/posts/1")
        .then()
            .statusCode(200)
            .extract().response();
        
        assertThat(response.jsonPath().getInt("id")).isEqualTo(1);
        assertThat(response.jsonPath().getString("title")).isNotEmpty();
    }

    @Test
    @Story("POST /posts")
    @Description("Verify POST /posts contract creates new post")
    @DisplayName("POST /posts should create post")
    @PactTestFor(pactMethod = "createPostPact")
    void createPostShouldReturnCreatedPost(MockServer mockServer) {
        String body = """
            {
                "title": "New Post",
                "body": "New post content",
                "userId": 1
            }
            """;
        
        var response = given()
            .baseUri(mockServer.getUrl())
            .contentType(ContentType.JSON)
            .body(body)
        .when()
            .post("/posts")
        .then()
            .statusCode(201)
            .extract().response();
        
        assertThat(response.jsonPath().getInt("id")).isPositive();
    }

    @Test
    @Story("GET /posts/{id} - Not Found")
    @Description("Verify GET /posts/99999 returns 404")
    @DisplayName("GET non-existent post should return 404")
    @PactTestFor(pactMethod = "getPostNotFoundPact")
    void getNonExistentPostShouldReturn404(MockServer mockServer) {
        given()
            .baseUri(mockServer.getUrl())
            .contentType(ContentType.JSON)
        .when()
            .get("/posts/99999")
        .then()
            .statusCode(404);
    }
}

