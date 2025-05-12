package org.example.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;


public class LoginPage extends PageObject {

    @FindBy(id = "email")
    private WebElementFacade emailInput;

    @FindBy(id = "passwd")
    private WebElementFacade passwordInput;

    @FindBy(id = "SubmitLogin")
    private WebElementFacade submitButton;

    @FindBy(xpath = "//p[text()='There is 1 error']")
    private WebElementFacade errorMessage;

    public void enter_email(String keyword) {
        emailInput.type(keyword);
    }

    public void enter_password(String keyword) {
        passwordInput.type(keyword);
    }

    public void click_submit() {
        submitButton.click();
    }

    public boolean isErrorMessageDisplayed() {
        return errorMessage.isCurrentlyVisible();
    }
}