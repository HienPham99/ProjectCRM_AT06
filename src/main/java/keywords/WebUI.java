package keywords;

import com.aventstack.extentreports.Status;
import drivers.DriverManager;
import helpers.PropertiesHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import reports.AllureManager;
import reports.ExtentTestManager;
import utils.LogUtils;

import java.time.Duration;
import java.util.List;

public class WebUI {

   // private static int TIMEOUT = 20;
    private static int TIMEOUT = Integer.parseInt(PropertiesHelper.getValue("TIMEOUT"));
    private static double STEP_TIME = Double.parseDouble(PropertiesHelper.getValue("STEP_TIME"));
    private static int PAGE_LOAD_TIMEOUT = Integer.parseInt(PropertiesHelper.getValue("PAGE_LOAD_TIMEOUT"));

    public static void sleep(double seconds) {
        try {
            Thread.sleep((long) (1000 * seconds));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void logConsole(Object message) {
        LogUtils.info(message);
    }

    public static WebElement getWebElement(By by) {
        return DriverManager.getDriver().findElement(by);
    }

    public static List<WebElement> getWebElements(By by) {
        return DriverManager.getDriver().findElements(by);
    }

    public static boolean checkElementExist(By by) {
        List<WebElement> listElement = getWebElements(by);
        if (listElement.size() > 0) {
            LogUtils.info("✅Check Element Exist: " + true + "---" + by);
            return true;
        } else {
            LogUtils.info("❌Check Element Exist: " + false + "---" + by + " NOT exist.");
            return false;
        }
    }

    public static boolean checkElementDisplayed(By by) {
        waitForElementVisible(by);
        boolean check = getWebElement(by).isDisplayed();
        return check;
    }

    public static boolean checkElementIsSelected(By by) {
        WebUI.sleep(2);
        boolean check = getWebElement(by).isSelected();
        return check;
    }

    @Step("Open URL: {0}")
    public static void opeURL(String url) {
        DriverManager.getDriver().get(url);
        LogUtils.info("\uD83C\uDF0DOpen Website: " + url);
        ExtentTestManager.logMessage(Status.INFO, "\uD83C\uDF0DOpen URL: " + url);
    }

    @Step("Click Element: {0}")
    public static void clickElement(By by) {
        sleep(3);
        //waitForElementClickable(by);
        getWebElement(by).click();
        LogUtils.info("\uD83D\uDC49Click Element: " + by);
        ExtentTestManager.logMessage(Status.INFO, "\uD83D\uDC49Click element: " + by);
    }

    @Step("Input text: {1} on element {0}")
    public static void setText(By by, String value) {
        waitForElementVisible(by);
        getWebElement(by).sendKeys(value);
        LogUtils.info("✍️Input text: " + "'" + value + "'" + " on input: " + by);
        ExtentTestManager.logMessage(Status.INFO, "✍️Input text: " + "'" + value + "'" + " on input: " + by);
    }

    @Step("Action: {1} on element {0}")
    public static void setKey(By by, Keys key) {
        waitForPageLoaded();
        getWebElement(by).sendKeys(key);
        LogUtils.info("\uD83D\uDC49Set key: " + key.name() + " on element " + by);
    }

    @Step("Clear data on element: {0}")
    public static void clearText(By by) {
        waitForElementVisible(by);
        getWebElement(by).clear();
        LogUtils.info("✍️Clear text on field: " + by);
        ExtentTestManager.logMessage(Status.INFO, "✍️Clear text on field: " + by);
    }

    @Step("GET TEXT OF ELEMENT: {0} ")
    public static String getElementText(By by) {
        waitForElementVisible(by);
        WebUI.sleep(STEP_TIME);
        String text = DriverManager.getDriver().findElement(by).getText();
        LogUtils.info("✍️GET TEXT OF ELEMENT: '" + by + "'" + " ===> " + text);
        ExtentTestManager.logMessage(Status.INFO, "✍️Get text of element: " + by);
        ExtentTestManager.logMessage(Status.INFO, "==> ✍️Text: " + getWebElement(by).getText());
        AllureManager.saveTextLog("✍️===> " + text);
        return text;
    }

    @Step("Get attribute of element: {0}")
    public static String getElementAttribute(By by, String attributeName) {
        waitForElementVisible(by);
        WebUI.sleep(STEP_TIME);
        String value = DriverManager.getDriver().findElement(by).getAttribute(attributeName);
        LogUtils.info("\uD83E\uDDBEGet attribute of element: " + by + " ===> " + value);
        ExtentTestManager.logMessage(Status.INFO, "\uD83E\uDDBEGet attribute value of element: " + by);
        ExtentTestManager.logMessage(Status.INFO, "==>\uD83E\uDDBE Attribute value: " + getWebElement(by).getAttribute(attributeName));
        AllureManager.saveTextLog("===> " + value);
        return value;
    }

    public static void waitForElementVisible(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));//hàm chờ đợi element hiển thị
        } catch (Throwable error) {
            LogUtils.error("\uD83E\uDEB2Timeout waiting for the element Visible. " + by.toString());
            Assert.fail("\uD83E\uDEB2Timeout waiting for the element Visible. " + by.toString());

        }
    }

    public static void waitForElementClickable(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.elementToBeClickable(getWebElement(by)));
        } catch (Throwable error) {
            LogUtils.error("\uD83D\uDC49Timeout waiting for the element ready to click. " + by.toString());
            Assert.fail("\uD83D\uDC49Timeout waiting for the element ready to click. " + by.toString());

        }
    }

    public static void waitForPageLoaded() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(PAGE_LOAD_TIMEOUT), Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        //Wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return js.executeScript("return document.readyState").toString().equals("complete");
            }
        };
        //Check JS is Ready
        boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");
        //Wait Javascript until it is Ready!
        if (!jsReady) {
            LogUtils.info("\uD83D\uDC19Javascript is NOT Ready.");
            //Wait for Javascript to load
            try {
                wait.until(jsLoad);
            } catch (Throwable error) {
                LogUtils.error(error.getStackTrace());
                Assert.fail("\uD83D\uDC19FAILED. Timeout waiting for page load.");
            }
        }
    }

    public static boolean hoverElement(By by) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.moveToElement(getWebElement(by)).perform();
            return true;
        } catch (Exception e) {
            LogUtils.error("\uD83E\uDEF0Hover on element: " + by);
            ExtentTestManager.logMessage(Status.FAIL, "\uD83E\uDEF0Hover on element " + by);
            return false;
        }

    }

    //18. Hàm cuộn chuột đến element
    public static void scrollToElement(By element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", getWebElement(element));
    }


    public static WebElement highLightElement(By by) {
        if (DriverManager.getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].style.border='3px solid blue'", getWebElement(by));
            sleep(STEP_TIME);
        }
        return getWebElement(by);
    }

    public static void assertEquals(Object actual, Object expected, String message) {
        waitForPageLoaded();
        LogUtils.info("\uD83C\uDD97Assert equals: " + actual + " \uD83D\uDFF0 " + expected);
        Assert.assertEquals(actual, expected, message);
    }
}
