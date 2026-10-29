package com.rest.models;

public class OrderItem {
    private final MenuItem menuItem;
    private int quantity;

    public OrderItem(MenuItem menuItem, int quantity) {
        if (menuItem == null || quantity <= 0) throw new IllegalArgumentException("Item and positive quantity required.");
        this.menuItem = menuItem; this.quantity = quantity;
    }
    public MenuItem getMenuItem() { return menuItem; }
    public int getQuantity() { return quantity; }
    public void addQuantity(int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be positive.");
        this.quantity += quantity;
    }
    public double getSubtotal() { return menuItem.getPrice() * quantity; }
    @Override public String toString() {
        return String.format("%-20s x %d = Rs. %.2f", menuItem.getName(), quantity, getSubtotal());
    }
}
