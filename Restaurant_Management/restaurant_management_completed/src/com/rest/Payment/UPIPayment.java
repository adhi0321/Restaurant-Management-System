package com.rest.payment;

public class upiPayment implements paymentmethod {
	@Override
	public void pay(double amount) {
		System.out.printf("Paid Rs %.2f using UPI.%n", amount);
	}

	@Override
	public String getMethodName() {
		return "UPI";
	}
}