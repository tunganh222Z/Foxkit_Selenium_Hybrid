package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

public class BasePage {

    //không cần phải khởi tạo đối tượng mà vẫn có thể
    // truy cập trực tiếp từ phạm vi class
    public static BasePage getBasePage(){
        return new BasePage();
    }
    // Hàm dùng để làm gì
    // Dùng hàm nào của Selenium
    // Kiểu trả về của hàm đó
    // Các hàm tương tác thì hầu như là void .click, .sendkeys, .accept, .cancel , ...
    // Các hàm để lấy dữ liệu ra thì hầu như là String, int, WebElement, List<WebElement>. Hầu hết là String
    // Tên hàm đặt theo tiêu chuẩn camelCase
    // Có tham số hay không tùy vào chức năng cần viết
    // Kiểu dữ liệu trả về cho hàm

    // Thằng này chứa các hàm chung của selenium


    public String getCurrentPageUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public String getPageSource(WebDriver driver) {
        return driver.getPageSource();
    }

    public void backToPage(WebDriver driver) {
        driver.navigate().back();
    }

    public void refreshCurrentPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    public void openPageURL (WebDriver driver, String URL){
        driver.get(URL);
    }

    public Alert waitForAlertPresence(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.alertIsPresent());
    }

    public void acceptToAlert(WebDriver driver) {
        waitForAlertPresence(driver).accept();
    }

    public void cancelToAlert(WebDriver driver) {
        waitForAlertPresence(driver).dismiss();
    }

    public String getTextInAlert(WebDriver driver) {
        return waitForAlertPresence(driver).getText();
    }

    public void sendkeyToAlert(WebDriver driver, String keysToSend) {
        driver.switchTo().alert().sendKeys(keysToSend);
    }

    public void sleepInSecond(long timeSleep) {
        try {
            Thread.sleep(timeSleep * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public Set<Cookie> getBrowserCookies(WebDriver driver) {
        return driver.manage().getCookies();
    }

    public void setCookies(WebDriver driver, Set<Cookie> cookies) {
        for (Cookie cookie : cookies) {
            driver.manage().addCookie(cookie);
        }
    }

    public void deleteCookies(WebDriver driver) {
        driver.manage().deleteAllCookies();
    }

    /* WebElement */
    // driver.findelement(By.xpath()) đang bị lặp lại


    public By getByLocator(String locator) {
        By by = null;
        String[] splitLocator = locator.split("=", 2);
        if (splitLocator[0].equalsIgnoreCase("xpath")) {
            by = By.xpath(splitLocator[1]);
        } else if (splitLocator[0].equalsIgnoreCase("css")) {
            by = By.cssSelector(splitLocator[1]);
        } else if (splitLocator[0].equalsIgnoreCase("id")) {
            by = By.id(splitLocator[1]);
        } else if (splitLocator[0].equalsIgnoreCase("name")) {
            by = By.name(splitLocator[1]);
        } else if (splitLocator[0].equalsIgnoreCase("class")) {
            by = By.className(splitLocator[1]);
        } else if (splitLocator[0].equalsIgnoreCase("tagname")) {
            by = By.tagName(splitLocator[1]);
        } else {
            throw new RuntimeException("Locator type is not valid");
        }
        return by;
    }

    public String getDynamicLocator (String locator, String... restParams){
        return String.format(locator, (Object[]) restParams);
    }

    public WebElement getWebElement(WebDriver driver, String locator) {
        return driver.findElement(getByLocator(locator));
    }

    public WebElement getWebElement(WebDriver driver, String locator, String... restParam) {
        return driver.findElement(getByLocator(getDynamicLocator(locator,restParam)));
    }

    public List<WebElement> getListWebElements(WebDriver driver, String locator) {
        return driver.findElements(getByLocator(locator));
    }

    public List<WebElement> getListWebElements(WebDriver driver, String locator, String... restParams) {
        return driver.findElements(getByLocator(getDynamicLocator(locator, restParams)));
    }

    public int getListElementsSize(WebDriver driver, String locator) {
        return getListWebElements(driver, locator).size();
    }

    public int getListElementsSize(WebDriver driver, String locator, String... restParam) {
        return getListWebElements(driver, getDynamicLocator(locator,restParam)).size();
    }

    public void clickToElement(WebDriver driver, String locator) {
        getWebElement(driver, locator).click();
    }

    public void clickToListElements(WebDriver driver, WebElement webElement){
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.click();
    }

    public void clickToElement(WebDriver driver, String locator, String... restParams) {
        getWebElement(driver, getDynamicLocator(locator, restParams)).click();
    }

    public void sendkeyToElement(WebDriver driver, String locator, String keysToSend) {
        getWebElement(driver, locator).clear();
        getWebElement(driver, locator).sendKeys(keysToSend);
    }

    public void sendkeyToElement(WebDriver driver, String locator, String keysToSend, String... restParams) {
        getWebElement(driver, getDynamicLocator(locator, restParams)).clear();
        getWebElement(driver, getDynamicLocator(locator, restParams)).sendKeys(keysToSend);
    }

    //Default dropdown

    public void selectItemInDefaultDropdown(WebDriver driver, String locator, String itemValue) {
        new Select(getWebElement(driver, locator)).selectByVisibleText(itemValue);
        //Select tunganh = new Select(driver.findElement(By.xpath(""))).selectByVisibleText();
    }

    public void selectItemInDefaultDropdown(WebDriver driver, String locator, String itemValue, String... restParams) {
        new Select(getWebElement(driver, getDynamicLocator(locator, restParams))).selectByVisibleText(itemValue);
    }

    public String getFirstSelectedTextInDefaultDropdown(WebDriver driver, String locator) {
        return new Select(getWebElement(driver, locator)).getFirstSelectedOption().getText();
    }

    public boolean isDefaultDropdownMultiple(WebDriver driver, String locator) {
        return new Select(getWebElement(driver, locator)).isMultiple();
    }

    //Custom dropdown
    public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childLocator, String expectedText) {
        getWebElement(driver, parentLocator).click();
        sleepInSecond(1);
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childLocator)));
        for (WebElement Item : getListWebElements(driver, childLocator)) {
            if (Item.getText().equals(expectedText)) {
                Item.click();
                break;
            }
        }
    }

    public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childLocator, String expectedText, String... restParam) {
        getWebElement(driver, getDynamicLocator(parentLocator, restParam)).click();
        sleepInSecond(1);
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(getDynamicLocator(childLocator, restParam))));
        for (WebElement Item : getListWebElements(driver, getDynamicLocator(childLocator, restParam))) {
            if (Item.getText().equals(expectedText)) {
                Item.click();
                break;
            }
        }
    }

    public String getWebElementText(WebDriver driver, String locator) {
        return getWebElement(driver, locator).getText();
    }

    public String getWebElementText(WebDriver driver, String locator, String... restParams) {
        return getWebElement(driver, getDynamicLocator(locator, restParams)).getText();
    }

    public String getWebElementAttribute(WebDriver driver, String locator, String attributeName) {
        return getWebElement(driver, locator).getAttribute(attributeName);
    }

    public String getWebElementAttribute(WebDriver driver, String locator, String attributeName, String... restParam) {
        return getWebElement(driver, getDynamicLocator(locator, restParam)).getAttribute(attributeName);
    }

    public String getWebElementCssValue(WebDriver driver, String locator, String cssPropertyName) {
        return getWebElement(driver, locator).getCssValue(cssPropertyName);
    }

    public String convertRGBAToHexColor(WebDriver driver, String locator) {
        return Color.fromString(getWebElementCssValue(driver, locator, "background-color")).asHex();
    }

    // Radio button / checkbox

    /**
     * Apply for checkbox and radio button
     *
     * @param driver
     * @param locator
     */
    public void checkToELement(WebDriver driver, String locator) {
        if (!getWebElement(driver, locator).isSelected()) {
            getWebElement(driver, locator).click();
        }
    }

    /**
     * Only apply for checkbox
     *
     * @param driver
     * @param locator
     */
    public void uncheckToElement(WebDriver driver, String locator) {
        if (getWebElement(driver, locator).isSelected()) {
            getWebElement(driver, locator).click();
        }
    }

    //Case 1 : Element hiền thị và có trong HTML
    // Case 2 : Element hiển thị và không có trong HTML
    public boolean isElementDisplayed(WebDriver driver, String locator) {
        boolean status;
        // Trước khi tìm element thì set time ngắn thôi
        setImplicitWait(driver, shortTimeOut);

        List<WebElement> elements = getListWebElements(driver, locator);

        if (elements.size() >0){
            if (elements.get(0).isDisplayed()){
                status = true;
            } else {
                status = false;
            }
        } else {
            status = false;
        }

        //Trả lại timeout mặc định cho các step còn lại, nếu không setLong là từ sau cứ short thôi
        setImplicitWait(driver, longTimeOut);

        return status;
    }

    public void setImplicitWait(WebDriver driver, long timeout){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
    }

    public boolean isELementUndisplayed(WebDriver driver, String locator){
        boolean status;
        // Trước khi tìm element thì set time ngắn thôi
        setImplicitWait(driver, shortTimeOut);

        List<WebElement> elements = getListWebElements(driver, locator);

        if (elements.size() >0){
            if (elements.get(0).isDisplayed()){
                status = false;
            } else {
                status = true;
            }
        } else {
            status = true;
        }

        //Trả lại timeout mặc định cho các step còn lại, nếu không setLong là từ sau cứ short thôi
        setImplicitWait(driver, longTimeOut);

        return status;
    }

    public boolean isELementUndisplayed(WebDriver driver, String locator, String... restParam){
        boolean status;
        // Trước khi tìm element thì set time ngắn thôi
        setImplicitWait(driver, shortTimeOut);

        List<WebElement> elements = getListWebElements(driver, getDynamicLocator(locator, restParam));

        if (elements.size() >0){
            if (elements.get(0).isDisplayed()){
                status = false;
            } else {
                status = true;
            }
        } else {
            status = true;
        }

        //Trả lại timeout mặc định cho các step còn lại, nếu không setLong là từ sau cứ short thôi
        setImplicitWait(driver, longTimeOut);

        return status;
    }



    public boolean isElementDisplayed(WebDriver driver, String locator, String... restParams) {
        return getWebElement(driver, getDynamicLocator(locator, restParams)).isDisplayed();
    }

    public boolean isElementSelected(WebDriver driver, String locator) {
        return getWebElement(driver, locator).isSelected();
    }

    public boolean isElementSelected(WebDriver driver, String locator, String... restParam) {
        return getWebElement(driver, getDynamicLocator(locator, restParam)).isSelected();
    }

    public boolean isElementEnabled(WebDriver driver, String locator) {
        return getWebElement(driver, locator).isEnabled();
    }

    // Frame / iFrame

    public void switchToiFrame(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(getByLocator(locator)));
    }

    public void switchBackParentFrame(WebDriver driver) {
        driver.switchTo().parentFrame();
    }

    public void switchToDefaultContent(WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    //Actions
    public void hoverToElement(WebDriver driver, String locator) {
        new Actions(driver).moveToElement(getWebElement(driver, locator)).perform();
    }

    public void doubleClickToElement(WebDriver driver, String locator) {
        new Actions(driver).doubleClick(getWebElement(driver, locator)).perform();
    }

    public void rightClickToElement(WebDriver driver, String locator) {
        new Actions(driver).contextClick(getWebElement(driver, locator)).perform();
    }

    public void dragAndDropElement(WebDriver driver, String locatorSource, String locatorTarget) {
        new Actions(driver).dragAndDrop(getWebElement(driver, locatorSource), getWebElement(driver, locatorTarget)).perform();
    }

    public void sendKeyBoardToElement(WebDriver driver, String locator, Keys key) {
        new Actions(driver).sendKeys(getWebElement(driver, locator), key);
    }

    public Object executeForBrowser(WebDriver driver, String javaScript) {
        return ((JavascriptExecutor) driver).executeScript(javaScript);
    }

    public String getInnerText(WebDriver driver) {
        return (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText;");
    }

    public boolean isExpectedTextInInnerText(WebDriver driver, String textExpected) {
        String textActual = (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void navigateToUrlByJS(WebDriver driver, String url) {
        ((JavascriptExecutor) driver).executeScript("window.location = '" + url + "'");
        sleepInSecond(3);
    }

    public void hightlightElement(WebDriver driver, String locator) {
        WebElement element = getWebElement(driver, locator);
        String originalStyle = element.getAttribute("style");
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSecond(2);
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, locator));
        sleepInSecond(3);
    }

    public void clickToElementByJS(WebDriver driver, String locator, String restParam) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, getDynamicLocator(locator, restParam)));
        sleepInSecond(3);
    }

    public void scrollToElementOnTop(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locator));
    }

    public void scrollToElementOnDown(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver, locator));
    }

    public void setAttributeInDOM(WebDriver driver, String locator, String attributeName, String attributeValue) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue + "');", getWebElement(driver, locator));
    }

    public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locator));
    }

    public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')", getWebElement(driver, locator));
    }

    public String getAttributeInDOM(WebDriver driver, String locator, String attributeName) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].getAttribute('" + attributeName + "');", getWebElement(driver, locator));
    }

    public String getAttributeInDOM(WebDriver driver, String locator, String attributeName, String... restParams) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].getAttribute('" + attributeName + "');", getWebElement(driver, getDynamicLocator(locator, restParams)));
    }

    public String getWebElementValidationMessage(WebDriver driver, String locator) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getWebElement(driver, locator));
    }

    public boolean isImageLoaded(WebDriver driver, String locator) {
        boolean status = (boolean) ((JavascriptExecutor) driver).executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getWebElement(driver, locator));
        return status;
    }

    public void waitForElementVisible(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
    }

    public void waitForElementVisible(WebDriver driver, String locator, String... restParams) {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicLocator(locator, restParams))));
    }

    public void waitForListElementsVisible(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locator)));
    }

    public void waitForListElementsVisible(WebDriver driver, String locator, String... restParams) {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(getDynamicLocator(locator, restParams))));
    }

    public void waitForDropdownOptionsVisible (WebDriver driver, String locator, String... restParams){
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(getDynamicLocator(locator, restParams))));
    }

    public void waitForElementInvisible(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
    }

    public void waitForElementInvisible(WebDriver driver, String locator, String... restParams) {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicLocator(locator, restParams))));
    }

    public void waitForListElementsInvisible(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.invisibilityOfAllElements(getListWebElements(driver, locator)));
    }

    public void waitForListElementsInvisible(WebDriver driver, String locator, String... restParams) {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.invisibilityOfAllElements(getListWebElements(driver, getDynamicLocator(locator, restParams))));
    }

    public void waitForElementClickable(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
    }


    public void waitForElementClickable(WebDriver driver, String locator, String... restParams) {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicLocator(locator, restParams))));
    }

    public void uploadMultipleFiles (WebDriver driver,String locator, String... fileNames){
        String filePath = GlobalConstant.UPLOAD_FILE_PATH;
        String fullFileName = "";
        for (String file : fileNames){
            fullFileName = fullFileName +filePath + file + "\n";
        }
        fullFileName = fullFileName.trim();
        getWebElement(driver, locator).sendKeys(fullFileName);
    }

    public void clearAndTypeUsingActions (WebDriver driver, String keyToSend, String locator, String... restParam){
        new Actions(driver).click(getWebElement(driver, getDynamicLocator(locator, restParam)))
                .keyDown(Keys.LEFT_CONTROL).sendKeys("a")
                .keyUp(Keys.LEFT_CONTROL).sendKeys(Keys.DELETE)
                .sendKeys(keyToSend).perform();
    }

    public String getDateTimeNow(String dateTimeFormatter){
        LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateTimeFormatter);
        return String.valueOf(now.format(formatter));
    }

    private long longTimeOut = GlobalConstant.LONG_TIMEOUT;
    private long shortTimeOut = GlobalConstant.SHORT_TIMEOUT;


}