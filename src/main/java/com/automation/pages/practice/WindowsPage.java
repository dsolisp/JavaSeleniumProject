package com.automation.pages.practice;

import com.automation.config.Settings;
import com.automation.pages.BasePage;
import com.automation.locators.practice.WindowsLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * WindowsPage — Practice App Windows page (ADV-E5, ADV-E6).
 * 
 * Responsibilities: navigate, open new tabs, switch window handles.
 * No assertions — callers decide what to assert (Law 2).
 * Inherits BasePage only (max 1 level — Law 4).
 */
public class WindowsPage extends BasePage {

    public WindowsPage(WebDriver driver) {
        super(driver);
    }

    public WindowsPage open() {
        driver.get(Settings.getInstance().getPracticeAppUrl() + "/windows.html");
        return this;
    }

    // ── ADV-E5: target="_blank" link ──────────────────────────────────────

    public String getTabLinkHref() {
        return getAttribute(WindowsLocators.OPEN_TAB_LINK, "href");
    }

    public String clickTabLink() {
        String originalHandle = driver.getWindowHandle();
        click(WindowsLocators.OPEN_TAB_LINK);
        return originalHandle;
    }

    // ── ADV-E6: window.open() ─────────────────────────────────────────────

    public boolean isTabButtonVisible() {
        return driver.findElements(WindowsLocators.OPEN_TAB_JS).size() > 0 && // gavel-ignore: selector-leak
               driver.findElement(WindowsLocators.OPEN_TAB_JS).isDisplayed(); // gavel-ignore: selector-leak
    }

    public String getTabButtonText() {
        return getText(WindowsLocators.OPEN_TAB_JS);
    }

    public String clickTabButton() {
        String originalHandle = driver.getWindowHandle();
        click(WindowsLocators.OPEN_TAB_JS);
        return originalHandle;
    }

    // ── Window handle helpers ─────────────────────────────────────────────

    public WindowsPage switchToNewWindow(String originalHandle) {
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }
        return this;
    }

    public WindowsPage switchToWindow(String handle) {
        driver.switchTo().window(handle);
        return this;
    }

    public WindowsPage closeCurrentWindow() {
        driver.close();
        return this;
    }

    // ── /windows/new.html getters (used after switching) ──────────────────

    public String getNewWindowHeading() {
        return getText(WindowsLocators.NEW_WINDOW_HEADING);
    }

    public String getNewWindowBody() {
        return getText(WindowsLocators.NEW_WINDOW_BODY);
    }
}
