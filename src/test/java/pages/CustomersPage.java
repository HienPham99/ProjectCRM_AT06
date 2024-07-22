package pages;

import drivers.DriverManager;
import keywords.WebUI;

import static keywords.WebUI.*;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import testcases.CustomerTest;
import utils.LogUtils;

public class CustomersPage {

    private String headerCustomerPageExpected = "Customers Summary";
    private String labelContactExpected = "Contacts";

    private By headerCustomerPage = By.xpath("//span[normalize-space()='Customers Summary']");
    private By listItem = By.xpath("//span[normalize-space()='Customers Summary']/parent::h4/following-sibling::div");
    private By inputSearch = By.xpath("//div[@id='clients_filter']//input");

    private By buttonAddNewCustomer = By.xpath("//a[normalize-space()='New Customer']");
    private By formAddNewCustomer = By.xpath("//div[@id='contact_info']/ancestor::div[@class='panel_s']");
    private By inputCompany = By.xpath("//input[@id='company']");
    private By inputVATNumber = By.xpath("//input[@id='vat']");
    private By inputPhone = By.xpath("//input[@id='phonenumber']");
    private By inputWebsite = By.xpath("//input[@id='website']");
    private By dropdownGroup = By.xpath("//button[@data-id='groups_in[]']");
    private By inputSearchGroup = By.xpath("//div[@app-field-wrapper='groups_in[]']//input[@type='search']");
    private By fieldGroup = By.xpath("//div[contains(text(),'Gold')]");
    private By dropdownCurrency = By.xpath("//label[normalize-space()='Currency']/following-sibling::div/button[@data-id='default_currency']");
    private By inputSearchCurrency = By.xpath("//div[@app-field-wrapper='default_currency']//input[@type='search']");
    private By dropdownDefaultLanguage = By.xpath("//label[normalize-space()='Default Language']/following-sibling::div//button[@type='button']");
    private By optionDefaultLanguage = By.xpath("//span[normalize-space()='Vietnamese']");
    private By inputAddress = By.xpath("//textarea[@id='address']");
    private By inputCity = By.xpath("//input[@id='city']");
    private By inputState = By.xpath("//input[@id='state']");
    private By inputZipCode = By.xpath("//input[@id='zip']");
    private By dropdownCountry = By.xpath("//label[@for='country']/following-sibling::div//button[@type='button']");
    private By inputSearchCountry = By.xpath("//div[@app-field-wrapper='country']//input[@type='search']");
    private By buttonSave = By.xpath("//div[@id='profile-save-section']//button[2]");
    private By buttonSaveAfterEdit = By.xpath("//div[@id='profile-save-section']//button");
    private By formCustomerDetail = By.xpath("//div[@id='wrapper']/child::div[@class='content']");

    private By buttonEdit = By.xpath("//*[@id=\"clients\"]/tbody/tr[1]/td[3]/div/a[1]");
    private By buttonDelete = By.xpath("//*[@id=\"clients\"]/tbody/tr[1]/td[3]/div/a[3]");
    private By fistItemcustomerOnTable = By.xpath("//td[@class='sorting_1']/a");
    private By fistItemcustomerOnTableAfterDelete = By.xpath("//td[@class='dataTables_empty']");

    //Contacts
    private By contactsTag = By.xpath("//a[normalize-space()='Contacts']");
    private By labelContacts = By.xpath("//h4[normalize-space()='Contacts']");
    private By formContenContactsPage = By.xpath("//h4[normalize-space()='Contacts']/ancestor::div[@class='tab-content']");
    private By inputSearchContact = By.xpath("//h4[normalize-space()='Contacts']/following-sibling::div//input[@type='search']");
    private By fistItemContactOnTable = By.xpath("//tbody/tr/td[1]");

    private By buttonAddNewContact = By.xpath("//h4[normalize-space()='Contacts']/following::a[contains(.,'New Contact')]");
    private By formAddNewContact = By.xpath("//form[@id='contact-form']");
    private By nameCompanyActual = By.xpath("//form[@id='contact-form']/descendant::div//h4/following-sibling::p");

    private By inputProfileImg = By.xpath("//input[@id='profile_image']");
    private By inputFirstName = By.xpath("//input[@id='firstname']");
    private By inputLastName = By.xpath("//input[@id='lastname']");
    private By inputPosition = By.xpath("//input[@id='title']");
    private By inputEmail = By.xpath("//input[@id='email']");
    private By inputPhoneContact = By.xpath("//input[@id='phonenumber']");
    private By dropdownDirection = By.xpath("//label[normalize-space()='Direction']/following-sibling::div//button");
    private By optionDirection = By.xpath("//span[normalize-space()='LTR']");
    private By inputPasswordContact = By.xpath("//input[@name='password']");
    private By checkboxDoNotSendWelcomeEmail = By.xpath("//input[@id='donotsendwelcomeemail']");
    private By buttonSaveContact = By.xpath("//button[normalize-space()='Save']");


    public void verifyHeaderCustomerPage() {
        SoftAssert softAssert = new SoftAssert();
        waitForPageLoaded();
        waitForElementVisible(headerCustomerPage);
        softAssert.assertTrue(checkElementDisplayed(headerCustomerPage), "Header Customer NOT displayed");
        softAssert.assertEquals(getElementText(headerCustomerPage), headerCustomerPageExpected, "Header Customer page NOT match.");
        LogUtils.info("\uD83C\uDF81Actual Header của Customers page là: " + getElementText(headerCustomerPage));
        LogUtils.info("☘️☘️☘️☘️☘️☘️☘️☘️☘️☘️☘️☘️");
        softAssert.assertAll();
    }

    public void verifyListItem() {
        waitForPageLoaded();
        Assert.assertTrue(checkElementExist(listItem), "ListItem NOT exists");
        Assert.assertTrue(checkElementDisplayed(listItem), "ListItem NOT displayed");
        LogUtils.info("\uD83E\uDD29List Item in Customer page is: " + getElementText(listItem));
    }

    public void clickButtonAddNewCustomer() {
        clickElement(buttonAddNewCustomer);
        waitForPageLoaded();
    }

    public void verifyFormAddNewCustomer() {
        Assert.assertTrue(checkElementExist(formAddNewCustomer), "Form add new customer NOT exists");
        Assert.assertTrue(checkElementDisplayed(formAddNewCustomer), "Form add new customer NOT displayed");
    }

    public void inputDataCustomer(String COMPANY_NAME) {
        setText(inputCompany, COMPANY_NAME);
        setText(inputVATNumber, "9");
        setText(inputPhone, "0987654321");
        setText(inputWebsite, "http://www.hientestauto.com");
        clickElement(dropdownGroup);
        sleep(1);
        setText(inputSearchGroup, "Gold");
        sleep(2);
        setKey(inputSearchGroup, Keys.ENTER);
        clickElement(dropdownGroup);
        clickElement(dropdownCurrency);
        sleep(1);
        setText(inputSearchCurrency, "USD");
        sleep(2);
        setKey(inputSearchCurrency, Keys.ENTER);
        clickElement(dropdownDefaultLanguage);
        sleep(1);
        clickElement(optionDefaultLanguage);
        sleep(1);
        setText(inputAddress, "Đông Anh, Hà Nội");
        setText(inputCity, "Hà Nội");
        setText(inputState, "123");
        setText(inputZipCode, "89898");
        clickElement(dropdownCountry);
        sleep(2);
        setText(inputSearchCountry, "Vietnam");
        sleep(1);
        setKey(inputSearchCountry, Keys.ENTER);
        clickElement(buttonSave);
        waitForPageLoaded();
    }

    public void verifyCustomerDetailAfterAddNew() {
        waitForPageLoaded();
        Assert.assertTrue(checkElementExist(formCustomerDetail), "Form customer detail NOT exist");
        Assert.assertTrue(checkElementDisplayed(formCustomerDetail), "Form customer detail NOT displayed");
    }

    public void clickContactTag() {
        clickElement(contactsTag);
        waitForPageLoaded();
    }

    public void verifyWhenClickContactTag() {
        Assert.assertTrue(checkElementExist(labelContacts), "Label Contacts NOT exist");
        Assert.assertTrue(checkElementDisplayed(labelContacts), "Label Contacts NOT displayed");
        assertEquals(getElementText(labelContacts), labelContactExpected, "Label Contacts NOT match.");
        Assert.assertTrue(checkElementExist(formContenContactsPage), "Form Contacts NOT exist");
        Assert.assertTrue(checkElementDisplayed(formContenContactsPage), "Form Contacts NOT displayed");
        LogUtils.info("\uD83C\uDF3ALabel contact page is: " + getElementText(labelContacts));
    }

    public void clickButtonAddNewContact(String COMPANY_NAME) {
        clickElement(buttonAddNewContact);
        waitForPageLoaded();
        sleep(2);
        Assert.assertTrue(checkElementExist(formAddNewContact), "Form add new contact NOT exist");
        Assert.assertTrue(checkElementDisplayed(formAddNewContact), "Form add new contact NOT displayed");
        assertEquals(getElementText(nameCompanyActual), COMPANY_NAME, "Name Company Actual NOT match.");
        LogUtils.info("\uD83C\uDF3AName Company Actual is: " + getElementText(nameCompanyActual));
    }

    public void inputDataContact() {
        setText((inputProfileImg), System.getProperty("user.dir") + "\\src\\test\\resources\\testdata\\picture.jpg");
        sleep(2);
        setText(inputFirstName, "HienQC");
        setText(inputLastName, "Pham");
        setText(inputPosition, "1");
        setText(inputEmail, "hienqa@gmail.com");
        setText(inputPhoneContact, "123456789");
        clickElement(dropdownDirection);
        sleep(1);
        clickElement(optionDirection);
        sleep(2);
        setText(inputPasswordContact, "123456");
        clickElement(checkboxDoNotSendWelcomeEmail);
        scrollToElement(buttonSaveContact);
        clickElement(buttonSaveContact);
        waitForPageLoaded();
    }

    public void clickMenuCustomer() {
        clickElement(DashboardPage.menuCustomer);
        waitForPageLoaded();
    }

    public void searchCustomer(String COMPANY_NAME) {
        setText(inputSearch, COMPANY_NAME);
        waitForPageLoaded();
        Assert.assertTrue(checkElementExist(fistItemcustomerOnTable), "COMPANY_NAME  NOT exists.");
        Assert.assertTrue(checkElementDisplayed(fistItemcustomerOnTable), "COMPANY_NAME NOT displayed.");
        sleep(1);
    }

    public void searchCustomerAfterEdit(String COMPANY_NAMEEDIT) {
        setText(inputSearch, COMPANY_NAMEEDIT);
        waitForPageLoaded();
        Assert.assertTrue(checkElementExist(fistItemcustomerOnTable), "COMPANY_NAME  NOT exists.");
        Assert.assertTrue(checkElementDisplayed(fistItemcustomerOnTable), "COMPANY_NAME NOT displayed.");
        sleep(1);
    }

    public void searchCustomerAfterDelete() {
        setText(inputSearch, "Nodo");
        waitForPageLoaded();
        assertEquals(getElementText(fistItemcustomerOnTableAfterDelete), "No matching records found", "Có dữ liệu");
        sleep(2);
    }

    public void verifyCustomerDetail(String COMPANY_NAME) {
        waitForPageLoaded();
        clickElement(fistItemcustomerOnTable);
        assertEquals(getElementAttribute(inputCompany, "value"), COMPANY_NAME, "Company NOT match.");
        assertEquals(getElementAttribute(inputVATNumber, "value"), "9", "VAT NOT match.");
        assertEquals(getElementAttribute(inputPhone, "value"), "0987654321", "Phone NOT match.");
        assertEquals(getElementAttribute(inputWebsite, "value"), "http://www.hientestauto.com", "WEB NOT match.");

        assertEquals(getElementText(dropdownGroup), "Gold", "Data Group NOT match.");
        assertEquals(getElementText(dropdownCurrency), "USD", "Data Currency NOT match.");
        assertEquals(getElementAttribute(dropdownDefaultLanguage, "title"), "Vietnamese", "Data Language NOT match.");
        assertEquals(getElementText(inputAddress), "Đông Anh, Hà Nội", "Data Address NOT match.");
        assertEquals(getElementAttribute(inputCity, "value"), "Hà Nội", "City NOT match.");
        assertEquals(getElementAttribute(inputState, "value"), "123", "State NOT match.");
        assertEquals(getElementAttribute(inputZipCode, "value"), "89898", "ZipCode NOT match.");
        assertEquals(getElementAttribute(dropdownCountry, "title"), "Vietnam", "Data Country NOT match.");
        LogUtils.info("\uD83C\uDF3AAdd new Customer successfully");
    }

    public void verifyCustomerDetailAfterEdit() {
        waitForPageLoaded();
        clickElement(fistItemcustomerOnTable);
        sleep(2);
        assertEquals(getElementAttribute(inputCompany, "value"), CustomerTest.COMPANY_NAMEEDIT, "Company NOT match.");
        assertEquals(getElementAttribute(inputVATNumber, "value"), "9", "VAT NOT match.");
        assertEquals(getElementAttribute(inputPhone, "value"), "0987654321", "Phone NOT match.");
        assertEquals(getElementAttribute(inputWebsite, "value"), "http://www.hientestauto.com", "WEB NOT match.");

        assertEquals(getElementText(dropdownGroup), "Gold", "Data Group NOT match.");
        assertEquals(getElementText(dropdownCurrency), "USD", "Data Currency NOT match.");
        assertEquals(getElementAttribute(dropdownDefaultLanguage, "title"), "Vietnamese", "Data Language NOT match.");
        assertEquals(getElementText(inputAddress), "Đông Anh, Hà Nội", "Data Address NOT match.");
        assertEquals(getElementAttribute(inputCity, "value"), "Hà Nội", "City NOT match.");
        assertEquals(getElementAttribute(inputState, "value"), "123", "State NOT match.");
        assertEquals(getElementAttribute(inputZipCode, "value"), "89898", "ZipCode NOT match.");
        assertEquals(getElementAttribute(dropdownCountry, "title"), "Vietnam", "Data Country NOT match.");
    }

    public void verifyWhenEditCustomer(String COMPANY_NAMEEDIT) {
        waitForPageLoaded();
        clickElement(fistItemcustomerOnTable);
        sleep(1);
        clearText(inputCompany);
        setText(inputCompany, COMPANY_NAMEEDIT);
        sleep(2);
        scrollToElement(dropdownCountry);
        sleep(2);
        clickElement(buttonSaveAfterEdit);
        sleep(1);
    }

    public void verifyWhenDeleteCustomer(String COMPANY_NAMEDELETE) {
        waitForPageLoaded();
        clickButtonAddNewCustomer();
        inputDataCustomer(COMPANY_NAMEDELETE);
        clickMenuCustomer();
        searchCustomer(COMPANY_NAMEDELETE);
        hoverElement(fistItemcustomerOnTable);
        clickElement(buttonDelete);
        DriverManager.getDriver().switchTo().alert().accept();
        sleep(2);
    }

    public void deleteCustomer(String COMPANY_NAMEDELETE) {
        waitForPageLoaded();
        clickMenuCustomer();
        sleep(2);
        searchCustomer(COMPANY_NAMEDELETE);
        hoverElement(fistItemcustomerOnTable);
        clickElement(buttonDelete);
        DriverManager.getDriver().switchTo().alert().accept();
        sleep(2);
    }

}
