package screens;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NavBar {
    WebDriver driver;
    WebDriverWait wait;

    public NavBar(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, Duration.ofMillis(10000));
        PageFactory.initElements(driver, this);
    }

    // Navbar Elements
    @FindBy(id = "filter_name")
    WebElement searchBar;
    @FindBy(id = "show-your-cart")
    WebElement cartButton;
    @FindBy(id = "nav-signin-text")
    WebElement signInButton;
    @FindBy(className = "preloader")
    WebElement preloader;

    public boolean isDisplayed(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
    }
    public boolean searchBarIsDisplayed() {
        return isDisplayed(searchBar);
    }
    public boolean cartButtonIsDisplayed() {
        return isDisplayed(cartButton);
    }
    public boolean signInButtonIsDisplayed() {
        return isDisplayed(signInButton);
    }

    public boolean isClickable(WebElement element) {
        boolean isDisplayed = wait.until(ExpectedConditions.elementToBeClickable(element)).isDisplayed();
        boolean isEnabled = wait.until(ExpectedConditions.elementToBeClickable(element)).isEnabled();
        return isDisplayed && isEnabled;
    }
    public boolean cartButtonIsClickable() {
        return isClickable(cartButton);
    }
    public boolean signInButtonIsClickable() {
        return isClickable(signInButton);
    }

    public void clickSignIn() {
        wait.until(ExpectedConditions.invisibilityOf(preloader));
        wait.until(ExpectedConditions.elementToBeClickable(signInButton)).click();
    }

    public void enterSearchBarInput(String input) {
        wait.until(ExpectedConditions.elementToBeClickable(searchBar)).sendKeys(input);
    }

    public boolean verifySearchInputValue(String expectedValue) {
        return wait.until(ExpectedConditions.attributeToBe(searchBar, "value", expectedValue));
    }

    public void clickSearch() {
        searchBar.sendKeys(Keys.ENTER);
    }
}
