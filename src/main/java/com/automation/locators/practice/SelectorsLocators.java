package com.automation.locators.practice;

import org.openqa.selenium.By;

/**
 * SelectorsLocators — locators for /selectors.html.
 * Pure locator definitions — zero logic, zero assertions (Law 1 & Law 2).
 */
public final class SelectorsLocators {

    private SelectorsLocators() {
        // Utility class, no instantiation
    }

    // ── 1 · id & name ──────────────────────────────────────────────────────
    public static final By INPUT_USERNAME = By.cssSelector("[data-test='input-username']");
    public static final By INPUT_PASSWORD = By.cssSelector("[data-test='input-password']");

    // ── 2 · CSS class & attribute ──────────────────────────────────────────
    public static final By BTN_PRIMARY = By.cssSelector("[data-test='btn-primary']");
    public static final By BTN_SECONDARY = By.cssSelector("[data-test='btn-secondary']");
    public static final By BADGE_SUCCESS = By.cssSelector("[data-test='badge-success']");
    public static final By BADGE_WARNING = By.cssSelector("[data-test='badge-warning']");
    public static final By BADGE_ERROR = By.cssSelector("[data-test='badge-error']");

    // ── 3 · link text ──────────────────────────────────────────────────────
    public static final By LINK_EXACT = By.cssSelector("[data-test='link-exact']");
    public static final By LINK_PARTIAL = By.cssSelector("[data-test='link-partial']");
    public static final By LINK_ARIA = By.cssSelector("[data-test='link-aria']");

    // ── 4 · ARIA ───────────────────────────────────────────────────────────
    public static final By INPUT_EMAIL = By.cssSelector("[data-test='input-email']");
    public static final By LIVE_REGION = By.cssSelector("[data-test='live-region']");
    public static final By BTN_TRIGGER_LIVE = By.cssSelector("[data-test='btn-trigger-live']");

    // ── 5 · form attributes ────────────────────────────────────────────────
    public static final By INPUT_DISABLED = By.cssSelector("[data-test='input-disabled']");
    public static final By SELECT_COUNTRY = By.cssSelector("[data-test='select-country']");
    public static final By CHECKBOX_AGREE = By.cssSelector("[data-test='checkbox-agree']");
    public static final By RADIO_BASIC = By.cssSelector("[data-test='radio-basic']");
    public static final By RADIO_PRO = By.cssSelector("[data-test='radio-pro']");

    // ── 6 · data attributes ────────────────────────────────────────────────
    public static final By PRODUCT_LIST = By.cssSelector("[data-test='product-list']");
    public static final By PRODUCT_ITEM = By.cssSelector("[data-test='product-item']");
    public static final By PRODUCT_ELECTRONICS = By.cssSelector("[data-category='electronics']");

    // ── 7 · image ──────────────────────────────────────────────────────────
    public static final By IMG_LOGO = By.cssSelector("[data-test='img-logo']");

    // ── 8 · title attribute ────────────────────────────────────────────────
    public static final By BTN_SAVE = By.cssSelector("[data-test='btn-save']");
    public static final By BTN_DELETE = By.cssSelector("[data-test='btn-delete']");
    public static final By ABBR_QA = By.cssSelector("[data-test='abbr-qa']");

    // ── 9 · table ──────────────────────────────────────────────────────────
    public static final By DATA_TABLE = By.cssSelector("[data-test='data-table']");
    public static final By TABLE_ROW = By.cssSelector("[data-test='table-row']");

    public static By tableRowNameCell(String rowId) {
        return By.cssSelector(String.format("[data-row-id='%s'] [headers='col-name']", rowId));
    }

    // ── 10 · XPath targets ─────────────────────────────────────────────────
    public static final By FRUIT_LIST = By.cssSelector("[data-test='fruit-list']");
    public static final By FRUIT_ITEM = By.cssSelector("[data-test='fruit-item']");
    public static final By XPATH_TEXT = By.cssSelector("[data-test='xpath-text']");
    public static final By XPATH_PARTIAL = By.cssSelector("[data-test='xpath-partial']");
}
