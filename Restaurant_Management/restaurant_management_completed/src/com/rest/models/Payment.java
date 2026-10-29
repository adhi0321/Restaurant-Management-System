package com.rest.models;

public class payment {
    public enum Method { CASH, CARD, UPI }
    public enum Status { SUCCESS, FAILED }

    private final int id;
    private final double amount;
    private final Method method;
    private final Status status;

    public payment(int id, double amount, Method method, Status status) {
        if (id <= 0) {
            throw new IllegalArgumentException("Payment id must be positive.");
        }
        if (!Double.isFinite(amount) || amount <= 0) {
            throw new IllegalArgumentException("Payment amount must be positive.");
        }
        if (method == null || status == null) {
            throw new IllegalArgumentException("Payment method and status are required.");
        }

        this.id = id;
        this.amount = amount;
        this.method = method;
        this.status = status;
    }

    public int getId() { return id; }
    public double getAmount() { return amount; }
    public Method getMethod() { return method; }
    public Status getStatus() { return status; }

    @Override
    public String toString() {
        return String.format("Payment #%d | %-4s | %-6s | Rs %.2f",
                id, method, status, amount);
    }
}