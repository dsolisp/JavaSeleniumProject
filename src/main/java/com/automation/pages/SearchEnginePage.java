package com.automation.pages;

import com.automation.locators.ResultPageLocators;
import com.automation.locators.SearchEngineLocators;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Page object for Bing Search.
 */
public class SearchEnginePage extends BasePage {

    public SearchEnginePage(WebDriver driver) {
        super(driver);
    }

    /**
     * Navigate to search engine homepage.
     */
    public SearchEnginePage open() {
        navigateTo(settings.getSearchEngineUrl());
        handleCookieConsent();
        log.info("Opened search engine homepage");
        return this;
    }

    /**
     * Handle cookie consent dialog if present.
     */
    private void handleCookieConsent() {
        try {
            org.openqa.selenium.By acceptButton = org.openqa.selenium.By.id("bnp_btn_accept");
            if (isElementPresent(acceptButton)) {
                click(acceptButton);
                log.info("Accepted cookie consent");
            }
        } catch (NoSuchElementException | TimeoutException e) {
            // Cookie consent not present or already accepted
            log.debug("No cookie consent dialog found");
        }
    }

    /**
     * Enter search query.
     */
    public SearchEnginePage enterSearchQuery(String query) {
        type(SearchEngineLocators.SEARCH_INPUT, query);
        log.info("Entered search query: {}", query);
        return this;
    }

    /**
     * Submit search.
     */
    public SearchEnginePage submitSearch() {
        pressKey(SearchEngineLocators.SEARCH_INPUT, Keys.ENTER);
        waitForSearchResults();
        log.info("Search submitted");
        return this;
    }

    /**
     * Perform a complete search.
     */
    public SearchEnginePage search(String query) {
        enterSearchQuery(query);
        submitSearch();
        return this;
    }

    /**
     * Wait for search results to load.
     * @return this page for fluent chaining
     */
    public SearchEnginePage waitForSearchResults() {
        try {
            // Wait for URL to change to indicate search was performed
            wait.until(d -> d.getCurrentUrl().contains("q="));
            // Wait for results container to be present
            wait.until(ExpectedConditions.presenceOfElementLocated(ResultPageLocators.RESULTS_CONTAINER));
        } catch (TimeoutException e) {
            log.warn("Search results wait timed out: {}", e.getMessage());
        }
        return this;
    }

    /**
     * Get search result titles.
     */
    public List<String> getSearchResultTitles() {
        List<WebElement> titles = findElements(ResultPageLocators.RESULT_TITLES);
        return titles.stream()
                .map(WebElement::getText)
                .filter(text -> !text.isEmpty())
                .collect(Collectors.toList());
    }

    /**
     * Get number of search results.
     */
    public int getSearchResultCount() {
        return findElements(ResultPageLocators.RESULT_ITEMS).size();
    }

    /**
     * Click on a search result by index.
     * @return this page for fluent chaining
     */
    public SearchEnginePage clickSearchResult(int index) {
        List<WebElement> results = findElements(ResultPageLocators.RESULT_TITLES);
        if (index < results.size()) {
            results.get(index).click();
            log.info("Clicked search result {}", index);
        } else {
            throw new IndexOutOfBoundsException(
                    "Result index " + index + " out of bounds. Total results: " + results.size()
            );
        }
        return this;
    }

    /**
     * Get search suggestions.
     */
    public List<String> getSearchSuggestions(String partialQuery) {
        type(SearchEngineLocators.SEARCH_INPUT, partialQuery);

        // Wait for suggestions to appear
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(SearchEngineLocators.SUGGESTION_ITEMS));
        } catch (TimeoutException e) {
            log.debug("No search suggestions appeared");
            return List.of();
        }

        List<WebElement> suggestions = driver.findElements(SearchEngineLocators.SUGGESTION_ITEMS);
        return suggestions.stream()
                .map(WebElement::getText)
                .filter(text -> !text.isEmpty())
                .collect(Collectors.toList());
    }

    /**
     * Navigate to Images search.
     */
    public SearchEnginePage clickImagesLink() {
        click(ResultPageLocators.IMAGES_TAB);
        log.info("Clicked Images link");
        return this;
    }

    /**
     * Navigate to News search.
     */
    public SearchEnginePage clickNewsLink() {
        click(ResultPageLocators.NEWS_TAB);
        log.info("Clicked News link");
        return this;
    }

    /**
     * Check if search input is displayed.
     */
    public boolean isSearchInputDisplayed() {
        return isElementVisible(SearchEngineLocators.SEARCH_INPUT);
    }

    /**
     * Get the search input element.
     */
    public WebElement getSearchInput() {
        return findElement(SearchEngineLocators.SEARCH_INPUT);
    }

    /**
     * Clear search input using JavaScript for reliability.
     */
    public SearchEnginePage clearSearch() {
        WebElement element = waitForVisible(SearchEngineLocators.SEARCH_INPUT);
        // Use JavaScript to clear the input for reliability across browsers
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].value = '';", element);
        // Also trigger input event to notify any listeners
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", element);
        log.debug("Cleared search input");
        return this;
    }

    /**
     * Get current search query.
     */
    public String getCurrentSearchQuery() {
        return getAttribute(SearchEngineLocators.SEARCH_INPUT, "value");
    }

    /**
     * Verify search results contain expected text.
     */
    public boolean resultsContain(String expectedText) {
        List<String> titles = getSearchResultTitles();
        return titles.stream()
                .anyMatch(title -> title.toLowerCase().contains(expectedText.toLowerCase()));
    }
}

