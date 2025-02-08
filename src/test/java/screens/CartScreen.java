package screens;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
}
