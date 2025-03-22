package pizzashop.repository;

import pizzashop.model.Payment;
import pizzashop.model.PaymentType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class PaymentRepository {
    private final String filename;
    private final List<Payment> paymentList;

    public PaymentRepository(){
        this.paymentList = new ArrayList<>();
        filename = "data/payments.txt";
        readPayments();
    }

    public PaymentRepository(String filename) {
        this.paymentList = new ArrayList<>();
        this.filename = filename;
        readPayments();
    }

    private void readPayments(){
        //ClassLoader classLoader = PaymentRepository.class.getClassLoader();
        File file = new File(filename);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = null;
            while((line=br.readLine())!=null){
                Payment payment=getPayment(line);
                paymentList.add(payment);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Payment getPayment(String line){
        Payment item=null;
        if (line==null|| line.equals("")) return null;
        StringTokenizer st=new StringTokenizer(line, ",");
        int tableNumber= Integer.parseInt(st.nextToken());
        String type= st.nextToken();
        double amount = Double.parseDouble(st.nextToken());
        item = new Payment(tableNumber, PaymentType.valueOf(type), amount);
        return item;
    }

    public void add(Payment payment) {
        paymentList.add(payment);
        appendToFile(payment);
    }

    public List<Payment> getAll(){
        return paymentList;
    }

    public void appendToFile(Payment payment) {
        //ClassLoader classLoader = PaymentRepository.class.getClassLoader();
        File file = new File(filename);

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file));) {
            System.out.println(payment.toString());
            bw.write(payment.toString());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}