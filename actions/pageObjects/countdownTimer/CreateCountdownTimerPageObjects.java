package pageObjects.countdownTimer;

import commons.PageGenerator;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.countdownTimer.CreateCountdownTimerPageUI;

public class CreateCountdownTimerPageObjects extends CountdownTimerPageObjects{
    WebDriver driver;
    public CreateCountdownTimerPageObjects (WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    @Step("Click To checked Active checkbox")
    public void clickToActiveCheckbox() {
        waitForElementClickable(driver, CreateCountdownTimerPageUI.ACTIVE_CHECKBOX);

        if (!isElementSelected(driver, CreateCountdownTimerPageUI.ACTIVE_CHECKBOX)){
            clickToElementByJS(driver, CreateCountdownTimerPageUI.ACTIVE_CHECKBOX);
        }
    }

    @Step("Click to checked schedule time checkbox")
    public void clickToScheduleTimeCheckbox() {
        waitForElementClickable(driver, CreateCountdownTimerPageUI.SCHEDULE_START_CHECKBOX);

        if (!isElementSelected(driver, CreateCountdownTimerPageUI.SCHEDULE_START_CHECKBOX)){
            clickToElementByJS(driver, CreateCountdownTimerPageUI.SCHEDULE_START_CHECKBOX);
        }
    }

    @Step("Send key to start date picker, {0}")
    public void sendKeyToStartDatePicker(String startDate) {
        waitForElementVisible(driver, CreateCountdownTimerPageUI.START_DATE_PICKER);
        sendkeyToElement(driver, CreateCountdownTimerPageUI.START_DATE_PICKER, startDate);
    }

    @Step("Send key to expire date picker, {0}")
    public void sendKeyToExpireDatePicker(String expireDate) {
        waitForElementVisible(driver, CreateCountdownTimerPageUI.EXPIRE_DATE_PICKER);
        clearAndTypeUsingActions(driver, expireDate,CreateCountdownTimerPageUI.EXPIRE_DATE_PICKER);
    }

    @Step("Send key to title textbox, {0}")
    public void sendKeyToTitleTextbox(String title) {
        waitForElementVisible(driver, CreateCountdownTimerPageUI.TITLE_UNIT_TEXTBOX);
        clearAndTypeUsingActions(driver, title,CreateCountdownTimerPageUI.TITLE_UNIT_TEXTBOX);
    }

    @Step("Send key to day unit label textbox, {0}")
    public void sendKeyToDayUnitTextbox(String dayLabel) {
        waitForElementVisible(driver, CreateCountdownTimerPageUI.DAY_UNIT_TEXTBOX);
        sendkeyToElement(driver, CreateCountdownTimerPageUI.DAY_UNIT_TEXTBOX, dayLabel);
    }

    @Step("Send key to hour unit label textbox, {0}")
    public void sendKeyToHourUnitTextbox(String hourLabel) {
        waitForElementVisible(driver, CreateCountdownTimerPageUI.HOUR_UNIT_TEXTBOX);
        sendkeyToElement(driver, CreateCountdownTimerPageUI.HOUR_UNIT_TEXTBOX, hourLabel);
    }

    @Step("Send key to minute unit label textbox, {0}")
    public void sendKeyToMinuteUnitTextbox(String minuteLabel) {
        waitForElementVisible(driver, CreateCountdownTimerPageUI.MINUTE_UNIT_TEXTBOX);
        sendkeyToElement(driver, CreateCountdownTimerPageUI.MINUTE_UNIT_TEXTBOX, minuteLabel);
    }

    @Step("Send key to second unit label textbox, {0}")
    public void sendKeyToSecondUnitTextbox(String secondLabel) {
        waitForElementVisible(driver, CreateCountdownTimerPageUI.SECOND_UNIT_TEXTBOX);
        sendkeyToElement(driver, CreateCountdownTimerPageUI.SECOND_UNIT_TEXTBOX, secondLabel);
    }

    @Step("Get countdown timer item status")
    public String getCountdownTimerItemStatus() {
        waitForElementVisible(driver, CreateCountdownTimerPageUI.COUNTDOWN_TIMER_STATUS);
        return getWebElementText(driver, CreateCountdownTimerPageUI.COUNTDOWN_TIMER_STATUS);
    }

    @Step("Get day label text in preview")
    public String getHourLabelTextPreview() {
        waitForElementVisible(driver, CreateCountdownTimerPageUI.HOUR_LABEL_IN_PREVIEW);
        return getWebElementText(driver, CreateCountdownTimerPageUI.HOUR_LABEL_IN_PREVIEW);
    }

    @Step("Get minute label text in preview")
    public String getMinuteLabelTextPreview() {
        waitForElementVisible(driver, CreateCountdownTimerPageUI.MINUTE_LABEL_IN_PREVIEW);
        return getWebElementText(driver, CreateCountdownTimerPageUI.MINUTE_LABEL_IN_PREVIEW);
    }

    @Step("Get second label text in preview")
    public String getSecondLabelTextPreview() {
        waitForElementVisible(driver, CreateCountdownTimerPageUI.SECOND_LABEL_IN_PREVIEW);
        return getWebElementText(driver, CreateCountdownTimerPageUI.SECOND_LABEL_IN_PREVIEW);
    }

    @Step("Click to back icon")
    public CountdownTimerPageObjects clickToBackIcon() {
        waitForElementClickable(driver, CreateCountdownTimerPageUI.BACK_ICON);
        clickToElement(driver, CreateCountdownTimerPageUI.BACK_ICON);
        return PageGenerator.getCountdownTimerPage(driver);
    }

    public String getDayLabelTextPreview() {
        waitForElementVisible(driver, CreateCountdownTimerPageUI.DAY_LABEL_IN_PREVIEW);
        return getWebElementText(driver, CreateCountdownTimerPageUI.DAY_LABEL_IN_PREVIEW);
    }
}
