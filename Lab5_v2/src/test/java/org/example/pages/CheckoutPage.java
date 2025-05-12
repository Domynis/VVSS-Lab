package org.example.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;

public class CheckoutPage extends PageObject {

    @FindBy(xpath = "//span[text()='Proceed to checkout']")
    private WebElementFacade proceedToCheckoutButton;

    @FindBy(xpath = "//button[@class='button btn btn-default standard-checkout button-medium'] / span")
    private WebElementFacade proceedToCheckoutButton2;

    public void clickOnProceedToCheckoutButton() {
        proceedToCheckoutButton.click();
    }

    public void clickOnProceedToCheckoutButton2() {
        proceedToCheckoutButton2.click();
    }
}
