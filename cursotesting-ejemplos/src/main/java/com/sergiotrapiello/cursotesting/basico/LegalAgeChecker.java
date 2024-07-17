package com.sergiotrapiello.cursotesting.basico;

import java.time.Clock;
import java.time.LocalDateTime;

public final class LegalAgeChecker {

	private static final int SPAIN_LEGAL_AGE = 18;
	
	private final Clock clock;
	

	public LegalAgeChecker(Clock clock) {
		this.clock = clock;
	}
	public boolean isOfLegalAge(LocalDateTime birthDate) {
		return LocalDateTime.now(clock).isAfter(birthDate.plusYears(SPAIN_LEGAL_AGE));
	}

}
