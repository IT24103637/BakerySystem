package lk.sliit.eleven.bakeryorder.model;

import java.io.Serializable;

public class OrderItem implements Serializable {
    private String productId;
    private String name;
    private int quantity;
    private double price;

    // Constructors
    public OrderItem() {}

    public OrderItem(String productId, String name, int quantity, double price) {
        this.productId = productId;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and Setters
    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public double getSubtotal() { return quantity * price; }
}