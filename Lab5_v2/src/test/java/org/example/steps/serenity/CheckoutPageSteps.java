package org.example.steps.serenity;

import net.thucydides.core.annotations.Step;
import org.example.pages.CheckoutPage;
import org.junit.Assert;

public class CheckoutPageSteps {
    CheckoutPage checkoutPage;

    @Step
    public void clickOnProceedToCheckoutButton() {
        checkoutPage.clickOnProceedToCheckoutButton();
    }

    @Step
    public void completeCheckoutPage2() {
        checkoutPage.clickOnProceedToCheckoutButton2();
        checkoutPage.clickOnCloseButton();
        checkoutPage.clickOnTermsOfServiceCheckbox();
        Assert.assertTrue("Terms of service checkbox is not checked", checkoutPage.isTermsOfServiceChecked());
    }
}
