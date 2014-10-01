package com.dataart.selenium.tests;


import com.dataart.selenium.pages.AjaxPage;
import com.dataart.selenium.pages.BasicPage;
import com.dataart.selenium.pages.HeaderPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.fest.assertions.Assertions.assertThat;
import static com.dataart.selenium.framework.BasePage.initPage;
import static com.dataart.selenium.models.UserBuilder.admin;

/**
 * Created by tkarpenko on 10.09.2014.
 */

public class AjaxTest extends LoginTest {

    private AjaxPage ajaxPage;

    @BeforeMethod(alwaysRun = true)
    @Override
    public void openLoginPage() {
        basicPage = initPage(BasicPage.class);
        loginPage = basicPage.forceLogout();
        headerPage = initPage(HeaderPage.class);
        user = admin();
        ajaxPage = initPage(AjaxPage.class);
        loginPage.loginAs(user);
    }

    @Test
    public void correctAjaxTest () {
        ajaxPage.openSimpleCalculator();
        ajaxPage.enterCorrectValue();
        ajaxPage.waitResult();
        assertThat(ajaxPage.checkCorrectResult()).isTrue();
    }

    @Test
    public void inCorrectAjaxTest() {
        ajaxPage.openSimpleCalculator();
        ajaxPage.enterIncorrectValue();
        ajaxPage.waitResult();
        assertThat(ajaxPage.checkErrorResult()).isEqualTo("Result is: Incorrect data");
    }

}

