import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class product {
    String name; // name of product
    double weight; // weight of the product in gm
    int quantity; // quantity available 
    double price; // price of a one product in $
    LocalDate expireDate; // if empty string "", no expire date
    boolean shipping; // require shipping or not
    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");

    public product(String name, double weight, double price, int quantity, String expireDate, boolean shipping){
      this.name = name;
      this.weight = weight;
      this.price = price;
      this.quantity = quantity;
      this.shipping = shipping;
      LocalDate expireDateParsed = null;
      
      if(expireDate != null){
        expireDateParsed = LocalDate.parse(expireDate, formatter);
      }

      this.expireDate = expireDateParsed;
    }

    public String getName() {
        return name;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getWeight() {
        return weight;
    }

    public void updateQuantity(int number, char operation) {
      if(operation == '-') {
        this.quantity -= number;
      }
      else if(operation == '+') {
        this.quantity += number;
      }
    }
}