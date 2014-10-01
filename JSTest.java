package com.dataart.selenium.tests;

import com.dataart.selenium.pages.BasicPage;
import com.dataart.selenium.pages.HeaderPage;
import com.dataart.selenium.pages.JSPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static com.dataart.selenium.framework.BasePage.initPage;
import static com.dataart.selenium.models.UserBuilder.admin;
import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by tkarpenko on 11.09.2014.
 */

public class JSTest extends LoginTest {
    private JSPage jsPage;

    @BeforeMethod(alwaysRun = true)
    @Override
    public void openLoginPage() {
       basicPage = initPage(BasicPage.class);
        loginPage = basicPage.forceLogout();
        headerPage = initPage(HeaderPage.class);
        user = admin();
        jsPage = initPage(JSPage.class);
        loginPage.loginAs(user);
    }

    @Test
    public void jsTest(){
        jsPage.elementPosition();
        assertThat(jsPage.alertMessage()).isEqualTo("Whoo Hoooo! Correct!");
    }

}
