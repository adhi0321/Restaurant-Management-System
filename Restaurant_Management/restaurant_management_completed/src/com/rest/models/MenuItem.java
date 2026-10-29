package com.rest.models;

public class MenuItem {
    private final int id;
    private final String name;
    private final double price;
    private boolean available;

    public MenuItem(int id, String name, double price) {
        if (id <= 0 || name == null || name.isBlank() || price < 0)
            throw new IllegalArgumentException("Invalid menu item details.");
        this.id = id; this.name = name; this.price = price; this.available = true;
    }
    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
    @Override public String toString() {
        return String.format("%d | %-20s | Rs. %.2f | %s", id, name, price, available ? "Available" : "Unavailable");
    }
}
