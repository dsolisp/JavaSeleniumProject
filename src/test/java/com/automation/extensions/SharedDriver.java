package com.automation.extensions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a test class to share a single WebDriver across all tests in the class.
 *
 * <p>When present on a class, {@link WebDriverExtension} creates the driver once
 * in {@code beforeAll} and quits it in {@code afterAll}. Between tests, cookies
 * and {@code localStorage} are cleared so each test starts from a clean slate.
 *
 * <p>Mirrors the C# {@code IClassFixture<WebTestBase>} and Python session-scoped
 * fixture patterns, reducing Chrome process churn that triggers
 * {@code "Timed out receiving message from renderer"} flakes.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface SharedDriver {
}
