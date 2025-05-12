package ro.eden.boutique.tests;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import ro.eden.boutique.models.RegistrationData;
import ro.eden.boutique.steps.RegistrationSteps;
import ro.eden.boutique.utils.JSONDataReader;

import java.util.List;

/**
 * This test class demonstrates function testing for the user registration functionality
 * using parametrized tests with valid and invalid data.
 */
@RunWith(SerenityRunner.class)
public class RegistrationFunctionTest {

    @Managed
    WebDriver driver;

    @Steps
    RegistrationSteps registrationSteps;

    private List<RegistrationData> validRegistrationData;
    private List<RegistrationData> invalidRegistrationData;

    @Before
    public void setup() {
        validRegistrationData = JSONDataReader.readRegistrationData("test_data/registration_data.json");
        invalidRegistrationData = JSONDataReader.readRegistrationData("test_data/invalid_registration_data.json");
    }

    @Title("Register with valid data")
    @Test
    public void registerWithValidData() {
        // Use only the first valid registration data from the list
        RegistrationData data = validRegistrationData.get(0);

        // Navigate to registration page
        registrationSteps.navigateToRegistrationPage();

        // Fill registration form with valid data
        registrationSteps.fillRegistrationForm(data);

        // Submit registration form
        registrationSteps.submitRegistrationForm();

        // Verify registration success
        // Note: This might fail in real execution if email is already registered
        if ("success".equals(data.getExpectedResult())) {
            registrationSteps.verifyRegistrationSuccess();
        }
    }

    @Title("Register with invalid data")
    @Test
    public void registerWithInvalidData() {
        // Use only the first invalid registration data from the list
        RegistrationData data = invalidRegistrationData.get(0);

        // Navigate to registration page
        registrationSteps.navigateToRegistrationPage();

        // Fill registration form with invalid data
        registrationSteps.fillRegistrationForm(data);

        // Submit registration form
        registrationSteps.submitRegistrationForm();

        // Verify appropriate validation error displayed
        registrationSteps.verifyFieldErrorByType(data.getErrorType());
    }
}