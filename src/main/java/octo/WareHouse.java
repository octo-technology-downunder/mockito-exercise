package octo;

import external.db.ProductRepository;
import external.email.EmailService;

import java.util.List;

public class WareHouse {

    public void processProducts(String user, List<Product> products) {
        for (Product product : products) {
            // calculate gst price gstCalculator
            // save to db using productRepository
        }
        //send email to user
    }
}
