package pizzashop.service;

import pizzashop.model.MenuItem;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;

import java.util.List;

public class RestaurantService {
    private final MenuRepository menuRepo;
    private final PaymentRepository payRepo;

    public RestaurantService(MenuRepository menuRepo, PaymentRepository payRepo){
        this.menuRepo=menuRepo;
        this.payRepo=payRepo;
    }

    public List<MenuItem> getMenuData() {
        return menuRepo.getMenu();
    }

    public List<Payment> getPayments() {
        return payRepo.getAll();
    }

    public void addPayment(int table, PaymentType type, double amount) {
        if (table < 1 || table > 8) {
            throw new IllegalArgumentException("Invalid table number");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid amount");
        }

        Payment payment = new Payment(table, type, amount);
        payRepo.add(payment);
    }

    public double getTotalAmount(PaymentType type){
        double total=0.0f;
        List<Payment> l=getPayments();
        if ((l==null) || (l.size() == 0)) return total;
        for (Payment p:l){
            if (p.getType().equals(type))
                total+=p.getAmount();
        }
        return total;
    }

}