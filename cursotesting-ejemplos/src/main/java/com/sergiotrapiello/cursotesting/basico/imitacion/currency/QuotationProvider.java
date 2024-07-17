package com.sergiotrapiello.cursotesting.basico.imitacion.currency;

public interface QuotationProvider {

	public double getQuotation(CurrencyEnum base, CurrencyEnum counter);

}
