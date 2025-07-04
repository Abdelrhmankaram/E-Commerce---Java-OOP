import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class cart {
  LocalDateTime cartDateTime; // the date of this cart checked out in
  List<item> cartItems; // store the items add to the cart along with their quantities
  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
  double shippingFees; // fees per one product item

  public cart(double shippingFees){
    this.cartDateTime = LocalDateTime.now(); // initialize the cartDate with the current date and time
    this.cartItems = new ArrayList<>(); // empty array list to store items

    this.shippingFees = shippingFees;
  }

  public void add(product item, int quantity) {
    if(item.expireDate != null) {
      if(item.expireDate.isBefore(LocalDate.now())) {
        System.out.println("Cannot import expired item " + item.getName());
        return;
      }
    }

    if(quantity <= item.getQuantity()) { // cart number is valid
      cartItems.add(new item(item, quantity));
      item.updateQuantity(quantity, '-'); // update the exisiting quantity
    }
    else { // cart number is not valid
      System.out.println("Insufficient quantity number for item " + item.getName());
      System.out.println("Couldn't import item");
    }
  }

  public void updateCartQuantity() { // update the storage of quantities in the store
    for(item i : cartItems) {
      i.itemProduct.updateQuantity(i.quantity, '-');
    }
  }

  public double getTotalWeight() {
    double totalWeight = 0;
    for(item i : this.cartItems){
      totalWeight += (i.itemProduct.getWeight() * i.quantity);
    }
    return totalWeight;
  }

  public double getTotalPrice() {
    double totalPrice = 0;
    for(item i : this.cartItems){
      totalPrice += (i.itemProduct.getPrice() * i.quantity);
    }
    return totalPrice;
  }

  public double getShippingFees() {
    double totalShipping = 0;
    for(item i : this.cartItems){
      if(i.itemProduct.shipping == true) {
        totalShipping += (i.quantity*shippingFees);
      }
    }
    return totalShipping;
  }
}