import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class Bp_Connection_Stripe_payment_system {
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
        System.out.println("Second: ToAccount ");
    }

    @Test(priority = 2)
    public void addNewPaymentSystem() {
        WebElement addNewButton = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.cssSelector("#main-container > div > div.main-content > div > div.row > div > div.row > div:nth-child(3) > div > div:nth-child(2) > div > div.panel-body > div > div > a"))));
        addNewButton.click();
    }

    @Test(priority = 3)
    public void connectStripeBP() {
        WebElement connectStripeButton = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.cssSelector("#main-container > div > div.main-content > div > div.row > div > div.row.margin-top-small.center > p > a:nth-child(2)"))));
        connectStripeButton.click();
    }

    @Test(priority = 4)
    public void signInWithStripeToConnect() {
        WebElement connectStripeButton = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.cssSelector("#oauth-authorize-login > div.initial_form > button.button.medium.blue > span"))));
        connectStripeButton.click();
    }

    @Test(priority = 5)
    public void signInStripeCredentials() {
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("arsgrendal@yandex.ru");

        WebElement passField = driver.findElement(By.id("password"));
        passField.sendKeys("Arsamas1984@");

        WebElement signIn = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.cssSelector("#main-body > div > span > div > form > div > p.submit > button > span"))));
        signIn.click();
    }

    @Test(priority = 6)
    public void connectMyStripeAccount() {
        WebElement connectMyStripeAccountButton = (new WebDriverWait(driver, 30).until(elementToBeClickable(By.cssSelector("#wrap > section:nth-child(5) > form > div.initial_form > button.button.medium.blue > span"))));
        connectMyStripeAccountButton.click();
    }

    @Test(priority = 7)
    public void step3ConnectStripeSalesSettings() {
        WebDriverWait waitUntilDropDownIsAppears = new WebDriverWait(driver, 30);
        waitUntilDropDownIsAppears.until(visibilityOfElementLocated(By.xpath("//*[@id='sales-txn-settings']/div[2]/div[1]/div/div[2]/div/span/span[1]/span/span[2]/b"))).click();

        WebElement elementsInDropDown = driver.findElement(By.xpath("//*[@id='deposit-account']"));
        Assert.assertNotNull(String.valueOf(elementsInDropDown));

        Select oSelect = new Select(elementsInDropDown);
        oSelect.selectByIndex(1);
        oSelect.selectByIndex(0);
        oSelect.selectByIndex(2);
        oSelect.selectByIndex(3);
        oSelect.selectByIndex(1);
        System.out.println("Second: Stripe - Sales settings - BankAccountField text: " + oSelect.getFirstSelectedOption().getText());
        //for (WebElement w : oSelect.getAllSelectedOptions()) {
        //    System.out.println(w.getText());
        //}
        WebElement closeDropDownAccounts = driver.findElement(By.xpath("//*[@id='sales-txn-settings']/div[2]/div[1]/div/div[2]/div/span/span[1]/span/span[2]/b"));
        closeDropDownAccounts.click();
    }

}
