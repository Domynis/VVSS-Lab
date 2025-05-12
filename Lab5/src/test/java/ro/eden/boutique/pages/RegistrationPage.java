package ro.eden.boutique.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class RegistrationPage extends BasePage {

    @FindBy(id = "firstname")
    private WebElement firstNameInput;

    @FindBy(id = "lastname")
    private WebElement lastNameInput;

    @FindBy(id = "email_address")
    private WebElement emailInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "password-confirmation")
    private WebElement confirmPasswordInput;

    @FindBy(css = ".action.submit.primary")
    private WebElement createAccountButton;

    @FindBy(css = ".messages .message-error")
    private WebElement errorMessage;

    public void fillRegistrationForm(String firstName, String lastName, String email, String password) {
        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);

        lastNameInput.clear();
        lastNameInput.sendKeys(lastName);

        emailInput.clear();
        emailInput.sendKeys(email);

        passwordInput.clear();
        passwordInput.sendKeys(password);

        confirmPasswordInput.clear();
        confirmPasswordInput.sendKeys(password);
    }

    public void clickCreateAccount() {
        scrollToElement(createAccountButton);
        createAccountButton.click();
    }

    public boolean isSuccessMessageDisplayed() {
        return isElementPresent(By.cssSelector(".messages .message-success"), 5);
    }

    public boolean isErrorMessageDisplayed() {
        return isElementPresent(By.cssSelector(".messages .message-error"), 5);
    }

    public String getErrorMessage() {
        if (isErrorMessageDisplayed()) {
            return errorMessage.getText();
        }
        return "";
    }

    public boolean isFirstNameErrorDisplayed() {
        return isElementPresent(By.id("firstname-error"), 2);
    }

    public boolean isLastNameErrorDisplayed() {
        return isElementPresent(By.id("lastname-error"), 2);
    }

    public boolean isEmailErrorDisplayed() {
        return isElementPresent(By.id("email_address-error"), 2);
    }

    public boolean isPasswordErrorDisplayed() {
        return isElementPresent(By.id("password-error"), 2);
    }

    public String getFieldError(String fieldId) {
        try {
            WebElement errorElement = getDriver().findElement(By.id(fieldId + "-error"));
            return errorElement.getText();
        } catch (Exception e) {
            return "";
        }
    }
}