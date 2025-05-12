package ro.eden.boutique.steps;

import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import ro.eden.boutique.pages.HomePage;
import ro.eden.boutique.pages.SearchResultsPage;

public class SearchSteps {

    private HomePage homePage;
    private SearchResultsPage searchResultsPage;

    @Step("Navigate to home page")
    public void navigateToHomePage() {
        homePage.navigateToHomePage();
    }

    @Step("Search for product: {0}")
    public void searchForProduct(String searchQuery) {
        homePage.searchFor(searchQuery);
    }

    @Step("Verify search results are displayed")
    public void verifySearchResultsDisplayed() {
        Assert.assertTrue("Search results should be displayed", searchResultsPage.hasResults());
    }

    @Step("Verify no search results are displayed")
    public void verifyNoSearchResultsDisplayed() {
        Assert.assertTrue("No search results should be displayed", searchResultsPage.hasNoResults());
    }

    @Step("Apply filter by price range: {0}")
    public void applyPriceFilter(String priceRange) {
        searchResultsPage.applyFilterByPrice(priceRange);
    }

    @Step("Apply filter by category: {0}")
    public void applyCategoryFilter(String category) {
        searchResultsPage.applyFilterByCategory(category);
    }

    @Step("Select first product from search results")
    public void selectFirstProduct() {
        searchResultsPage.clickOnFirstProduct();
    }
}