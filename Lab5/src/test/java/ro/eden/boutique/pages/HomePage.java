package ro.eden.boutique.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@DefaultUrl("https://www.edenboutique.ro/")
public class HomePage extends BasePage {

    @FindBy(css = ".header__content [data-action='search-form']")
    private WebElement searchIcon;

    @FindBy(css = "form.search-form input[name='q']")
    private WebElement searchInput;

    @FindBy(css = "form.search-form button[type='submit']")
    private WebElement searchSubmitButton;

    @FindBy(css = ".header__content [data-action='my-account']")
    private WebElement myAccountIcon;

    @FindBy(css = ".header__content [data-action='cart']")
    private WebElement cartIcon;

    public void clickSearchIcon() {
        searchIcon.click();
        waitForElement(By.cssSelector("form.search-form input[name='q']"), 5);
    }

    public void searchFor(String searchTerm) {
        clickSearchIcon();
        searchInput.clear();
        searchInput.sendKeys(searchTerm);
        searchSubmitButton.click();
    }

    public void openMyAccount() {
        myAccountIcon.click();
        waitABit(1000); // Wait for any animation to complete
    }

    public void openCart() {
        cartIcon.click();
        waitABit(1000); // Wait for any animation to complete
    }

    public void navigateToRegistrationPage() {
        openMyAccount();
        WebElement registerLink = waitForElement(By.xpath("//a[contains(@href, 'register') or contains(text(), 'Creează cont')]"), 5);
        registerLink.click();
    }
}