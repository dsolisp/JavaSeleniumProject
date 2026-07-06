package com.automation.pages.practice;

import com.automation.config.Settings;
import com.automation.pages.BasePage;
import com.automation.locators.practice.SelectorsLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * SelectorsPage — Practice App Selector Playground.
 * 
 * No assertions — callers decide what to assert (Law 2).
 * Inherits BasePage only (max 1 level — Law 4).
 */
public class SelectorsPage extends BasePage {

    public SelectorsPage(WebDriver driver) {
        super(driver);
    }

    public SelectorsPage open() {
        driver.get(Settings.getInstance().getPracticeAppUrl() + "/selectors.html");
        return this;
    }

    public String getUsernameInputAttribute(String attr) {
        return getAttribute(SelectorsLocators.INPUT_USERNAME, attr);
    }

    public String getPasswordInputAttribute(String attr) {
        return getAttribute(SelectorsLocators.INPUT_PASSWORD, attr);
    }

    public String getPrimaryButtonClass() {
        return getAttribute(SelectorsLocators.BTN_PRIMARY, "class");
    }

    public String getSecondaryButtonClass() {
        return getAttribute(SelectorsLocators.BTN_SECONDARY, "class");
    }

    public String getSuccessBadgeText() {
        return getText(SelectorsLocators.BADGE_SUCCESS);
    }

    public String getWarningBadgeText() {
        return getText(SelectorsLocators.BADGE_WARNING);
    }

    public String getErrorBadgeText() {
        return getText(SelectorsLocators.BADGE_ERROR);
    }

    public String getExactLinkText() {
        return getText(SelectorsLocators.LINK_EXACT);
    }

    public String getPartialLinkText() {
        return getText(SelectorsLocators.LINK_PARTIAL);
    }

    public String getAriaLinkAttribute(String attr) {
        return getAttribute(SelectorsLocators.LINK_ARIA, attr);
    }

    public String getEmailInputAttribute(String attr) {
        return getAttribute(SelectorsLocators.INPUT_EMAIL, attr);
    }

    public String getLiveRegionText() {
        return getText(SelectorsLocators.LIVE_REGION);
    }

    public SelectorsPage triggerLiveRegion() {
        click(SelectorsLocators.BTN_TRIGGER_LIVE);
        return this;
    }

    public boolean isInputDisabled() {
        WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(SelectorsLocators.INPUT_DISABLED));
        return !el.isEnabled();
    }

    public boolean isRadioProChecked() {
        WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(SelectorsLocators.RADIO_PRO));
        return el.isSelected();
    }

    public boolean isRadioBasicChecked() {
        WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(SelectorsLocators.RADIO_BASIC));
        return el.isSelected();
    }

    public SelectorsPage selectCountry(String value) {
        WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(SelectorsLocators.SELECT_COUNTRY));
        new Select(el).selectByValue(value);
        return this;
    }

    public int getProductItemsCount() {
        return driver.findElements(SelectorsLocators.PRODUCT_ITEM).size(); // gavel-ignore: selector-leak
    }

    public int getElectronicsItemsCount() {
        return driver.findElements(SelectorsLocators.PRODUCT_ELECTRONICS).size(); // gavel-ignore: selector-leak
    }

    public String getLogoAttribute(String attr) {
        return getAttribute(SelectorsLocators.IMG_LOGO, attr);
    }

    public String getSaveButtonTitle() {
        return getAttribute(SelectorsLocators.BTN_SAVE, "title");
    }

    public String getDeleteButtonTitle() {
        return getAttribute(SelectorsLocators.BTN_DELETE, "title");
    }

    public String getAbbrQaTitle() {
        return getAttribute(SelectorsLocators.ABBR_QA, "title");
    }

    public int getTableRowsCount() {
        return driver.findElements(SelectorsLocators.TABLE_ROW).size(); // gavel-ignore: selector-leak
    }

    public String getTableRowNameCellText(String rowId) {
        return getText(SelectorsLocators.tableRowNameCell(rowId));
    }

    public int getFruitItemsCount() {
        return driver.findElements(SelectorsLocators.FRUIT_ITEM).size(); // gavel-ignore: selector-leak
    }

    public String getFruitItemText(int index) {
        List<WebElement> items = driver.findElements(SelectorsLocators.FRUIT_ITEM); // gavel-ignore: selector-leak
        return index < items.size() ? items.get(index).getText() : "";
    }

    public String getXpathText() {
        return getText(SelectorsLocators.XPATH_TEXT);
    }

    public String getXpathPartialText() {
        return getText(SelectorsLocators.XPATH_PARTIAL);
    }
}
