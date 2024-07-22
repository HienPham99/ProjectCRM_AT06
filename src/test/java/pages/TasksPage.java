package pages;

import drivers.DriverManager;

import static drivers.DriverManager.getDriver;
import static keywords.WebUI.*;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import testcases.CustomerTest;
import utils.LogUtils;

import java.util.ArrayList;

public class TasksPage {

    private String headerTasksExpected = "Tasks Summary";
    private String headerFormAddNewTasksExpected = "Add new task";

    private By headerTasks = By.xpath("//span[normalize-space()='Tasks Summary']");
    private By inputSearch = By.xpath("//div[@id='tasks_filter']//input");
    private By firstItemTaskOnTableAddNew = By.xpath("//*[@id=\"tasks\"]/tbody/tr/td[3]/a");

    private By buttonAddNewTask = By.xpath("//a[normalize-space()='New Task']");
    private By formAddNewTask = By.xpath("//div[@id='_task_modal']//div[@class='modal-content']");
    private By headerFormAddNewTask = By.xpath("//h4[@id='myModalLabel']");
    private By checkboxPublic = By.xpath("//input[@id='task_is_public']");
    private By inputSubject = By.xpath("//input[@id='name']");
    private By inputHourlyRate = By.xpath("//input[@id='hourly_rate']");
    private By inputDueDate = By.xpath("//input[@id='duedate']");
    private By dropdownPriority = By.xpath("//button[@data-id='priority']");
    private By optionPriority = By.xpath("//span[normalize-space()='High']");
    private By dropdownRepeatEvery = By.xpath("//button[@data-id='repeat_every']");
    private By optionRepeatEvery = By.xpath("//span[normalize-space()='1 Year']");
    private By dropdownRepeatTo = By.xpath("//label[normalize-space()='Related To']/following-sibling::div/descendant::button");
    private By optionRepeatTo = By.xpath("(//span[normalize-space()='Customer'])[3]");
    private By dropdownOptionRepeatTo = By.xpath("//label[@for='rel_id']/following-sibling::div/descendant::button");
    private By inputOptionRepeatTo = By.xpath("//label[@for='rel_id']/following-sibling::div/descendant::input[@type='search']");
    private By valueOptionRepeatTo = By.xpath("(//span[normalize-space()='Currently Selected']/ancestor::ul/descendant::span)[2]");

    private By textAreaTaskDescription = By.xpath("//textarea[@id='description']");
    private By buttonSave = By.xpath("//button[normalize-space()='Save']");

    private By formAddTaskSuccess = By.xpath("//*[@id=\"task-modal\"]/div/div");
    private By valueRelated = By.xpath("(//h4[normalize-space()='Description']/ancestor::div)[6]/descendant::a[1]");
    private By valueDescription = By.xpath("//div[@id='task_view_description']");
    private By buttonX = By.xpath("(//span[contains(.,'×')])[4]");


    SoftAssert softAssert = new SoftAssert();

    public void verifyHeaderTasks() {
        waitForPageLoaded();
        Assert.assertTrue(getDriver().getCurrentUrl().contains("tasks"), "\uD83E\uDD40NOT the Tasks page");
        softAssert.assertTrue(checkElementExist(headerTasks), "Header Task NOT exist");
        softAssert.assertTrue(checkElementDisplayed(headerTasks), "Header Task NOT displayed");
        softAssert.assertEquals(getElementText(headerTasks), headerTasksExpected, "Content header task NOT match.");
        softAssert.assertAll();
    }

    public void clickButtonAddNewTask() {
        clickElement(buttonAddNewTask);
        waitForPageLoaded();
        verifyFormAddNewTask();
    }

    public void verifyFormAddNewTask() {
        // waitForPageLoaded();
        sleep(2);
        softAssert.assertTrue(checkElementExist(formAddNewTask), "Form Add New Task NOT exist");
        softAssert.assertTrue(checkElementDisplayed(formAddNewTask), "Form Add New Task NOT displayed");
        softAssert.assertEquals(getElementText(headerFormAddNewTask), headerFormAddNewTasksExpected, "Content header add new task NOT match.");
        LogUtils.info("\uD83C\uDF3AHeader form add new task is: " + getElementText(headerFormAddNewTask));
        softAssert.assertAll();
    }

    public void inputDataAddNewTask(String SUBJECT_NAME) {
        waitForPageLoaded();
        clickElement(checkboxPublic);
        setText(inputSubject, SUBJECT_NAME);
        setKey(inputHourlyRate, Keys.CLEAR);
        setText(inputHourlyRate, "10");
        setText(inputDueDate, "31-12-2025");
        clickElement(dropdownPriority);
        sleep(1);
        clickElement(optionPriority);
        sleep(1);
        clickElement(dropdownRepeatEvery);
        sleep(1);
        clickElement(optionRepeatEvery);
        clickElement(dropdownRepeatTo);
        sleep(1);
        clickElement(optionRepeatTo);
        sleep(2);
        clickElement(dropdownOptionRepeatTo);
        sleep(1);
        setText(inputOptionRepeatTo, CustomerTest.COMPANY_NAME);
        sleep(1);
        clickElement(valueOptionRepeatTo);
        setText(textAreaTaskDescription, "Work hard, play hard, sleep in normally on Saturday and Sunday.");
        clickElement(buttonSave);
        waitForPageLoaded();
    }

    public void verifyAddTaskSuccess() {
        waitForPageLoaded();
        Assert.assertTrue(checkElementExist(formAddTaskSuccess), "Form add task NOT exist");
        Assert.assertTrue(checkElementDisplayed(formAddTaskSuccess), "Form add task NOT displayed");
        sleep(1);
        Assert.assertTrue(checkElementExist(valueRelated), "Value related NOT exist");
        Assert.assertTrue(checkElementDisplayed(valueRelated), "Value related NOT displayed");
        assertEquals(getElementText(valueRelated), CustomerTest.COMPANY_NAME, "Customer name NOT match.");
        Assert.assertTrue(checkElementDisplayed(valueDescription), "Value description NOT displayed");
        assertEquals(getElementText(valueDescription), "Work hard, play hard, sleep in normally on Saturday and Sunday.", "Content description NOT match.");
        LogUtils.info("❇️Customer name được add to Task is: " + getElementText(valueRelated));
        LogUtils.info("\uD83D\uDD1CDescription is: " + getElementText(valueDescription));
        clickElement(buttonX);
        sleep(2);
    }

    public void searchTask(String SUBJECT) {
        waitForPageLoaded();
        setText(inputSearch, SUBJECT);
        sleep(1);
        Assert.assertTrue(checkElementExist(firstItemTaskOnTableAddNew), "Task name NOT exists");
        Assert.assertTrue(checkElementDisplayed(firstItemTaskOnTableAddNew), "Task name NOT displayed");
        clickElement(firstItemTaskOnTableAddNew);
        LogUtils.info("\uD83C\uDF49Keyword Task search is: " + SUBJECT);
        waitForPageLoaded();
    }
}
