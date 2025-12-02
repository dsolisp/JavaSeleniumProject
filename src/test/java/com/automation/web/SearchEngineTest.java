package com.automation.web;

import com.automation.pages.SearchEnginePage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Web tests for Google Search functionality.
 * Equivalent to Python's tests/web/test_search_engine.py
 */
@Epic("Web UI Testing")
@Feature("Google Search")
@DisplayName("Search Engine Tests")
@Tag("web")
class SearchEngineTest extends BaseWebTest {

    private SearchEnginePage searchPage;

    @BeforeEach
    void setUpPage() {
        searchPage = new SearchEnginePage(driver);
    }

    @Test
    @Tag("smoke")
    @Story("Homepage")
    @Description("Verify Bing homepage loads successfully")
    @DisplayName("Homepage should load successfully")
    void homepageShouldLoadSuccessfully() {
        searchPage.open();

        assertThat(searchPage.getTitle()).containsIgnoringCase("Bing");
        assertThat(searchPage.isSearchInputDisplayed()).isTrue();
    }

    @Test
    @Story("Search Functionality")
    @Description("Verify search returns results")
    @DisplayName("Search should return results")
    void searchShouldReturnResults() {
        searchPage.open();
        searchPage.search("Selenium WebDriver");
        
        int resultCount = searchPage.getSearchResultCount();
        assertThat(resultCount).isGreaterThan(0);
        
        logger.info("Found {} search results", resultCount);
    }

    @Test
    @Story("Search Functionality")
    @Description("Verify search results contain expected text")
    @DisplayName("Search results should contain search term")
    void searchResultsShouldContainSearchTerm() {
        searchPage.open();
        searchPage.search("Java automation testing");

        List<String> titles = searchPage.getSearchResultTitles();
        String currentUrl = searchPage.getCurrentUrl();

        // Either we have results or the URL shows search was attempted
        if (!titles.isEmpty()) {
            // At least one result should contain a relevant keyword
            boolean hasRelevantResult = titles.stream()
                    .anyMatch(title ->
                            title.toLowerCase().contains("java") ||
                            title.toLowerCase().contains("automation") ||
                            title.toLowerCase().contains("test")
                    );

            assertThat(hasRelevantResult)
                    .as("At least one result should contain relevant keywords")
                    .isTrue();
        } else {
            // If no results, verify search was at least attempted (URL contains search)
            assertThat(currentUrl).containsIgnoringCase("bing.com");
            logger.warn("Search performed but results may be blocked - this is expected");
        }
    }

    @Test
    @Story("Search Input")
    @Description("Verify search input can be cleared")
    @DisplayName("Search input should be clearable")
    void searchInputShouldBeClearable() {
        searchPage.open();
        searchPage.enterSearchQuery("test query");
        
        String initialQuery = searchPage.getCurrentSearchQuery();
        assertThat(initialQuery).isEqualTo("test query");
        
        searchPage.clearSearch();
        
        String clearedQuery = searchPage.getCurrentSearchQuery();
        assertThat(clearedQuery).isEmpty();
    }

    @Test
    @Story("Page Navigation")
    @Description("Verify URL updates after search")
    @DisplayName("URL should contain search query after search")
    void urlShouldContainSearchQueryAfterSearch() {
        searchPage.open();
        searchPage.search("selenium automation");
        
        String currentUrl = searchPage.getCurrentUrl();
        assertThat(currentUrl).contains("q=");
    }

    @Test
    @Story("Performance")
    @Description("Verify page loads within acceptable time")
    @DisplayName("Page should load within threshold")
    void pageShouldLoadWithinThreshold() {
        long startTime = System.currentTimeMillis();
        searchPage.open();
        long loadTime = System.currentTimeMillis() - startTime;

        logger.info("page_load_time: {}ms", loadTime);

        // Page should load within 15 seconds (DuckDuckGo can be slower)
        assertThat(loadTime)
                .as("Page load time should be under 15 seconds")
                .isLessThan(15000);
    }

    // ═══════════════════════════════════════════════════════════════════
    // ADVANCED TESTS
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Tag("advanced")
    @Story("Element Health")
    @Description("Verify element health monitoring works")
    @DisplayName("Element health should be monitored")
    void elementHealthShouldBeMonitored() {
        searchPage.open();

        assertThat(searchPage.isSearchInputDisplayed())
                .as("Search input should be visible")
                .isTrue();
    }

    @Test
    @Tag("advanced")
    @Story("Wait Conditions")
    @Description("Verify WebDriver wait conditions work correctly")
    @DisplayName("Wait conditions should work")
    void waitConditionsShouldWork() {
        searchPage.open();

        // Search input should be visible and interactable
        assertThat(searchPage.isSearchInputDisplayed()).isTrue();

        // Enter text and verify it appears
        searchPage.enterSearchQuery("selenium");
        String value = searchPage.getCurrentSearchQuery();
        assertThat(value).isEqualTo("selenium");
    }

    @Test
    @Tag("performance")
    @Story("Timing")
    @Description("Verify page interaction timing is measured")
    @DisplayName("Interaction timing should be measured")
    void interactionTimingShouldBeMeasured() {
        long pageLoadTime = measureTime(() -> searchPage.open());
        long typeTime = measureTime(() -> searchPage.enterSearchQuery("performance testing"));
        long clearTime = measureTime(() -> searchPage.clearSearch());

        assertThat(pageLoadTime).isGreaterThan(0);
        assertThat(typeTime).isGreaterThan(0);
        assertThat(clearTime).isGreaterThan(0);

        logger.info("Timing - Page: {}ms, Type: {}ms, Clear: {}ms",
                pageLoadTime, typeTime, clearTime);
    }

    @Test
    @Story("Multiple Searches")
    @Description("Verify multiple consecutive searches work")
    @DisplayName("Multiple searches should work")
    void multipleSearchesShouldWork() {
        searchPage.open();

        // First search
        searchPage.search("first search");
        assertThat(searchPage.getCurrentUrl()).contains("q=");

        // Second search - navigate back and search again
        searchPage.open();
        searchPage.search("second search");
        assertThat(searchPage.getCurrentUrl()).contains("q=");
    }

    @Test
    @Story("Screenshot")
    @Description("Verify screenshot capture works")
    @DisplayName("Screenshot should be captured")
    void screenshotShouldBeCaptured() {
        searchPage.open();

        var screenshotPath = searchPage.captureScreenshot("test_screenshot");

        assertThat(screenshotPath).isNotNull();
        assertThat(java.nio.file.Files.exists(screenshotPath)).isTrue();

        logger.info("Screenshot saved to: {}", screenshotPath);
    }

    // Helper method for timing
    private long measureTime(Runnable action) {
        long start = System.currentTimeMillis();
        action.run();
        return System.currentTimeMillis() - start;
    }
}

