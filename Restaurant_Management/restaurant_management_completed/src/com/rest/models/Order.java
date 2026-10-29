package com.rest.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {
    public enum Status { OPEN, PAID, CANCELLED }
    private final int id;
    private final int tableId;
    private final List<OrderItem> items = new ArrayList<>();
    private Status status = Status.OPEN;

    public Order(int id, int tableId) {
        if (id <= 0 || tableId <= 0) throw new IllegalArgumentException("IDs must be positive.");
        this.id = id; this.tableId = tableId;
    }
    public int getId() { return id; }
    public int getTableId() { return tableId; }
    public Status getStatus() { return status; }
    public List<OrderItem> getItems() { return Collections.unmodifiableList(items); }
    public void addItem(MenuItem menuItem, int quantity) {
        if (status != Status.OPEN) throw new IllegalStateException("Order is not open.");
        for (OrderItem item : items) {
            if (item.getMenuItem().getId() == menuItem.getId()) { item.addQuantity(quantity); return; }
        }
        items.add(new OrderItem(menuItem, quantity));
    }
    public double getTotal() { return items.stream().mapToDouble(OrderItem::getSubtotal).sum(); }
    public void markPaid() {
        if (status != Status.OPEN || items.isEmpty()) throw new IllegalStateException("Only a non-empty open order can be paid.");
        status = Status.PAID;
    }
    public void cancel() {
        if (status != Status.OPEN) throw new IllegalStateException("Only an open order can be cancelled.");
        status = Status.CANCELLED;
    }
    @Override public String toString() {
        return String.format("Order ID: %d | Table: %d | Status: %s | Total: Rs. %.2f", id, tableId, status, getTotal());
    }
}
