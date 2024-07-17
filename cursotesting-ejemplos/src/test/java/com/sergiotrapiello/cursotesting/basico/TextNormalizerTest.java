package com.sergiotrapiello.cursotesting.basico;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

public class TextNormalizerTest {

	private TextNormalizer textNormalizer;
	private String resultString;
	private String EMPTY;

	@BeforeEach
	void settup() {
		textNormalizer = new TextNormalizer();
		resultString = "";
		EMPTY = "";
	}

	@Test
	void shouldNormalizeTest() {

		// GIVEN
		String input = " AqueLLO está alli";

		// WHEN
		resultString = textNormalizer.normalizeUppercase(input);

		// THEN
		assertEquals("AQUELLO ESTA ALLI", resultString);
	}

	/**
	 * Shorter format
	 */
	
	@ParameterizedTest
	@CsvSource({"patatá, PATATA", " AqueLLO está alli, AQUELLO ESTA ALLI",
		"'serGIO , García', 'SERGIO , GARCIA'"})
	void shouldNormalizeTest_paramsCSV(String inputText, String expectedText) {
		
		// WHEN
		resultString = textNormalizer.normalizeUppercase(expectedText);
		
		// THEN
		assertEquals(expectedText, resultString);
	}
	
	@ParameterizedTest
	@NullAndEmptySource
	void shouldNormalizedTest_NullAndEmpty(String email) {
		// WHEN
		resultString = textNormalizer.normalizeUppercase(email);

		// THEN
		assertEquals(EMPTY, resultString);
	}
	/**
	 * end of shorter format 
	 */
	@Test
	void shouldNormalizeTest_empty() {

		// GIVEN
		String empty_input = "";

		// WHEN
		resultString = textNormalizer.normalizeUppercase(empty_input);

		// THEN
		assertEquals("", resultString);
	}
	
	@Test
	void shouldNormalizeTest_null() {

		// GIVEN
		String null_input = null;

		// WHEN
		resultString = textNormalizer.normalizeUppercase(null_input);

		// THEN
		assertEquals("", resultString);
	}
}
