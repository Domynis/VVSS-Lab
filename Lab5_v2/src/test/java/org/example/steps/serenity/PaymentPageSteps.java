package org.example.steps.serenity;

import net.thucydides.core.annotations.Step;
import org.example.pages.CheckoutPage;
import org.example.pages.PaymentPage;
import org.junit.Assert;

public class PaymentPageSteps {
    PaymentPage paymentPage;

    @Step
    public void completePaymentPage() {
        paymentPage.clickOnPayByBankWireButton();
        Assert.assertTrue("Bank wire payment heading is not displayed", paymentPage.isBankWirePaymentHeadingDisplayed());
        paymentPage.clickOnConfirmOrderButton();
        Assert.assertTrue("Order confirmation message is not displayed", paymentPage.getOrderConfirmationMessage().contains("Your order on My Shop is complete."));
    }
}
