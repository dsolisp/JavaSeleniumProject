package com.automation.extensions;

import com.automation.pages.BasePage;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Constructor;

/**
 * Injects page objects into test methods so specs never call {@code new XxxPage(driver)}.
 *
 * <p>Resolves any {@link BasePage} subclass that exposes a {@code (WebDriver)} constructor,
 * sourcing the active driver from {@link WebDriverExtension}. Register it after
 * {@code WebDriverExtension} so the driver is available at resolution time:
 *
 * <pre>{@code
 * @ExtendWith({WebDriverExtension.class, PageObjectExtension.class})
 * class MyTest {
 *     @Test
 *     void myTest(LoginPage loginPage) { ... }
 * }
 * }</pre>
 */
public class PageObjectExtension implements ParameterResolver {

    @Override
    public boolean supportsParameter(ParameterContext parameterContext,
                                     ExtensionContext extensionContext) {
        return BasePage.class.isAssignableFrom(parameterContext.getParameter().getType());
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext,
                                   ExtensionContext extensionContext) {
        Class<?> type = parameterContext.getParameter().getType();
        WebDriver driver = WebDriverExtension.getDriver();
        if (driver == null) {
            throw new ParameterResolutionException(
                    "No active WebDriver for " + type.getSimpleName()
                            + " — register WebDriverExtension before PageObjectExtension");
        }
        try {
            Constructor<?> constructor = type.getConstructor(WebDriver.class);
            return constructor.newInstance(driver);
        } catch (ReflectiveOperationException e) {
            throw new ParameterResolutionException(
                    "Cannot construct " + type.getSimpleName() + " with a WebDriver argument", e);
        }
    }
}
