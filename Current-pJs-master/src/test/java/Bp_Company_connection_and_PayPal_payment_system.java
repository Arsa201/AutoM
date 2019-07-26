import org.aspectj.lang.annotation.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class Bp_Company_connection_and_PayPal_payment_system {
    private static WebDriver driver;

    @BeforeSuite
    public void toLoginPage() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://stage-bpayments.cloudbusinessltd.com/login/auth");
        WebElement loginIntuit = driver.findElement(By.className("intuit_login"));
        loginIntuit.click();
        WebElement loginWithGoogle = driver.findElement(By.id("ius-google-button"));
        loginWithGoogle.click();
        WebElement emailField = driver.findElement(By.id("identifierId"));
        emailField.sendKeys("arseniy.yanson@realizeideas.net");
        WebElement identifierNext = driver.findElement(By.id("identifierNext"));
        identifierNext.click();
        WebElement passField = driver.findElement(By.xpath("//*[@id='password']/div[1]/div/div[1]/input"));
        passField.sendKeys("Ars2473849");
        WebElement passNext = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.id("passwordNext"))));
        passNext.click();
        WebElement intuitPass = driver.findElement(By.xpath("//*[@id=\"ius-sign-in-mfa-password-collection-current-password\"]"));
        intuitPass.sendKeys("Arsamas1984@");
        WebElement intuitPassContinue = driver.findElement(By.xpath("//*[@id='ius-sign-in-mfa-password-collection-continue-btn']"));
        intuitPassContinue.click();
    }

    @Test(priority = 1)
    public void connectYourQuickBooksCompanyStep1() {
        WebElement connectButton = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.cssSelector("#main-container > div > div.main-content > div > div.row > div > div:nth-child(4) > div > a > img"))));
        connectButton.click();
    }

    @Test(priority = 2)
    public void connectYourQuickBooksCompany2() {
        WebElement chooseCompany = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.xpath("//*[@id='company-3']/a"))));//third in the list sandbox US
        chooseCompany.click();
    }

    @Test(priority = 3)
    public void connectYourQuickBooksCompany3() {
        WebElement chooseConnectButton = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.xpath("//*[@id='authorize']/div/div[2]/div[3]/form/button"))));
        chooseConnectButton.click();

        System.out.println("First: Company connection passed: " + driver.getCurrentUrl());
    }

    @Test(priority = 4)
    public void connectYourPayPalSystemStep2() {
        WebElement payPalButton = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.cssSelector("#main-container > div > div.main-content > div > div.row > div > div.row.margin-top-small.center > p > a:nth-child(1)"))));
        System.out.println("First: connectYourPayPalSystem text: " + payPalButton.getText());
        Assert.assertEquals("Connect PayPal", payPalButton.getText());
        payPalButton.click();
    }

    @Test(priority = 5)
    public void loginInPayPal() {
        WebElement loginField = driver.findElement(By.id("login_email"));
        loginField.sendKeys("6653312@gmail.com");
        WebElement passwField = driver.findElement(By.id("login_password"));
        passwField.sendKeys("Arsamas1984");
        WebElement submitButton = driver.findElement(By.id("login.x"));
        submitButton.click();
    }

    @Test(priority = 6)
    public void grantPermission() {
        WebElement grantPermissionButton = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.xpath("//*[@id='apiGrantPermission']/p[5]/input[1]"))));
        grantPermissionButton.click();

        WebElement salesSettingsPage = (new WebDriverWait(driver, 30).until(presenceOfElementLocated(By.xpath("//*[@id='sales-txn-settings']/div[1]/h4"))));
        Assert.assertEquals("Sales settings", salesSettingsPage.getText());
        System.out.println("First: Payment system connection step2 passed");
    }

    @Test(priority = 7)
    public void step3ConnectYourPayPalSystemSalesSettings() {
        WebDriverWait waitUntilDropDownIsAppears = new WebDriverWait(driver, 30);
        waitUntilDropDownIsAppears.until(visibilityOfElementLocated(By.xpath("//*[@id='sales-txn-settings']/div[2]/div[1]/div/div[2]/div/span/span[1]/span/span[2]/b"))).click();

        WebElement elementsInDropDown = driver.findElement(By.xpath("//*[@id='deposit-account']"));
        Assert.assertNotNull(String.valueOf(elementsInDropDown));

        Select oSelect = new Select(elementsInDropDown);
        oSelect.selectByIndex(1);
        oSelect.selectByIndex(0);
        oSelect.selectByIndex(2);
        oSelect.selectByIndex(3);
        System.out.println("First: PayPal - Sales settings - BankAccountField text: " + oSelect.getFirstSelectedOption().getText());
        //for (WebElement w : oSelect.getAllSelectedOptions()) {
        //    System.out.println(w.getText());
        //}
        WebElement closeDropDownAccounts = driver.findElement(By.xpath("//*[@id='sales-txn-settings']/div[2]/div[1]/div/div[2]/div/span/span[1]/span/span[2]/b"));
        closeDropDownAccounts.click();
    }

    @Test(priority = 8)
    public void step3ConnectPayPalSystemPaymentMethodFieldSalesSettings() {
        WebDriverWait waitUntilDropDownIsAppears = new WebDriverWait(driver, 30);
        waitUntilDropDownIsAppears.until(visibilityOfElementLocated(By.xpath("//*[@id='payment-method']"))).click();

        WebElement elementsInDropDown = driver.findElement(By.xpath("//*[@id='payment-method']"));
        Assert.assertNotNull(String.valueOf(elementsInDropDown));

        Select oSelect = new Select(elementsInDropDown);
        oSelect.selectByIndex(1);
        oSelect.selectByIndex(0);
        oSelect.selectByIndex(2);
        oSelect.selectByIndex(5);
        System.out.println("First: PayPal - Sales settings - PaymentMethodField text: " + oSelect.getFirstSelectedOption().getText());

        WebElement closeDropDownAccounts = driver.findElement(By.xpath("//*[@id='payment-method']"));
        closeDropDownAccounts.click();

        /*try {
            Thread.sleep(2000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }*/

        WebElement toStep4Button = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.cssSelector("#main-container > div > div.main-content > div > div.row > div > form > div > div > div.col-md-12.center.flow-buttons > div > div > button.btn.btn-primary.btn-squared.next-step-button"))));
        toStep4Button.click();
    }

    @Test(priority = 9)
    public void connectPayPalSystemSalesSettingsBackStep() {
        WebElement BackStepButton = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.cssSelector("#main-container > div > div.main-content > div > div.row > div > form > div > div > div.col-md-12.center.flow-buttons > div > div > button.btn.btn-teal.btn-squared.previous-step-button"))));
        Assert.assertTrue(BackStepButton.isDisplayed());
        BackStepButton.click();

        WebDriverWait waitUntilDropDownIsAppears = new WebDriverWait(driver, 30);
        waitUntilDropDownIsAppears.until(visibilityOfElementLocated(By.xpath("//*[@id='sales-txn-settings']/div[2]/div[1]/div/div[2]/div/span/span[1]/span/span[2]/b"))).click();

        WebElement elementsInDropDown = driver.findElement(By.xpath("//*[@id='deposit-account']"));
        Assert.assertNotNull(String.valueOf(elementsInDropDown));

        Select oSelect = new Select(elementsInDropDown);
        oSelect.selectByIndex(2);
        oSelect.selectByIndex(0);
        System.out.println("First: PayPal - Sales settings - BankAccountField - BackStep text: " + oSelect.getFirstSelectedOption().getText());

        WebElement closeDropDownAccounts = driver.findElement(By.xpath("//*[@id='sales-txn-settings']/div[2]/div[1]/div/div[2]/div/span/span[1]/span/span[2]/b"));
        closeDropDownAccounts.click();
    }

    @Test(priority = 10)
    public void connectPayPalSystemSalesSettingsPaymentMethodFieldBackStep() {

        WebDriverWait waitUntilDropDownIsAppears = new WebDriverWait(driver, 30);
        waitUntilDropDownIsAppears.until(visibilityOfElementLocated(By.xpath("//*[@id='payment-method']"))).click();

        WebElement elementsInDropDown = driver.findElement(By.xpath("//*[@id='payment-method']"));
        Assert.assertNotNull(String.valueOf(elementsInDropDown));

        Select oSelect = new Select(elementsInDropDown);
        oSelect.selectByIndex(1);
        oSelect.selectByIndex(9);

        System.out.println("First: PayPal - Sales settings - PaymentMethodField - BackStep text: " + oSelect.getFirstSelectedOption().getText());

        WebElement closeDropDownAccounts = driver.findElement(By.xpath("//*[@id='payment-method']"));
        closeDropDownAccounts.click();

        WebElement toStep4Button = (new WebDriverWait(driver, 30).until(presenceOfElementLocated(By.cssSelector("#main-container > div > div.main-content > div > div.row > div > form > div > div > div.col-md-12.center.flow-buttons > div > div > button.btn.btn-primary.btn-squared.next-step-button"))));
        toStep4Button.click();
    }

    @Test(priority = 11)
    public void step4ConnectPayPalProductSettings() {

        WebDriverWait waitUntilDropDownIsAppears = new WebDriverWait(driver, 30);
        waitUntilDropDownIsAppears.until(visibilityOfElementLocated(By.xpath("//*[@id='itemType']"))).click();

        WebElement elementsInDropDown = driver.findElement(By.xpath("//*[@id='itemType']"));
        Assert.assertNotNull(String.valueOf(elementsInDropDown));

        Select oSelect = new Select(elementsInDropDown);
        oSelect.selectByIndex(0);
        oSelect.selectByIndex(1);
        System.out.println("First: PayPal - Product settings - Product/ServiceField text: " + oSelect.getFirstSelectedOption().getText());

        WebElement closeDropDownAccounts = driver.findElement(By.xpath("//*[@id='itemType']"));
        closeDropDownAccounts.click();
    }

    @Test(priority = 12)
    public void step4ConnectPayPalProductSettingsIncomeAccount() {
        WebDriverWait waitUntilDropDownIsAppears = new WebDriverWait(driver, 30);
        waitUntilDropDownIsAppears.until(visibilityOfElementLocated(By.xpath("//*[@id='select2-item-income-account-container']"))).click();

        WebElement elementsInDropDown = driver.findElement(By.xpath("//*[@id='item-income-account']"));
        Assert.assertNotNull(String.valueOf(elementsInDropDown));

        Select oSelect = new Select(elementsInDropDown);
        oSelect.selectByIndex(1);
        oSelect.selectByIndex(0);
        oSelect.selectByIndex(2);
        oSelect.selectByIndex(5);
        System.out.println("First: PayPal - Product settings - IncomeAccountField text: " + oSelect.getFirstSelectedOption().getText());

        WebElement closeDropDownAccounts = driver.findElement(By.xpath("//*[@id='item-settings']/div[2]/div[2]/div/div[2]/div/span/span[1]/span/span[2]/b"));
        closeDropDownAccounts.click();

        WebElement toStep5Button = (new WebDriverWait(driver, 30).until(presenceOfElementLocated(By.cssSelector("#main-container > div > div.main-content > div > div.row > div > form > div > div > div.col-md-12.center.flow-buttons > div > div > button.btn.btn-primary.btn-squared.next-step-button"))));
        toStep5Button.click();
    }

    @Test(priority = 13)
    public void connectPayPalStep4BackStep() {
        WebElement BackStepButton = (new WebDriverWait(driver, 30).until(presenceOfElementLocated(By.cssSelector("#main-container > div > div.main-content > div > div.row > div > form > div > div > div.col-md-12.center.flow-buttons > div > div > button.btn.btn-teal.btn-squared.previous-step-button"))));
        Assert.assertTrue(BackStepButton.isDisplayed());
        BackStepButton.click();

        WebDriverWait waitUntilDropDownIsAppears = new WebDriverWait(driver, 30);
        waitUntilDropDownIsAppears.until(visibilityOfElementLocated(By.xpath("//*[@id='itemType']"))).click();

        WebElement elementsInDropDown = driver.findElement(By.xpath("//*[@id='itemType']"));
        Assert.assertNotNull(String.valueOf(elementsInDropDown));

        Select oSelect = new Select(elementsInDropDown);
        oSelect.selectByIndex(1);
        oSelect.selectByIndex(0);
        System.out.println("First: PayPal - Product settings - Product/ServiceField - BackStep text: " + oSelect.getFirstSelectedOption().getText());

        WebElement closeDropDownAccounts = driver.findElement(By.xpath("//*[@id='itemType']"));
        closeDropDownAccounts.click();
    }

    @Test(priority = 14)
    public void connectPayPalSystemIncomeAccountStepBack() {
        WebDriverWait waitUntilDropDownIsAppears = new WebDriverWait(driver, 30);
        waitUntilDropDownIsAppears.until(presenceOfElementLocated(By.xpath("//*[@id='select2-item-income-account-container']"))).click();

        WebElement elementsInDropDown = driver.findElement(By.xpath("//*[@id='item-income-account']"));
        Assert.assertNotNull(String.valueOf(elementsInDropDown));

        Select oSelect = new Select(elementsInDropDown);
        oSelect.selectByIndex(1);
        oSelect.selectByIndex(0);

        System.out.println("First: PayPal - Product settings - IncomeAccountField - BackStep text: " + oSelect.getFirstSelectedOption().getText());

        WebElement closeDropDownAccounts = driver.findElement(By.xpath("//*[@id='item-settings']/div[2]/div[2]/div/div[2]/div/span/span[1]/span/span[2]/b"));
        closeDropDownAccounts.click();

        WebElement toStep5Button = (new WebDriverWait(driver, 30).until(presenceOfElementLocated(By.cssSelector("#main-container > div > div.main-content > div > div.row > div > form > div > div > div.col-md-12.center.flow-buttons > div > div > button.btn.btn-primary.btn-squared.next-step-button"))));
        toStep5Button.click();
    }

    @Test(priority = 15)
    public void connectPayPalSystemApplyTaxesToggle() {

        WebElement toggleOn = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.xpath("//*[@id='tax-settings-tab-wrapper']/div[1]/div/div/div/label[1]"))));
        WebElement toggleOff = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.xpath("//*[@id='tax-settings-tab-wrapper']/div[1]/div/div/div/label[2]"))));

        boolean notifyToggleOn;
        notifyToggleOn = toggleOn.isSelected();
        boolean notifyToggleOff = toggleOff.isSelected();
        if (!notifyToggleOff) {
            try {
                toggleOff.click();
                System.out.println("First: applyTaxes toggle: !OFF>click ");
            } catch (Exception ignored) {
            }
        }

        if (!notifyToggleOn) {
            try {
                toggleOn.click();
                System.out.println("First: applyTaxes toggle: !ON>click ");
            } catch (Exception ignored) {
            }
        }
        WebElement defaultTaxCode = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.xpath("//*[@id='default-tax-code']"))));
        defaultTaxCode.click();
        Assert.assertNotNull(String.valueOf(defaultTaxCode));
        Select oSelect = new Select(defaultTaxCode);
        oSelect.selectByIndex(1);
        oSelect.selectByIndex(0);
        oSelect.selectByIndex(2);
        oSelect.selectByIndex(4);
        System.out.println("First: PayPal - Tax settings - DefaultTaxCodeField text: " + oSelect.getFirstSelectedOption().getText());

        WebElement toStep6Button = (new WebDriverWait(driver, 30).until(presenceOfElementLocated(By.cssSelector("#main-container > div > div.main-content > div > div.row > div > form > div > div > div.col-md-12.center.flow-buttons > div > div > button.btn.btn-primary.btn-squared.next-step-button"))));
        toStep6Button.click();
    }

    @Test(priority = 16)
    public void connectPayPalSystemFeeSettingsVendorField() {
        WebDriverWait waitUntilDropDownIsAppears = new WebDriverWait(driver, 30);
        waitUntilDropDownIsAppears.until(elementToBeClickable(By.xpath("//*[@id='fee-settings']/div[2]/div[1]/div[1]/div[2]/div/span"))).click();

        WebElement elementsInDropDown = driver.findElement(By.xpath("//*[@id='fee-vendor']"));
        Assert.assertNotNull(String.valueOf(elementsInDropDown));
        Select oSelect = new Select(elementsInDropDown);
        oSelect.selectByIndex(5);
        oSelect.selectByIndex(0);
        oSelect.selectByIndex(2);
        oSelect.selectByIndex(4);
        oSelect.selectByIndex(0);

        System.out.println("First: PayPal - FeeSettings - VendorField text: " + oSelect.getFirstSelectedOption().getText());

        WebElement closeDropDownAccounts = driver.findElement(By.xpath("//*[@id='fee-settings']/div[2]/div[1]/div[1]/div[2]/div/span/span[1]/span/span[2]/b"));
        closeDropDownAccounts.click();
    }

    @Test(priority = 17)
    public void connectPayPalSystemFeeSettingsExpenseLineAccountField() {
        WebDriverWait waitUntilDropDownIsAppears = new WebDriverWait(driver, 30);
        waitUntilDropDownIsAppears.until(elementToBeClickable(By.xpath("//*[@id='fee-settings']/div[2]/div[2]/div/div[2]/div/span/span[1]/span"))).click();

        WebElement elementsInDropDown = driver.findElement(By.xpath("//*[@id='fee-line-account']"));
        Assert.assertNotNull(String.valueOf(elementsInDropDown));
        Select oSelect = new Select(elementsInDropDown);
        oSelect.selectByIndex(5);
        oSelect.selectByIndex(0);
        oSelect.selectByIndex(2);
        oSelect.selectByIndex(4);
        oSelect.selectByIndex(0);

        System.out.println("First: PayPal - FeeSettings - ExpenseLineAccountField text: " + oSelect.getFirstSelectedOption().getText());
    }

    @Test(priority = 18)
    public void connectPayPalSystemFeeSettingsBankAccountField() {
        WebDriverWait waitUntilDropDownIsAppears = new WebDriverWait(driver, 30);
        waitUntilDropDownIsAppears.until(visibilityOfElementLocated(By.xpath("//*[@id='fee-bank-account']"))).click();

        WebElement elementsInDropDown = driver.findElement(By.xpath("//*[@id='fee-bank-account']"));
        Assert.assertNotNull(String.valueOf(elementsInDropDown));
        /*try {
            Select oSelect = new Select(elementsInDropDown);
            oSelect.selectByIndex(5);
            oSelect.selectByIndex(0);
            oSelect.selectByIndex(2);
            oSelect.selectByIndex(4);
            oSelect.selectByIndex(0);
            System.out.println("test PayPalSystemBankAccount text: " + oSelect.getFirstSelectedOption().getText());
        } catch (Exception e) {
        }*/
        System.out.println("First: PayPal - FeeSettings - BankAccountField text: " + elementsInDropDown.getAttribute("value"));
        WebElement toStep7Button = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.cssSelector("#main-container > div > div.main-content > div > div.row > div > form > div > div > div.col-md-12.center.flow-buttons > div > div > button.btn.btn-primary.btn-squared.next-step-button"))));
        toStep7Button.click();
    }

    @Test(priority = 19)
    public void connectPayPalSystemExpenseSettingsBankCreditAccountField() {
        WebDriverWait waitUntilDropDownIsAppears = new WebDriverWait(driver, 30);
        waitUntilDropDownIsAppears.until(visibilityOfElementLocated(By.xpath("//*[@id='expense-bank-account']"))).click();

        WebElement elementsInDropDown = driver.findElement(By.xpath("//*[@id='expense-bank-account']"));
        Assert.assertNotNull(String.valueOf(elementsInDropDown));
        System.out.println("First: PayPal - ExpenseSettings - Bank/CreditAccountField: " + elementsInDropDown.getAttribute("value"));
        /*try {
            Select oSelect = new Select(elementsInDropDown);
            oSelect.selectByIndex(5);
            oSelect.selectByIndex(0);
            oSelect.selectByIndex(2);
            oSelect.selectByIndex(4);
            oSelect.selectByIndex(0);
            System.out.println("test PayPalSystemExpenseSettingsBankCreditAccountField text: " + (String.valueOf(elementsInDropDown)));
            //System.out.println("test PayPalSystemExpenseSettingsBankCreditAccountField text: " + oSelect.getFirstSelectedOption().getText());
        } catch (Exception e) {
        }*/
    }

    @Test(priority = 20)
    public void connectPayPalSystemExpenseSettingsExpenseLineAccountField() {
        WebDriverWait waitUntilDropDownIsAppears = new WebDriverWait(driver, 30);
        waitUntilDropDownIsAppears.until(presenceOfElementLocated(By.xpath("//*[@id='purchase-txn-settings']/div[2]/div[2]/div/div[2]/div/span/span[1]/span"))).click();

        WebElement elementsInDropDown = driver.findElement(By.xpath("//*[@id=\"expense-line-account\"]"));
        Assert.assertNotNull(String.valueOf(elementsInDropDown));
        try {
            Select oSelect = new Select(elementsInDropDown);
            oSelect.selectByIndex(5);
            oSelect.selectByIndex(0);
            oSelect.selectByIndex(2);
            oSelect.selectByIndex(4);
            System.out.println("First: PayPal - ExpenseSettings - ExpenseLineAccountField text: " + oSelect.getFirstSelectedOption().getText());
        } catch (Exception ignored) {
        }

        WebElement closeDropDownAccounts = driver.findElement(By.xpath("//*[@id='purchase-txn-settings']/div[2]/div[2]/div/div[2]/div/span/span[1]/span/span[2]/b"));
        closeDropDownAccounts.click();

        WebElement toStep8Button = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.cssSelector("#main-container > div > div.main-content > div > div.row > div > form > div > div > div.col-md-12.center.flow-buttons > div > div > button.btn.btn-primary.btn-squared.next-step-button"))));
        toStep8Button.click();
    }

    @Test(priority = 21)
    public void step8ConnectPayPalSystemImportOldTransactions() {

        WebElement startDateCalendar = driver.findElement(By.id("start-date"));
        Assert.assertNotNull(String.valueOf(startDateCalendar));
        ((JavascriptExecutor)driver).executeScript("arguments[0].value = arguments[1];", startDateCalendar, "12/01/2018");

        WebElement endDateCalendar = driver.findElement(By.id("end-date"));
        Assert.assertNotNull(String.valueOf(endDateCalendar));
        ((JavascriptExecutor)driver).executeScript("arguments[0].value = arguments[1];", endDateCalendar, "12/01/2018");


        //endDateCalendar.click();

        //WebElement activeStartDate = driver.findElement((By.cssSelector("body > div:nth-child(7) > div.datepicker-days > table > tbody > tr:nth-child(1) > td:nth-child(7)")));
        //activeStartDate.click();
        //Assert.assertNotNull(String.valueOf(activeStartDate));

        //WebElement endDateCalendar = driver.findElement(By.id("end-date"));
        //endDateCalendar.click();

//        WebElement activeEndDate = driver.findElement((By.cssSelector("body > div:nth-child(7) > div.datepicker-days > table > tbody > tr:nth-child(1) > td:nth-child(7)")));
//        activeEndDate.click();
//        Assert.assertNotNull(String.valueOf(activeEndDate));

        WebElement showTxnsButton = driver.findElement((By.id("load-data")));
        showTxnsButton.click();

        String valueStartDate = startDateCalendar.getAttribute("value");/*Here, input type -- String phField = phoneField.getText();*/
        System.out.println("First: startDateCalendar value: " + valueStartDate);
        Assert.assertEquals("12/01/2018", valueStartDate);/*Assert.assertEquals("112233", phField );*/

        String valueEndDate = endDateCalendar.getAttribute("value");
        System.out.println("First: endDateCalendar value: " + valueEndDate);
        Assert.assertEquals("12/01/2018", valueEndDate);

        //WebElement TxnDateField = driver.findElement((By.cssSelector("#main-container > div > div.main-content > div > div.row > div > form > div.import-data-table > table > tbody > tr > td:nth-child(1)")));
        //WebElement skipTestButton = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.cssSelector("#skip-step"))));
        WebElement TxnDateField = (new WebDriverWait(driver, 30).until(visibilityOfElementLocated(By.cssSelector("#main-container > div > div.main-content > div > div.row > div > form > div.import-data-table > table > tbody > tr > td.center:nth-child(1)"))));
        System.out.println("TxnDateField " + TxnDateField.getText());
        Assert.assertEquals("12/01/2018 13:12:00", TxnDateField.getText());




    }
    @Test(priority = 22)
    public void finishConnectPayPalSystem() {

        WebElement ImportButton = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.cssSelector("#main-container > div > div.main-content > div > div.row > div > form > div:nth-child(8) > div > div > button.btn.btn-green-custom.btn-squared"))));
        ImportButton.click();

        WebElement finishButton = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.cssSelector("#main-container > div > div.main-content > div > div.row > div > form > div > div > a"))));
        finishButton.click();
        System.out.println("First: PayPal connection flow is complete. ");
    }

















    @Test(priority = 1, enabled = false)
    public void toMyProfile() {
        WebElement myProfile = (new WebDriverWait(driver, 20)).until(presenceOfElementLocated(By.xpath("/html/body/div[1]/div/div[2]/ul/li/a/span")));
        String mailUser = myProfile.getText();
        Assert.assertEquals("My profile", mailUser);
        myProfile.click();
    }

    @Test(priority = 2, enabled = false)
    public void toSettingsAccount() {
        WebElement settingsButton = (new WebDriverWait(driver, 20)).until(presenceOfElementLocated(By.xpath("/html/body/div[1]/div/div[2]/ul/li/ul/li[1]/a")));
        settingsButton.click();
        WebElement settingsAccountButton = driver.findElement(By.xpath("//*[@id='main-container']/div/div[3]/div/div[3]/div/div[1]/div/div[2]/div/label[2]"));
        String accountButton = settingsAccountButton.getText();
        System.out.println("test settingsAccountButton text: " + accountButton);
        Assert.assertEquals("Account", accountButton);
        settingsAccountButton.click();
    }

    @Test(priority = 3, enabled = false)
    public void phoneField() {
        WebElement phone = (new WebDriverWait(driver, 20)).until(presenceOfElementLocated(By.xpath("//*[@id='phone']")));
        phone.clear();/*driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);*/
        phone.sendKeys("+112(2) 33");
        WebElement updateButton = driver.findElement(By.xpath("//*[@id='account-settings-form']/form/div[2]/div/button"));
        updateButton.click();
    }

    @Test(priority = 4, enabled = false)
    public void phoneFieldAssertion() {
        WebElement accountButton = (new WebDriverWait(driver, 20)).until(presenceOfElementLocated(By.xpath("//*[@id='main-container']/div/div[3]/div/div[3]/div/div[1]/div/div[2]/div/label[2]")));
        accountButton.click();
        WebElement phoneField = driver.findElement(By.id("phone"));
        String value = phoneField.getAttribute("value");/*Here, input type -- String phField = phoneField.getText();*/
        System.out.println("test phone field: value of input type Phone field= " + value);
        Assert.assertEquals("+112(2) 33", value);/*Assert.assertEquals("112233", phField );*/
    }

    @Test(priority = 5, enabled = false)
    public void notifyAboutAppActivities() {

        WebElement notifyToggleYes = driver.findElement(By.xpath("//*[@id='account-settings-form']/form/div[1]/div[1]/div[2]/div/div/label[1]"));
        WebElement notifyToggleNo = driver.findElement(By.xpath("//*[@id='account-settings-form']/form/div[1]/div[1]/div[2]/div/div/label[2]"));

        boolean ToggleYes = notifyToggleYes.isSelected();
        boolean ToggleNo = notifyToggleNo.isSelected();
        if (!ToggleYes) {
            try {
                notifyToggleYes.click();
                System.out.println("test notifyAboutActivities toggle: It was YES");
                WebElement updateButton = driver.findElement(By.xpath("//*[@id='account-settings-form']/form/div[2]/div/button"));
                updateButton.click();
            } catch (Exception e) {
                //System.out.println("test notifyAboutActivities toggle: There is no such text 'YES', must be 'No'");
            }
        }
        if (!ToggleNo) {
            try {
                notifyToggleNo.click();
                System.out.println("test notifyAboutActivities toggle: It was NO");
                WebElement updateButton = driver.findElement(By.xpath("//*[@id='account-settings-form']/form/div[2]/div/button"));
                updateButton.click();
            } catch (Exception e) {
                //System.out.println("test notifyAboutActivities toggle: There is no such text 'No', must be 'Yes'");
            }
        } else {
            System.out.println("We will see that message if: Switching is NOT_OK!!!");
        }
        WebElement accountButton = (new WebDriverWait(driver, 20)).until(presenceOfElementLocated(By.xpath("//*[@id='main-container']/div/div[3]/div/div[3]/div/div[1]/div/div[2]/div/label[2]")));
        accountButton.click();
    }

    @Test(priority = 6, enabled = false)
    public void notifyOffersAndServices() {

        WebElement notifyToggleYes = driver.findElement(By.xpath("//*[@id=\"account-settings-form\"]/form/div[1]/div[1]/div[3]/div/div/label[1]"));
        WebElement notifyToggleNo = driver.findElement(By.xpath("//*[@id=\"account-settings-form\"]/form/div[1]/div[1]/div[3]/div/div/label[2]"));

        boolean ToggleYes = notifyToggleYes.isSelected();
        boolean ToggleNo = notifyToggleNo.isSelected();
        if (!ToggleYes) {
            try {
                notifyToggleYes.click();
                System.out.println("test notifyOffersAndServices toggle: It was YES");
                WebElement updateButton = driver.findElement(By.xpath("//*[@id='account-settings-form']/form/div[2]/div/button"));
                updateButton.click();
            } catch (Exception e) {
                //System.out.println("test notifyOffersAndServices toggle: There is no such text 'YES', must be 'No'");
            }
        }
        if (!ToggleNo) {
            try {
                notifyToggleNo.click();
                System.out.println("test notifyOffersAndServices toggle: It was NO");
                WebElement updateButton = driver.findElement(By.xpath("//*[@id='account-settings-form']/form/div[2]/div/button"));
                updateButton.click();
            } catch (Exception e) {
                //System.out.println("test notifyOffersAndServices toggle: There is no such text 'No', must be 'Yes'");
            }
        } else {
            System.out.println("We will see that message if: Switching is NOT_OK!!!");
        }
        WebElement accountButton = (new WebDriverWait(driver, 20)).until(presenceOfElementLocated(By.xpath("//*[@id='main-container']/div/div[3]/div/div[3]/div/div[1]/div/div[2]/div/label[2]")));
        accountButton.click();
    }

    @Test(priority = 7, enabled = false)
    public void areYouAccountantBookkeeper() {

        WebElement notifyToggleYes = driver.findElement(By.xpath("//*[@id=\"account-settings-form\"]/form/div[1]/div[1]/div[4]/div/div/label[1]"));
        WebElement notifyToggleNo = driver.findElement(By.xpath("//*[@id=\"account-settings-form\"]/form/div[1]/div[1]/div[4]/div/div/label[2]"));

        boolean ToggleYes = notifyToggleYes.isSelected();
        boolean ToggleNo = notifyToggleNo.isSelected();
        if (!ToggleYes) {
            try {
                notifyToggleYes.click();
                System.out.println("test areYouAccountantBookkeeper toggle: It was YES");
                WebElement updateButton = driver.findElement(By.xpath("//*[@id='account-settings-form']/form/div[2]/div/button"));
                updateButton.click();
            } catch (Exception e) {
                //System.out.println("test areYouAccountantBookkeeper toggle: There is no such text 'YES', must be 'No'");
            }
        }
        if (!ToggleNo) {
            try {
                notifyToggleNo.click();
                System.out.println("test areYouAccountantBookkeeper toggle: It was NO");
                WebElement updateButton = driver.findElement(By.xpath("//*[@id='account-settings-form']/form/div[2]/div/button"));
                updateButton.click();
            } catch (Exception e) {
                //System.out.println("test areYouAccountantBookkeeper toggle: There is no such text 'No', must be 'Yes'");
            }
        } else {
            System.out.println("We will see that message if: Switching is NOT_OK!!!");
        }
        WebElement accountButton = (new WebDriverWait(driver, 20)).until(presenceOfElementLocated(By.xpath("//*[@id='main-container']/div/div[3]/div/div[3]/div/div[1]/div/div[2]/div/label[2]")));
        accountButton.click();
    }

    @Test(priority = 8, enabled = false)
    public void settingsCompany() {
        WebElement settingsCompanyButton = driver.findElement(By.xpath("//*[@id=\"main-container\"]/div/div[3]/div/div[3]/div/div[1]/div/div[2]/div/label[1]"));
        String companyButton = settingsCompanyButton.getText();
        System.out.println("test settingsCompanyButton text: " + companyButton);
        Assert.assertEquals("Company", companyButton);
        settingsCompanyButton.click();
    }


@AfterClass
public void afterClass() {
        driver.quit();
}

}



