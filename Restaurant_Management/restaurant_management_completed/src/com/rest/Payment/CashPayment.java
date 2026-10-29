package com.rest.payment;

public class cashpayment implements paymentmethod {
    @Override 
    public void pay(double amount) {
        System.out.printf("Paid Rs %.2f using cash.%n", amount);
    }

    @Override
    public String getMethodName() {
        return "Cash";
    }
}