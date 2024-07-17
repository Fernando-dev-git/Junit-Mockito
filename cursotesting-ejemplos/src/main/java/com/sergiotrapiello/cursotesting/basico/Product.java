package com.sergiotrapiello.cursotesting.basico;

import org.apache.commons.lang3.StringUtils;

import lombok.Data;

@Data
public class Product {

	private final String description;
	
	private final double basePrice;
	
	private final double vat;
	
	public Product(String description, double basePrice, int vat) {
		validParams(description, basePrice, vat);
		this.description = description;
		this.basePrice = basePrice;
		this.vat = vat;
	}

	private void validParams(String description, double basePrice, int vat) {
		validDescription(description);
		validBasePrice(basePrice);
		validVat(vat);
	}

	private void validVat(int vat) {
		if(vat<0) {
			throw new IllegalArgumentException("int cannot be negative");
		}
	}

	private void validBasePrice(double basePrice) {
		if(basePrice<0){
			throw new IllegalArgumentException("basePrice cannot be negative");
		}
	}

	private void validDescription(String description) {
		if(StringUtils.isBlank(description)) {
			throw new IllegalArgumentException("description cannot be blank");
		}
	}

	@Override
	public String toString() {
		return "Product \"" + description + "\"";
	}

}
