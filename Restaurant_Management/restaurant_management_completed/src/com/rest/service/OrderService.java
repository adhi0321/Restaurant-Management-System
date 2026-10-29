package com.rest.service;

import com.rest.models.*;
import com.rest.repository.OrderRepository;
import com.rest.repository.MenuItemRepository;
import com.rest.repository.TableRepository;
import java.util.List;
import java.util.Optional;

public class OrderService {
    private final OrderRepository orders = new OrderRepository();
    private final MenuItemRepository menu = new MenuItemRepository();
    private final TableRepository tables = new TableRepository();

    public Optional<Order> createOrder(int tableId) {
        var table = tables.findById(tableId);
        if (table.isEmpty() || table.get().getStatus() != Table.Availability.OCCUPIED) return Optional.empty();
        if (orders.findOpenByTableId(tableId).isPresent()) return orders.findOpenByTableId(tableId);
        return Optional.of(orders.saveNew(tableId));
    }
    public List<Order> getOrders() { return orders.findAll(); }
    public Optional<Order> findOrder(int id) { return orders.findById(id); }
    public Optional<MenuItem> findMenuItem(int id) { return menu.findById(id); }
    public List<MenuItem> getMenu() { return menu.findAll(); }
    public boolean addItem(int orderId, int menuId, int quantity) {
        if (quantity <= 0) return false;
        Optional<Order> order = orders.findById(orderId);
        Optional<MenuItem> item = menu.findById(menuId);
        if (order.isEmpty() || item.isEmpty() || !item.get().isAvailable() || order.get().getStatus() != Order.Status.OPEN) return false;
        order.get().addItem(item.get(), quantity);
        return true;
    }
    public boolean payOrder(int orderId) {
        Optional<Order> order = orders.findById(orderId);
        if (order.isEmpty() || order.get().getStatus() != Order.Status.OPEN || order.get().getItems().isEmpty()) return false;
        order.get().markPaid();
        return true;
    }
}
