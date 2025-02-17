package pageObjects.countdownTimer;

import commons.BaseActions;
import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.countdownTimer.CountdownTimerPageUI;

public class CountdownTimerPageObjects extends BaseActions {
    WebDriver driver;
    public CountdownTimerPageObjects(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public CreateCountdownTimerPageObjects clickToCreateCountdownButton() {
        waitForElementClickable(driver, CountdownTimerPageUI.CREATE_COUNTDOWN_BUTTON);
        clickToElement(driver, CountdownTimerPageUI.CREATE_COUNTDOWN_BUTTON);
        return new CreateCountdownTimerPageObjects(driver);
    }
}
