package org.example.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.interactions.Actions;

public class DressPage extends PageObject {

    @FindBy(xpath = "//img[@src='http://www.automationpractice.pl/img/p/1/2/12-home_default.jpg']")
    private WebElementFacade printedSummerDressImage;

    @FindBy(xpath = "//a[@href='http://www.automationpractice.pl/index.php?id_product=5&controller=product#/size-s/color-black']")
    private WebElementFacade dressColorLink;

    @FindBy(id = "color_11")
    private WebElementFacade dressBlackColorButton;

    @FindBy(xpath = "//span[text()='Add to cart']")
    private WebElementFacade addToCartButton;

    @FindBy(xpath = "//a[@class='btn btn-default button button-medium'] / span")
    private WebElementFacade proceedToCheckoutButton;

    @FindBy(className = "icon-check")
    private WebElementFacade successMessage;

    public void hoverAndClickPrintedDressImage() {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(printedSummerDressImage).perform();
        waitABit(500);
        dressColorLink.click();
        waitABit(1500);
    }

    public void clickOnDressColorButton() {
        dressBlackColorButton.click();
        waitABit(1500);
    }

    public boolean isAddToCartButtonDisplayed() {
        return addToCartButton.isDisplayed();
    }

    public void clickOnAddToCartButton() {
        addToCartButton.click();
        waitABit(1500);
    }

    public boolean isSuccessMessageDisplayed() {
        return successMessage.isDisplayed();
    }

    public void clickOnProceedToCheckoutButton() {
        proceedToCheckoutButton.click();
    }
}
