package com.rest.payment;

public class cardpayment implements paymentmethod {
	@Override
	public void pay(double amount) {
		System.out.printf("Paid Rs %.2f using card.%n", amount);
	}

	@Override
	public String getMethodName() {
		return "Card";
	}
}