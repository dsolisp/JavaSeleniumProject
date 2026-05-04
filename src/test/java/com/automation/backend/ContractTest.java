package com.automation.backend;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import com.automation.config.Constants;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * API Contract Testing Module.
 * Equivalent to Cypress contract.cy.ts.
 * Validates API schemas and contract stability for SWAPI.
 */
@Epic("API Testing")
@Feature("Contract Validation")
@DisplayName("Contract Tests")
@Tag("api")
@Tag("contract")
class ContractTest {

    private static final String BASE_URL = Constants.Urls.SWAPI;

    @BeforeAll
    static void setup() {
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.baseURI = BASE_URL;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    @DisplayName("should match expected schema for /people endpoint")
    void testSchemaForPeopleEndpoint() {
        Response response = given()
        .when()
                .get("/people/1/");
        
        response.then().statusCode(200);

        List<String> keys = List.of(
                "name", "height", "mass", "hair_color", "skin_color", "eye_color", 
                "birth_year", "gender", "homeworld", "films", "species", "vehicles", 
                "starships", "created", "edited", "url"
        );

        Map<String, Object> jsonPath = response.jsonPath().getMap("$");
        keys.forEach(key -> assertThat(jsonPath).containsKey(key));
    }

    @Test
    @DisplayName("should validate films endpoint contract")
    void testValidateFilmsEndpointContract() {
        Response response = given()
        .when()
                .get("/films/1/");
        
        response.then().statusCode(200);

        List<String> keys = List.of(
                "title", "episode_id", "opening_crawl", "director", "producer", 
                "release_date", "created", "edited", "url"
        );

        Map<String, Object> jsonPath = response.jsonPath().getMap("$");
        keys.forEach(key -> assertThat(jsonPath).containsKey(key));
        
        assertThat(response.jsonPath().getList("characters")).isNotNull();
        assertThat(response.jsonPath().getList("planets")).isNotNull();
        assertThat(response.jsonPath().getList("starships")).isNotNull();
        assertThat(response.jsonPath().getList("vehicles")).isNotNull();
        assertThat(response.jsonPath().getList("species")).isNotNull();
    }

    @Test
    @DisplayName("should validate planets endpoint contract")
    void testValidatePlanetsEndpointContract() {
        Response response = given()
        .when()
                .get("/planets/1/");
        
        response.then().statusCode(200);

        List<String> keys = List.of(
                "name", "rotation_period", "orbital_period", "diameter", "climate", 
                "gravity", "terrain", "surface_water", "population"
        );

        Map<String, Object> jsonPath = response.jsonPath().getMap("$");
        keys.forEach(key -> assertThat(jsonPath).containsKey(key));
        
        assertThat(response.jsonPath().getList("residents")).isNotNull();
        assertThat(response.jsonPath().getList("films")).isNotNull();
    }

    @Test
    @DisplayName("should ensure contract stability — no unexpected fields removed")
    void testEnsureContractStability() {
        Response response = given()
        .when()
                .get("/people/1/");
        
        response.then().statusCode(200);

        List<String> requiredFields = List.of(
                "name", "height", "mass", "hair_color", "skin_color", "eye_color", 
                "birth_year", "gender"
        );

        Map<String, Object> jsonPath = response.jsonPath().getMap("$");
        requiredFields.forEach(field -> assertThat(jsonPath).containsKey(field));
    }

    @Test
    @DisplayName("should validate array response structure for list endpoints")
    void testValidateArrayResponseStructure() {
        given()
        .when()
                .get("/people/")
        .then()
                .statusCode(200)
                .body("count", notNullValue())
                .body("count", instanceOf(Integer.class))
                .body("next", notNullValue())
                .body("previous", nullValue())
                .body("results", notNullValue())
                .body("results[0]", hasKey("name"))
                .body("results[0]", hasKey("height"));
    }
}
