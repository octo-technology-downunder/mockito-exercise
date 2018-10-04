package external.db;

import octo.Product;

public class ProductRepository {
    public void save(final Product product, final int price) {
        System.out.print("saved a gst price " + price + "to db for product " + product.getName());
    }
}
