import java.util.ArrayList;
import java.util.List;

class app {
    public void checkout(customer x, cart y) {
        if (x.getBalance() >= (y.getTotalPrice() + y.getShippingFees())) {
            y.updateCartQuantity();
            System.out.println("\n** Shipment notice **");

            List<shippable> shippableItems = new ArrayList<>();
            
            double totalWeight = 0;

            for (item i : y.cartItems) {
                double itemWeight = i.itemProduct.getWeight() * i.quantity;
                totalWeight += itemWeight;

                System.out.printf("%-3s %-15s %5dg\n",
                    i.quantity + "x",
                    i.itemProduct.name,
                    (int)itemWeight);
            }

            double totalWeightKg = totalWeight / 1000.0;
            System.out.printf("Total package weight %.1fkg\n\n", totalWeightKg);

            double totalPrice = 0;
            System.out.println("** Checkout receipt**");
            for (item i : y.cartItems) {
                if(i.itemProduct.shipping) {
                    shippableItems.add(i.itemProduct);
                }

                double itemPrice = i.itemProduct.getPrice() * i.quantity;
                totalPrice += itemPrice;

                System.out.printf("%-3s %-15s %5d$\n",
                    i.quantity + "x",
                    i.itemProduct.name,
                    (int)itemPrice);
            }

            System.out.println("--------------------------");
            System.out.printf("Subtotal %16.0f$\n", totalPrice);
            System.out.printf("Shipping %16.0f$\n", y.getShippingFees());
            System.out.printf("Amount   %16.0f$\n\n\n", totalPrice + y.getShippingFees());

            shippingService.send(shippableItems);
        } 
        else {
            System.out.println("Insufficient balance for checkout.");
        }
    }


    public static void main(String[] args){
        // Customer
        customer abdelrahman = new customer("Abdelrahman", "abdelrhmankaram171@gmail.com", 100);
        
        // Available Products
        product cheese = new product("Cheese", 200, 10, 2, "22/7/2025", true);
        product scratchCard = new product("Scratch Card", 0, 30, 3, null, false);

        // Cart
        cart cart1 = new cart(10);

        cart1.add(cheese, 2);
        cart1.add(scratchCard, 2);

        new app().checkout(abdelrahman, cart1);
    }
}