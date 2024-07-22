package testcases;

import common.BaseTest;
import listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.CustomersPage;
import pages.DashboardPage;
import pages.LoginPage;

@Listeners(TestListener.class)
public class CustomerTest extends BaseTest {
    LoginPage loginPage;
    DashboardPage dashboardPage;
    CustomersPage customersPage;

    public static String COMPANY_NAME = "HienGMC";
    public static String COMPANY_NAMEEDIT = "HinGMC";
    private String COMPANY_NAMEDELETE = "Nodo";
    private String CONTACT_NAME = "HienQC2";

    @Test(priority = 4, description = "✅TC04_Verify Add new Customer success.")
    public void verifyAddNewCustomerSucess() {
        loginPage = new LoginPage();
        dashboardPage = new DashboardPage();
        loginPage.inputDataLoginSuccess();
        customersPage = dashboardPage.clickMenuCustomer();
        customersPage.verifyHeaderCustomerPage();
        customersPage.verifyListItem();
        customersPage.clickButtonAddNewCustomer();
        customersPage.verifyFormAddNewCustomer();
        customersPage.inputDataCustomer(COMPANY_NAME);
        customersPage.verifyCustomerDetailAfterAddNew();
        customersPage.clickMenuCustomer();
        customersPage.searchCustomer(COMPANY_NAME);
        customersPage.verifyCustomerDetail(COMPANY_NAME);
    }

    @Test(priority = 5, description = "✅TC05_Verify when edit Customer success.")
    public void verifyEditCustomerSucess() {
        loginPage = new LoginPage();
        dashboardPage = new DashboardPage();
        loginPage.inputDataLoginSuccess();
        customersPage = dashboardPage.clickMenuCustomer();
        customersPage.clickButtonAddNewCustomer();
        customersPage.inputDataCustomer(COMPANY_NAME);
        customersPage.clickMenuCustomer();
        customersPage.searchCustomer(COMPANY_NAME);
        customersPage.verifyWhenEditCustomer(COMPANY_NAMEEDIT);
        customersPage.clickMenuCustomer();
        customersPage.searchCustomerAfterEdit(COMPANY_NAMEEDIT);
        customersPage.verifyCustomerDetailAfterEdit();
    }

    @Test(priority = 6, description = "✅TC06_Verify when delete Customer success.")
    public void verifyDeleteCustomerSucess() {
        loginPage = new LoginPage();
        dashboardPage = new DashboardPage();
        loginPage.inputDataLoginSuccess();
        customersPage = dashboardPage.clickMenuCustomer();
        customersPage.verifyWhenDeleteCustomer(COMPANY_NAMEDELETE);
        customersPage.searchCustomerAfterDelete();
    }

    @Test(priority = 7, description = "✅TC07_Verify Add new Contact for Customer success.")
    public void verifyAddNewContactForCustomerSuccess() {
        loginPage = new LoginPage();
        dashboardPage = new DashboardPage();
        loginPage.inputDataLoginSuccess();
        customersPage = dashboardPage.clickMenuCustomer();
        customersPage.clickButtonAddNewCustomer();
        customersPage.inputDataCustomer(COMPANY_NAME);
        customersPage.clickContactTag();
        customersPage.verifyWhenClickContactTag();
        customersPage.clickButtonAddNewContact(COMPANY_NAME);
        customersPage.inputDataContact();
        customersPage.deleteCustomer(COMPANY_NAME);
    }

}
