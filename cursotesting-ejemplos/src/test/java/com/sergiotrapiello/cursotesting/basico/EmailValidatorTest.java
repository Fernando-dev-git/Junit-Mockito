package com.sergiotrapiello.cursotesting.basico;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class EmailValidatorTest {

	private EmailValidator emailValidator;

	@BeforeEach
	void setup() {
		emailValidator = new EmailValidator();
	}

	@Test
	void shloudEmailValidatorTest() {

		// GIVEN
		String email = "algo@dominio.com";

		// WHEN
		Boolean isValid = emailValidator.isValid(email);

		// THEN
		assertEquals(true, isValid);
	}

	@Test
	void shouldEmailValidatorNull_Test() {

		// GIVEN
		String email = null;

		// WHEN
		Executable executable = () -> emailValidator.isValid(email);

		// THEN
		assertThrows(IllegalArgumentException.class, executable);
	}

	@Test
	void shoulEmailValidatorEmpty_Test() {

		// GIVEN
		String email = "";

		// WHEN
		Executable executable = () -> emailValidator.isValid(email);

		// THEN
		assertThrows(IllegalArgumentException.class, executable);
	}
	
	/**
	 * Method whit parameterized 
	 */
	
	@ParameterizedTest
	@ValueSource(strings = {"usuario@dominio.com", "user@dominio.com.mx", "us.fr@gmail.com"})
	void shouldEmailValidatorTest_parameters(String email) {
		// WHEN
		Boolean isValid = emailValidator.isValid(email);

		// THEN
		assertTrue(isValid);
	}

}
