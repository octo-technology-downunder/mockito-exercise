package octo;

import external.tracking.GstTracker;

public class GSTCalculator {

    private GstTracker gstTracker;

    public GSTCalculator() {
        gstTracker = new GstTracker();
    }

    public int calculatePriceWithGST(Product product) {
        int gstPercentage = gstTracker.getGSTPercentage(product.getPrice());
        return product.getPrice() + (gstPercentage * product.getPrice() / 100);
    }
}
