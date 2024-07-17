package com.sergiotrapiello.cursotesting.basico;

import lombok.Data;

@Data
public final class InvoiceLine {

	private final String productDescription;
	
	private final double basePrice;
	
	private final double appliedVat;
	
	private final double totalPrice;

	private final int units;
	
	public InvoiceLine(Product product, int units) {
		validateParams(product, units);
		this.productDescription = product.getDescription();
		this.basePrice = product.getBasePrice();
		this.appliedVat = product.getVat();
		this.units = units;
		this.totalPrice = calculateTotalPrice();
	}

	private void validateParams(Product product, int units) {
		if(product == null) {
			throw new IllegalArgumentException("product cannot be null");
		}
		if(units <= 0) {
			throw new IllegalArgumentException("units must be > 0");
		}
	}

	private double calculateTotalPrice() {
		return basePrice * (1 + appliedVat / 100) * this.units;
	}

}
