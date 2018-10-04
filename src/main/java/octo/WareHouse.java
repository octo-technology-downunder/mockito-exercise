package octo;

import external.db.ProductRepository;
import external.email.EmailService;

import java.util.List;

public class WareHouse {

    private GSTCalculator gstCalculator;
    private ProductRepository productRepository;
    private EmailService emailService;

    public WareHouse() {
        gstCalculator = new GSTCalculator();
        productRepository = new ProductRepository();
        emailService = new EmailService();
    }

    public void processProducts(String user, List<Product> products) {
        for (Product product : products) {
            // calculate gst price gstCalculator
            // save to db using productRepository
        }
        //send email to user
    }
}
