package com.sergiotrapiello.cursotesting.basico;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.function.Executable;

public class TestUtils {

	private TestUtils() {
	}

	public static void assertThrowsIllegalArgument(Executable exe, String msg) {
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class, exe);
		assertEquals(msg, e.getMessage());

	}
}
