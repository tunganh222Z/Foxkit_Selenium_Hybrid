package commons;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.BaseActionsPageUI;

public class BaseActions extends BasePage{
    WebDriver driver;

    public BaseActions(WebDriver driver){
        this.driver = driver;
    }

    public void switchToFoxkitIframe(){
        switchToiFrame(driver, BaseActionsPageUI.EMBEDDED_APP_IFRAME);
    }

    @Step("Click to save button shopify")
    public void clickToSaveButton() {
        switchBackParentFrame(driver);
        waitForElementClickable(driver, BaseActionsPageUI.SAVE_BUTTON);
        clickToElement(driver, BaseActionsPageUI.SAVE_BUTTON);
    }

    @Step("get update successfully message")
    public String getUpdatedSuccessfullyMsg() {
        waitForElementVisible(driver, BaseActionsPageUI.UPDATE_TOAST_MESSAGE);
        String updateMsg = getWebElementText(driver, BaseActionsPageUI.UPDATE_TOAST_MESSAGE);
        switchToFoxkitIframe();
        return updateMsg;
    }
}
