package com.dataart.selenium.pages;

import com.dataart.selenium.framework.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by tkarpenko on 10.09.2014.
 */

public class AjaxPage extends BasePage {

    private int x=5, y=10, sum=x+y;

    @FindBy (xpath = AJAX_LINK_XPATH)
    WebElement ajaxLink;
    @FindBy (xpath = X_FIELD_XPATH)
    WebElement xField;
    @FindBy (xpath = Y_FIELD_XPATH)
    WebElement yField;
    @FindBy (xpath = SUM_BUTTON_XPATH)
    WebElement sumButton;
    @FindBy (xpath = RESULT_XPATH)
    WebElement result;

    public void openSimpleCalculator() {
        ajaxLink.click();
    }

    public void fillBasicFields () {
        xField.clear();
        yField.clear();
        String strX=Integer.toString(x);
        xField.sendKeys(strX);
    }

    public void enterCorrectValue () {
        fillBasicFields();
        String strY = Integer.toString(y);
        yField.sendKeys(strY);
    }

    public void waitResult () {
        sumButton.click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(result));
    }
    public boolean checkCorrectResult() {
        boolean sumFlag;
        String allResult = result.getText();
        String splitResult = allResult.split(" ")[2];
        String splitPart = splitResult.split(".0")[0];
        int calculatedSum = Integer.parseInt(splitPart);
       if (calculatedSum==sum){sumFlag=true;}
        else sumFlag=false;
        return sumFlag;
    }

    public void enterIncorrectValue() {
        fillBasicFields();
        yField.sendKeys("Some text");
    }

    public String checkErrorResult() {
        return result.getText();
    }

    public static final String AJAX_LINK_XPATH = "//a[@href='/calc/']";
    public static final String X_FIELD_XPATH = "//*[@id='x']";
    public static final String Y_FIELD_XPATH = "//*[@id='y']";
    public static final String SUM_BUTTON_XPATH = "//*[@id='calc']";
    public static final String RESULT_XPATH = "//*[@id='result']";
}
