package testcases;

import common.BaseTest;
import listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;

@Listeners(TestListener.class)
public class LoginTest extends BaseTest {
    LoginPage loginPage;
    DashboardPage dashboardPage;

    @Test(priority = 1, description = "\uD83D\uDCE2TC01_Verify Login Success.")
    public void verifyLoginSuccess() {
        loginPage = new LoginPage();
        dashboardPage = new DashboardPage();
        dashboardPage = loginPage.inputDataLoginSuccess();
        loginPage.verifyLoginSuccess();
        dashboardPage.logout();
    }

    @Test(priority = 2, description = "\uD83D\uDCE2TC02_Verify Login Fail With Invalid Email.")
    public void verifyLoginFailWithInvalidEmail() {
        loginPage = new LoginPage();
        dashboardPage = new DashboardPage();
        loginPage.inputDataLoginFailWithInvalidEmail();
        loginPage.verifyLoginFailureWitInvalidEmailAndPassword();
    }

    @Test(priority = 3, description = "\uD83D\uDCE2TC03_Verify Login Fail With Invalid Password.")
    public void verifyLoginFailWithInvalidPassword() {
        loginPage = new LoginPage();
        dashboardPage = new DashboardPage();
        loginPage.inputDataLoginFailWithInvalidPassword();
        loginPage.verifyLoginFailureWitInvalidEmailAndPassword();
    }

}
