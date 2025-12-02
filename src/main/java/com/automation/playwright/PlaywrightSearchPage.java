package com.automation.playwright;

import com.automation.config.Settings;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Playwright-based Search Engine Page Object.
 * Equivalent to Selenium SearchEnginePage but using Playwright API.
 */
public class PlaywrightSearchPage extends PlaywrightBasePage {

    // Selectors
    private static final String SEARCH_INPUT = "input[name='q']";
    private static final String SEARCH_BUTTON = "button[type='submit']";
    private static final String RESULT_TITLES = "article h2 a";
    private static final String RESULT_SNIPPETS = "article span[data-testid='result-snippet']";

    public PlaywrightSearchPage(Page page) {
        super(page);
    }

    /**
     * Open the search engine.
     */
    public PlaywrightSearchPage open() {
        String url = Settings.getInstance().getBaseUrl();
        navigateTo(url);
        return this;
    }

    /**
     * Check if search input is visible.
     */
    public boolean isSearchInputVisible() {
        return isVisible(SEARCH_INPUT);
    }

    /**
     * Enter search query.
     */
    public PlaywrightSearchPage enterSearchQuery(String query) {
        logger.info("Entering search query: {}", query);
        fill(SEARCH_INPUT, query);
        return this;
    }

    /**
     * Submit the search.
     */
    public PlaywrightSearchPage submitSearch() {
        click(SEARCH_BUTTON);
        page.waitForLoadState();
        return this;
    }

    /**
     * Perform search (enter query and submit).
     */
    public PlaywrightSearchPage search(String query) {
        enterSearchQuery(query);
        submitSearch();
        return this;
    }

    /**
     * Get search results count.
     */
    public int getResultsCount() {
        return count(RESULT_TITLES);
    }

    /**
     * Get all result titles.
     */
    public List<String> getResultTitles() {
        return getAll(RESULT_TITLES).stream()
            .map(Locator::textContent)
            .collect(Collectors.toList());
    }

    /**
     * Click on result by index.
     */
    public void clickResult(int index) {
        List<Locator> results = getAll(RESULT_TITLES);
        if (index < results.size()) {
            results.get(index).click();
        }
    }

    /**
     * Check if results are displayed.
     */
    public boolean hasResults() {
        return count(RESULT_TITLES) > 0;
    }
}

