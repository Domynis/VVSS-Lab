package pizzashop.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pizzashop.model.PaymentType;
import pizzashop.repository.PaymentRepository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class RestaurantServiceTest {
    private static final String PAYMENTS_FILE = "data/test/payments.txt";

    private RestaurantService restaurantService;
    private PaymentRepository paymentRepo;

    @BeforeAll
    public static void init() {
        System.out.println("Starting RestaurantServiceTest...");
        // create test file
        File file = new File(PAYMENTS_FILE);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @BeforeEach
    public void setUp() {
        paymentRepo = new PaymentRepository(PAYMENTS_FILE);
        restaurantService = new RestaurantService(null, paymentRepo);
    }

    public void verifyAddedPayment(int table, PaymentType type, double amount) {
        paymentRepo.getAll()
                .stream()
                .filter(p -> p.getTableNumber() == table && p.getType() == type && p.getAmount() == amount)
                .findFirst()
                .orElseThrow(() -> new AssertionError("Payment not found"));
    }

    @Test
    @Tag("valid")
    @DisplayName("ECP Valid Case: Add valid payment")
    public void testAddPayment_ValidECP() {
        int table = 3;
        PaymentType type = PaymentType.CARD;
        double amount = 15;

        restaurantService.addPayment(table, type, amount);
        verifyAddedPayment(table, type, amount);
    }

    @Test
    @Tag("invalid")
    @DisplayName("ECP Invalid Case: Negative table number")
    public void testAddPayment_InvalidTable_ECP() {
        int table = -3;
        PaymentType type = PaymentType.CASH;
        double amount = 15;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            restaurantService.addPayment(table, type, amount);
        });

        assertEquals("Invalid table number", exception.getMessage());
    }

    @Test
    @Tag("invalid")
    @DisplayName("ECP Invalid Case: Table number at upper boundary (9)")
    public void testAddPayment_InvalidTableUpperBound_ECP() {
        int table = 9;
        PaymentType type = PaymentType.CARD;
        double amount = 20;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            restaurantService.addPayment(table, type, amount);
        });

        assertEquals("Invalid table number", exception.getMessage());
    }

    @ParameterizedTest
    @Tag("invalid")
    @DisplayName("BVA Invalid Case: Table numbers at boundary (0, 9)")
    @ValueSource(ints = {0, 9})
    public void testAddPayment_BVATableBoundary(int table) {
        PaymentType type = PaymentType.CARD;
        double amount = 15;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            restaurantService.addPayment(table, type, amount);
        });

        assertEquals("Invalid table number", exception.getMessage());
    }

    @Test
    @Tag("valid")
    @DisplayName("BVA Valid Case: Table number at upper boundary (8)")
    public void testAddPayment_BVATableEight() {
        int table = 8;
        PaymentType type = PaymentType.CARD;
        double amount = 20;

        restaurantService.addPayment(table, type, amount);
        verifyAddedPayment(table, type, amount);
    }

    @RepeatedTest(2)
    @Tag("valid")
    @DisplayName("BVA Valid Case: Smallest valid amount (0.1)")
    public void testAddPayment_BVAAmountSmallestValid() {
        int table = 3;
        PaymentType type = PaymentType.CASH;
        double amount = 0.1;

        restaurantService.addPayment(table, type, amount);
        verifyAddedPayment(table, type, amount);
    }

    @Test
    @Tag("invalid")
    @DisplayName("BVA Invalid Case: Negative amount")
    public void testAddPayment_BVAInvalidAmount() {
        int table = 3;
        PaymentType type = PaymentType.CARD;
        double amount = -1;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            restaurantService.addPayment(table, type, amount);
        });

        assertEquals("Invalid amount", exception.getMessage());
    }

    @Test
    @Disabled("This test is temporarily disabled for debugging")
    @DisplayName("Disabled Test: Future enhancement case")
    public void testAddPayment_FutureEnhancement() {
        int table = 5;
        PaymentType type = PaymentType.CARD;
        double amount = 50;

        restaurantService.addPayment(table, type, amount);
        verifyAddedPayment(table, type, amount);
    }

    @Test
    @Tag("invalid")
    @DisplayName("Invalid case: Null payment list")
    public void testGetTotalAmount_NullPaymentList() {
        PaymentType type = PaymentType.CASH;

        double total = restaurantService.getTotalAmount(type);
        assertEquals(0.0, total, 0.01);
    }


    @AfterEach
    public void tearDown() {
        restaurantService = null;

        // Delete test file content
        try (FileWriter writer = new FileWriter(PAYMENTS_FILE)) {
            writer.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    public static void cleanup() {
        System.out.println("All test cases executed.");

        // delete test file
        File file = new File(PAYMENTS_FILE);
        if (file.exists()) {
            file.delete();
        }
    }
}
