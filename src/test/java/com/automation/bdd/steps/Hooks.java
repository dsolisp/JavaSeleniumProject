package com.automation.bdd.steps;

import com.automation.utils.WebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

/**
 * Shared Cucumber hooks for all scenarios.
 * Manages WebDriver lifecycle.
 */
public class Hooks {

    private final SharedContext context;

    public Hooks(SharedContext context) {
        this.context = context;
    }

    @Before("@web or @login or @cart or @checkout")
    public void setUp() {
        WebDriver driver = WebDriverFactory.createDriver("chrome", true);
        context.setDriver(driver);
    }

    @After("@web or @login or @cart or @checkout")
    public void tearDown() {
        context.cleanup();
    }
}

