package com.automation.extensions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a test as requiring an authenticated SauceDemo session (ADR-009).
 *
 * <p>When present on a {@code @Test} method, {@link SauceAuthExtension} will
 * inject cached cookies from {@code .auth/sauce.json} into the WebDriver before
 * the test executes, bypassing the login UI.
 *
 * <p>Usage:
 * <pre>{@code
 * @ExtendWith({WebDriverExtension.class, SauceAuthExtension.class})
 * class MyTest {
 *     @Test
 *     @SauceAuthenticated
 *     void inventoryFlow(WebDriver driver) {
 *         // driver is already on the inventory page
 *     }
 * }
 * }</pre>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface SauceAuthenticated {
}
