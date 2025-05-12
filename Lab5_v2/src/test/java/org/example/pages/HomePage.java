package org.example.pages;

import net.thucydides.core.annotations.DefaultUrl;
import net.serenitybdd.core.pages.WebElementFacade;

import net.serenitybdd.core.annotations.findby.FindBy;

import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.interactions.Actions;


@DefaultUrl("http://www.automationpractice.pl/index.php")
public class HomePage extends PageObject {

    @FindBy(id= "search_query_top")
    private WebElementFacade searchBar;

    @FindBy(id = "slider_row")
    private WebElementFacade sliderRow;

    public boolean isSliderRowDisplayed() {
        return sliderRow.isDisplayed();
    }

    public void typeInSearchBarAndEnter(String searchTerm) {
        searchBar.typeAndEnter(searchTerm);
    }
}