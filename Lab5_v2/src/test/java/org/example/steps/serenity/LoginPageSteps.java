package org.example.steps.serenity;

import net.thucydides.core.annotations.Step;
import org.example.pages.HomePage;
import org.example.pages.LoginPage;
import org.junit.Assert;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;

public class LoginPageSteps {
    LoginPage loginPage;

    @Step
    public void type_email(String keyword) {
        loginPage.enter_email(keyword);
    }

    @Step
    public void type_password(String keyword) {
        loginPage.enter_password(keyword);
    }

    @Step
    public void sign_in() {
        loginPage.click_submit();
    }

    @Step
    public void login(String email, String password) {
        type_email(email);
        type_password(password);
        sign_in();
    }

    @Step
    public void verifyErrorMessageDisplayed() {
        Assert.assertTrue("Error message is not displayed", loginPage.isErrorMessageDisplayed());
    }
}