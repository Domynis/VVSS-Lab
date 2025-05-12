package ro.eden.boutique.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchResultsPage extends BasePage {

    @FindBy(css = ".products-grid .product-item")
    private List<WebElement> productItems;

    @FindBy(css = ".toolbar-number")
    private WebElement resultsCount;

    @FindBy(css = ".filter-options")
    private WebElement filterOptions;

    @FindBy(css = ".empty-search-result")
    private WebElement emptySearchResult;

    public int getResultsCount() {
        return productItems.size();
    }

    public boolean hasResults() {
        try {
            return !productItems.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean hasNoResults() {
        return isElementPresent(By.cssSelector(".empty-search-result"), 2);
    }

    public void clickOnFirstProduct() {
        if (!productItems.isEmpty()) {
            WebElement firstProduct = productItems.get(0);
            scrollToElement(firstProduct);
            firstProduct.click();
        } else {
            throw new RuntimeException("No products found in search results");
        }
    }

    public void applyFilterByCategory(String categoryName) {
        WebElement categoryFilter = waitForElement(
                By.xpath("//div[contains(@class, 'filter-options')]//div[contains(@class, 'filter-options-title') and contains(text(), 'Categorie')]/.."),
                5
        );

        if (categoryFilter != null) {
            scrollToElement(categoryFilter);

            // Expand the category filter if collapsed
            WebElement filterTitle = categoryFilter.findElement(By.cssSelector(".filter-options-title"));
            if (!isElementPresent(By.xpath("//li//a[contains(text(), '" + categoryName + "')]"), 1)) {
                filterTitle.click();
                waitABit(500);
            }

            // Click on the specific category option
            WebElement option = waitForElement(
                    By.xpath("//li//a[contains(text(), '" + categoryName + "')]"),
                    5
            );

            if (option != null) {
                scrollToElement(option);
                option.click();
                waitForPageToLoad();
            } else {
                throw new RuntimeException("Category filter option not found: " + categoryName);
            }
        } else {
            throw new RuntimeException("Category filter not found");
        }
    }

    public void applyFilterByPrice(String priceRange) {
        WebElement priceFilter = waitForElement(
                By.xpath("//div[contains(@class, 'filter-options')]//div[contains(@class, 'filter-options-title') and contains(text(), 'Preț')]/.."),
                5
        );

        if (priceFilter != null) {
            scrollToElement(priceFilter);

            // Expand the price filter if collapsed
            WebElement filterTitle = priceFilter.findElement(By.cssSelector(".filter-options-title"));
            if (!isElementPresent(By.xpath("//li//a[contains(text(), '" + priceRange + "')]"), 1)) {
                filterTitle.click();
                waitABit(500);
            }

            // Click on the specific price range option
            WebElement option = waitForElement(
                    By.xpath("//li//a[contains(text(), '" + priceRange + "')]"),
                    5
            );

            if (option != null) {
                scrollToElement(option);
                option.click();
                waitForPageToLoad();
            } else {
                throw new RuntimeException("Price filter option not found: " + priceRange);
            }
        } else {
            throw new RuntimeException("Price filter not found");
        }
    }

    private void waitForPageToLoad() {
        waitABit(2000); // Wait for the page to reload with new filters
    }
}