package org.example.features.login;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Issue;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;

import org.example.steps.serenity.LoginPageSteps;
import org.example.steps.serenity.NavBarPageSteps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import org.example.steps.serenity.HomePageSteps;

@RunWith(SerenityRunner.class)
public class LoginStory {

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Steps
    public HomePageSteps homePage;

    @Steps
    public NavBarPageSteps navBarPage;

    @Steps
    public LoginPageSteps loginPage;

    @Issue("#CLOTHES-1")
    @Test
    public void loginMatchingCredentials_succes() {
        homePage.open_homePage();
        navBarPage.clickSignIn();
        loginPage.login("dominic.bacs@stud.ubbcluj.ro", "maga123");
        navBarPage.verifyAccountName("Elonita Musca");
    }

    @Issue("#CLOTHES-2")
    @Test
    public void loginWrongCredentials_error() {
        homePage.open_homePage();
        navBarPage.clickSignIn();
        loginPage.login("stefan.alexandrescu@stud.ubbcluj.ro", "maga123");
        loginPage.verifyErrorMessageDisplayed();
    }
} 