package pizzashop.service;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import pizzashop.model.PaymentType;

import java.util.Optional;

public class PaymentAlert implements PaymentOperation {
    private RestaurantService service;
    private static final String LINE = "--------------------------";

    public PaymentAlert(RestaurantService service){
        this.service=service;
    }

    @Override
    public void cardPayment() {
        System.out.println(LINE);
        System.out.println("Paying by card...");
        System.out.println("Please insert your card!");
        System.out.println(LINE);
    }
    @Override
    public void cashPayment() {
        System.out.println(LINE);
        System.out.println("Paying cash...");
        System.out.println("Please show the cash...!");
        System.out.println(LINE);
    }
    @Override
    public void cancelPayment() {
        System.out.println(LINE);
        System.out.println("Payment choice needed...");
        System.out.println(LINE);
    }
      public void showPaymentAlert(int tableNumber, double totalAmount ) {
        Alert paymentAlert = new Alert(Alert.AlertType.CONFIRMATION);
        paymentAlert.setTitle("Payment for Table "+tableNumber);
        paymentAlert.setHeaderText("Total amount: " + totalAmount);
        paymentAlert.setContentText("Please choose payment option");
        ButtonType cardPayment = new ButtonType("Pay by Card");
        ButtonType cashPayment = new ButtonType("Pay Cash");
        ButtonType cancel = new ButtonType("Cancel");
        paymentAlert.getButtonTypes().setAll(cardPayment, cashPayment, cancel);
        Optional<ButtonType> result = paymentAlert.showAndWait();
        if (result.get() == cardPayment) {
            cardPayment();
            service.addPayment(tableNumber, PaymentType.CARD,totalAmount);
        } else if (result.get() == cashPayment) {
            cashPayment();
            service.addPayment(tableNumber, PaymentType.CASH,totalAmount);
        } else {
            cancelPayment();
        }
    }
}