package com.sergiotrapiello.cursotesting.basico.tdd;

import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;

public class WordCounter {

	private static final String NOT_ALPHANUMERIC_REGEX = "[^a-zA-Z0-9ñÑ]";

	public int cont(String text) {
		int words = 0;
		if (StringUtils.isBlank(text)) {
			return words;
		}	
		
		for(String token: getTokens(text)) {
			if(isAWord(token))
				words++;
		}
		return words;
	}
	
	private String[] getTokens(String texto) {
		return StringUtils.split(texto);
	}
	private boolean isAWord(String token) {
		return StringUtils.isNotBlank(RegExUtils.removeAll(token, NOT_ALPHANUMERIC_REGEX));
	}
}
