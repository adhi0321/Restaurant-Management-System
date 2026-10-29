package com.rest.services;

import com.rest.models.payment;
import com.rest.repository.paymentrepo;
import java.util.List;

public class paymentservice {
    private final paymentrepo paymentRepo;
    private int nextPaymentId = 1;

    public paymentservice() {
        paymentRepo = new paymentrepo();
    }

    public payment processPayment(double amount, payment.Method method) {
        payment newPayment = new payment(nextPaymentId++, amount, method, payment.Status.SUCCESS);
        paymentRepo.save(newPayment);
        return newPayment;
    }

    public List<payment> getAllPayments() {
        return paymentRepo.findAll();
    }

    public payment getPaymentById(int id) {
        payment foundPayment = paymentRepo.findById(id);
        if (foundPayment == null) {
            throw new IllegalArgumentException("Payment " + id + " not found.");
        }
        return foundPayment;
    }
}