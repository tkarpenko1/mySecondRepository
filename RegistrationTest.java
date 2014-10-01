package com.dataart.selenium.tests;

import com.dataart.selenium.models.User;
import com.dataart.selenium.pages.BasicPage;
import com.dataart.selenium.pages.HeaderPage;
import com.dataart.selenium.pages.RegistrationPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static com.dataart.selenium.models.UserBuilder.newUser;
import static com.dataart.selenium.models.UserBuilder.newDeveloper;
import static com.dataart.selenium.framework.BasePage.initPage;
import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by tkarpenko on 08.09.2014.
 */

public class RegistrationTest extends LoginTest {
    public RegistrationPage registrationPage;
    private User ruser;

    @BeforeMethod(alwaysRun = true)
    @Override
    public void openLoginPage () {
        basicPage = initPage(BasicPage.class);
        loginPage = basicPage.forceLogout();
        headerPage = initPage(HeaderPage.class);
        ruser = newUser();
        registrationPage = initPage(RegistrationPage.class);
    }

    @Test
    public void registrationLoginTest() {
        registrationPage.registrationAsUser();
        assertHeader(newUser());
    }

    @Test
    public void registrationLogoutLoginTest() {
        registrationPage.registrationAsUser();
        registrationPage.registrationLogout();
        loginPage.loginAs(ruser);
        loginPage.waitFor();
        assertHeader(newUser());
    }

    @Test
    public void registrationDeveloperUploadTest () {
        registrationPage.registrationAsDeveloper();
        assertHeader(newDeveloper());
        registrationPage.registrationAddApplication();
        loginPage.waitFor();
        assertThat(headerPage.getApplicationMessage()).isEqualTo("New application");
    }

    @Test
    public void registrationUserCannotUploadTest(){
        registrationPage.registrationAsUser();
        assertThat(headerPage.isApplicationPresent()).isTrue();
        assertThat(headerPage.isMyApplicationPresent()).isFalse();
    }

}
