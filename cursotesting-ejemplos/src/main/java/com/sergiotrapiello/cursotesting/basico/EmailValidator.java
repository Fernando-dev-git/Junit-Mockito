package com.sergiotrapiello.cursotesting.basico;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class EmailValidator {

	private static final String EMAIL_REGEX = "^([_A-Za-z0-9]+[-]?)*[_A-Za-z0-9]+((\\.[_A-Za-z0-9]+)+([-]?[_A-Za-z0-9]+)*)*@"
			+ "([A-Za-z0-9]+[-]?[A-Za-z0-9]+)+(\\.[A-Za-z0-9]+[-]?[A-Za-z0-9])*(\\.[A-Za-z]{2,})$";

	public boolean isValid(String email) {
		if (email == null || email == "") {
			throw new IllegalArgumentException();
		}
		Pattern pattern = Pattern.compile(EMAIL_REGEX);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
}
