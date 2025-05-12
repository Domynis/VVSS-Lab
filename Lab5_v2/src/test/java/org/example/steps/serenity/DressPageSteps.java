package org.example.steps.serenity;

import net.thucydides.core.annotations.Step;
import org.example.pages.DressPage;
import org.junit.Assert;

public class DressPageSteps {
    DressPage dressPage;

    @Step
    public void clickOnPrintedDressImage() {
        dressPage.hoverAndClickPrintedDressImage();
    }

    @Step
    public void clickOnDressColorButton() {
        dressPage.clickOnDressColorButton();
        Assert.assertTrue("Add to cart button should be displayed", dressPage.isAddToCartButtonDisplayed());
    }

    @Step
    public void clickOnAddToCartButton() {
        dressPage.clickOnAddToCartButton();
        Assert.assertTrue("Success message should be displayed", dressPage.isSuccessMessageDisplayed());
    }

    @Step
    public void clickOnProceedToCheckoutButton() {
        dressPage.clickOnProceedToCheckoutButton();
    }
}
