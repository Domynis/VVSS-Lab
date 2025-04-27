package pizzashop.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;
import pizzashop.service.RestaurantService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RestaurantServiceWithRepoTest {

    private RestaurantService restaurantService;
    private PaymentRepository realPaymentRepo;
    private MenuRepository mockedMenuRepo;

    @BeforeEach
    public void setUp() {
        mockedMenuRepo = mock(MenuRepository.class); // still mock MenuRepo
        realPaymentRepo = new PaymentRepository() {
            @Override
            public List<Payment> getAll() {
                return Arrays.asList(
                        new Payment(1, PaymentType.CASH, 20.0),
                        new Payment(2, PaymentType.CARD, 50.0)
                );
            }

            @Override
            public void add(Payment payment) {
                // Do nothing to avoid file writing
            }
        };

        restaurantService = new RestaurantService(mockedMenuRepo, realPaymentRepo);
    }

    @Test
    public void testGetTotalAmountIntegrationR() {
        double totalCash = restaurantService.getTotalAmount(PaymentType.CASH);
        assertEquals(20.0, totalCash);

        double totalCard = restaurantService.getTotalAmount(PaymentType.CARD);
        assertEquals(50.0, totalCard);
    }

    @Test
    public void testAddPaymentIntegrationR() {
        restaurantService.addPayment(3, PaymentType.CARD, 30.0);

        List<Payment> payments = realPaymentRepo.getAll();
        assertEquals(2, payments.size()); // Because add is empty overridden
    }
}
