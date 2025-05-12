package org.example.features.scenarios;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Issue;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.example.steps.serenity.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityRunner.class)
public class ScenarioStory {

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Steps
    public HomePageSteps homePage;

    @Steps
    public NavBarPageSteps navBarPage;

    @Steps
    public LoginPageSteps loginPage;

    @Steps
    public DressPageSteps dressPage;

    @Steps
    public CheckoutPageSteps checkoutPage;

    @Steps
    public PaymentPageSteps paymentPage;

    @Issue("#CLOTHES-3")
    @Test
    public void sequenceOfFeatures_succes() {
        homePage.open_homePage();
        navBarPage.clickSignIn();
        loginPage.login("dominic.bacs@stud.ubbcluj.ro", "maga123");
        navBarPage.verifyAccountName("Elonita Musca");

        navBarPage.clickGoToHomePage();
        homePage.verify_homePage_opened();
        homePage.searchFor("dress");

        dressPage.clickOnPrintedDressImage();

        dressPage.clickOnDressColorButton();
        dressPage.clickOnAddToCartButton();
        dressPage.clickOnProceedToCheckoutButton();

        checkoutPage.clickOnProceedToCheckoutButton();
        checkoutPage.clickOnProceedToCheckoutButton();
        checkoutPage.completeCheckoutPage2();

        paymentPage.completePaymentPage();

        navBarPage.logout();
    }
}
