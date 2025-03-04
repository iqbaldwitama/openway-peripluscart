package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BookPage {
    WebDriver driver;
    WebDriverWait wait;
    String bookTitle;
    String bookXPath;

    public BookPage(WebDriver driver, String bookTitle) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        this.bookTitle = bookTitle;
        this.bookXPath = "//h2[contains(normalize-space(text()), '%s')]";
        PageFactory.initElements(driver, this);
    }

    // Book screen elements
    @FindBy(xpath = "//button[@name='plus']")
    WebElement plusButton;
    @FindBy(xpath = "//button[@name='minus']")
    WebElement minusButton;
    @FindBy(id = "qty_65254796")
    WebElement quantity;
    @FindBy(xpath = "//button[contains(@class, 'btn-add-to-cart') and text()='Add to Cart']")
    WebElement addToCartButton;
    @FindBy(id = "Notification-Modal")
    WebElement notificationModal;
    @FindBy(xpath = "//button[contains(@class, 'btn-modal-close')]")
    WebElement closeModalButton;
    @FindBy(className = "preloader")
    WebElement preloader;

    // Check if UI elements are visible on the page
    public boolean isDisplayed(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
    }
    public boolean bookTitleIsDisplayed() {
        String xPath = String.format(bookXPath, bookTitle);
        return isDisplayed(driver.findElement(By.xpath(xPath)));
    }
    public boolean plusQtyButtonIsDisplayed() {
        return isDisplayed(plusButton);
    }
    public boolean minusQtyButtonIsDisplayed() {
        return isDisplayed(minusButton);
    }
    public boolean qtyNumberIsDisplayed() {
        return isDisplayed(quantity);
    }
    public boolean addToCartButtonIsDisplayed() {
        return isDisplayed(addToCartButton);
    }
    public boolean notificationModalIsDisplayed() {
        return isDisplayed(notificationModal);
    }

    // Check if UI elements are clickable on the page
    public boolean isClickable(WebElement element) {
        boolean isDisplayed = wait.until(ExpectedConditions.elementToBeClickable(element)).isDisplayed();
        boolean isEnabled = wait.until(ExpectedConditions.elementToBeClickable(element)).isEnabled();
        return isDisplayed && isEnabled;
    }
    public boolean plusButtonIsClickable() {
        return isClickable(plusButton);
    }
    public boolean minusButtonIsClickable() {
        return isClickable(minusButton);
    }
    public boolean addToCartButtonIsClickable() {
        return isClickable(addToCartButton);
    }

    // Check UI elements' clicking behavior
    public void clickAddToCart() {
        wait.until(ExpectedConditions.invisibilityOf(preloader));
        addToCartButton.click();
    }
    public void clickCloseModal() {
        closeModalButton.click();
    }
}
