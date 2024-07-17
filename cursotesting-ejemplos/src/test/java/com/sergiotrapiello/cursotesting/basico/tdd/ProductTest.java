package com.sergiotrapiello.cursotesting.basico.tdd;

import static com.sergiotrapiello.cursotesting.basico.TestUtils.assertThrowsIllegalArgument;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.sergiotrapiello.cursotesting.basico.Product;

public class ProductTest {
	
	private String description;
	private double basePrice;
	private int vat;
	
	@BeforeEach
	void setup() {
		description = "p1";
		basePrice = 99.99;
		vat = 21;
	}
	
	@ParameterizedTest
	@CsvSource( {"0, 0", "99.99, 21"})
	void shouldCreateProduct(double basePrice, int vat) {
		
		// WHEN
		Product product = new Product(description, basePrice, vat);
		product.getBasePrice();
		
		// THEN
		assertNotNull(product);
		assertEquals(description, product.getDescription());
		assertEquals(basePrice, product.getBasePrice());
		assertEquals(vat, product.getVat());
	}
	
	@ParameterizedTest
	@NullSource
	@ValueSource(strings = {"", "    "})
	void shouldFailCreatingProduct_description(String description) {

		// WHEN
		Executable executable = ()-> new Product(description, basePrice, vat);

		// THEN
		assertThrowsIllegalArgument(executable, "description cannot be blank");
	}
	
	@ParameterizedTest
	@ValueSource(doubles = {-0.01d, -100d})
	void shouldFailCreatingProduct_negativeBasePrice(double basePrice) {
		// WHEN
		Executable exe = () -> new Product(description, basePrice, vat);

		// THEN
		assertThrowsIllegalArgument(exe, "basePrice cannot be negative");
	}

	@ParameterizedTest
	@ValueSource(ints = {-1, -100})
	void shouldFailCreatingProduct_negativeVat(int vat) {
		// WHEN
		Executable exe = () -> new Product(description, basePrice, vat);

		// THEN
		assertThrowsIllegalArgument(exe, "int cannot be negative");
	}
	

	

}
