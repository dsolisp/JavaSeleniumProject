package com.automation.pages.practice;

import com.automation.pages.BasePage;
import com.automation.locators.practice.IframesLocators;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * IframesPage — Practice App Iframes page (ADV-E3, ADV-E4).
 * 
 * Responsibilities: navigate, switch frame context, interact with frame content.
 * No assertions — callers decide what to assert (Law 2).
 * Inherits BasePage only (max 1 level — Law 4).
 */
public class IframesPage extends BasePage {

    private static final String PRACTICE_BASE_URL = "http://localhost:8080";

    public IframesPage(WebDriver driver) {
        super(driver);
    }

    public IframesPage open() {
        driver.get(PRACTICE_BASE_URL + "/iframes.html");
        return this;
    }

    // ── ADV-E3: Simple iframe (contenteditable editor) ────────────────────

    public boolean isParentFrameVisible() {
        return driver.findElements(IframesLocators.PARENT_FRAME).size() > 0 &&
               driver.findElement(IframesLocators.PARENT_FRAME).isDisplayed();
    }

    public IframesPage typeInEditor(String text) {
        WebElement frame = wait.until(ExpectedConditions.presenceOfElementLocated(IframesLocators.PARENT_FRAME));
        driver.switchTo().frame(frame);
        WebElement editor = wait.until(ExpectedConditions.elementToBeClickable(IframesLocators.EDITOR));
        editor.click();
        editor.sendKeys(text);
        driver.switchTo().defaultContent();
        return this;
    }

    public String getEditorText() {
        WebElement frame = wait.until(ExpectedConditions.presenceOfElementLocated(IframesLocators.PARENT_FRAME));
        driver.switchTo().frame(frame);
        WebElement editor = wait.until(ExpectedConditions.presenceOfElementLocated(IframesLocators.EDITOR));
        String text = editor.getText();
        driver.switchTo().defaultContent();
        return text;
    }

    public IframesPage clearEditor() {
        WebElement frame = wait.until(ExpectedConditions.presenceOfElementLocated(IframesLocators.PARENT_FRAME));
        driver.switchTo().frame(frame);
        WebElement editor = wait.until(ExpectedConditions.presenceOfElementLocated(IframesLocators.EDITOR));
        ((JavascriptExecutor) driver).executeScript("arguments[0].innerHTML = '';", editor);
        driver.switchTo().defaultContent();
        return this;
    }

    // ── ADV-E4: Nested iframes ─────────────────────────────────────────────

    public boolean isOuterFrameVisible() {
        return driver.findElements(IframesLocators.OUTER_FRAME).size() > 0 &&
               driver.findElement(IframesLocators.OUTER_FRAME).isDisplayed();
    }

    public IframesPage submitInnerForm(String name, String email) {
        WebElement outerFrame = wait.until(ExpectedConditions.presenceOfElementLocated(IframesLocators.OUTER_FRAME));
        driver.switchTo().frame(outerFrame);
        WebElement childFrame = wait.until(ExpectedConditions.presenceOfElementLocated(IframesLocators.CHILD_FRAME));
        driver.switchTo().frame(childFrame);

        wait.until(ExpectedConditions.visibilityOfElementLocated(IframesLocators.INNER_NAME)).sendKeys(name);
        wait.until(ExpectedConditions.visibilityOfElementLocated(IframesLocators.INNER_EMAIL)).sendKeys(email);
        wait.until(ExpectedConditions.elementToBeClickable(IframesLocators.INNER_SUBMIT)).click();

        driver.switchTo().defaultContent();
        return this;
    }

    public String getInnerResult() {
        WebElement outerFrame = wait.until(ExpectedConditions.presenceOfElementLocated(IframesLocators.OUTER_FRAME));
        driver.switchTo().frame(outerFrame);
        WebElement childFrame = wait.until(ExpectedConditions.presenceOfElementLocated(IframesLocators.CHILD_FRAME));
        driver.switchTo().frame(childFrame);

        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(IframesLocators.INNER_RESULT)).getText();

        driver.switchTo().defaultContent();
        return text;
    }
}
