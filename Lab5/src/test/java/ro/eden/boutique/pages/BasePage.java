package ro.eden.boutique.pages;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage extends PageObject {

    protected static final String BASE_URL = "https://www.edenboutique.ro/";

    public void navigateTo(String url) {
        getDriver().get(url);
    }

    public void navigateToHomePage() {
        navigateTo(BASE_URL);

        // Handle any cookies or consent popups if present
        try {
            WebElement cookieConsentBtn = waitForElement(By.xpath("//button[contains(text(), 'Accept') or contains(text(), 'Acceptă')]"), 3);
            if (cookieConsentBtn != null && cookieConsentBtn.isDisplayed()) {
                cookieConsentBtn.click();
            }
        } catch (Exception ignored) {
            // Cookie banner not present or has a different structure
        }
    }

    protected WebElement waitForElement(By locator, int timeoutInSeconds) {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutInSeconds))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForClickableElement(By locator, int timeoutInSeconds) {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutInSeconds))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void scrollToElement(WebElement element) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        waitABit(500); // Small pause after scrolling
    }

    protected boolean isElementPresent(By locator) {
        try {
            return getDriver().findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean isElementPresent(By locator, int timeoutInSeconds) {
        try {
            waitForElement(locator, timeoutInSeconds);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}