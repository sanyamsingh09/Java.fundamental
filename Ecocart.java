package java.fundamental;
import java.util.*;

	abstract class Product {
    private int id;
    private String name;
    private double price;

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }

    public abstract double getDiscountedPrice();

    public void displayProductInfo() {
        System.out.printf("Product ID %d Name %s Base Price %.2f Discounted Price %.2f\n", 
                          id, name, price, getDiscountedPrice());
    }

   
    public boolean equals(Object obj) {
        if (obj instanceof Product)
            return this.id == ((Product)obj).id;
        return false;
    }
}