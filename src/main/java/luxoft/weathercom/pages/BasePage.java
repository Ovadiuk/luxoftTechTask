package luxoft.weathercom.pages;


import luxoft.driver.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
public class BasePage {
    private WebDriver driver;

    public BasePage (WebDriverManager webDriverManager){
        this.driver = webDriverManager.getInstance();
    }

    public void waitUntilElementDisplayed(final By element, final int waitTimeInSeconds) {
        new WebDriverWait(driver, waitTimeInSeconds).until(
                webDriver -> (ExpectedConditions.visibilityOfElementLocated(element)));
    }

    public void waitUntilElementDisplayed(final By element) {
        new WebDriverWait(driver, 20).until(
                webDriver -> (ExpectedConditions.visibilityOfElementLocated(element)));
    }

    public void waitForPageToBeLoaded() {
        new WebDriverWait(driver, 20).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    public void waitUntilElementClickable(final WebElement element) {
        new WebDriverWait(driver, 20).until(
                webDriver -> (elementToBeClickable(element)));
    }

    public void waitForElementAttributeToBe(final WebElement element, final String attributeName, final String valueToBePresent) {
        new WebDriverWait(driver, 20).until(driver -> (ExpectedConditions.attributeContains(element, attributeName, valueToBePresent)));
    }

    public void waitForElement(final WebElement element) {
        new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(InvalidElementStateException.class)
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .until(elementToBeClickable(element));
    }
}
