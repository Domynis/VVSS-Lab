package org.example.steps.serenity;

import net.thucydides.core.annotations.Step;
import org.example.pages.CheckoutPage;

public class CheckoutPageSteps {
    CheckoutPage checkoutPage;

    @Step
    public void clickOnProceedToCheckoutButton() {
        checkoutPage.clickOnProceedToCheckoutButton();
    }

    @Step
    public void clickOnProceedToCheckoutButton2() {
        checkoutPage.clickOnProceedToCheckoutButton2();
    }
}
