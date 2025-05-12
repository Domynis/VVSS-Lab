package ro.eden.boutique.tests;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import ro.eden.boutique.steps.CartSteps;
import ro.eden.boutique.steps.ProductSteps;
import ro.eden.boutique.steps.SearchSteps;

/**
 * This test class demonstrates scenario-based testing for a complete shopping flow.
 * It simulates a user searching for a product, adding it to cart, and verifying the cart.
 */
@RunWith(SerenityRunner.class)
public class ShoppingScenarioTest {

    @Managed
    WebDriver driver;

    @Steps
    SearchSteps searchSteps;

    @Steps
    ProductSteps productSteps;

    @Steps
    CartSteps cartSteps;

    @Title("Complete shopping scenario - Search, filter, add to cart, verify cart")
    @Test
    public void completeShoppingScenario() {
        // Step 1: Navigate to home page and search for a product
        searchSteps.navigateToHomePage();
        searchSteps.searchForProduct("rochie");
        searchSteps.verifySearchResultsDisplayed();

        // Step 2: Apply a filter to narrow search results
        searchSteps.applyCategoryFilter("Rochii");
        searchSteps.verifySearchResultsDisplayed();

        // Step 3: Select the first product from search results
        searchSteps.selectFirstProduct();

        // Step 4: Get product title for later verification
        String productTitle = productSteps.getProductTitle();

        // Step 5: Select size and add product to cart
        productSteps.selectFirstAvailableSize();
        productSteps.addProductToCart();
        productSteps.verifyAddToCartSuccessMessage();

        // Step 6: Navigate to cart page
        productSteps.navigateToCartPage();

        // Step 7: Verify product is in cart
        cartSteps.verifyCartIsNotEmpty();
        cartSteps.verifyProductInCart(productTitle);

        // Step 8: Update quantity
        int initialItemsCount = cartSteps.getCartItemsCount();
        cartSteps.updateQuantity(0, 2);

        // Optional: Proceed to checkout would be the next step in a real shopping flow
        // but we're stopping at cart verification for this test
    }
}