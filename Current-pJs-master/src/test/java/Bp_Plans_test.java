import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class Bp_Plans_test {
    private static WebDriver driver;

    @BeforeClass
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
    public void goToAccount() {
        WebElement myProfile = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.cssSelector("body > div.navbar.navbar-inverse.navbar-fixed-top > div > div.navbar-tools > ul > li > a > span"))));
        myProfile.click();

        WebElement accountButton = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.xpath("/html/body/div[1]/div/div[2]/ul/li/ul/li[2]/a"))));
        accountButton.click();
        System.out.println("Third: ToAccount ");
    }

    @Test(priority = 2)
    public void toYearlyPlans() {
        try{
            WebElement yearlyPlans = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.cssSelector("#main-container > div > div.main-content > div > div.row > div > div:nth-child(3) > div.panel-body > div.row.pricing-plans > div > div.row.center > div > div > button.btn.btn-default > strong"))));
            yearlyPlans.click();}
        catch (Exception e){
            System.out.println("It was on Yearly already ");
        }
    }

    @Test(priority = 3)
    public void subscribeToYearlySmall() {
        WebElement yearlySmall = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.cssSelector("#main-container > div > div.main-content > div > div.row > div > div:nth-child(3) > div.panel-body > div.row.pricing-plans > div > div.pricing-model.row > div:nth-child(3) > form > button > span"))));
        yearlySmall.click();
        System.out.println("Third: subscribeToYearlySmall ");

    }

    @Test(priority = 4)
    public void sandboxPayPalToYearlySmall() {
        try{
            WebElement payPalEmailField = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.id("email"))));
            payPalEmailField.sendKeys("6653312@gmail.com");

            WebElement payPalNextButton = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.id("btnNext"))));
            payPalNextButton.click();

            WebElement payPalPasswordField = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.id("password"))));
            payPalPasswordField.sendKeys("Arsamas1984");

            WebElement payPalLogInButton = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.id("btnLogin"))));
            payPalLogInButton.click();

            WebElement payPalContinueButton = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.xpath("//*[@id='button']/button"))));
            payPalContinueButton.click();
            WebElement payPalAgreeButton = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.id("confirmButtonTop"))));
            payPalAgreeButton.click();}

        catch(Exception ignored){
            WebElement payPalContinueButton = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.xpath("//*[@id='button']/button"))));
            payPalContinueButton.click();
            WebElement payPalAgreeButton = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.id("confirmButtonTop"))));
            payPalAgreeButton.click();
        }
        System.out.println("Third: sandboxPayPalToYearlySmall ");

    }

    @Test(priority = 5)
    public void subscribeToYearlyMedium() {
        WebElement yearlyMedium = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.cssSelector("#main-container > div > div.main-content > div > div.row > div > div:nth-child(3) > div.panel-body > div.row.pricing-plans > div > div.pricing-model.row > div:nth-child(5) > form > button"))));
        yearlyMedium.click();
        System.out.println("Third: subscribeToYearlyMedium ");

    }

    @Test(priority = 6)
    public void sandboxPayPalToYearlyMedium() {
        try{
        WebElement payPalEmailField = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.id("email"))));
        payPalEmailField.sendKeys("6653312@gmail.com");

        WebElement payPalNextButton = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.id("btnNext"))));
        payPalNextButton.click();

        WebElement payPalPasswordField = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.id("password"))));
        payPalPasswordField.sendKeys("Arsamas1984");

        WebElement payPalLogInButton = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.id("btnLogin"))));
        payPalLogInButton.click();

        WebElement payPalContinueButton = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.xpath("//*[@id='button']/button"))));
        payPalContinueButton.click();
        WebElement payPalAgreeButton = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.id("confirmButtonTop"))));
        payPalAgreeButton.click();}

        catch(Exception ignored){
            WebElement payPalContinueButton = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.xpath("//*[@id='button']/button"))));
            payPalContinueButton.click();
            WebElement payPalAgreeButton = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.id("confirmButtonTop"))));
            payPalAgreeButton.click();
        }

        System.out.println("Third: sandboxPayPalToYearlyMedium ");

    }

    @Test(priority = 7)
    public void subscribeToYearlyLarge() {
        WebElement yearlyLarge = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.cssSelector("#main-container > div > div.main-content > div > div.row > div > div:nth-child(3) > div.panel-body > div.row.pricing-plans > div > div.pricing-model.row > div:nth-child(7) > form > button"))));
        yearlyLarge.click();

        System.out.println("Third: subscribeToYearlyLarge ");

    }

    @Test(priority = 8)
    public void sandboxPayPalToYearlyLarge() {
        try{
            WebElement payPalEmailField = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.id("email"))));
            payPalEmailField.sendKeys("6653312@gmail.com");

            WebElement payPalNextButton = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.id("btnNext"))));
            payPalNextButton.click();

            WebElement payPalPasswordField = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.id("password"))));
            payPalPasswordField.sendKeys("Arsamas1984");

            WebElement payPalLogInButton = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.id("btnLogin"))));
            payPalLogInButton.click();

            WebElement payPalContinueButton = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.xpath("//*[@id='button']/button"))));
            payPalContinueButton.click();
            WebElement payPalAgreeButton = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.id("confirmButtonTop"))));
            payPalAgreeButton.click();}

        catch(Exception ignored){
            WebElement payPalContinueButton = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.xpath("//*[@id='button']/button"))));
            payPalContinueButton.click();
            WebElement payPalAgreeButton = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.id("confirmButtonTop"))));
            payPalAgreeButton.click();
        }

        System.out.println("Third: sandboxPayPalToYearlyLarge ");

    }

    @Test(priority = 9)
    public void subscribeToYearlyReseller() {
        WebElement yearlyReseller = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.cssSelector("#main-container > div > div.main-content > div > div.row > div > div:nth-child(3) > div.panel-body > div.row.pricing-plans > div > div.pricing-model.row > div:nth-child(9) > form > button"))));
        yearlyReseller.click();

        System.out.println("Third: subscribeToYearlyReseller ");

    }

    @Test(priority = 10)
    public void sandboxPayPalToYearlyReseller() {
        try{
            WebElement payPalEmailField = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.id("email"))));
            payPalEmailField.sendKeys("6653312@gmail.com");

            WebElement payPalNextButton = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.id("btnNext"))));
            payPalNextButton.click();

            WebElement payPalPasswordField = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.id("password"))));
            payPalPasswordField.sendKeys("Arsamas1984");

            WebElement payPalLogInButton = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.id("btnLogin"))));
            payPalLogInButton.click();

            WebElement payPalContinueButton = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.xpath("//*[@id='button']/button"))));
            payPalContinueButton.click();
            WebElement payPalAgreeButton = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.id("confirmButtonTop"))));
            payPalAgreeButton.click();}

        catch(Exception ignored){
            WebElement payPalContinueButton = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.xpath("//*[@id='button']/button"))));
            payPalContinueButton.click();
            WebElement payPalAgreeButton = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.id("confirmButtonTop"))));
            payPalAgreeButton.click();
        }

        System.out.println("Third: sandboxPayPalToYearlyReseller ");

    }

    @Test(priority = 11)
    public void toMonthlyPlans() {
        try{
        WebElement monthlyButton = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.cssSelector("#main-container > div > div.main-content > div > div.row > div > div:nth-child(3) > div.panel-body > div.row.pricing-plans > div > div.row.center > div > div > button.btn.btn-default"))));
        monthlyButton.click();}
        catch (Exception e){
            System.out.println("It was on Monthly already ");
        }
    }

    @Test(priority = 12)
    public void subscribeToMonthlySmall() {
        WebElement monthlySmall = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.cssSelector("#main-container > div > div.main-content > div > div.row > div > div:nth-child(3) > div.panel-body > div.row.pricing-plans > div > div.pricing-model.row > div:nth-child(2) > form > button"))));
        monthlySmall.click();
        System.out.println("Third: subscribeToMonthlySmall ");

    }

    @Test(priority = 13)
    public void sandboxPayPalToMonthlySmall() {
        try{
            WebElement payPalEmailField = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.id("email"))));
            payPalEmailField.sendKeys("6653312@gmail.com");

            WebElement payPalNextButton = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.id("btnNext"))));
            payPalNextButton.click();

            WebElement payPalPasswordField = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.id("password"))));
            payPalPasswordField.sendKeys("Arsamas1984");

            WebElement payPalLogInButton = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.id("btnLogin"))));
            payPalLogInButton.click();

            WebElement payPalContinueButton = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.xpath("//*[@id='button']/button"))));
            payPalContinueButton.click();
            WebElement payPalAgreeButton = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.id("confirmButtonTop"))));
            payPalAgreeButton.click();}

        catch(Exception ignored){
            WebElement payPalContinueButton = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.xpath("//*[@id='button']/button"))));
            payPalContinueButton.click();
            WebElement payPalAgreeButton = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.id("confirmButtonTop"))));
            payPalAgreeButton.click();
        }
        System.out.println("Third: sandboxPayPalToMonthlySmall ");

    }

    @Test(priority = 14)
    public void subscribeToMonthlyMedium() {
        WebElement monthlyMedium = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.cssSelector("#main-container > div > div.main-content > div > div.row > div > div:nth-child(3) > div.panel-body > div.row.pricing-plans > div > div.pricing-model.row > div:nth-child(4) > form > button"))));
        monthlyMedium.click();

        System.out.println("Third: subscribeToMonthlyMedium ");

    }

    @Test(priority = 15)
    public void sandboxPayPalToMonthlyMedium() {
        try{
            WebElement payPalEmailField = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.id("email"))));
            payPalEmailField.sendKeys("6653312@gmail.com");

            WebElement payPalNextButton = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.id("btnNext"))));
            payPalNextButton.click();

            WebElement payPalPasswordField = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.id("password"))));
            payPalPasswordField.sendKeys("Arsamas1984");

            WebElement payPalLogInButton = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.id("btnLogin"))));
            payPalLogInButton.click();

            WebElement payPalContinueButton = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.xpath("//*[@id='button']/button"))));
            payPalContinueButton.click();
            WebElement payPalAgreeButton = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.id("confirmButtonTop"))));
            payPalAgreeButton.click();}

        catch(Exception ignored){
            WebElement payPalContinueButton = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.xpath("//*[@id='button']/button"))));
            payPalContinueButton.click();
            WebElement payPalAgreeButton = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.id("confirmButtonTop"))));
            payPalAgreeButton.click();
        }

        System.out.println("Third: sandboxPayPalToMonthlyMedium ");

    }

    @Test(priority = 16)
    public void subscribeToMonthlyLarge() {
        WebElement monthlyLarge = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.cssSelector("#main-container > div > div.main-content > div > div.row > div > div:nth-child(3) > div.panel-body > div.row.pricing-plans > div > div.pricing-model.row > div:nth-child(6) > form > button"))));
        monthlyLarge.click();

        System.out.println("Third: subscribeToMonthlyLarge ");

    }

    @Test(priority = 17)
    public void sandboxPayPalToMonthlyLarge() {
        try{
            WebElement payPalEmailField = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.id("email"))));
            payPalEmailField.sendKeys("6653312@gmail.com");

            WebElement payPalNextButton = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.id("btnNext"))));
            payPalNextButton.click();

            WebElement payPalPasswordField = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.id("password"))));
            payPalPasswordField.sendKeys("Arsamas1984");

            WebElement payPalLogInButton = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.id("btnLogin"))));
            payPalLogInButton.click();

            WebElement payPalContinueButton = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.xpath("//*[@id='button']/button"))));
            payPalContinueButton.click();
            WebElement payPalAgreeButton = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.id("confirmButtonTop"))));
            payPalAgreeButton.click();}

        catch(Exception ignored){
            WebElement payPalContinueButton = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.xpath("//*[@id='button']/button"))));
            payPalContinueButton.click();
            WebElement payPalAgreeButton = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.id("confirmButtonTop"))));
            payPalAgreeButton.click();
        }
        System.out.println("Third: sandboxPayPalToMonthlyLarge ");

    }

    @Test(priority = 18)
    public void subscribeToMonthlyReseller() {
        WebElement monthlyReseller = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.cssSelector("#main-container > div > div.main-content > div > div.row > div > div:nth-child(3) > div.panel-body > div.row.pricing-plans > div > div.pricing-model.row > div:nth-child(8) > form > button"))));
        monthlyReseller.click();

        System.out.println("Third: subscribeToMonthlyReseller ");

    }

    @Test(priority = 19)
    public void sandboxPayPalToMonthlyReseller() {
        try{
            WebElement payPalEmailField = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.id("email"))));
            payPalEmailField.sendKeys("6653312@gmail.com");

            WebElement payPalNextButton = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.id("btnNext"))));
            payPalNextButton.click();

            WebElement payPalPasswordField = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.id("password"))));
            payPalPasswordField.sendKeys("Arsamas1984");

            WebElement payPalLogInButton = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.id("btnLogin"))));
            payPalLogInButton.click();

            WebElement payPalContinueButton = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.xpath("//*[@id='button']/button"))));
            payPalContinueButton.click();
            WebElement payPalAgreeButton = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.id("confirmButtonTop"))));
            payPalAgreeButton.click();}

        catch(Exception ignored){
            WebElement payPalContinueButton = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.xpath("//*[@id='button']/button"))));
            payPalContinueButton.click();
            WebElement payPalAgreeButton = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.id("confirmButtonTop"))));
            payPalAgreeButton.click();
        }

        System.out.println("Third: sandboxPayPalToMonthlyReseller ");

    }

    @AfterSuite
    public void afterClass() {
        driver.quit();
    }

}