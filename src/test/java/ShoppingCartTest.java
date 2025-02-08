import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import screens.BookScreen;
import screens.NavBar;
import screens.SearchResultsScreen;
import screens.SignInScreen;

import java.time.Duration;

public class ShoppingCartTest {
    WebDriver driver;
    WebDriverWait wait;
    NavBar navBar;
    SignInScreen signInScreen;
    SearchResultsScreen searchResultsScreen;
    BookScreen bookScreen;
    String url;
    String email;
    String password;
    String bookTitle;

    @BeforeTest
    public void TestSetup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        this.url = "https://www.periplus.com";
        this.email = "iqbaldwitama18@gmail.com";
        this.password = "openwayTesting1";
        this.bookTitle = "The Silent Patient";

        driver.get(url);
        navBar = new NavBar(driver);
        signInScreen = new SignInScreen(driver);
        searchResultsScreen = new SearchResultsScreen(driver, bookTitle);
        bookScreen = new BookScreen(driver, bookTitle);
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
    public void shouldNavigateToYourAccountPage() {
        signInScreen.clickLoginButton();

        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = "https://www.periplus.com/account/Your-Account";
        Assert.assertEquals(currentUrl, expectedUrl, "ERROR: Page did not redirect to the expected URL.");
    }

    @Test(priority = 12)
    public void shouldDisplaySearchBar() {
        boolean isDisplayed = navBar.searchBarIsDisplayed();
        Assert.assertTrue(isDisplayed, "ERROR: Search bar is NOT displayed in the navigation bar.");
    }

    @Test(priority = 13)
    public void shouldEnterBookTitle() {
        navBar.enterSearchBarInput(bookTitle);

        boolean isVerified = navBar.verifySearchInputValue(bookTitle);
        Assert.assertTrue(isVerified);
    }

    @Test(priority = 14)
    public void shouldSearchBook() {
        navBar.clickSearch();

        String currentURL = driver.getCurrentUrl();
        String expectedFilterURL = "filter_name=" + bookTitle.replaceAll("\\s","+");
        Assert.assertTrue(currentURL.contains(expectedFilterURL), "ERROR: Search term is not found in the URL.");
    }

    @Test(priority = 15)
    public void shouldDisplayDesiredBook() {
        boolean isDisplayed = searchResultsScreen.bookIsDisplayed();
        Assert.assertTrue(isDisplayed, "ERROR: Book is NOT available");
    }

    @Test(priority = 16)
    public void shouldAllowClickDesiredBook() {
        boolean isClickable = searchResultsScreen.bookIsClickable();
        Assert.assertTrue(isClickable, "ERROR: Book is NOT clickable");
    }

    @Test(priority = 17)
    public void shouldNavigateToBookPage() {
        searchResultsScreen.clickBook();

        String currentURL = driver.getCurrentUrl();
        String expectedFilterURL = bookTitle.toLowerCase().replaceAll("\\s","-");
        Assert.assertTrue(currentURL.contains(expectedFilterURL), "ERROR: Incorrect book page redirection.");
    }

    @Test(priority = 18)
    public void shouldDisplayBookTitle() {
        boolean isDisplayed = bookScreen.bookTitleIsDisplayed();
        Assert.assertTrue(isDisplayed);
    }
    @Test(priority = 19)
    public void shouldDisplayPlusQtyButton() {
        boolean isDisplayed = bookScreen.plusQtyButtonIsDisplayed();
        Assert.assertTrue(isDisplayed);
    }
    @Test(priority = 20)
    public void shouldDisplayMinusQtyButton() {
        boolean isDisplayed = bookScreen.bookTitleIsDisplayed();
        Assert.assertTrue(isDisplayed);
    }
    @Test(priority = 21)
    public void shouldDisplayQtyNumber() {
        boolean isDisplayed = bookScreen.qtyNumberIsDisplayed();
        Assert.assertTrue(isDisplayed);
    }
    @Test(priority = 22)
    public void shouldDisplayAddToCartButton() {
        boolean isDisplayed = bookScreen.addToCartButtonIsDisplayed();
        Assert.assertTrue(isDisplayed);
    }
    @Test(priority = 23)
    public void shouldAllowClickPlusQtyBtn() {
        boolean isClickable = bookScreen.plusButtonIsClickable();
        Assert.assertTrue(isClickable);
    }
    @Test(priority = 24)
    public void shouldAllowClickMinusQtyBtn() {
        boolean isClickable = bookScreen.minusButtonIsClickable();
        Assert.assertTrue(isClickable);
    }
    @Test(priority = 25)
    public void shouldAllowClickAddToCartBtn() {
        boolean isClickable = bookScreen.addToCartButtonIsClickable();
        Assert.assertTrue(isClickable);
    }
    @Test(priority = 26)
    public void shouldDisplayModal() {
        bookScreen.clickAddToCart();

        boolean isDisplayed = bookScreen.notificationModalIsDisplayed();
        Assert.assertTrue(isDisplayed);
        bookScreen.clickCloseModal();
    }

}
