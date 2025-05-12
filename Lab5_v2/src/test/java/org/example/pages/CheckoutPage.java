package org.example.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;

public class CheckoutPage extends PageObject {

    @FindBy(xpath = "//span[text()='Proceed to checkout']")
    private WebElementFacade proceedToCheckoutButton;

    @FindBy(xpath = "//button[@class='button btn btn-default standard-checkout button-medium'] / span")
    private WebElementFacade proceedToCheckoutButton2;

    @FindBy(css = "a.fancybox-item.fancybox-close")
    private WebElementFacade closeButton;

    @FindBy(id = "cgv")
    private WebElementFacade termsOfServiceCheckbox;

    @FindBy(xpath = "//div[@id='uniform-cgv']/span")
    private WebElementFacade termsOfServiceSpan;

    public void clickOnProceedToCheckoutButton() {
        proceedToCheckoutButton.click();
    }

    public void clickOnProceedToCheckoutButton2() {
        proceedToCheckoutButton2.click();
        waitABit(500);
    }

    public void clickOnCloseButton() {
        closeButton.click();
        waitABit(500);
    }

    public void clickOnTermsOfServiceCheckbox() {
        termsOfServiceSpan.click();
    }

    public boolean isTermsOfServiceChecked() {
        return termsOfServiceSpan.getAttribute("class").contains("checked");
    }
}
