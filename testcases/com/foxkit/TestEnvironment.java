package com.foxkit;

import commons.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

public class TestEnvironment extends BaseTest {
    WebDriver driver;

    @Parameters({"browser", "browserProfile", "url"})
    @BeforeClass
    public void beforeClass(String browser, String browserProfile, String url){
        driver = getBrowserDriver(browser, browserProfile, url);
    }

    @Test
    public void TC_01(){
        WebElement iframeElement = driver.findElement(By.xpath("//embedded-app//iframe"));
        driver.switchTo().frame(iframeElement);

        driver.findElement(By.xpath("//div[@class='AppCard__info']//h3[text()='Cart countdown']")).click();

    }

    @AfterClass
    public void afterClass(){
        closeBrowser();
    }
}
