package ro.eden.boutique.tests;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import ro.eden.boutique.models.SearchData;
import ro.eden.boutique.steps.SearchSteps;

/**
 * This test class demonstrates function testing for the search functionality
 * using parametrized tests with valid and invalid data.
 */
@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom(value = "test_data/search_data.csv")
public class SearchFunctionTest {

    @Managed
    WebDriver driver;

    @Steps
    SearchSteps searchSteps;

    // Test data from CSV
    private String searchQuery;
    private boolean expectedResultCount;

    @Title("Search with valid query: #searchQuery")
    @Test
    public void searchWithValidQuery() {
        // Navigate to home page
        searchSteps.navigateToHomePage();

        // Perform search with data from CSV
        searchSteps.searchForProduct(searchQuery);

        // Verify search results are displayed
        searchSteps.verifySearchResultsDisplayed();
    }

    @RunWith(SerenityParameterizedRunner.class)
    @UseTestDataFrom(value = "test_data/invalid_search_data.csv")
    public static class InvalidSearchTest {

        @Managed
        WebDriver driver;

        @Steps
        SearchSteps searchSteps;

        // Test data from CSV
        private String searchQuery;
        private boolean expectedEmptyResults;

        @Title("Search with invalid query: #searchQuery")
        @Test
        public void searchWithInvalidQuery() {
            // Navigate to home page
            searchSteps.navigateToHomePage();

            // Perform search with invalid data from CSV
            searchSteps.searchForProduct(searchQuery);

            // Verify no search results are displayed
            searchSteps.verifyNoSearchResultsDisplayed();
        }
    }
}