package screens;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartScreen {
    WebDriver driver;
    WebDriverWait wait;
    public String url;
    String bookTitle;
    String bookXPath;

    public CartScreen(WebDriver driver, String bookTitle) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, Duration.ofMillis(10000));
        this.url = "https://www.periplus.com/checkout/cart";
        this.bookTitle = bookTitle;
        this.bookXPath = "//p[contains(@class, 'product-name')]//a[contains(normalize-space(text()), '%s')]";
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[contains(@name, 'plus[53553084]')]")
    WebElement plusQtyButton;
    @FindBy(xpath = "//button[contains(@name, 'minus[53553084]')]")
    WebElement minusQtyButton;
    @FindBy(xpath = "//input[contains(@name, 'quantity[53553084]')]")
    WebElement quantity;
    @FindBy(xpath = "//a[contains(text(), 'Remove')]")
    WebElement removeButton;
    @FindBy(xpath = "//a[contains(text(), 'Save for later')]")
    WebElement saveButton;
    @FindBy(xpath = "//input[@type='submit' and @value='Update']")
    WebElement updateButton;
    @FindBy(xpath = "//a[contains(text(), 'Continue shopping')]")
    WebElement continueShoppingButton;
    @FindBy(id = "sub_total")
    WebElement totalPrice;
    @FindBy(xpath = "//a[contains(text(), 'Checkout')]")
    WebElement checkoutButton;
    @FindBy(xpath = "//div[@class='content' and contains(text(), 'Your shopping cart is empty')]")
    WebElement emptyCartMessage;
    @FindBy(className = "preloader")
    WebElement preloader;

    public boolean isDisplayed(WebElement element) {
        try {
            return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public boolean bookTitleIsDisplayed() {
        String xPath = String.format(bookXPath, bookTitle);
        return isDisplayed(driver.findElement(By.xpath(xPath)));
    }
    public boolean plusQtyButtonIsDisplayed() {
        return isDisplayed(plusQtyButton);
    }
    public boolean minusQtyButtonIsDisplayed() {
        return isDisplayed(minusQtyButton);
    }
    public boolean quantityIsDisplayed() {
        return isDisplayed(quantity);
    }
    public boolean removeButtonIsDisplayed() {
        return isDisplayed(removeButton);
    }
    public boolean saveButtonIsDisplayed() {
        return isDisplayed(saveButton);
    }
    public boolean updateButtonIsDisplayed() {
        return isDisplayed(updateButton);
    }
    public boolean contShoppingButtonIsDisplayed() {
        return isDisplayed(continueShoppingButton);
    }
    public boolean totalPriceIsDisplayed() {
        return isDisplayed(totalPrice);
    }
    public boolean checkoutButtonIsDisplayed() {
        return isDisplayed(checkoutButton);
    }
    public boolean emptyCartIsDisplayed() {
        return isDisplayed(emptyCartMessage);
    }

    public boolean isClickable(WebElement element) {
        boolean isDisplayed = wait.until(ExpectedConditions.elementToBeClickable(element)).isDisplayed();
        boolean isEnabled = wait.until(ExpectedConditions.elementToBeClickable(element)).isEnabled();
        return isDisplayed && isEnabled;
    }
    public boolean plusBtnIsClickable() {
        return isClickable(plusQtyButton);
    }
    public boolean minusBtnIsClickable() {
        return isClickable(minusQtyButton);
    }
    public boolean removeBtnIsClickable() {
        return isClickable(removeButton);
    }
    public boolean saveBtnIsClickable() {
        return isClickable(saveButton);
    }
    public boolean updateBtnIsClickable() {
        return isClickable(updateButton);
    }
    public boolean contShoppingBtnIsClickable() {
        return isClickable(continueShoppingButton);
    }
    public boolean checkoutBtnIsClickable() {
        return isClickable(checkoutButton);
    }

    public boolean verifyQuantity(String expectedValue) {
        return wait.until(ExpectedConditions.attributeToBe(quantity, "value", expectedValue));
    }

    public void clickPlusQtyBtn() {
        wait.until(ExpectedConditions.invisibilityOf(preloader));
        plusQtyButton.click();
    }
    public void clickMinusQtyBtn() {
        wait.until(ExpectedConditions.invisibilityOf(preloader));
        minusQtyButton.click();
    }
    public void clickRemoveBtn() {
        wait.until(ExpectedConditions.invisibilityOf(preloader));
        removeButton.click();
    }
}
