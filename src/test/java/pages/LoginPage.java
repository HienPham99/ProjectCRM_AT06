package pages;

import static drivers.DriverManager.*;
import static helpers.PropertiesHelper.*;

import helpers.ExcelHelper;
import keywords.WebUI;

import static keywords.WebUI.*;

import org.openqa.selenium.By;
import org.testng.Assert;
import utils.LogUtils;

public class LoginPage {

    private String headerLoginPageExpeced = "Invalid email or password";
    private String contentItemDashboardExpected = "Dashboard";

    private By headerPageLogin = By.xpath("//h1");
    private By inputEmail = By.id("email");
    private By inputPassword = By.id("password");
    private By buttonLogin = By.xpath("//input[@id='password']/parent::div/following-sibling::div//button");
    private By errorMessage = By.xpath("//div[@id='alerts']//div");

    public DashboardPage inputDataLoginSuccess() {
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/testdata/DataLogin.xlsx", "Loginsuccess");
        loginCRM(
                excelHelper.getCellData("EMAIL", 1),
                excelHelper.getCellData("PASSWORD", 1)
        );
        return new DashboardPage();
    }

    public void inputDataLoginFailWithInvalidEmail() {
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/testdata/DataLogin.xlsx", "LoginFail");
        loginCRM(
                excelHelper.getCellData("EMAIL", 1),
                excelHelper.getCellData("PASSWORD", 1)
        );
    }

    public void inputDataLoginFailWithInvalidPassword() {
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/testdata/DataLogin.xlsx", "LoginFail");
        loginCRM(
                excelHelper.getCellData("EMAIL", 2),
                excelHelper.getCellData("PASSWORD", 2)
        );
    }

    private void setEmail(String email) {
        setText(inputEmail, email);
    }

    private void setPassword(String password) {
        setText(inputPassword, password);
    }

    private void clickButtonLogin() {
        clickElement(buttonLogin);
        waitForPageLoaded();
    }

    public void verifyLoginSuccess() {
        waitForPageLoaded();
        Assert.assertFalse(getDriver().getCurrentUrl().contains("authentication"), "\uD83E\uDD40Login Unsuccessful. Vẫn ở trang login page.");
        Assert.assertTrue(checkElementDisplayed(DashboardPage.menuDashboardTotal), "Login unsuccessful. Menu dashboard NOT displayed.");
        assertEquals(getElementText(DashboardPage.menuDashboard), contentItemDashboardExpected, "Content menu Dashboard page NOT match.");
        LogUtils.info("\uD83D\uDC90\uD83D\uDC90\uD83D\uDC90Login successful\uD83D\uDC90\uD83D\uDC90\uD83D\uDC90");
    }

    public void verifyLoginFailureWitInvalidEmailAndPassword() {
        waitForPageLoaded();
        Assert.assertTrue(getDriver().getCurrentUrl().contains("authentication"), "⛔Login successfully");
        Assert.assertTrue(checkElementDisplayed(errorMessage), "\uD83E\uDD40Error message NOT displayed");
        assertEquals(getElementText(errorMessage), headerLoginPageExpeced, "\uD83E\uDD40Header login page NOT match.");
        highLightElement(errorMessage);
        LogUtils.info("\uD83C\uDF84Content error message: " + getElementText(errorMessage));
    }

    public DashboardPage loginCRM(String email, String password) {
        opeURL(getValue("url"));
        waitForPageLoaded();
        setEmail(email);
        setPassword(password);
        clickButtonLogin();
        return new DashboardPage();
    }
}
