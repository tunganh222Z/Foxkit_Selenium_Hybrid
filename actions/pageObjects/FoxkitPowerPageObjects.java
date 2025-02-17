package pageObjects;

import commons.BaseActions;
import commons.PageGenerator;
import org.openqa.selenium.WebDriver;
import pageObjects.countdownTimer.CountdownTimerPageObjects;
import pageUIs.FoxkitPowerPageUI;

public class FoxkitPowerPageObjects extends BaseActions {
    WebDriver driver;
    public FoxkitPowerPageObjects(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public CountdownTimerPageObjects clickToCountdownTimer() {
        switchToFoxkitIframe();

        waitForElementClickable(driver, FoxkitPowerPageUI.COUNTDOWNTIMER_BUTTON);

        clickToElement(driver, FoxkitPowerPageUI.COUNTDOWNTIMER_BUTTON);

        return PageGenerator.getCountdownTimerPage(driver);
    }

    public void waitFoxifyLoadingInvisible() {
        waitForElementInvisible(driver, FoxkitPowerPageUI.FOXIFY_LOADING);
    }
}
