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
        this.wait = new WebDriverWait(this.driver, Duration.ofMillis(10000));
        this.bookTitle = bookTitle;
        this.bookXPath = String.format("//h3/a[contains(normalize-space(text()), '%s')]", bookTitle);
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "preloader")
    WebElement preloader;

    public boolean bookIsDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(bookXPath))).isDisplayed();
    }

    public boolean bookIsClickable() {
        boolean isDisplayed = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(bookXPath))).isDisplayed();
        boolean isEnabled = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(bookXPath))).isEnabled();
        return isDisplayed && isEnabled;
    }

    public void clickBook() {
        wait.until(ExpectedConditions.invisibilityOf(preloader));

        WebElement book = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(bookXPath)));
        book.click();
    }

}
