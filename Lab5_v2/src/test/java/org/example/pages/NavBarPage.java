package org.example.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;


public class NavBarPage extends PageObject {

    @FindBy(className = "login")
    private WebElementFacade lookupButton;

    @FindBy(xpath = "//a[@title='View my customer account']")
    private WebElementFacade accountPageButton;

    @FindBy(xpath = "//a[@title='My Shop']")
    private WebElementFacade logo;

    @FindBy(xpath = "//a[@title='Log me out']")
    private WebElementFacade logoutButton;

    public void click_signIn() {
        lookupButton.click();
    }

    public boolean isSignInButtonDisplayed() {
        return lookupButton.isDisplayed();
    }

    public void click_logo() {
        logo.click();
    }

    public String getAccountName() {
        return accountPageButton.getText();
    }

    public void clickOnLogoutButton() {
        logoutButton.click();
    }
}