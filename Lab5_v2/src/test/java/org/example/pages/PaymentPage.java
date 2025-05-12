package org.example.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;

public class PaymentPage extends PageObject {

    @FindBy(xpath = "//a[@href='http://www.automationpractice.pl/index.php?fc=module&module=bankwire&controller=payment']")
    private WebElementFacade payByBankWireButton;

    @FindBy(xpath = "//h3[@class='page-subheading']")
    private WebElementFacade bankWirePaymentHeading;

    @FindBy(xpath = "//button[@class='button btn btn-default button-medium']")
    private WebElementFacade confirmOrderButton;

    @FindBy(xpath = "//p[@class='alert alert-success']")
    private WebElementFacade orderConfirmationMessage;

    public void clickOnPayByBankWireButton() {
        payByBankWireButton.click();
    }

    public boolean isBankWirePaymentHeadingDisplayed() {
        return bankWirePaymentHeading.isDisplayed();
    }

    public void clickOnConfirmOrderButton() {
        confirmOrderButton.click();
        waitABit(1500);
    }

    public String getOrderConfirmationMessage() {
        return orderConfirmationMessage.getText();
    }
}
