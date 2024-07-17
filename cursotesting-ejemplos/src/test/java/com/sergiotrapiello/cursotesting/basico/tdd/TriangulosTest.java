package com.sergiotrapiello.cursotesting.basico.tdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.sergiotrapiello.cursotesting.basico.exception.TriangleMalFormedException;
import com.sergiotrapiello.cursotesting.basico.tdd.Triangulo.Type;

public class TriangulosTest {
	
	@ParameterizedTest
	@CsvSource({"2,2,2,EQUILATERAL", "3,3,3,EQUILATERAL",
		"2,2,3, ISOSCELES", "6,6,8,ISOSCELES",
		"2,3,4, SCALENE", "8,6,10, SCALENE"})
	void shouldGetType(int a, int b, int c, Type expectedType) {

		// GIVEN
		Triangulo triangulo = new Triangulo(a, b, c);

		// WHEN
		Type type = triangulo.getType();

		// THEN
		assertEquals(expectedType, type);
		
	}
	
	@ParameterizedTest
	@CsvSource({"1,2,3"})
	void shouldFailCreatingTriangle_malformed(int a, int b, int c) {
		assertThrows(TriangleMalFormedException.class,
				()-> new Triangulo(a, b, c));
	}
}
