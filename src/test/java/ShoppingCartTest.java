import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.*;

import java.time.Duration;

public class ShoppingCartTest {
    WebDriver driver;
    WebDriverWait wait;
    NavBar navBar;
    SignInPage signInPage;
    SearchResultsPage searchResultsPage;
    BookPage bookPage;
    CartPage cartPage;
    String url;
    String email;
    String password;
    String bookTitle;

    @Parameters({"url", "email", "password", "bookTitle"})
    @BeforeTest
    public void TestSetup(String url, String email, String password, String bookTitle) {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        this.url = url;
        this.email = email;
        this.password = password;
        this.bookTitle = bookTitle;

        driver.get(url);
        navBar = new NavBar(driver);
        signInPage = new SignInPage(driver);
        searchResultsPage = new SearchResultsPage(driver, bookTitle);
        bookPage = new BookPage(driver, bookTitle);
        cartPage = new CartPage(driver, bookTitle);
    }

    @Test(priority = 1)
    public void shouldDisplaySignInButton() {
        boolean isDisplayed = navBar.signInButtonIsDisplayed();
        Assert.assertTrue(isDisplayed);
    }

    @Test(priority = 2)
    public void shouldAllowClickSignInButton() {
        boolean isClickable = navBar.signInButtonIsClickable();
        Assert.assertTrue(isClickable);
    }

    @Test(priority = 3)
    public void shouldNavigateToSignInPage() {
        navBar.clickSignIn();

        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = signInPage.url;
        Assert.assertEquals(currentUrl, expectedUrl);
    }

    @Test(priority = 4)
    public void shouldDisplaySignInTitle() {
        boolean isDisplayed = signInPage.signInTitleIsDisplayed();
        Assert.assertTrue(isDisplayed);
    }

    @Test(priority = 5)
    public void shouldDisplayEmailInput() {
        boolean isDisplayed = signInPage.emailInputIsDisplayed();
        Assert.assertTrue(isDisplayed);
    }

    @Test(priority = 6)
    public void shouldDisplayPasswordInput() {
        boolean isDisplayed = signInPage.passwordInputIsDisplayed();
        Assert.assertTrue(isDisplayed);
    }

    @Test(priority = 7)
    public void shouldDisplayLoginButton() {
        boolean isDisplayed = signInPage.loginButtonIsDisplayed();
        Assert.assertTrue(isDisplayed);
    }

    @Test(priority = 8)
    public void shouldEnterEmail() {
        signInPage.enterEmail(email);

        boolean isVerified = signInPage.verifyEmailInput(email);
        Assert.assertTrue(isVerified);
    }

    @Test(priority = 9)
    public void shouldEnterPassword() {
        signInPage.enterPassword(password);

        boolean isVerified = signInPage.verifyPasswordInput(password);
        Assert.assertTrue(isVerified);
    }

    @Test(priority = 10)
    public void shouldAllowClickLoginButton() {
        boolean isClickable = signInPage.loginButtonIsClickable();
        Assert.assertTrue(isClickable);
    }

    @Test(priority = 11)
    public void shouldNavigateToYourAccountPage() {
        signInPage.clickLoginButton();

        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = "https://www.periplus.com/account/Your-Account";
        Assert.assertEquals(currentUrl, expectedUrl);
    }

    @Test(priority = 12)
    public void shouldDisplaySearchBar() {
        boolean isDisplayed = navBar.searchBarIsDisplayed();
        Assert.assertTrue(isDisplayed);
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

        String currentUrl = driver.getCurrentUrl();
        String expectedFilterUrl = "filter_name=" + bookTitle.replaceAll("\\s","+");
        Assert.assertTrue(currentUrl.contains(expectedFilterUrl));
    }

    @Test(priority = 15)
    public void shouldDisplayDesiredBook() {
        boolean isDisplayed = searchResultsPage.bookIsDisplayed();
        Assert.assertTrue(isDisplayed);
    }

    @Test(priority = 16)
    public void shouldAllowClickDesiredBook() {
        boolean isClickable = searchResultsPage.bookIsClickable();
        Assert.assertTrue(isClickable);
    }

    @Test(priority = 17)
    public void shouldNavigateToBookPage() {
        searchResultsPage.clickBook();

        String currentUrl = driver.getCurrentUrl();
        String expectedFilterUrl = bookTitle.toLowerCase().replaceAll("\\s","-");
        Assert.assertTrue(currentUrl.contains(expectedFilterUrl));
    }

    @Test(priority = 18)
    public void shouldDisplayBookTitle() {
        boolean isDisplayed = bookPage.bookTitleIsDisplayed();
        Assert.assertTrue(isDisplayed);
    }

    @Test(priority = 19)
    public void shouldDisplayPlusQtyButton() {
        boolean isDisplayed = bookPage.plusQtyButtonIsDisplayed();
        Assert.assertTrue(isDisplayed);
    }

    @Test(priority = 20)
    public void shouldDisplayMinusQtyButton() {
        boolean isDisplayed = bookPage.bookTitleIsDisplayed();
        Assert.assertTrue(isDisplayed);
    }

    @Test(priority = 21)
    public void shouldDisplayQtyNumber() {
        boolean isDisplayed = bookPage.qtyNumberIsDisplayed();
        Assert.assertTrue(isDisplayed);
    }

    @Test(priority = 22)
    public void shouldDisplayAddToCartButton() {
        boolean isDisplayed = bookPage.addToCartButtonIsDisplayed();
        Assert.assertTrue(isDisplayed);
    }

    @Test(priority = 23)
    public void shouldAllowClickPlusQtyBtn() {
        boolean isClickable = bookPage.plusButtonIsClickable();
        Assert.assertTrue(isClickable);
    }

    @Test(priority = 24)
    public void shouldAllowClickMinusQtyBtn() {
        boolean isClickable = bookPage.minusButtonIsClickable();
        Assert.assertTrue(isClickable);
    }

    @Test(priority = 25)
    public void shouldAllowClickAddToCartBtn() {
        boolean isClickable = bookPage.addToCartButtonIsClickable();
        Assert.assertTrue(isClickable);
    }

    @Test(priority = 26)
    public void shouldDisplayModal() {
        bookPage.clickAddToCart();

        boolean isDisplayed = bookPage.notificationModalIsDisplayed();
        Assert.assertTrue(isDisplayed);
        bookPage.clickCloseModal();
    }

    @Test(priority = 27)
    public void shouldDsiplayClickCartButton() {
        boolean isDisplayed = navBar.cartButtonIsDisplayed();
        Assert.assertTrue(isDisplayed);
    }

    @Test(priority = 28)
    public void shouldAllowClickCartButton() {
        boolean isClickable = navBar.cartButtonIsClickable();
        Assert.assertTrue(isClickable);
    }

    @Test(priority = 29)
    public void shouldNavigateToCartPage() {
        navBar.clickCartButton();

        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = cartPage.url;
        Assert.assertEquals(currentUrl, expectedUrl);
    }

    @Test(priority = 30)
    public void shouldDisplayBookInCart() {
        boolean isDisplayed = cartPage.bookTitleIsDisplayed();
        Assert.assertTrue(isDisplayed);
    }

    @Test(priority = 31)
    public void shouldDisplayPlusQtyButtonCart() {
        boolean isDisplayed = cartPage.plusQtyButtonIsDisplayed();
        Assert.assertTrue(isDisplayed);
    }
    @Test(priority = 31)
    public void shouldDisplayMinusQtyButtonCart() {
        boolean isDisplayed = cartPage.minusQtyButtonIsDisplayed();
        Assert.assertTrue(isDisplayed);
    }
    @Test(priority = 32)
    public void shouldDisplayQtyCart() {
        boolean isDisplayed = cartPage.quantityIsDisplayed();
        Assert.assertTrue(isDisplayed);
    }
    @Test(priority = 33)
    public void shouldDisplayRemoveButton() {
        boolean isDisplayed = cartPage.removeButtonIsDisplayed();
        Assert.assertTrue(isDisplayed);
    }
    @Test(priority = 34)
    public void shouldDisplaySaveButton() {
        boolean isDisplayed = cartPage.saveButtonIsDisplayed();
        Assert.assertTrue(isDisplayed);
    }
    @Test(priority = 35)
    public void shouldDisplayUpdateButton() {
        boolean isDisplayed = cartPage.updateButtonIsDisplayed();
        Assert.assertTrue(isDisplayed);
    }
    @Test(priority = 36)
    public void shouldDisplayContShoppingButton() {
        boolean isDisplayed = cartPage.contShoppingButtonIsDisplayed();
        Assert.assertTrue(isDisplayed);
    }
    @Test(priority = 37)
    public void shouldDisplayTotalPrice() {
        boolean isDisplayed = cartPage.totalPriceIsDisplayed();
        Assert.assertTrue(isDisplayed);
    }
    @Test(priority = 38)
    public void shouldDisplayCheckoutButton() {
        boolean isDisplayed = cartPage.checkoutButtonIsDisplayed();
        Assert.assertTrue(isDisplayed);
    }

    @Test(priority = 39)
    public void shouldAllowClickPlusBtn() {
        boolean isClickable = cartPage.plusBtnIsClickable();
        Assert.assertTrue(isClickable);
    }

    @Test(priority = 40)
    public void shouldAllowClickMinusBtn() {
        boolean isClickable = cartPage.minusBtnIsClickable();
        Assert.assertTrue(isClickable);
    }

    @Test(priority = 41)
    public void shouldAllowClickRemoveBtn() {
        boolean isClickable = cartPage.removeBtnIsClickable();
        Assert.assertTrue(isClickable);
    }

    @Test(priority = 42)
    public void shouldAllowClickSaveBtn() {
        boolean isClickable = cartPage.saveBtnIsClickable();
        Assert.assertTrue(isClickable);
    }

    @Test(priority = 43)
    public void shouldAllowClickUpdateBtn() {
        boolean isClickable = cartPage.updateBtnIsClickable();
        Assert.assertTrue(isClickable);
    }

    @Test(priority = 44)
    public void shouldAllowClickContShoppingBtn() {
        boolean isClickable = cartPage.contShoppingBtnIsClickable();
        Assert.assertTrue(isClickable);
    }

    @Test(priority = 45)
    public void shouldAllowClickCheckoutBtn() {
        boolean isClickable = cartPage.checkoutBtnIsClickable();
        Assert.assertTrue(isClickable);
    }

    @Test(priority = 46)
    public void shouldIncreaseQty() {
        cartPage.clickPlusQtyBtn();

        String expectedQty = "2";
        boolean isIncreased = cartPage.verifyQuantity(expectedQty);
        Assert.assertTrue(isIncreased);
    }

    @Test(priority = 47)
    public void shouldDecreaseQty() {
        cartPage.clickMinusQtyBtn();

        String expectedQty = "1";
        boolean isDecreased = cartPage.verifyQuantity(expectedQty);
        Assert.assertTrue(isDecreased);
    }

    @Test(priority = 48)
    public void shouldRemoveItemCart() {
        cartPage.clickRemoveBtn();
        boolean isMessageDisplayed = cartPage.emptyCartIsDisplayed();
        Assert.assertTrue(isMessageDisplayed);
    }

    @AfterTest
    public void quitDriver() {
        driver.quit();
    }
}
