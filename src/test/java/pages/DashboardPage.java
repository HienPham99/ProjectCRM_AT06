package pages;

import drivers.DriverManager;
import keywords.WebUI;
import static keywords.WebUI.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class DashboardPage {

    private String contentItemDashboardExpected="Dashboard";
    public static By menuDashboardTotal = By.xpath("//ul[@id='side-menu']");
    public static By menuDashboard = By.xpath("//span[normalize-space()='Dashboard']");
    public static By menuCustomer = By.xpath("//span[normalize-space()='Customers']");
    private By menuProjects = By.xpath("//span[normalize-space()='Projects']");
    private By menuTasks = By.xpath("//span[normalize-space()='Tasks']");

    private By dropdownProfile = By.xpath("//li[contains(@class,'icon header-user-profile')]");
    private By optionLogout = By.xpath("//ul[@class='dropdown-menu animated fadeIn']//li[@class='header-logout']//a[@href='#'][normalize-space()='Logout']");


    public CustomersPage clickMenuCustomer() {
        clickElement(menuCustomer);
        waitForPageLoaded();
        return new CustomersPage();
    }

    public ProjectsPage clickMenuProject() {
        clickElement(menuProjects);
        waitForPageLoaded();
        return new ProjectsPage();
    }

    public TasksPage clickMenuTasks() {
        clickElement(menuTasks);
        waitForPageLoaded();
        return new TasksPage();
    }

    public LoginPage logout() {
        clickElement(dropdownProfile);
        clickElement(optionLogout);
        waitForPageLoaded();
        return new LoginPage();
    }
}

