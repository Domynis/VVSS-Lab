package org.example.steps.serenity;

import org.example.pages.HomePage;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

public class HomePageSteps {
    HomePage homePage;

    @Step
    public void open_homePage() {
        homePage.open();
    }

    @Step
    public void verify_homePage_opened() {
        Assert.assertTrue("Home page should be opened", homePage.isSliderRowDisplayed());
    }

    @Step
    public void searchFor(String searchTerm) {
        homePage.typeInSearchBarAndEnter(searchTerm);
    }
}