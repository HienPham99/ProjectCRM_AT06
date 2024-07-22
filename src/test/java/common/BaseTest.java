package common;

import drivers.DriverManager;
import helpers.PropertiesHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class BaseTest {

    @BeforeMethod
    @Parameters({"browser"})
    public void createBrowser(@Optional("chrome") String browserName) {
        PropertiesHelper.loadAllFiles();
        WebDriver driver = setupBrowser(PropertiesHelper.getValue("browser"));
        DriverManager.setDriver(driver);

    }

    public WebDriver setupBrowser(String browserName) {
        WebDriver driver;
        switch (browserName.trim().toLowerCase()) {
            case "chrome":
                driver = initChromeDriver();
                break;
            case "edge":
                driver = initEdgeDriver();
                break;
            case "firefox":
                driver = initFirefoxDriver();
                break;
            default:
                System.out.println("Browser: " + browserName + "is invalid, launching Chrome as browser of choice...");
                driver = initChromeDriver();
        }
        return driver;
    }

    public WebDriver initChromeDriver() {
        WebDriver driver;
        System.out.println("\uD83C\uDF0DLaunching Chrome browser............");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    public WebDriver initEdgeDriver() {
        WebDriver driver;
        System.out.println("\uD83C\uDF0DLaunching Edge browser............");
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    public WebDriver initFirefoxDriver() {
        WebDriver driver;
        System.out.println("\uD83C\uDF0DLaunching Firefox browser............");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        return driver;
    }

    @AfterMethod
    public void closeDriver(ITestResult iTestResult) {
        DriverManager.quit();
    }
}
