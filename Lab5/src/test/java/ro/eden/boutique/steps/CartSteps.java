package ro.eden.boutique.steps;

import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import ro.eden.boutique.pages.CartPage;

public class CartSteps {

    private CartPage cartPage;

    @Step("Verify cart is not empty")
    public void verifyCartIsNotEmpty() {
        Assert.assertFalse("Cart should not be empty", cartPage.isCartEmpty());
        Assert.assertTrue("Cart should contain items", cartPage.getCartItemsCount() > 0);
    }

    @Step("Verify product is in cart: {0}")
    public void verifyProductInCart(String productName) {
        Assert.assertTrue("Product should be in the cart: " + productName,
                cartPage.isProductInCart(productName));
    }

    @Step("Update quantity of item {0} to {1}")
    public void updateQuantity(int itemIndex, int quantity) {
        cartPage.updateQuantity(itemIndex, quantity);
    }

    @Step("Remove item from cart")
    public void removeItemFromCart(int itemIndex) {
        cartPage.removeItemFromCart(itemIndex);
    }

    @Step("Proceed to checkout")
    public void proceedToCheckout() {
        cartPage.proceedToCheckout();
    }

    @Step("Get cart items count")
    public int getCartItemsCount() {
        return cartPage.getCartItemsCount();
    }
}