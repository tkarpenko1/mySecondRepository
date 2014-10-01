package com.dataart.selenium.tests;

import com.dataart.selenium.pages.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.fest.assertions.Assertions.assertThat;
import static com.dataart.selenium.framework.BasePage.initPage;
import static com.dataart.selenium.models.UserBuilder.admin;

/**
 * Created by tkarpenko on 15.09.2014.
 */

public class ApplicationTest extends LoginTest {
    public ApplicationPage applicationPage;

    @BeforeMethod(alwaysRun = true)
    @Override
    public void openLoginPage() {
        basicPage = initPage(BasicPage.class);
        loginPage = basicPage.forceLogout();
        headerPage = initPage(HeaderPage.class);
        user = admin();
        applicationPage = initPage(ApplicationPage.class);
        loginPage.loginAs(user);
    }

    @Test
    public void parsingTest() {
        applicationPage.noteInformationApplication();
        applicationPage.noteInformationJson();
        applicationPage.checkInformation();
    }

    @Test
    public void creatingApplication() {
        applicationPage.creatingNewApplication();
        assertThat(applicationPage.isTestedApplicationPresent()).isTrue();
        applicationPage.checkDownloading();
    }

    @Test
    public void editApplication() {
        applicationPage.testedApplicationEdit();
        applicationPage.verifyEding();
    }

    @Test
    public void creatingApplicationWithImageIcon() {
        applicationPage.creatingNewApplicationWithImageIcon();
    }

    @Test
    public void inMostPopularSection() {
        applicationPage.downloadingManyTimes();
        assertThat(applicationPage.isTestedApplicationInMostPopular()).isTrue();
        applicationPage.linkToDetailPage();
    }

    @Test
    public void deleteAnApplication () {
        applicationPage.deleteApplication();
        applicationPage.checkDeleting();
    }

}
