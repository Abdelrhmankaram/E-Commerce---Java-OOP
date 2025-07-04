public class customer {
    String name;
    String email;
    double balance;

    public customer(String name, String email, double balance){
        this.name = name;
        this.email = email;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public double getBalance() {
        return balance;
    }
}
