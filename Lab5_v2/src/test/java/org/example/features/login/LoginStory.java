package org.example.features.login;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Issue;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;

import net.thucydides.junit.annotations.UseTestDataFrom;
import org.example.steps.serenity.LoginPageSteps;
import org.example.steps.serenity.NavBarPageSteps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import org.example.steps.serenity.HomePageSteps;

@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom("src/test/resources/features/search/LoginData.csv")
public class LoginStory {

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Steps
    public HomePageSteps homePage;

    @Steps
    public NavBarPageSteps navBarPage;

    @Steps
    public LoginPageSteps loginPage;

    String email, password, accountName, success;

    @Issue("#CLOTHES-12")
    @Test
    public void loginWithCredentials() {
        homePage.open_homePage();
        navBarPage.clickSignIn();
        loginPage.login(email, password);
        if (success.equals("yes")) {
            navBarPage.verifyAccountName(accountName);
        } else {
            loginPage.verifyErrorMessageDisplayed();
        }
    }
} 