package testcases;

import keywords.WebUI;
import pages.DashboardPage;
import pages.LoginPage;
import pages.TasksPage;
import common.BaseTest;
import helpers.ExcelHelper;
import listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class TasksTest extends BaseTest {
    LoginPage loginPage;
    DashboardPage dashboardPage;
    TasksPage tasksPage;

    private String SUBJECT = "HienQA";

    @Test(priority = 9,description = "✅TC09_Verify Add new Task for Customer success.")
    public void verifyAddNewTask() {
        loginPage = new LoginPage();
        dashboardPage = new DashboardPage();
        dashboardPage = loginPage.inputDataLoginSuccess();
        tasksPage = dashboardPage.clickMenuTasks();
        tasksPage.verifyHeaderTasks();
        tasksPage.clickButtonAddNewTask();
        tasksPage.inputDataAddNewTask(SUBJECT);
        tasksPage.verifyAddTaskSuccess();
        dashboardPage.clickMenuTasks();
        tasksPage.searchTask(SUBJECT);
    }

}
