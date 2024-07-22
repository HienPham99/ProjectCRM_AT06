package pages;

import drivers.DriverManager;

import static keywords.WebUI.*;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import testcases.CustomerTest;
import utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

public class ProjectsPage {

    private String headerProjectExpected = "Projects Summary";
    private String headerAddNewProjectExpected = "Add new project";

    private By headerProject = By.xpath("//span[normalize-space()='Projects Summary']");
    private By inputSearch = By.xpath("(//input[@type='search'])[2]");
    private By fistItemProjectOnTable = By.xpath("//table//tbody//tr[1]//td[2]/a");

    private By buttonAddNewProject = By.xpath("//div[@id='vueApp']//a[normalize-space()='New Project']");
    private By headerAddNewProject = By.xpath("(//div[@id='tab_project']/ancestor::div[@class='panel_s'])/preceding-sibling::h4");
    private By formAddNewProject = By.xpath("//div[@id='tab_project']/ancestor::div[contains(@class,'panel-body')]");
    private By inputProjectName = By.xpath("//input[@id='name']");
    private By dropdownCustomer = By.xpath("//button[@data-id='clientid']");
    private By inputSearchCustomer = By.xpath("(//select[@id='clientid']/following::div//input[@type='search'])[1]");
    private By inputOptionCustomer = By.xpath("(//div[@id='bs-select-6']//ul[@role='presentation']//span)[2]");//span[normalize-space()='Merkle Software A6']";
    private By dropdownBillingType = By.xpath("//button[@data-id='billing_type']");
    private By inputBillingType = By.xpath("//span[normalize-space()='Fixed Rate']");
            //button[@data-id='billing_type']/following::a[normalize-space()='Fixed Rate']");
    private By dropdownStatus = By.xpath("//button[@data-id='status']");
    private By inputStatus = By.xpath("//span[normalize-space()='In Progress']");
    private By inputTotalRate = By.xpath("//input[@id='project_cost']");
    private By inputEstimatedHours = By.xpath("//input[@id='estimated_hours']");
    private By inputDeadline = By.xpath("//input[@id='deadline']");
    private By buttonSave = By.xpath("//button[@type='submit']");

    private By nameProjects = By.xpath("//button[@data-id='project_top']");
    private By valueStatusProject = By.xpath("//div[@id='project_view_name']/following-sibling::span");
    private By allButtontitle = By.xpath("//div[@id='project_view_name']/ancestor::div[contains(@class,'project-heading')]/following-sibling::div");
    private By subMenuProject = By.xpath("//a[normalize-space()='Overview']/ancestor::ul");

    private By valueCustomer = By.xpath("//dt[normalize-space()='Customer']/parent::div/descendant::a");
    private By valueBillingType = By.xpath("//dt[normalize-space()='Billing Type']/following-sibling::dd");
    private By valueStatus = By.xpath("//dt[normalize-space()='Status']/following-sibling::dd");
    private By valueDeadline = By.xpath("//dt[normalize-space()='Deadline']/following-sibling::dd");
    private By valueStartDate = By.xpath("//dt[normalize-space()='Start Date']/following-sibling::dd");
    private By valueDateCreated = By.xpath("//dt[normalize-space()='Date Created']/following-sibling::dd");

    SoftAssert softAssert = new SoftAssert();

    public void verifyHeaderProjectPage() {
        waitForPageLoaded();
        waitForElementVisible(headerProject);
        softAssert.assertTrue(checkElementDisplayed(headerProject), "Header Project NOT displayed.");
        softAssert.assertEquals(getElementText(headerProject), headerProjectExpected, "Conten Header Project NOT match.");
        LogUtils.info("\uD83C\uDF81Actual Header của Project page là: " + getElementText(headerProject));
        LogUtils.info("☘️☘️☘️☘️☘️☘️☘️☘️☘️☘️☘️☘️☘️☘️☘️☘️☘️☘️☘️☘️☘️☘️☘️☘️");
        softAssert.assertAll();
    }

    public void clickButtonAddNewProject() {
        clickElement(buttonAddNewProject);
        waitForPageLoaded();
    }

    public void verifyFormAddNewProject() {
        softAssert.assertTrue(checkElementDisplayed(headerAddNewProject), "Header Add New Project NOT displayed");
        softAssert.assertEquals(getElementText(headerAddNewProject), headerAddNewProjectExpected, "Content Add New Project NOT match.");
        Assert.assertTrue(checkElementDisplayed(formAddNewProject), "Form Add New Project NOT displayed");
        waitForPageLoaded();
        softAssert.assertAll();
    }

    public void addNewProject(String PROJECT_NAME) {
        setText(inputProjectName, PROJECT_NAME);
        clickElement(dropdownCustomer);
        sleep(1);
        setText(inputSearchCustomer, CustomerTest.COMPANY_NAME);
        sleep(2);
        clickElement(inputOptionCustomer);
        sleep(2);
        clickElement(dropdownBillingType);
        sleep(2);
        clickElement(inputBillingType);
        //setKey(inputBillingType, Keys.ENTER);
        sleep(1);
        clickElement(dropdownStatus);
        sleep(1);
        clickElement(inputStatus);
        sleep(2);
        setText(inputTotalRate, "8");
        setText(inputEstimatedHours, "1600");
        sleep(1);
        setText(inputDeadline, "17-10-2025");
        sleep(1);
        clickElement(buttonSave);
        waitForPageLoaded();
    }

    public void verifyProjectDetail() {
        waitForPageLoaded();
        Assert.assertTrue(checkElementExist(nameProjects), "Name project NOT exist");
        Assert.assertTrue(checkElementDisplayed(nameProjects), "Name project and customer NOT displayed");
        Assert.assertTrue(checkElementExist(valueStatusProject), "Status Project NOT exist");
        Assert.assertTrue(checkElementDisplayed(valueStatusProject), "Status Project NOT displayed");
        Assert.assertTrue(checkElementExist(allButtontitle), "Button title Project NOT exist");
        Assert.assertTrue(checkElementDisplayed(allButtontitle), "Button title Project NOT displayed");
        Assert.assertTrue(checkElementExist(subMenuProject), "SubMenu Project NOT exist");
        Assert.assertTrue(checkElementDisplayed(subMenuProject), "SubMenu Project NOT displayed");
        Assert.assertTrue(checkElementExist(valueCustomer), "Name customer NOT exist");
        Assert.assertTrue(checkElementDisplayed(valueCustomer), "Name customer NOT displayed");
        Assert.assertTrue(checkElementExist(valueBillingType), "Value Billing Type NOT exist");
        Assert.assertTrue(checkElementDisplayed(valueBillingType), "Value Billing Type NOT displayed");
        Assert.assertTrue(checkElementExist(valueStatus), "Status Project NOT exist");
        Assert.assertTrue(checkElementDisplayed(valueStatus), "Status Project NOT displayed");
        assertEquals(getElementText(valueStatus), getElementText(valueStatusProject), "❌Status project NOT match");
        Assert.assertTrue(checkElementExist(valueDeadline), "Value Deadline of Project NOT exist");
        Assert.assertTrue(checkElementDisplayed(valueDeadline), "Value Deadline of Project NOT displayed");
        LogUtils.info("\uD83C\uDF3AName Project and Customer is: " + getElementText(nameProjects));
        LogUtils.info("\uD83C\uDF3AStatus Project is: " + getElementText(valueStatusProject));
        LogUtils.info("\uD83C\uDF3AName customer is: " + getElementText(valueCustomer));
        LogUtils.info("\uD83C\uDF3AValue Billing Type is: " + getElementText(valueBillingType));
        LogUtils.info("\uD83C\uDF3AValue Deadline of Project is: " + getElementText(valueDeadline));
    }

    public void searchProject(String PROJECT_NAME) {
        setText(inputSearch, PROJECT_NAME);
        waitForPageLoaded();
        Assert.assertTrue(checkElementExist(fistItemProjectOnTable), "PROJECT_NAME  NOT exists.");
        Assert.assertTrue(checkElementDisplayed(fistItemProjectOnTable), "Không tìm thấy PROJECT_NAME vừa add.");
        sleep(1);
        LogUtils.info("\uD83C\uDF81Project Name tìm thấy là: " + getElementText(fistItemProjectOnTable));
    }

    public void verifyProjectDetailAfterSearch() {
        waitForPageLoaded();
        clickElement(fistItemProjectOnTable);
        sleep(2);
        assertEquals(getElementText(valueCustomer),CustomerTest.COMPANY_NAME,"❌Customer name NOT match.");
        assertEquals(getElementText(valueBillingType),"Fixed Rate","❌Value Billing Type NOT match.");
        assertEquals(getElementText(valueStatus),"In Progress","❌Status NOT match.");
        assertEquals(getElementText(valueDeadline),"17-10-2025","❌Value Deadline name NOT match.");
    }

}
