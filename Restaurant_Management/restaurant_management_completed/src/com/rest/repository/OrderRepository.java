package com.rest.repository;

import com.rest.models.Order;
import java.util.*;

public class OrderRepository {
    private final List<Order> orders = new ArrayList<>();
    private int nextId = 1;
    public Order saveNew(int tableId) {
        Order order = new Order(nextId++, tableId);
        orders.add(order);
        return order;
    }
    public List<Order> findAll() { return new ArrayList<>(orders); }
    public Optional<Order> findById(int id) { return orders.stream().filter(o -> o.getId() == id).findFirst(); }
    public Optional<Order> findOpenByTableId(int tableId) {
        return orders.stream().filter(o -> o.getTableId() == tableId && o.getStatus() == Order.Status.OPEN).findFirst();
    }
}
