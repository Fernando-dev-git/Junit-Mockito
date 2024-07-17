package com.sergiotrapiello.cursotesting.basico.imitacion.currency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CurrencyConverterTest {
	
	private QuotationProvider quotationProviderMock;
	private CurrencyConverter convertCurrency;
		
	@BeforeEach
	void setup() {
		 quotationProviderMock = mock(QuotationProvider.class);
		 convertCurrency = new CurrencyConverter(quotationProviderMock);	
	}

	@ParameterizedTest
	@CsvSource({ "1.18d, 382.32d", "1.06d, 343.44d" })
	void shouldConvert_euros_to_dolars(double quotation, double expectedDolars) {

		// GIVEN
		double eurosToConvert = 324d;
		when(quotationProviderMock.getQuotation(CurrencyEnum.EUR, CurrencyEnum.USD)).thenReturn(quotation);

		// WHEN
		double result = convertCurrency.convert(eurosToConvert, CurrencyEnum.EUR, CurrencyEnum.USD);

		// THEN
		assertEquals(expectedDolars, result, 0.01);
	}
	
	@ParameterizedTest
	@CsvSource({ "0.85, 701.59", "0.94, 775.876d" })
	void shouldConvert_dolars_to_euros(double quotation, double expectedEuros) {

		// GIVEN
		double eurosToConvert = 825.4d;
		when(quotationProviderMock.getQuotation(CurrencyEnum.USD, CurrencyEnum.EUR)).thenReturn(quotation);

		// WHEN
		double result = convertCurrency.convert(eurosToConvert, CurrencyEnum.USD, CurrencyEnum.EUR);

		// THEN
		assertEquals(expectedEuros, result, 0.01);
	}
	
	@Test
	void shouldConvert_sameCurrency() {

		// GIVEN
		double amountToConvert = 5d;

		// WHEN
		double result = convertCurrency.convert(amountToConvert, CurrencyEnum.EUR, CurrencyEnum.EUR);

		// THEN
		assertEquals(amountToConvert, result);
		verify(quotationProviderMock, never()).getQuotation(any(), any());
	}

}
