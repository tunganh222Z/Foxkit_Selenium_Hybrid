package commons;

import org.openqa.selenium.WebDriver;
import pageObjects.countdownTimer.CountdownTimerPageObjects;
import pageObjects.FoxkitPowerPageObjects;

public class PageGenerator {
    WebDriver driver;
    public PageGenerator (WebDriver driver){
        this.driver = driver;
    }

    public static FoxkitPowerPageObjects getFoxkitPowerPage(WebDriver driver){
        return new FoxkitPowerPageObjects(driver);
    }

    public static CountdownTimerPageObjects getCountdownTimerPage(WebDriver driver){
        return new CountdownTimerPageObjects(driver);
    }
}
