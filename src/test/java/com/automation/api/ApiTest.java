package com.automation.api;

import com.automation.config.Settings;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * API tests using REST Assured.
 * Equivalent to Python's tests/api/test_api.py
 */
@Epic("API Testing")
@Feature("JSONPlaceholder API")
@DisplayName("API Tests")
@Tag("api")
class ApiTest {

    private static final Logger logger = LoggerFactory.getLogger(ApiTest.class);

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = Settings.getInstance().getApiBaseUrl();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @BeforeEach
    void logTestStart(TestInfo testInfo) {
        logger.info("Test started: {}", testInfo.getDisplayName());
    }

    // ═══════════════════════════════════════════════════════════════════
    // GET TESTS
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Tag("smoke")
    @Story("GET Operations")
    @Description("Verify GET /users returns list of users")
    @DisplayName("GET /users should return user list")
    void getUsersShouldReturnUserList() {
        long startTime = System.currentTimeMillis();

        Response response = given()
                .contentType(ContentType.JSON)
        .when()
                .get("/users")
        .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("$", hasSize(greaterThan(0)))
                .body("[0].id", notNullValue())
                .body("[0].name", notNullValue())
                .body("[0].email", notNullValue())
                .extract().response();

        long duration = System.currentTimeMillis() - startTime;
        logger.info("GET /users - 200 ({}ms)", duration);

        assertThat(response.jsonPath().getList("$").size()).isGreaterThan(0);
    }

    @Test
    @Story("GET Operations")
    @Description("Verify GET /users/{id} returns single user")
    @DisplayName("GET /users/1 should return user details")
    void getUserByIdShouldReturnUserDetails() {
        given()
                .contentType(ContentType.JSON)
                .pathParam("id", 1)
        .when()
                .get("/users/{id}")
        .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("name", notNullValue())
                .body("username", notNullValue())
                .body("email", containsString("@"));
    }

    @Test
    @Story("GET Operations")
    @DisplayName("GET /posts should return posts list")
    void getPostsShouldReturnPostsList() {
        given()
                .contentType(ContentType.JSON)
        .when()
                .get("/posts")
        .then()
                .statusCode(200)
                .body("$", hasSize(greaterThan(0)))
                .body("[0].userId", notNullValue())
                .body("[0].title", notNullValue())
                .body("[0].body", notNullValue());
    }

    // ═══════════════════════════════════════════════════════════════════
    // POST TESTS
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Story("POST Operations")
    @Description("Verify POST /posts creates new post")
    @DisplayName("POST /posts should create new post")
    void postShouldCreateNewPost() {
        String requestBody = """
                {
                    "title": "Test Post",
                    "body": "This is a test post body",
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
                .body("id", notNullValue())
                .body("title", equalTo("Test Post"))
                .body("body", equalTo("This is a test post body"))
                .body("userId", equalTo(1));
    }

    // ═══════════════════════════════════════════════════════════════════
    // PUT TESTS
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Story("PUT Operations")
    @DisplayName("PUT /posts/1 should update post")
    void putShouldUpdatePost() {
        String requestBody = """
                {
                    "id": 1,
                    "title": "Updated Title",
                    "body": "Updated body content",
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

    // ═══════════════════════════════════════════════════════════════════
    // DELETE TESTS
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Story("DELETE Operations")
    @DisplayName("DELETE /posts/1 should delete post")
    void deleteShouldRemovePost() {
        given()
                .pathParam("id", 1)
        .when()
                .delete("/posts/{id}")
        .then()
                .statusCode(200);
    }

    // ═══════════════════════════════════════════════════════════════════
    // ERROR HANDLING TESTS
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Story("Error Handling")
    @DisplayName("GET /users/999999 should return 404")
    void getNonExistentUserShouldReturn404() {
        given()
                .contentType(ContentType.JSON)
                .pathParam("id", 999999)
        .when()
                .get("/users/{id}")
        .then()
                .statusCode(404);
    }

    @Test
    @Story("Error Handling")
    @DisplayName("GET /posts/999999 should return 404")
    void getNonExistentPostShouldReturn404() {
        given()
                .contentType(ContentType.JSON)
                .pathParam("id", 999999)
        .when()
                .get("/posts/{id}")
        .then()
                .statusCode(404);
    }

    // ═══════════════════════════════════════════════════════════════════
    // PERFORMANCE TESTS
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Tag("performance")
    @Story("Performance")
    @Description("Verify API response times are within threshold")
    @DisplayName("API response time should be under 30 seconds")
    void apiResponseTimeShouldBeUnderThreshold() {
        long maxResponseTime = Settings.getInstance().getApiResponseThresholdMs();

        for (int i = 1; i <= 5; i++) {
            long startTime = System.currentTimeMillis();

            given()
                    .contentType(ContentType.JSON)
                    .pathParam("id", i)
            .when()
                    .get("/posts/{id}")
            .then()
                    .statusCode(200);

            long responseTime = System.currentTimeMillis() - startTime;
            logger.debug("api_request_{}: {}ms", i, responseTime);

            assertThat(responseTime)
                    .as("Request %d should complete within %dms", i, maxResponseTime)
                    .isLessThan(maxResponseTime);
        }
    }

    @Test
    @Tag("performance")
    @Story("Performance")
    @DisplayName("Multiple parallel requests should complete quickly")
    void multipleRequestsShouldCompleteQuickly() {
        long startTime = System.currentTimeMillis();

        for (int i = 1; i <= 10; i++) {
            given()
                    .contentType(ContentType.JSON)
            .when()
                    .get("/posts/" + i)
            .then()
                    .statusCode(200);
        }

        long totalTime = System.currentTimeMillis() - startTime;
        logger.info("batch_requests: {}ms", totalTime);

        long maxTotalTime = Settings.getInstance().getApiResponseThresholdMs();

        assertThat(totalTime)
                .as("10 requests should complete within %d milliseconds", maxTotalTime)
                .isLessThan(maxTotalTime);
    }

    // ═══════════════════════════════════════════════════════════════════
    // VALIDATION TESTS
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Story("Validation")
    @DisplayName("Response headers should contain correct content type")
    void responseHeadersShouldContainCorrectContentType() {
        given()
                .contentType(ContentType.JSON)
        .when()
                .get("/posts")
        .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .header("Content-Type", containsString("application/json"));
    }

    @Test
    @Story("Validation")
    @DisplayName("Post structure should have all required fields")
    void postStructureShouldHaveAllRequiredFields() {
        given()
                .contentType(ContentType.JSON)
        .when()
                .get("/posts/1")
        .then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("userId", notNullValue())
                .body("title", notNullValue())
                .body("body", notNullValue())
                .body("id", instanceOf(Integer.class))
                .body("userId", instanceOf(Integer.class))
                .body("title", instanceOf(String.class))
                .body("body", instanceOf(String.class));
    }

    @Test
    @Story("Validation")
    @DisplayName("User address structure should be valid")
    void userAddressStructureShouldBeValid() {
        given()
                .contentType(ContentType.JSON)
        .when()
                .get("/users/1")
        .then()
                .statusCode(200)
                .body("address.street", notNullValue())
                .body("address.city", notNullValue())
                .body("address.zipcode", notNullValue())
                .body("address.geo.lat", notNullValue())
                .body("address.geo.lng", notNullValue());
    }

    // ═══════════════════════════════════════════════════════════════════
    // QUERY PARAMETER TESTS
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Story("Query Parameters")
    @DisplayName("Filter posts by userId should work")
    void filterPostsByUserIdShouldWork() {
        int userId = 1;

        Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("userId", userId)
        .when()
                .get("/posts")
        .then()
                .statusCode(200)
                .body("$", hasSize(greaterThan(0)))
                .extract().response();

        // All returned posts should belong to userId 1
        response.jsonPath().getList("userId", Integer.class).forEach(id ->
                assertThat(id).isEqualTo(userId)
        );
    }

    @Test
    @Story("Query Parameters")
    @DisplayName("Get comments for a specific post should work")
    void getCommentsForPostShouldWork() {
        given()
                .contentType(ContentType.JSON)
                .pathParam("postId", 1)
        .when()
                .get("/posts/{postId}/comments")
        .then()
                .statusCode(200)
                .body("$", hasSize(greaterThan(0)))
                .body("[0].postId", equalTo(1))
                .body("[0].email", containsString("@"));
    }

    // ═══════════════════════════════════════════════════════════════════
    // PATCH TESTS
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Story("PATCH Operations")
    @DisplayName("PATCH /posts/1 should partially update post")
    void patchShouldPartiallyUpdatePost() {
        String requestBody = """
                {
                    "title": "Patched Title"
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .pathParam("id", 1)
                .body(requestBody)
        .when()
                .patch("/posts/{id}")
        .then()
                .statusCode(200)
                .body("title", equalTo("Patched Title"))
                .body("id", equalTo(1));  // ID should remain unchanged
    }
}

