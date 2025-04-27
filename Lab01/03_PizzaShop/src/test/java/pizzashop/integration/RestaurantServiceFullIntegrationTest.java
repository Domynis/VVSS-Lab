package pizzashop.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;
import pizzashop.service.RestaurantService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class RestaurantServiceFullIntegrationTest {

    private RestaurantService restaurantService;
    private PaymentRepository realPaymentRepo;
    private MenuRepository mockedMenuRepo;

    @BeforeEach
    void setUp() throws IOException {
        // Setup a temporary file with payments
        File tempFile = File.createTempFile("full_integration_payments", ".txt");
        tempFile.deleteOnExit();
        FileWriter fw = new FileWriter(tempFile);
        fw.write("4,CASH,40.0\n");
        fw.write("5,CARD,60.0\n");
        fw.close();

        mockedMenuRepo = mock(MenuRepository.class);
        realPaymentRepo = new PaymentRepository(tempFile.getAbsolutePath());

        restaurantService = new RestaurantService(mockedMenuRepo, realPaymentRepo);
    }

    @Test
    void testGetTotalAmountFullIntegration() {
        double totalCash = restaurantService.getTotalAmount(PaymentType.CASH);
        assertEquals(40.0, totalCash);

        double totalCard = restaurantService.getTotalAmount(PaymentType.CARD);
        assertEquals(60.0, totalCard);
    }

    @Test
    void testAddPaymentFullIntegration() {
        int initialSize = restaurantService.getPayments().size();
        restaurantService.addPayment(6, PaymentType.CASH, 25.0);

        List<Payment> updatedPayments = restaurantService.getPayments();
        assertEquals(initialSize + 1, updatedPayments.size());
        Payment lastPayment = updatedPayments.get(updatedPayments.size() - 1);
        assertEquals(6, lastPayment.getTableNumber());
        assertEquals(PaymentType.CASH, lastPayment.getType());
        assertEquals(25.0, lastPayment.getAmount());
    }
}
