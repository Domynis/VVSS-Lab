package ro.eden.boutique;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import ro.eden.boutique.tests.*;

/**
 * This test suite runs all tests for Eden Boutique website.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        SearchFunctionTest.class,
        SearchFunctionTest.InvalidSearchTest.class,
        RegistrationFunctionTest.class,
        ShoppingScenarioTest.class,
        FilterApplyScenarioTest.class
})
public class EdenBoutiqueTestSuite {
    // This is just a placeholder class to run the suite
}