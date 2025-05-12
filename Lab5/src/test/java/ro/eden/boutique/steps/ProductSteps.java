package ro.eden.boutique.steps;

import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import ro.eden.boutique.pages.ProductPage;

public class ProductSteps {

    private ProductPage productPage;

    @Step("Get product title")
    public String getProductTitle() {
        return productPage.getProductTitle();
    }

    @Step("Select first available size")
    public void selectFirstAvailableSize() {
        productPage.selectFirstAvailableSize();
    }

    @Step("Set quantity to: {0}")
    public void setQuantity(int quantity) {
        productPage.selectQuantity(quantity);
    }

    @Step("Add product to cart")
    public void addProductToCart() {
        productPage.addToCart();
    }

    @Step("Verify add to cart success message is displayed")
    public void verifyAddToCartSuccessMessage() {
        Assert.assertTrue("Add to cart success message should be displayed",
                productPage.isAddToCartSuccessMessageDisplayed());
    }

    @Step("Navigate to cart page")
    public void navigateToCartPage() {
        productPage.viewCart();
    }
}