package com.dataart.selenium.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;


/**
 * Created by tkarpenko on 11.09.2014.
 */

public class JSPage extends BasicPage {

    @FindBy (xpath = JS_LINK_XPATH)
    WebElement jsLink;
    @FindBy (css = TOP_FIELD_CSS)
    WebElement topField;
    @FindBy (css = LEFT_FIELD_CSS)
    WebElement leftField;
    @FindBy (css = BUTTON_PROCESS_CSS)
    WebElement buttonProcess;

    public void elementPosition() {
        jsLink.click();
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        String top = jse.executeScript("return document.getElementsByClassName('flash')[0].offsetTop").toString();
        String left = jse.executeScript("return document.getElementsByClassName('flash')[0].offsetLeft").toString();
        topField.clear();
        leftField.clear();
        topField.sendKeys(top);
        leftField.sendKeys(left);
        buttonProcess.click();
    }

    public String alertMessage () {
        return driver.switchTo().alert().getText();
    }

    public static final String JS_LINK_XPATH ="//a[.='JS test page']";
    public static final String TOP_FIELD_CSS = "#top";
    public static final String LEFT_FIELD_CSS = "#left";
    public static final String BUTTON_PROCESS_CSS = "#process";
}
