package screens;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignInScreen {
    WebDriver driver;
    WebDriverWait wait;
    public String url;

    public SignInScreen(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        this.url = "https://www.periplus.com/account/Login";
        PageFactory.initElements(driver, this);
    }

    // Sign-in screen elements
    @FindBy(className = "signin_main")
    WebElement signInTitle;
    @FindBy(xpath = "//input[@name='email']")
    WebElement emailInput;
    @FindBy(xpath = "//input[@name='password']")
    WebElement passwordInput;
    @FindBy(id = "button-login")
    WebElement loginButton;

    // Check if UI elements are visible on the page
    public boolean isDisplayed(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
    }
    public boolean signInTitleIsDisplayed() {
        return isDisplayed(signInTitle);
    }
    public boolean emailInputIsDisplayed() {
        return isDisplayed(emailInput);
    }
    public boolean passwordInputIsDisplayed() {
        return isDisplayed(passwordInput);
    }
    public boolean loginButtonIsDisplayed() {
        return isDisplayed(loginButton);
    }

    // Check if UI elements are clickable on the page
    public boolean isClickable(WebElement element) {
        boolean isDisplayed = wait.until(ExpectedConditions.elementToBeClickable(element)).isDisplayed();
        boolean isEnabled = wait.until(ExpectedConditions.elementToBeClickable(element)).isEnabled();
        return isDisplayed && isEnabled;
    }
    public boolean loginButtonIsClickable() {
        return isClickable(loginButton);
    }

    // Enter input values
    public void enterInput(WebElement element, String input) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(input);
    }
    public void enterEmail(String email) {
        enterInput(emailInput, email);
    }
    public void enterPassword(String password) {
        enterInput(passwordInput, password);
    }

    // Verify input values
    public boolean verifyInputValue(WebElement element, String expectedValue) {
        return wait.until(ExpectedConditions.attributeToBe(element, "value", expectedValue));
    }
    public boolean verifyEmailInput(String email) {
        return verifyInputValue(emailInput, email);
    }
    public boolean verifyPasswordInput(String password) {
        return verifyInputValue(passwordInput, password);
    }

    // Check UI element's clicking behavior
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }
}
