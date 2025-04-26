package pizzashop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {

    @Test
    void testPaymentConstructorAndGetters() {
        Payment payment = new Payment(5, PaymentType.CARD, 100.0);

        assertEquals(5, payment.getTableNumber());
        assertEquals(PaymentType.CARD, payment.getType());
        assertEquals(100.0, payment.getAmount());
    }

    @Test
    void testPaymentSetters() {
        Payment payment = new Payment(1, PaymentType.CASH, 50.0);
        payment.setTableNumber(3);
        payment.setType(PaymentType.CARD);
        payment.setAmount(75.5);

        assertEquals(3, payment.getTableNumber());
        assertEquals(PaymentType.CARD, payment.getType());
        assertEquals(75.5, payment.getAmount());
    }

    @Test
    void testToStringFormat() {
        Payment payment = new Payment(2, PaymentType.CASH, 20.0);
        assertEquals("2,CASH,20.0", payment.toString());
    }
}
