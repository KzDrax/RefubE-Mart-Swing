package styles;

public class Product {
    private String name;
    private String description;
    private double price;
    private int stock; // available stock

    public Product(String name, String description, double price, int stock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void reduceStock(int amount) {
        if (amount <= stock) {
            stock -= amount;
        }
    }
}
