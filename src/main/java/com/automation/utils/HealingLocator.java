package com.automation.utils;

import com.automation.config.AiSettings;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Heuristic locator healing (ADR-019). Optional Maven profile {@code ai-heal} adds headout autoheal-locator.
 */
public final class HealingLocator {

    private static final Logger LOG = LoggerFactory.getLogger(HealingLocator.class);
    private static final AtomicInteger HEALS_THIS_RUN = new AtomicInteger(0);

    private HealingLocator() {}

    public static WebElement find(WebDriver driver, String locatorKey, By primary, List<Fallback> fallbacks) {
        try {
            return driver.findElement(primary);
        } catch (NoSuchElementException ignored) {
            // try fallbacks
        }

        for (Fallback fb : fallbacks) {
            try {
                WebElement el = driver.findElement(fb.by());
                LOG.info("Healed {} using {}", locatorKey, fb.name());
                HEALS_THIS_RUN.incrementAndGet();
                return el;
            } catch (NoSuchElementException ignored) {
                // next
            }
        }

        if (AiSettings.isHealingEnabled() && HEALS_THIS_RUN.get() < AiSettings.maxHealsPerRun()) {
            LOG.warn("AI heal enabled; add com.headout:autoheal-locator via -Pai-heal for LLM fallback");
        }

        return driver.findElement(primary);
    }

    public record Fallback(String name, By by) {}
}
