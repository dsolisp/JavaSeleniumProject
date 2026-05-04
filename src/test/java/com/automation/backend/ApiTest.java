package com.automation.backend;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import com.automation.config.Constants;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * SWAPI — Comprehensive API Tests
 * Equivalent to Cypress api.cy.ts.
 * Covers: positive, negative, schema validation, SLA, and pagination.
 */
@Epic("API Testing")
@Feature("SWAPI REST API Validation")
@DisplayName("API Tests")
@Tag("api")
class ApiTest {

    private static final String BASE_URL = Constants.Urls.SWAPI;

    @BeforeAll
    static void setup() {
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.baseURI = BASE_URL;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    // ─── Positive Tests ──────────────────────────────────────────────────────────

    @Test
    @DisplayName("Example 1: Fetches a specific person (Luke Skywalker)")
    void example1FetchesSpecificPerson() {
        given()
        .when()
                .get("/people/1/")
        .then()
                .statusCode(200)
                .body("name", equalTo("Luke Skywalker"))
                .body("height", equalTo("172"));
    }

    @Test
    @DisplayName("Example 2: Fetches a paginated collection of people")
    void example2FetchesPaginatedCollection() {
        given()
        .when()
                .get("/people/")
        .then()
                .statusCode(200)
                .body("count", greaterThan(0))
                .body("next", notNullValue())
                .body("previous", nullValue())
                .body("results[0]", hasKey("name"))
                .body("results[0]", hasKey("gender"));
    }

    @Test
    @DisplayName("Example 3: Fetches a person using search query")
    void example3FetchesPersonSearchQuery() {
        given()
                .queryParam("search", "Darth Vader")
        .when()
                .get("/people/")
        .then()
                .statusCode(200)
                .body("count", equalTo(1))
                .body("results[0].name", equalTo("Darth Vader"));
    }

    // ─── Schema Validation ───────────────────────────────────────────────────────

    @Test
    @DisplayName("Example 4: Validates starship resource schema")
    void example4ValidatesStarshipSchema() {
        Response response = given()
        .when()
                .get("/starships/9/");

        response.then().statusCode(200);

        List<String> expectedKeys = List.of(
                "name", "model", "manufacturer", "cost_in_credits", "length",
                "max_atmosphering_speed", "crew", "passengers", "cargo_capacity",
                "consumables", "hyperdrive_rating", "MGLT", "starship_class",
                "pilots", "films", "created", "edited", "url"
        );

        Map<String, Object> jsonPath = response.jsonPath().getMap("$");
        expectedKeys.forEach(key -> assertThat(jsonPath).containsKey(key));
    }

    // ─── SLA / Performance ───────────────────────────────────────────────────────

    @Test
    @DisplayName("Example 5: Verifies response time is under 3000ms (external API)")
    void example5VerifiesResponseTime() {
        long startTime = System.currentTimeMillis();
        given()
        .when()
                .get("/planets/1/")
        .then()
                .statusCode(200);
        long duration = System.currentTimeMillis() - startTime;

        assertThat(duration).isLessThan(3000L);
    }

    // ─── Negative Tests ──────────────────────────────────────────────────────────

    @Test
    @DisplayName("Example 6: Verifies 404 for non-existent resource ID")
    void example6Verifies404NonExistentId() {
        given()
        .when()
                .get("/people/99999/")
        .then()
                .statusCode(404)
                .body("detail", equalTo("Not found"));
    }

    @Test
    @DisplayName("Example 7: Verifies 404 for invalid endpoint")
    void example7Verifies404InvalidEndpoint() {
        given()
        .when()
                .get("/invalid_endpoint/")
        .then()
                .statusCode(404);
    }

    @Test
    @DisplayName("Example 8: Handles search with no matches")
    void example8HandlesSearchNoMatches() {
        given()
                .queryParam("search", "xyz_no_match")
        .when()
                .get("/people/")
        .then()
                .statusCode(200)
                .body("count", equalTo(0))
                .body("results", hasSize(0));
    }

    // ─── Pagination Boundary ─────────────────────────────────────────────────────

    @Test
    @DisplayName("Example 9: Verifies first page has no previous link")
    void example9VerifiesFirstPageNoPrevious() {
        given()
                .queryParam("page", 1)
        .when()
                .get("/people/")
        .then()
                .statusCode(200)
                .body("previous", nullValue())
                .body("next", notNullValue());
    }

    @Test
    @DisplayName("Example 10: Verifies last page has no next link")
    void example10VerifiesLastPageNoNext() {
        Response response = given()
        .when()
                .get("/people/");

        response.then().statusCode(200);

        int count = response.jsonPath().getInt("count");
        List<?> results = response.jsonPath().getList("results");
        int totalPages = (int) Math.ceil((double) count / results.size());

        given()
                .queryParam("page", totalPages)
        .when()
                .get("/people/")
        .then()
                .statusCode(200)
                .body("next", nullValue());
    }
}

