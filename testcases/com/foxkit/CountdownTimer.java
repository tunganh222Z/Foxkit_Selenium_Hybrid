package com.foxkit;

import commons.BaseTest;
import commons.PageGenerator;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.countdownTimer.CountdownTimerPageObjects;
import pageObjects.FoxkitPowerPageObjects;
import pageObjects.countdownTimer.CreateCountdownTimerPageObjects;

public class CountdownTimer extends BaseTest {
    WebDriver driver;
    private FoxkitPowerPageObjects foxkitPowerPage;

    private CountdownTimerPageObjects countdownTimerPage;

    private CreateCountdownTimerPageObjects createCountdownTimerPage;

    private String startTime, expireTime, title, dayLabel, minuteLabel, hourLabel, secondLabel, updatedSuccessfullyMsg, countdownTimerItemStatus;
    @Parameters({"browser", "browserProfile", "url"})
    @BeforeClass
    public void beforeClass(String browser, String browserProfile, String url){
        //data test
        expireTime = "2024/12/27 11:29 AM";

        title = "FOXKIT AUTO TESTING";

        dayLabel = "day123";

        minuteLabel = "minute123";

        hourLabel = "hour123";

        secondLabel = "second123";

        updatedSuccessfullyMsg = "Updated successfully";

        countdownTimerItemStatus = "Active";

        //
        driver = getBrowserDriver(browser, browserProfile, url);

        foxkitPowerPage = PageGenerator.getFoxkitPowerPage(driver);

        foxkitPowerPage.waitFoxifyLoadingInvisible();

        countdownTimerPage = foxkitPowerPage.clickToCountdownTimer();
    }
    @Description("Create countdown timer")
    @Story("Countdown timer")
    @Test
    public void TC_01_Create_Countdown_Timer(){
        createCountdownTimerPage = countdownTimerPage.clickToCreateCountdownButton();

        createCountdownTimerPage.clickToActiveCheckbox();

        createCountdownTimerPage.clickToScheduleTimeCheckbox();

        createCountdownTimerPage.sendKeyToExpireDatePicker(expireTime);

        createCountdownTimerPage.sendKeyToTitleTextbox(title);

        createCountdownTimerPage.sendKeyToDayUnitTextbox(dayLabel);

        createCountdownTimerPage.sendKeyToHourUnitTextbox(hourLabel);

        createCountdownTimerPage.sendKeyToMinuteUnitTextbox(minuteLabel);

        createCountdownTimerPage.sendKeyToSecondUnitTextbox(secondLabel);

        createCountdownTimerPage.clickToSaveButton();

        createCountdownTimerPage.switchToFoxkitIframe();

        verifyEqual(createCountdownTimerPage.getCountdownTimerItemStatus(), countdownTimerItemStatus);

        verifyTrue(createCountdownTimerPage.getDayLabelTextPreview().equalsIgnoreCase(dayLabel));

        verifyTrue(createCountdownTimerPage.getHourLabelTextPreview().equalsIgnoreCase(hourLabel));

        verifyTrue(createCountdownTimerPage.getMinuteLabelTextPreview().equalsIgnoreCase(minuteLabel));

        verifyTrue(createCountdownTimerPage.getSecondLabelTextPreview().equalsIgnoreCase(secondLabel));

        countdownTimerPage = createCountdownTimerPage.clickToBackIcon();

        countdownTimerPage.isCountdownItemDisplayed();

        countdownTimerPage.getCountdownItemStatusByTitle(title);
    }

    @Description("")
    @Story("")
    public void TC_02_(){
        countdownTimerPage.selectCountdownItemByTitle(title);

        countdownTimerPage.setCountdownItemInactive();

        countdownTimerPage.openInactiveCountdownItemsTable();

        countdownTimerPage.isCountdownItemDisplayed();

        countdownTimerPage.getCountdownItemStatusByTitle(title);

        countdownTimerPage.setCountdownItemActive();

        countdownTimerPage.openActiveCountdownItemsTable();

        countdownTimerPage.isCountdownItemDisplayed();

        countdownTimerPage.getCountdownItemStatusByTitle(title);

        countdownTimerPage.openAllCountdownItemsTable();

        countdownTimerPage.selectCountdownItemByTitle(title);

        countdownTimerPage.clickToMoreOptions();

        countdownTimerPage.clickToDeleteCountdown();

        countdownTimerPage.clickToDeleteButtonInPopup();

        countdownTimerPage.isCountdownItemDisplayed();
    }

    @AfterClass
    public void afterClass(){
        closeBrowser();
    }
}
