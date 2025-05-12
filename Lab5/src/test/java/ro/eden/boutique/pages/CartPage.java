package ro.eden.boutique.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class CartPage extends BasePage {

    @FindBy(css = ".cart-items .cart-item")
    private List<WebElement> cartItems;

    @FindBy(css = ".grand.totals .price")
    private WebElement totalPrice;

    @FindBy(css = ".checkout-methods-items .action.primary.checkout")
    private WebElement proceedToCheckoutButton;

    @FindBy(css = ".cart-empty")
    private WebElement emptyCartMessage;

    public boolean isCartEmpty() {
        return isElementPresent(By.cssSelector(".cart-empty"), 3);
    }

    public int getCartItemsCount() {
        try {
            return cartItems.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public String getTotalPrice() {
        if (totalPrice != null) {
            return totalPrice.getText();
        }
        return "0.00";
    }

    public void updateQuantity(int index, int quantity) {
        if (index < cartItems.size()) {
            WebElement item = cartItems.get(index);
            WebElement qtyInput = item.findElement(By.cssSelector(".qty"));
            qtyInput.clear();
            qtyInput.sendKeys(String.valueOf(quantity));

            // Click update button if present, or trigger change event
            try {
                WebElement updateButton = item.findElement(By.cssSelector(".update-cart-item"));
                updateButton.click();
            } catch (Exception e) {
                // If no update button, try clicking elsewhere to trigger onchange
                qtyInput.sendKeys("\t");
            }

            waitABit(2000); // Wait for cart to update
        }
    }

    public void removeItemFromCart(int index) {
        if (index < cartItems.size()) {
            WebElement item = cartItems.get(index);
            WebElement removeButton = item.findElement(By.cssSelector(".action-delete"));
            removeButton.click();
            waitABit(2000); // Wait for cart to update
        }
    }

    public void proceedToCheckout() {
        if (proceedToCheckoutButton != null) {
            scrollToElement(proceedToCheckoutButton);
            proceedToCheckoutButton.click();
        }
    }

    public boolean isProductInCart(String productName) {
        for (WebElement item : cartItems) {
            WebElement nameElement = item.findElement(By.cssSelector(".product-item-name"));
            if (nameElement.getText().contains(productName)) {
                return true;
            }
        }
        return false;
    }
}