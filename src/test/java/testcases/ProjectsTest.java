package testcases;

import pages.DashboardPage;
import pages.LoginPage;
import pages.ProjectsPage;
import common.BaseTest;
import helpers.ExcelHelper;
import listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class ProjectsTest extends BaseTest {

    LoginPage loginPage;
    DashboardPage dashboardPage;
    ProjectsPage projectsPage;

    private String PROJECT_NAME = "ProjectAT06_HienQC2";

    @Test(priority = 8, description = "✅TC08_Verify Add new Project success.")
    public void verifyAddNewProject() {
        loginPage = new LoginPage();
        dashboardPage = new DashboardPage();
        loginPage.inputDataLoginSuccess();
        projectsPage = dashboardPage.clickMenuProject();
        projectsPage.verifyHeaderProjectPage();
        projectsPage.clickButtonAddNewProject();
        projectsPage.verifyFormAddNewProject();
        projectsPage.addNewProject(PROJECT_NAME);
        projectsPage.verifyProjectDetail();
        dashboardPage.clickMenuProject();
        projectsPage.searchProject(PROJECT_NAME);
        projectsPage.verifyProjectDetailAfterSearch();
    }

}
