package pizzashop.service;

import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.PaymentRepository;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;


class RestaurantServiceMockitoTest {
    @InjectMocks
    private RestaurantService restaurantService;

    @Mock
    private PaymentRepository paymentRepo;

    @BeforeEach
    void setUp() {
        paymentRepo = Mockito.mock(PaymentRepository.class);
        restaurantService = new RestaurantService(null, paymentRepo);
    }

    @Test
    @Tag("invalid")
    @DisplayName("Invalid Case: Get total amount with null list")
    void testGetTotalAmount_nullList() {
        Mockito.when(paymentRepo.getAll()).thenReturn(null);

        double result = restaurantService.getTotalAmount(PaymentType.CASH);

        assertEquals(0.0, result);
    }

    @Test
    @Tag("invalid")
    @DisplayName("Invalid Case: Get total amount with empty list")
    void testGetTotalAmount_emptyList() {
        Mockito.when(paymentRepo.getAll()).thenReturn(Collections.emptyList());

        double result = restaurantService.getTotalAmount(PaymentType.CASH);

        assertEquals(0.0, result);
    }

    @Test
    @Tag("valid")
    @DisplayName("Valid Case: Get total zero amount with non-empty list")
    void testGetTotalAmount_nonEmptyList() {
        Mockito.when(paymentRepo.getAll()).thenReturn(Collections.singletonList(new Payment(1, PaymentType.CARD, 100)));

        double result = restaurantService.getTotalAmount(PaymentType.CASH);

        assertEquals(0.0, result);
    }

    @Test
    @Tag("valid")
    @DisplayName("Valid Case: Get total amount with non-empty list")
    void testGetTotalAmountWith_matchingType() {
        Mockito.when(paymentRepo.getAll()).thenReturn(Collections.singletonList(new Payment(1, PaymentType.CARD, 100)));

        double result = restaurantService.getTotalAmount(PaymentType.CARD);

        assertEquals(100.0, result);
    }

    @Test
    @Tag("valid")
    @DisplayName("Valid Case: Get total amount with non-empty list and all types")
    void testGetTotalAmountWith_allTypes() {
        Mockito.when(paymentRepo.getAll()).thenReturn(
                Arrays.asList(
                        new Payment(1, PaymentType.CASH, 100),
                        new Payment(2, PaymentType.CARD, 150))
        );

        double result = restaurantService.getTotalAmount(PaymentType.CARD);

        assertEquals(150.0, result);
    }

    @Test
    @Tag("valid")
    @DisplayName("Valid Case: Get total amount with non-empty list and multiple matching types")
    void testGetTotalAmountWith_multipleMatchingTypes() {
        Mockito.when(paymentRepo.getAll()).thenReturn(
                Arrays.asList(
                        new Payment(1, PaymentType.CASH, 100),
                        new Payment(2, PaymentType.CARD, 200),
                        new Payment(3, PaymentType.CASH, 150))
        );

        double result = restaurantService.getTotalAmount(PaymentType.CASH);

        assertEquals(250.0, result);
    }
}
