import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import screens.NavBar;
import screens.SignInScreen;

import java.time.Duration;

public class ShoppingCartTest {
    WebDriver driver;
    WebDriverWait wait;
    NavBar navBar;
    SignInScreen signInScreen;
    String url;
    String email;
    String password;

    @BeforeTest
    public void TestSetup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        this.url = "https://www.periplus.com";
        this.email = "iqbaldwitama18@gmail.com";
        this.password = "openwayTesting1";

        driver.get(url);
        navBar = new NavBar(driver);
        signInScreen = new SignInScreen(driver);
    }

    @Test(priority = 1)
    public void shouldDisplaySignInButton() {
        boolean isDisplayed = navBar.signInButtonIsDisplayed();
        Assert.assertTrue(isDisplayed, "ERROR: Sign-In button is NOT displayed on the navbar.");
    }

    @Test(priority = 2)
    public void shouldAllowClickSignInButton() {
        boolean isClickable = navBar.signInButtonIsClickable();
        Assert.assertTrue(isClickable, "ERROR: Sign-In button is NOT displayed on the navbar.");
    }

    @Test(priority = 3)
    public void shouldNavigateToSignInPage() {
        navBar.clickSignIn();

        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = signInScreen.url;
        Assert.assertEquals(currentUrl, expectedUrl, "ERROR: Page did not redirect to the expected URL.");
    }

    @Test(priority = 4)
    public void shouldDisplaySignInTitle() {
        boolean isDisplayed = signInScreen.signInTitleIsDisplayed();
        Assert.assertTrue(isDisplayed, "ERROR: Sign-in title is NOT displayed on the sign-in page.");
    }

    @Test(priority = 5)
    public void shouldDisplayEmailInput() {
        boolean isDisplayed = signInScreen.emailInputIsDisplayed();
        Assert.assertTrue(isDisplayed, "ERROR: Email input is NOT displayed on the sign-in page.");
    }

    @Test(priority = 6)
    public void shouldDisplayPasswordInput() {
        boolean isDisplayed = signInScreen.passwordInputIsDisplayed();
        Assert.assertTrue(isDisplayed, "ERROR: Password input is NOT displayed on the sign-in page.");
    }

    @Test(priority = 7)
    public void shouldDisplayLoginButton() {
        boolean isDisplayed = signInScreen.loginButtonIsDisplayed();
        Assert.assertTrue(isDisplayed, "ERROR: Login button is NOT displayed on the sign-in page.");
    }

    @Test(priority = 8)
    public void shouldEnterEmail() {
        signInScreen.enterEmail(email);

        boolean isVerified = signInScreen.verifyEmailInput(email);
        Assert.assertTrue(isVerified, "ERROR: Email does NOT input correctly.");
    }

    @Test(priority = 9)
    public void shouldEnterPassword() {
        signInScreen.enterPassword(password);

        boolean isVerified = signInScreen.verifyPasswordInput(password);
        Assert.assertTrue(isVerified, "ERROR: Password does NOT input correctly.");
    }

    @Test(priority = 10)
    public void shouldAllowClickLoginButton() {
        boolean isClickable = signInScreen.loginButtonIsClickable();
        Assert.assertTrue(isClickable, "ERROR: Login button is NOT clickable.");
    }

    @Test(priority = 11)
    public void shouldDisplay() {
        signInScreen.clickLoginButton();
    }
}
