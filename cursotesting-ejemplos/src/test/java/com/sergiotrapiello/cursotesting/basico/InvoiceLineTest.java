package com.sergiotrapiello.cursotesting.basico;

import static com.sergiotrapiello.cursotesting.basico.TestUtils.assertThrowsIllegalArgument;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

public class InvoiceLineTest {
	
	private Product product;
	private int units;
	
	@BeforeEach
	void setup() {
		product = new Product("Product1", 82.64d, 21);
		units = 21;
	}

	@ParameterizedTest
	@MethodSource
	void shouldCreateFromProduct(Product product, int units, double expectedTotalPrice) {
		// WHEN
		InvoiceLine createdLine = new InvoiceLine(product, units);

		// THEN
		assertEquals(expectedTotalPrice, createdLine.getTotalPrice(), 0.05d);

	}
	
	@Test
	void shouldFailCreatingNullProduct() {

		// GIVEN
		product = null;
		units = 1;

		// WHEN
		Executable executable = ()-> new InvoiceLine(product, units);
		
		// THEN
		assertThrowsIllegalArgument(executable, "product cannot be null");
	}
	
	@ParameterizedTest
	@ValueSource(ints = {0, -1, -100})
	void shouldFailCreating_units(int units) {			
		// THEN
		assertThrowsIllegalArgument(()-> new InvoiceLine(product, units), "units must be > 0");
	}
	
	private static Stream<Arguments> shouldCreateFromProduct() {
		return Stream.of(Arguments.of(new Product("Product1", 82.64d, 21), 2, 199.99d),
				Arguments.of(new Product("Product1", 82.64d, 21), 2, 199.99d)
		);
	}
	
	

}
