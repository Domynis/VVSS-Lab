package pizzashop.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PaymentRepositoryTest {

    private PaymentRepository paymentRepository;

    @BeforeEach
    void setUp() throws IOException {
        // Prepare a temporary file
        File tempFile = File.createTempFile("test_payments", ".txt");
        tempFile.deleteOnExit(); // delete file on JVM exit
        FileWriter fw = new FileWriter(tempFile);
        fw.write("1,CASH,50.0\n");
        fw.write("2,CARD,100.0\n");
        fw.close();

        paymentRepository = new PaymentRepository(tempFile.getAbsolutePath());
    }

    @Test
    void testReadPayments() {
        List<Payment> payments = paymentRepository.getAll();
        assertEquals(2, payments.size());

        assertEquals(1, payments.get(0).getTableNumber());
        assertEquals(PaymentType.CASH, payments.get(0).getType());
        assertEquals(50.0, payments.get(0).getAmount());
    }

    @Test
    void testAddPayment() {
        Payment newPayment = new Payment(3, PaymentType.CARD, 70.0);
        PaymentRepository spyRepo = spy(paymentRepository);

        doNothing().when(spyRepo).appendToFile(any(Payment.class));

        spyRepo.add(newPayment);

        List<Payment> payments = spyRepo.getAll();
        assertEquals(3, payments.size());
        assertEquals(3, payments.get(2).getTableNumber());
    }
}
