package com.automation.pact;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Epic("Pact")
@Feature("Consumer Contracts")
@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "JSONPlaceholder")
class JsonPlaceholderPactTest {

    @Pact(consumer = "JavaSeleniumProject")
    RequestResponsePact getPost(PactDslWithProvider builder) {
        return builder
                .given("a post with id 1 exists")
                .uponReceiving("a request for post 1")
                .path("/posts/1")
                .method("GET")
                .willRespondWith()
                .status(200)
                .headers(Map.of("Content-Type", "application/json; charset=utf-8"))
                .body(new PactDslJsonBody()
                        .integerType("userId", 1)
                        .integerType("id", 1)
                        .stringType("title", "hello")
                        .stringType("body", "world"))
                .toPact();
    }

    @Test
    @PactTestFor(pactMethod = "getPost")
    void verifyGetPost(MockServer mockServer) {
        RestAssured.baseURI = mockServer.getUrl();
        given()
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1));
    }
}

