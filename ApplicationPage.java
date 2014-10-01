package com.dataart.selenium.pages;

import com.dataart.selenium.framework.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static org.fest.assertions.Assertions.assertThat;
import java.io.File;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

/**
 * Created by tkarpenko on 15.09.2014.
 */

public class ApplicationPage extends BasePage {

    private String title="Tested",
            description = "Just a sample description for this application [Application Tested]",
            descriptionNew = "New Just a sample description for this application [Application Tested]",
            descriptionImage = "With Image. Just a sample description for this application [Application With Image]";
    private File fileImage = new File("//C:/Users/tkarpenko/Documents/automation/image.jpg"),
           fileIcon = new File("//C:/Users/tkarpenko/Documents/automation/icon.jpg");
    private String nameApp, descriptionAppParametr, descriptionAppValue, categoryAppParametr,  authorAppParametr, categoryAppValue, authorAppValue, downloadAppParametr, downloadAppValue;
    JSONObject tempJsn = new JSONObject();

    @FindBy (xpath = APPLICATION_DETAIL_LINK_XPATH)
    WebElement applicationDetailLink;
    @FindBy (css = APPLICATION_NAME_CSS)
    WebElement applicationName;
    @FindBy (css = APPLICATION_DESCRIPTION_CSS)
    WebElement applicationDescription;
    @FindBy (css = APPLICATION_CATEGORY_CSS)
    WebElement applicationCategory;
    @FindBy (css = APPLICATION_AUTHOR_CSS)
    WebElement applicationAuthor;
    @FindBy (css = APPLICATION_DOWNLOADS_CSS)
    WebElement applicationDownloads;
    @FindBy (xpath = APPLICATION_DOWNLOAD_XPATH)
    WebElement applicationDownload;
    @FindBy (xpath = JSON_INFORMATION_XPATH)
    WebElement jsonInformation;
    @FindBy (css = MY_APPLICATIONS_LINK_CSS)
    WebElement myApplicationsLink;
    @FindBy (css = ADD_NEW_APPLICATION_LINK_CSS)
    WebElement addNewApplication;
    @FindBy (css = TITLE_FIELD_CSS)
    WebElement titleField;
    @FindBy (css = DESCRIPTION_TEXTAREA_CSS)
    WebElement descriptionTextarea;
    @FindBy (css = CATEGORY_SELECT_CSS)
    WebElement categorySelect;
    @FindBy (xpath = CATEGORY_SELECT_GAMES_XPATH)
    WebElement categorySelectGames;
    @FindBy (xpath = CATEGORY_SELECT_FUN_XPATH)
    WebElement categorySelectFun;
    @FindBy (xpath = BUTTON_XPATH)
    WebElement button;
    @FindBy (css = NAVIGATE_TO_HOME_CSS)
    WebElement navigateToHome;
    @FindBy (xpath = NAVIGATE_TO_GAME_CATEGORY_XPATH)
    WebElement navigateToGameCategory;
    @FindBy (xpath = APPLICATION_TESTED_XPATH)
    WebElement applicationTested;
    @FindBy (css = APPLICATION_TESTED_DETAIL_CSS)
    WebElement applicationTestedDetail;
    @FindBy (css = APPLICATION_TESTED_DOWNLOAD_CSS)
    WebElement applicationTestedDownload;
    @FindBy (css = APPLICATION_TESTED_EDIT_CSS)
    WebElement applicationTestedEdit;
    @FindBy (xpath = NAVIGATE_TO_FUN_CATEGORY_XPATH)
    WebElement navigateToFunCategory;
    @FindBy (css = DESCRIPTION_TEXT_CSS)
    WebElement descriptionText;
    @FindBy (css = IMAGE_BROWSE_BUTTON_CSS)
    WebElement imageBrowseButton;
    @FindBy (css = ICON_BROWSE_BUTTON_CSS)
    WebElement iconBrowseButton;
    @FindBy (xpath = APPLICATION_TESTED_IN_MOST_POPULAR_XPATH)
    WebElement applicationTestedInMostPopular;
    @FindBy (css = APPLICATION_TESTED_DELETE_BUTTON_CSS)
    WebElement applicationTestedDeleteButton;

    public void openApplication() {
        applicationDetailLink.click();
    }

    public void noteInformationApplication() {
        openApplication();
        nameApp = applicationName.getText().split(":")[0];
        String descriptionApp = applicationDescription.getText();
        descriptionAppParametr = descriptionApp.split(":")[0].toLowerCase();
        descriptionAppValue = descriptionApp.split(":")[1].trim();
        String categoryApp = applicationCategory.getText();
        categoryAppParametr = categoryApp.split(":")[0].toLowerCase();
        categoryAppValue = categoryApp.split(":")[1];
        String authorApp = applicationAuthor.getText();
        authorAppParametr = authorApp.split(":")[0].toLowerCase();
        authorAppValue = authorApp.split(":")[1];
        String downloadsApp = applicationDownloads.getText();
        downloadAppParametr = downloadsApp.split(":")[0];
        downloadAppValue = downloadsApp.split(":")[1];
    }

    public void openApplicationDownload() {
        applicationDownload.click();
    }

    public String noteJson() {
        return jsonInformation.getText();
    }

    public void noteInformationJson() {
        openApplicationDownload();
        String noteJson = noteJson();
        JSONParser parser=new JSONParser();
        try{
            Object obj = parser.parse(noteJson);
            tempJsn  = (JSONObject) obj;
        }catch(ParseException pe) {}
    }

   public void checkInformation() {
        assertThat(tempJsn.containsValue(nameApp)).isTrue();
        assertThat(tempJsn.containsKey(descriptionAppParametr)).isTrue();
        assertThat(tempJsn.containsValue(descriptionAppValue)).isTrue();
        assertThat(tempJsn.containsKey(categoryAppParametr)).isTrue();
      //assertThat(tempJsn.containsValue(categoryAppValue)).isTrue();
        assertThat(tempJsn.containsKey(authorAppParametr)).isTrue();
     // assertThat(tempJsn.containsValue(authorAppValue)).isTrue();
     // assertThat(tempJsn.containsKey(downloadAppParametr)).isTrue();
      //assertThat(tempJsn.containsValue(downloadAppValue)).isTrue();
    }

    public void navigateToCreateNewApplication() {
        myApplicationsLink.click();
        addNewApplication.click();
    }

    public void clearFields() {
        titleField.clear();
        descriptionTextarea.clear();
    }

    public void creatingNewApplication() {
        navigateToCreateNewApplication();
        clearFields();
        titleField.sendKeys(title);
        descriptionTextarea.sendKeys(description);
        categorySelect.click();
        categorySelectGames.click();
        button.click();
    }

    public boolean isTestedApplicationPresent(){
        try {
            assertThat(applicationTested.isDisplayed());
                    return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void checkDownloading() {
        applicationTestedDetail.click();
        assertThat(applicationTestedDownload.isDisplayed()).isTrue();
        applicationTestedDownload.click();
    }

    public void navigatedToTestedApplication() {
        applicationTestedDetail.click();
    }

    public void ifApplicationNotPresentCreateIt() {
        if (isTestedApplicationPresent()==false)
            creatingNewApplication();
    }

    public void testedApplicationEdit() {
        ifApplicationNotPresentCreateIt();
        navigatedToTestedApplication();
        applicationTestedEdit.click();
        descriptionTextarea.clear();
        descriptionTextarea.sendKeys(descriptionNew);
        categorySelect.click();
        categorySelectFun.click();
        button.click();
    }

    public void verifyEding () {
        navigateToHome.click();
        navigateToGameCategory.click();
        assertThat(isTestedApplicationPresent()).isFalse();
        navigateToFunCategory.click();
        assertThat(isTestedApplicationPresent()).isTrue();
        applicationTestedDetail.click();
        String description = descriptionText.getText().split(":")[1].trim();
        assertThat(description).isEqualTo(descriptionNew);
    }

    public void creatingNewApplicationWithImageIcon() {
        navigateToCreateNewApplication();
        clearFields();
        titleField.sendKeys("With image");
        descriptionTextarea.sendKeys(descriptionImage);
        categorySelect.click();
        categorySelectGames.click();
        imageBrowseButton.sendKeys(fileImage.getAbsolutePath());
        iconBrowseButton.sendKeys(fileIcon.getAbsolutePath());
        button.click();
    }

    public void deleteTestedApplication() {
            applicationTestedDetail.click();
            applicationTestedDeleteButton.click();
            driver.switchTo().alert().accept();
    }

    public void downloadingManyTimes() {
        if (isTestedApplicationPresent()==true)
            deleteTestedApplication();
        creatingNewApplication();
        applicationTestedDetail.click();
        int n=0;
        while (n<10) {
            applicationTestedDownload.click();
            driver.navigate().back();
            n++;
        }
        driver.navigate().refresh();
    }

    public boolean isTestedApplicationInMostPopular(){
        try {
            assertThat(applicationTestedInMostPopular.isDisplayed());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void linkToDetailPage() {
        applicationTestedInMostPopular.click();
        assertThat(applicationTestedDownload.isDisplayed()).isTrue();
    }

    public void deleteApplication() {
        ifApplicationNotPresentCreateIt();
        deleteTestedApplication();
    }

    public void checkDeleting() {
        navigateToHome.click();
        assertThat(isTestedApplicationPresent()).isFalse();
    }

    public static final String APPLICATION_DETAIL_LINK_XPATH = "//div[@class='apps']/div[1]/a";
    public static final String APPLICATION_NAME_CSS = ".name";
    public static final String APPLICATION_DESCRIPTION_CSS = ".name + .description";
    public static final String APPLICATION_CATEGORY_CSS = ".name + * + .description";
    public static final String APPLICATION_AUTHOR_CSS = ".description + * + .description";
    public static final String APPLICATION_DOWNLOADS_CSS = ".downloads";
    public static final String APPLICATION_DOWNLOAD_XPATH = "//a[.='Download']";
    public static final String JSON_INFORMATION_XPATH = "//pre";
    public static final String MY_APPLICATIONS_LINK_CSS = "a[href='/my']";
    public static final String ADD_NEW_APPLICATION_LINK_CSS = "a[href='/new']";
    public static final String TITLE_FIELD_CSS = "input[name='title']";
    public static final String DESCRIPTION_TEXTAREA_CSS = "textarea[name='description']";
    public static final String CATEGORY_SELECT_CSS = "select[name='category']";
    public static final String CATEGORY_SELECT_GAMES_XPATH = "//option[contains(text(), 'Games')]";
    public static final String CATEGORY_SELECT_FUN_XPATH = "//option[contains(text(), 'Fun')]";
    public static final String BUTTON_XPATH ="//input[@type='submit']";
    public static final String NAVIGATE_TO_HOME_CSS = "a[href='/']";
    public static final String NAVIGATE_TO_GAME_CATEGORY_XPATH = "//a[.='Games']";
    public static final String NAVIGATE_TO_FUN_CATEGORY_XPATH = "//a[.='Fun']";
    public static final String APPLICATION_TESTED_XPATH = "//div[@class='app']/img[@alt='Tested']/..";
    public static final String APPLICATION_TESTED_DETAIL_CSS = "a[href='/app?title=Tested']";
    public static final String APPLICATION_TESTED_DOWNLOAD_CSS = "a[href='/download?title=Tested']";
    public static final String APPLICATION_TESTED_EDIT_CSS = "a[href='/edit?title=Tested']";
    public static final String APPLICATION_TESTED_DELETE_BUTTON_CSS = "a[href='/delete?title=Tested']";
    public static final String DESCRIPTION_TEXT_CSS = ".name + .description";
    public static final String IMAGE_BROWSE_BUTTON_CSS = "input[name='image']";
    public static final String ICON_BROWSE_BUTTON_CSS = "input[name='icon']";
    public static final String APPLICATION_TESTED_IN_MOST_POPULAR_XPATH = "//div[@class='popular-container']/div[@class='popular-app']/a[@href='/app?title=Tested']";

}
