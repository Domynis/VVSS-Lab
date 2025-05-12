package ro.eden.boutique.tests;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import ro.eden.boutique.steps.SearchSteps;

/**
 * This test class demonstrates scenario-based testing for applying filters to search results.
 */
@RunWith(SerenityRunner.class)
public class FilterApplyScenarioTest {

    @Managed
    WebDriver driver;

    @Steps
    SearchSteps searchSteps;

    @Title("Apply multiple filters to search results")
    @Test
    public void applyMultipleFiltersToSearchResults() {
        // Step 1: Navigate to home page and search for a product
        searchSteps.navigateToHomePage();
        searchSteps.searchForProduct("rochie");
        searchSteps.verifySearchResultsDisplayed();

        // Step 2: Apply category filter
        searchSteps.applyCategoryFilter("Rochii");
        searchSteps.verifySearchResultsDisplayed();

        // Step 3: Apply price filter (adjust the price range according to actual options on the site)
        searchSteps.applyPriceFilter("100.00 RON - 200.00 RON");
        searchSteps.verifySearchResultsDisplayed();

        // Step 4: Select first product from filtered results
        searchSteps.selectFirstProduct();
    }
}