package com.automation.playwright;

import com.microsoft.playwright.Page;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Minimal smoke coverage for the Playwright Java module ({@link PlaywrightFactory},
 * {@link PlaywrightSauceDemoPage}) so README and architecture claims match {@code src/test}.
 */
@Feature("Playwright Java")
@DisplayName("Playwright Java smoke")
@Tag("playwright")
@Tag("smoke")
class PlaywrightSmokeTest {

    @AfterEach
    void tearDown() {
        PlaywrightFactory.close();
    }

    @Test
    @DisplayName("opens SauceDemo login page via Playwright")
    void opensSauceDemoLogin() {
        Page page = PlaywrightFactory.createPage();
        PlaywrightSauceDemoPage sauceDemo = new PlaywrightSauceDemoPage(page);
        sauceDemo.open();
        assertThat(sauceDemo.isOnLoginPage()).isTrue();
    }
}
