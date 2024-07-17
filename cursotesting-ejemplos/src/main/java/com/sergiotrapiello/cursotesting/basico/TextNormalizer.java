package com.sergiotrapiello.cursotesting.basico;

/**
 * Clase de utilidad para normalizaciones
 * 
 * @author sergio
 *
 */
public class TextNormalizer {

	private static final char[] TILDES = new char[] { 'Á', 'É', 'Í', 'Ó', 'Ú' };
	private static final char[] NORMAL = new char[] { 'A', 'E', 'I', 'O', 'U' };

	public String normalizeUppercase(String text) {
		if (text == null || "".equals(text)) {
			return "";
		}

		String output = text;

		output = output.toUpperCase();
		output = output.trim();
		for (int i = 0; i < TILDES.length; i++) {
			output = output.replace(TILDES[i], NORMAL[i]);
		}

		return output;
	}

}
