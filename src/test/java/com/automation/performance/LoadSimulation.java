package com.automation.performance;

import com.automation.config.Settings;
import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

/**
 * Gatling Load Test Simulation.
 *
 * Run with: mvn gatling:test
 */
public class LoadSimulation extends Simulation {

    private static final Settings settings = Settings.getInstance();

    // ═══════════════════════════════════════════════════════════════════
    // HTTP CONFIGURATION
    // ═══════════════════════════════════════════════════════════════════

    private final HttpProtocolBuilder httpProtocol = http
        .baseUrl(settings.getApiBaseUrl())
        .acceptHeader("application/json")
        .contentTypeHeader("application/json")
        .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) " +
                "AppleWebKit/537.36 Chrome/119.0.0.0 Safari/537.36");

    // ═══════════════════════════════════════════════════════════════════
    // SCENARIOS
    // ═══════════════════════════════════════════════════════════════════

    /**
     * API Health Check Scenario - Tests basic API responsiveness.
     */
    private final ScenarioBuilder apiHealthCheck = scenario("API Health Check")
        .exec(
            http("Get Posts")
                .get("/posts")
                .check(status().is(200))
                .check(jsonPath("$[0].id").exists())
        )
        .pause(Duration.ofMillis(500), Duration.ofSeconds(1));

    /**
     * CRUD Operations Scenario - Tests create, read, update, delete.
     */
    private final ScenarioBuilder crudOperations = scenario("CRUD Operations")
        .exec(
            http("List Posts")
                .get("/posts")
                .check(status().is(200))
        )
        .pause(Duration.ofMillis(200))
        .exec(
            http("Get Single Post")
                .get("/posts/1")
                .check(status().is(200))
                .check(jsonPath("$.id").is("1"))
        )
        .pause(Duration.ofMillis(200))
        .exec(
            http("Create Post")
                .post("/posts")
                .body(StringBody("""
                    {
                        "title": "Load Test Post",
                        "body": "Testing with Gatling",
                        "userId": 1
                    }
                    """))
                .check(status().is(201))
        )
        .pause(Duration.ofMillis(200))
        .exec(
            http("Update Post")
                .put("/posts/1")
                .body(StringBody("""
                    {
                        "id": 1,
                        "title": "Updated Post",
                        "body": "Updated by Gatling",
                        "userId": 1
                    }
                    """))
                .check(status().is(200))
        )
        .pause(Duration.ofMillis(200))
        .exec(
            http("Delete Post")
                .delete("/posts/1")
                .check(status().is(200))
        );

    /**
     * Search/Filter Scenario - Tests query parameters.
     */
    private final ScenarioBuilder searchScenario = scenario("Search and Filter")
        .exec(
            http("Filter by User")
                .get("/posts?userId=1")
                .check(status().is(200))
                .check(jsonPath("$[*].userId").is("1"))
        )
        .pause(Duration.ofMillis(300))
        .exec(
            http("Get Comments")
                .get("/posts/1/comments")
                .check(status().is(200))
        )
        .pause(Duration.ofMillis(300))
        .exec(
            http("Get Users")
                .get("/users")
                .check(status().is(200))
        );

    /**
     * Stress Test Scenario - High volume requests.
     */
    private final ScenarioBuilder stressTest = scenario("Stress Test")
        .repeat(5).on(
            exec(
                http("Rapid Posts Request")
                    .get("/posts")
                    .check(status().is(200))
            ).pause(Duration.ofMillis(100))
        );

    // ═══════════════════════════════════════════════════════════════════
    // LOAD PROFILES
    // ═══════════════════════════════════════════════════════════════════

    {
        setUp(
            // Smoke test: 1 user for quick validation
            apiHealthCheck.injectOpen(
                atOnceUsers(1)
            ),
            
            // Load test: Gradual ramp up
            crudOperations.injectOpen(
                nothingFor(Duration.ofSeconds(2)),
                rampUsers(10).during(Duration.ofSeconds(10)),
                constantUsersPerSec(2).during(Duration.ofSeconds(20))
            ),
            
            // Search load
            searchScenario.injectOpen(
                nothingFor(Duration.ofSeconds(5)),
                rampUsers(5).during(Duration.ofSeconds(10))
            ),
            
            // Stress test: spike
            stressTest.injectOpen(
                nothingFor(Duration.ofSeconds(15)),
                atOnceUsers(20)
            )
        )
        .protocols(httpProtocol)
        .assertions(
            global().responseTime().max().lt(3000),
            global().successfulRequests().percent().gt(95.0)
        );
    }
}

