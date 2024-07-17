package com.sergiotrapiello.cursotesting.basico.tdd;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class WordCounterTest {

	private WordCounter wordCounter;

	@BeforeEach
	void setup() {
		wordCounter = new WordCounter();
	}

	@ParameterizedTest
	@NullAndEmptySource
	void shouldCounterZeroWords_emptyText(String text) {

		// WHEN
		int result = wordCounter.cont(text);

		// THEN
		assertEquals(0, result, "El numero de palabras no es el esperado");
	}

	@ParameterizedTest
	@CsvSource({ "'Hola que tal, me llamo Sergio', 6", "'   ', 0" })
	void testName(String text, int numWordExpected) {
		// WHEN
		int result = wordCounter.cont(text);

		// THEN
		assertNumWords(numWordExpected, result);
	}
	
	void assertNumWords(int numWordExpected, int numberResult) {
		assertEquals(numWordExpected, numberResult, "El numero es diferente");
	}

}
