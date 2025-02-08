package screens;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchResultsScreen {
    WebDriver driver;
    WebDriverWait wait;
    String bookTitle;
    String bookXPath;

    public SearchResultsScreen(WebDriver driver, String bookTitle) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        this.bookTitle = bookTitle;
        this.bookXPath = "//h3/a[contains(normalize-space(text()), '%s')]";
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "preloader")
    WebElement preloader;

    // Check if UI element is visible on the page
    public boolean bookIsDisplayed() {
        String xpath = String.format(bookXPath, bookTitle);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).isDisplayed();
    }

    // Check if UI element is clickable on the page
    public boolean bookIsClickable() {
        String xpath = String.format(bookXPath, bookTitle);
        boolean isDisplayed = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).isDisplayed();
        boolean isEnabled = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).isEnabled();
        return isDisplayed && isEnabled;
    }

    // Check UI element's clicking behavior
    public void clickBook() {
        String xpath = String.format(bookXPath, bookTitle);

        wait.until(ExpectedConditions.invisibilityOf(preloader));
        WebElement book = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        book.click();
    }

}
