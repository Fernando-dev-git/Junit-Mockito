package com.sergiotrapiello.cursotesting.basico;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.apache.commons.lang3.math.Fraction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class FractionTest {
	
	@Test
	void shouldGetFractionFromString() {

		// GIVEN
		String fractionAsText = "3/4";
		double expectedValue = 0.75d;
		
		// WHEN
		Fraction fraction = Fraction.getFraction(fractionAsText);

		// THEN
		assertEquals(expectedValue, fraction.doubleValue());
	}
	
	
	@Test
	void shouldFailGettingFractionFromString_wrongNumberFormat_trycatch() {

		// GIVEN
		String wrongNumber = "erwe";

		// WHEN
		try {
			Fraction.getFraction(wrongNumber);
			
			// THEN
			fail("An exception should be thrown");
			
		}catch(NumberFormatException e) {
			assertEquals("For input string: \""+ wrongNumber+"\"", e.getMessage(),
					"The message of the exception is not what expected");
		}

		
	}
	
	@Test
	void shouldFailGettingFraction_denominatorIsZero_asserThrowIdiom() {

		// GIVEN
		int numerator = 7;
		int denominator = 0;

		// WHEN
		Executable executable = () -> Fraction.getFraction(numerator, denominator);

		// THEN
		ArithmeticException ex = assertThrows(ArithmeticException.class, executable);
		assertExceptionMessage(ex, "The denominator must not be zero");
	}
	
	private void assertExceptionMessage(Exception e, String expectedMessage) {
		assertEquals(expectedMessage, e.getMessage(),
				"The message of the exception is not what expected. ");
	}

}
