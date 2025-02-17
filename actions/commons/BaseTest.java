package commons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Random;

public class BaseTest {
     WebDriver driver;

    protected WebDriver getBrowserDriver(String browserName, String browserProfile, String url){
        BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());
        if (browser == BrowserList.FIREFOX){
            driver = new FirefoxDriver();
        } else if (browser == BrowserList.CHROME){
            driver = new ChromeDriver();
        } else if (browser == BrowserList.EDGE) {
            driver = new EdgeDriver(getEdgeProfile(browserProfile));
        } else {
            throw new RuntimeException("Browser name is not valid");
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        /* setSize để test reponsive cũng được
        driver.manage().window().setPosition(new Point(0,0));
        driver.manage().window().setSize(new Dimension(720,720));
        */
        driver.manage().window().maximize();
        driver.get(url);
        return driver;
    }

    protected EdgeOptions getEdgeProfile(String profile){
        EdgeOptions edgeOptions = new EdgeOptions();

        edgeOptions.addArguments("--user-data-dir=C:\\Users\\TugAh\\AppData\\Local\\Microsoft\\Edge\\User Data\\");

        edgeOptions.addArguments("--profile-directory=" + profile);

        return edgeOptions;
    }

    protected void closeBrowser(){
        String cmd = null;
        try {
            String osName = GlobalConstant.OS_NAME.toLowerCase();

            String driverInstanceName = driver.toString().toLowerCase();

            String browserDriverName = null;

            if (driverInstanceName.contains("chrome")) {
                browserDriverName = "chromedriver";
            } else if (driverInstanceName.contains("internetexplorer")) {
                browserDriverName = "IEDriverServer";
            } else if (driverInstanceName.contains("firefox")) {
                browserDriverName = "geckodriver";
            } else if (driverInstanceName.contains("edge")) {
                browserDriverName = "msedgedriver";
            } else if (driverInstanceName.contains("opera")) {
                browserDriverName = "operadriver";
            } else {
                browserDriverName = "safaridriver";
            }

            if (osName.contains("window")) {
                cmd = "taskkill /F /FI \"IMAGENAME eq " + browserDriverName + "*\"";
            } else {
                cmd = "pkill " + browserDriverName;
            }
            // close browser
            if (driver != null) {
//                driver.manage().deleteAllCookies();
                driver.quit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // quit driver
            try {
                Process process = Runtime.getRuntime().exec(cmd);
                process.waitFor();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    protected boolean verifyTrue(boolean condition){
        boolean status = true;
        try{
            Assert.assertTrue(condition);
//            log.info("------------Passed------------");
        } catch (Throwable e) { // Phải dùng Throwable thì sẽ không dùng lại, Exception sẽ dừng lại khi không thỏa mãn
            status = false;
//            log.info("------------Fail------------");
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return status;
    }

    protected Boolean verifyFalse(boolean condition){
        boolean status = true;
        try{
            Assert.assertFalse(condition);
//            log.info("------------Passed------------");
        } catch (Throwable e) { // Phải dùng Throwable thì sẽ không dùng lại, Exception sẽ dừng lại khi không thỏa mãn
            status = false;
//            log.info("------------Fail------------");
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return status;
    }

    protected boolean verifyEqual(Object actual, Object expected){
        boolean status = true;
        try{
            Assert.assertEquals(actual, expected);
//            log.info("------------Passed------------");
        } catch (Throwable e) { // Phải dùng Throwable thì sẽ không dùng lại, Exception sẽ dừng lại khi không thỏa mãn
            status = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return status;
    }

    protected String getRandom(){
        Random rand = new Random();
        int x = rand.nextInt(1,999999);
        return Integer.toString(x);
    }

    @BeforeSuite
    public void deleteFileInReport(){
        deleteAllFileInFolder("allure-json");
    }

    public void deleteAllFileInFolder(String folderName) {
        try {
            String pathFolderDownload = GlobalConstant.RELATIVE_PATH + File.separator + folderName;
            File file = new File(pathFolderDownload);
            File[] listOfFiles = file.listFiles();
            if (listOfFiles.length != 0) {
                for (int i = 0; i < listOfFiles.length; i++) {
                    if (listOfFiles[i].isFile() && !listOfFiles[i].getName().equals("environment.properties")) {
                        new File(listOfFiles[i].toString()).delete();
                    }
                }
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

}
