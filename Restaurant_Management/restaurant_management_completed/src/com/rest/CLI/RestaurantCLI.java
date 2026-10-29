package com.rest.CLI;

import com.rest.models.*;
import com.rest.service.*;
import java.util.Scanner;

public class RestaurantCLI {
    private final TableService tables = new TableService();
    private final OrderService orders = new OrderService();
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("\n=== RESTAURANT MANAGEMENT SYSTEM ===");
            System.out.println("1. View tables");
            System.out.println("2. Occupy table");
            System.out.println("3. Free table");
            System.out.println("4. View menu");
            System.out.println("5. Create order for occupied table");
            System.out.println("6. Add menu item to order");
            System.out.println("7. View orders");
            System.out.println("8. Print order bill");
            System.out.println("9. Mark order as paid");
            System.out.println("0. Exit");
            int choice = readInt("Enter choice: ");
            switch (choice) {
                case 1 -> tables.getAllTables().forEach(System.out::println);
                case 2 -> changeTable(true);
                case 3 -> changeTable(false);
                case 4 -> showMenu();
                case 5 -> createOrder();
                case 6 -> addItem();
                case 7 -> orders.getOrders().forEach(System.out::println);
                case 8 -> printBill();
                case 9 -> payOrder();
                case 0 -> { running = false; System.out.println("Goodbye!"); }
                default -> System.out.println("Invalid choice.");
            }
        }
        scanner.close();
    }
    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try { return Integer.parseInt(scanner.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.println("Enter a valid whole number."); }
        }
    }
    private void changeTable(boolean occupy) {
        int id = readInt("Enter table ID: ");
        boolean ok = occupy ? tables.occupyTable(id) : tables.freeTable(id);
        System.out.println(ok ? "Table status updated." : "Could not update table; check ID/status.");
    }
    private void showMenu() {
        System.out.println("\nID | Menu item | Price | Availability");
        orders.getMenu().forEach(System.out::println);
    }
    private void createOrder() {
        int tableId = readInt("Enter occupied table ID: ");
        var result = orders.createOrder(tableId);
        System.out.println(result.map(o -> "Order created/found: " + o).orElse("Order not created. Occupy a valid table first."));
    }
    private void addItem() {
        int orderId = readInt("Enter order ID: ");
        int menuId = readInt("Enter menu item ID: ");
        int quantity = readInt("Enter quantity: ");
        System.out.println(orders.addItem(orderId, menuId, quantity) ? "Item added." : "Item not added. Check IDs, quantity, and order status.");
    }
    private void printBill() {
        int id = readInt("Enter order ID: ");
        var result = orders.findOrder(id);
        if (result.isEmpty()) { System.out.println("Order not found."); return; }
        Order order = result.get();
        System.out.println("\n========== RESTAURANT BILL ==========");
        System.out.println("Order ID: " + order.getId() + " | Table: " + order.getTableId());
        order.getItems().forEach(System.out::println);
        System.out.printf("TOTAL: Rs. %.2f%n", order.getTotal());
        System.out.println("Status: " + order.getStatus());
        System.out.println("=====================================");
    }
    private void payOrder() {
        int id = readInt("Enter order ID to mark paid: ");
        System.out.println(orders.payOrder(id) ? "Payment recorded. Order marked PAID." : "Payment failed. Check order ID/status/items.");
    }
}
