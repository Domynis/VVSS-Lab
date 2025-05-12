package ro.eden.boutique.steps;

import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import ro.eden.boutique.models.RegistrationData;
import ro.eden.boutique.pages.HomePage;
import ro.eden.boutique.pages.RegistrationPage;

public class RegistrationSteps {

    private HomePage homePage;
    private RegistrationPage registrationPage;

    @Step("Navigate to registration page")
    public void navigateToRegistrationPage() {
        homePage.navigateToHomePage();
        homePage.navigateToRegistrationPage();
    }

    @Step("Fill registration form")
    public void fillRegistrationForm(RegistrationData data) {
        registrationPage.fillRegistrationForm(
                data.getFirstName(),
                data.getLastName(),
                data.getEmail(),
                data.getPassword()
        );
    }

    @Step("Submit registration form")
    public void submitRegistrationForm() {
        registrationPage.clickCreateAccount();
    }

    @Step("Verify registration success")
    public void verifyRegistrationSuccess() {
        Assert.assertTrue("Registration success message should be displayed",
                registrationPage.isSuccessMessageDisplayed());
    }

    @Step("Verify registration error")
    public void verifyRegistrationError() {
        Assert.assertTrue("Registration error message should be displayed",
                registrationPage.isErrorMessageDisplayed());
    }

    @Step("Verify first name error is displayed")
    public void verifyFirstNameError() {
        Assert.assertTrue("First name error should be displayed",
                registrationPage.isFirstNameErrorDisplayed());
    }

    @Step("Verify last name error is displayed")
    public void verifyLastNameError() {
        Assert.assertTrue("Last name error should be displayed",
                registrationPage.isLastNameErrorDisplayed());
    }

    @Step("Verify email error is displayed")
    public void verifyEmailError() {
        Assert.assertTrue("Email error should be displayed",
                registrationPage.isEmailErrorDisplayed());
    }

    @Step("Verify password error is displayed")
    public void verifyPasswordError() {
        Assert.assertTrue("Password error should be displayed",
                registrationPage.isPasswordErrorDisplayed());
    }

    @Step("Verify appropriate field error based on error type: {0}")
    public void verifyFieldErrorByType(String errorType) {
        switch (errorType) {
            case "firstNameRequired":
                verifyFirstNameError();
                break;
            case "lastNameRequired":
                verifyLastNameError();
                break;
            case "invalidEmail":
                verifyEmailError();
                break;
            case "weakPassword":
                verifyPasswordError();
                break;
            default:
                verifyRegistrationError();
        }
    }
}