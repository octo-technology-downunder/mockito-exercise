package external.tracking;

public class GstTracker {

    //some dummy logic for get gst percentage
    public int getGSTPercentage(int itemPrice) {

        if (itemPrice < 1000) {
            return 10;
        } else {
            return 20;
        }
    }
}
