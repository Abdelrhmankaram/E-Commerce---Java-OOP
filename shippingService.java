import java.util.List;

public class shippingService {
    public static void send(List<shippable> items) {
        System.out.println("Sending the following items to shipping:");
        for (shippable item : items) {
            System.out.println("- " + item.getName() + " (" + item.getWeight() + "g)");
        }
    }
}
