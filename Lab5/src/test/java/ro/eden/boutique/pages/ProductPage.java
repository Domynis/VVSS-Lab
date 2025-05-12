package ro.eden.boutique.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class ProductPage extends BasePage {

    @FindBy(css = ".product-info-main .page-title")
    private WebElement productTitle;

    @FindBy(css = "#product-addtocart-button")
    private WebElement addToCartButton;

    @FindBy(css = ".swatch-option")
    private List<WebElement> sizeOptions;

    @FindBy(css = ".messages .message-success")
    private WebElement successMessage;

    @FindBy(css = ".qty")
    private WebElement quantityInput;

    public String getProductTitle() {
        return productTitle.getText();
    }

    public void selectFirstAvailableSize() {
        if (!sizeOptions.isEmpty()) {
            // Find a size that is not out of stock
            for (WebElement sizeOption : sizeOptions) {
                if (!sizeOption.getAttribute("class").contains("disabled")) {
                    scrollToElement(sizeOption);
                    sizeOption.click();
                    waitABit(500);
                    return;
                }
            }
            throw new RuntimeException("No available size options found");
        }
    }

    public void selectQuantity(int quantity) {
        if (quantityInput != null) {
            quantityInput.clear();
            quantityInput.sendKeys(String.valueOf(quantity));
        }
    }

    public void addToCart() {
        scrollToElement(addToCartButton);
        addToCartButton.click();
    }

    public boolean isAddToCartSuccessMessageDisplayed() {
        return isElementPresent(By.cssSelector(".messages .message-success"), 5);
    }

    public String getSuccessMessage() {
        if (isAddToCartSuccessMessageDisplayed()) {
            return successMessage.getText();
        }
        return "";
    }

    public void viewCart() {
        WebElement viewCartLink = waitForElement(
                By.xpath("//a[contains(@class, 'action viewcart') or contains(text(), 'Coșul meu') or contains(text(), 'Vezi coșul')]"),
                5
        );

        if (viewCartLink != null) {
            viewCartLink.click();
        } else {
            // Alternative way to navigate to cart if link not found
            WebElement cartIcon = waitForElement(
                    By.cssSelector(".header__content [data-action='cart']"),
                    5
            );
            cartIcon.click();
        }
    }
}