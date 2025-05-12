package org.example.steps.serenity;

import net.thucydides.core.annotations.Step;
import org.example.pages.NavBarPage;
import org.junit.Assert;

import static org.junit.matchers.JUnitMatchers.containsString;

public class NavBarPageSteps {
    NavBarPage navBarPage;

    @Step
    public void clickSignIn() {
        navBarPage.click_signIn();
    }

    @Step
    public void clickGoToHomePage() {
        navBarPage.click_logo();
    }

    @Step
    public void verifyAccountName(String expectedName) {
        String actualName = navBarPage.getAccountName();
        Assert.assertThat(actualName, containsString(expectedName));
    }
}